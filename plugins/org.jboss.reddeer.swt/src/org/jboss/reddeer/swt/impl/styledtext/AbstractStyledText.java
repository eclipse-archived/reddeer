package org.jboss.reddeer.swt.impl.styledtext;

import org.hamcrest.Matcher;
import org.jboss.reddeer.common.logging.Logger;
import org.jboss.reddeer.swt.api.StyledText;
import org.jboss.reddeer.swt.handler.StyledTextHandler;
import org.jboss.reddeer.swt.handler.WidgetHandler;
import org.jboss.reddeer.swt.reference.ReferencedComposite;
import org.jboss.reddeer.swt.widgets.AbstractWidget;

/**
 * Abstract Styled Text contains common rutines for styled text widget.
 * Concrete impementations of styledtext should extend this class.
 * @author rawagner
 */
public abstract class AbstractStyledText extends AbstractWidget<org.eclipse.swt.custom.StyledText> implements StyledText {
    
    /**
     * Logger.
     */
    private static final Logger log = Logger.getLogger(AbstractStyledText.class);

    protected AbstractStyledText(ReferencedComposite refComposite, int index, Matcher<?>... matchers) {
		super(org.eclipse.swt.custom.StyledText.class, refComposite, index, matchers);
	}
    
    /**
     * Returns styledtext text.
     * @return text of this StyledText
     */
    @Override
    public String getText() {
        String text = WidgetHandler.getInstance().getText(swtWidget);
        return text;
    }

    /**
     * Sets text of this StyledText.
     * @param text to set
     */
    @Override
    public void setText(final String text) {
        log.info("Styled Text set to: " + text);
        WidgetHandler.getInstance().setText(swtWidget, text);
    }

    /**
     * Returns tooltip text.
     * @return Tooltip text of this StyledText
     */
    @Override
    public String getToolTipText() {
        String tooltipText = WidgetHandler.getInstance().getToolTipText(swtWidget);
        return tooltipText;
    }

    /**
     * Insert text into styled text content.
     * @param line line to insert text
     * @param column column to insert text
     * @Param text to insert
     */
    @Override
    public void insertText(final int line, final int column, final String text) {
    	log.info("Insert text into styled text on line " + line + ", column " + column + ": " + text);
        StyledTextHandler.getInstance().insertText(swtWidget, line, column, text);
    }

    /**
     * @see org.jboss.reddeer.swt.api.StyledText#insertText(String)
     */
    @Override
    public void insertText(final String text) {
    	log.info("Insert text into styled text: " + text);
        StyledTextHandler.getInstance().insertText(swtWidget, text);
    }

    /**
     * @see org.jboss.reddeer.swt.api.StyledText#getPositionOfText(String)
     */
    @Override
    public int getPositionOfText(final String text) {
        return StyledTextHandler.getInstance().getPositionOfText(swtWidget,text);
    }

    /**
     * @see org.jboss.reddeer.swt.api.StyledText#selectText(String)
     */
    @Override
    public void selectText(final String text) {
    	log.info("Select text " + text + " in styled text");
        StyledTextHandler.getInstance().selectText(swtWidget, text);
    }

    /**
     * @see org.jboss.reddeer.swt.api.StyledText#selectPosition(int)
     */
    @Override
    public void selectPosition(final int position) {
    	log.info("Select position " + position + " in styled text");
        StyledTextHandler.getInstance().selectPosition(swtWidget, position);
    }

    /**
     * @see org.jboss.reddeer.swt.api.StyledText#getSelectionText()
     */
    @Override
    public String getSelectionText() {
        return StyledTextHandler.getInstance().getSelectionText(swtWidget);
    }

}
