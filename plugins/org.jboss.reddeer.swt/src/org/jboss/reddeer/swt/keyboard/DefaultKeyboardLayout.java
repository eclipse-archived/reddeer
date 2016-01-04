package org.jboss.reddeer.swt.keyboard;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.swt.SWT;
import org.jboss.reddeer.swt.exception.SWTLayerException;

/**
 * This class handles decomposition of some characters to keystrokes by given
 * configuration file.
 * 
 * Note, that for now only default.keyboard (en_US) will be taken in
 * consideration. Possibility to provide your own configuration file will be
 * added in future.
 * 
 * @author rhopp
 *
 */

public class DefaultKeyboardLayout {

	private static DefaultKeyboardLayout instance = null;

	private static Map<Character, int[]> keyMap;
	
	private static Toolkit toolkit;

	/**
	 * Constructor of the class.
	 */
	public DefaultKeyboardLayout() {
		ClassLoader classLoader = DefaultKeyboardLayout.class.getClassLoader();
		InputStream in = classLoader.getResourceAsStream(toFolder(myPackage()
				+ "/default")
				+ ".keyboard");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new SWTLayerException(
					"Unable to parse keyboard layout config file", e);
		}
		try {
			loadKeyboardLayoutFile(br);
		} catch (IOException e) {
			throw new SWTLayerException(
					"Unable to parse keyboard layout config file", e);
		} catch (ParseException e) {
			throw new SWTLayerException(
					"Unable to parse keyboard layout config file", e);
		}
	}

	/**
	 * Gets the single instance of DefaultKeyboardLayout.
	 *
	 * @return single instance of DefaultKeyboardLayout
	 */
	public static DefaultKeyboardLayout getInstance() {
		if (instance == null) {
			instance = new DefaultKeyboardLayout();
		}
		return instance;
	}
	
	/**
	 * Returns desired combination for character as array of integers.
	 *
	 * @param c the character
	 * @return the key combination
	 */

	public int[] getKeyCombination(char c) {
		if (c > 96 && c < 123) {
			if (DefaultKeyboardLayout.isCapsLockOn()){
				return new int[] {SWT.SHIFT,c};
			}
			else{
				return new int[] { c };
			}
		}
		if (c > 64 && c < 91) {
			if (DefaultKeyboardLayout.isCapsLockOn()){
				return new int[] {c + 32 };
			}
			else{
				return new int[] { SWT.SHIFT, c + 32 };	
			}
		}
		if (keyMap.containsKey(c))
			return keyMap.get(c);
		return new int[] { c };
	}

	private void loadKeyboardLayoutFile(BufferedReader in) throws IOException,
			ParseException {
		keyMap = new HashMap<Character, int[]>();
		String line;
		while ((line = in.readLine()) != null) {
			char ch = line.charAt(0);
			String keystrokeString = line.substring(2).replaceAll("\\s+", "");
			KeyStroke keyStroke = KeyStroke.getInstance(keystrokeString);
			if (keyStroke.getModifierKeys() != 0) {
				keyMap.put(ch, new int[] { keyStroke.getModifierKeys(), ch });
			}
		}
		in.close();
	}

	private String myPackage() {
		return DefaultKeyboardLayout.class.getPackage().getName();
	}

	private String toFolder(String layoutName) {
		return layoutName.replaceAll("\\.", "/");
	}

	private static boolean isCapsLockOn(){
		boolean isOn = false;
		if (DefaultKeyboardLayout.toolkit == null){
			DefaultKeyboardLayout.toolkit = Toolkit.getDefaultToolkit();
		}
		
		try{
			isOn = DefaultKeyboardLayout.toolkit.getLockingKeyState(KeyEvent.VK_CAPS_LOCK);	
		} catch (HeadlessException he){
			// current OS doesn't implement getLockingKeyState(KeyEvent.VK_CAPS_LOCC) nethod correctly
			// therefore method returns default value i. e. false
		}
		
		return isOn;
	}
	
}
