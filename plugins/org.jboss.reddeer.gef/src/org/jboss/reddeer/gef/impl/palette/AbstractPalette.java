package org.jboss.reddeer.gef.impl.palette;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.jboss.reddeer.gef.GEFLayerException;
import org.jboss.reddeer.gef.api.Palette;
import org.jboss.reddeer.gef.handler.PaletteHandler;
import org.jboss.reddeer.gef.matcher.IsToolEntry;
import org.jboss.reddeer.gef.matcher.IsToolEntryWithLabel;
import org.jboss.reddeer.gef.matcher.IsToolEntryWithParent;

/**
 * Abstract class form Palette implementation
 * 
 * @author Andrej Podhradsky (andrej.podhradsky@gmail.com)
 *
 */
public abstract class AbstractPalette implements Palette {

	protected PaletteViewer paletteViewer;
	protected PaletteHandler paletteHandler;

	/**
	 * Constructor for AbstractPalette. You nedd to specify palette viewer.
	 * 
	 * @param paletteViewer
	 *            Palette viewer
	 */
	public AbstractPalette(PaletteViewer paletteViewer) {
		this.paletteViewer = paletteViewer;
		this.paletteHandler = PaletteHandler.getInstance();
	}

	@Override
	public void activateTool(String label) {
		activateTool(label, null);
	}

	@Override
	public void activateTool(String tool, String group) {
		List<Matcher<? super PaletteEntry>> matchers = new ArrayList<Matcher<? super PaletteEntry>>();
		matchers.add(new IsToolEntryWithLabel(tool));
		if (group != null) {
			matchers.add(new IsToolEntryWithParent(group));
		}
		Matcher<PaletteEntry> matcher = new AllOf<PaletteEntry>(matchers);
		activateTool(matcher, 0);
	}

	/**
	 * Activates a tool with a given matcher at specified index
	 * 
	 * @param matcher
	 *            Matcher
	 * @param index
	 *            Index
	 */
	protected void activateTool(Matcher<PaletteEntry> matcher, int index) {
		List<PaletteEntry> entries = paletteHandler.getPaletteEntries(paletteViewer, matcher);
		if (entries.size() <= index) {
			throw new GEFLayerException("Cannot find tool entry with " + matcher + " at index " + index);
		}
		ToolEntry toolEntry = (ToolEntry) entries.get(index);
		paletteHandler.activateTool(paletteViewer, toolEntry);
	}

	@Override
	public String getActiveTool() {
		ToolEntry activeTool = paletteHandler.getActiveTool(paletteViewer);
		return paletteHandler.getLabel(activeTool);
	}

	@Override
	public List<String> getTools() {
		List<PaletteEntry> entries = paletteHandler.getPaletteEntries(paletteViewer, new IsToolEntry());
		List<String> tools = new ArrayList<String>();
		for (PaletteEntry entry : entries) {
			tools.add(paletteHandler.getLabel(entry));
		}
		return tools;
	}

}
