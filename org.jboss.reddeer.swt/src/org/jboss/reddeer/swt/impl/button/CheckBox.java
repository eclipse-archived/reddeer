package org.jboss.reddeer.swt.impl.button;

import org.apache.log4j.Logger;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.jboss.reddeer.swt.api.Button;
import org.jboss.reddeer.swt.util.Bot;

/**
 * Class represents Button with type Toggle (Checkbox)
 * 
 * @author rhopp
 * 
 */

public class CheckBox implements Button {

	protected final Logger log = Logger.getLogger(this.getClass());
	
	private SWTBotCheckBox checkBox;

	/**
	 * Default constructor
	 */

	public CheckBox() {
		checkBox = Bot.get().checkBox();
	}
	
	/**
	 * Checkbox with given index
	 * 
	 * @param index
	 */
	
	public CheckBox(int index){
		checkBox = Bot.get().checkBox(index);
	}
	
	/**
	 * CheckBox button with given index in given Group
	 * @param index of button
	 * @param inGroup in group
	 */
	public CheckBox(String inGroup, int index){
	    checkBox = Bot.get().checkBoxInGroup(index, inGroup);
	}
	/**
	 * CheckBox button with given text in given Group
	 * @param text of button
	 * @param inGroup in group
	 */
	public CheckBox(String inGroup, String text){
		checkBox = Bot.get().checkBoxInGroup(inGroup, text);
	}

	/**
	 * Checkbox with given label
	 * 
	 * @param label
	 */
	public CheckBox(String label) {
		checkBox = Bot.get().checkBox(label);
	}

	public boolean isChecked() {
		return checkBox.isChecked();
	}
	
	/**
	 * Sets checkbox to state 'checked'
	 * 
	 * @param checked
	 */
	public void toggle(boolean checked){
		if (checked){
			if (checkBox.isChecked()) {
				log.debug("Checkbox already checked");
				return;
			}else{
				log.info("Checking checkbox " + checkBox.getText());
				click();
			}
		}else{
			if (checkBox.isChecked()) {
				log.info("Unchecking checkbox " + checkBox.getText());
				click();
			}else{
				log.debug("Checkbox already unchecked");
				return;
			}
		}
	}

	@Override
	public void click() {
		log.debug("Clicking on checkbox");
		checkBox.click();
	}

	@Override
	public String getText() {
		return checkBox.getText();
	}

	@Override
	public boolean isEnabled() {
		return checkBox.isEnabled();
	}
}
