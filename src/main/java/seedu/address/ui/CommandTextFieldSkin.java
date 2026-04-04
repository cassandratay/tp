package seedu.address.ui;

import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Skin that places command assistance inside the text field layout.
 */
public class CommandTextFieldSkin extends TextFieldSkin {

    private final CommandTextField commandTextField;
    private final Text suggestionText = new Text();
    private final Rectangle suggestionClip = new Rectangle();

    /**
     * Creates a skin that renders the current suggestion inline for the given field.
     */
    public CommandTextFieldSkin(CommandTextField commandTextField) {
        super(commandTextField);
        this.commandTextField = commandTextField;

        suggestionText.setManaged(false);
        suggestionText.setMouseTransparent(true);
        suggestionText.setTextOrigin(VPos.TOP);
        suggestionText.getStyleClass().add("command-assistance-text");
        suggestionText.fontProperty().bind(commandTextField.fontProperty());
        suggestionText.setClip(suggestionClip);

        getChildren().add(suggestionText);

        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> commandTextField.requestLayout());
        commandTextField.suggestionProperty().addListener((unused1, unused2, unused3)
                -> commandTextField.requestLayout());
        commandTextField.caretPositionProperty().addListener((unused1, unused2, unused3)
                -> commandTextField.requestLayout());
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);

        String suggestion = commandTextField.getSuggestion();
        if (suggestion == null || suggestion.isEmpty()) {
            suggestionText.setVisible(false);
            return;
        }

        suggestionText.setText(suggestion);
        suggestionText.setVisible(true);

        Rectangle2D anchorBounds = getSuggestionAnchorBounds();
        if (anchorBounds == null) {
            anchorBounds = new Rectangle2D(x + snappedLeftInset(), y, 0, h);
        }

        suggestionText.relocate(anchorBounds.getMinX(), anchorBounds.getMinY());
        suggestionClip.setX(0);
        suggestionClip.setY(0);
        suggestionClip.setWidth(Math.max(0, x + w - anchorBounds.getMinX()));
        suggestionClip.setHeight(Math.max(h, anchorBounds.getHeight()));
    }

    private Rectangle2D getSuggestionAnchorBounds() {
        int textLength = commandTextField.getLength();

        Rectangle2D boundsAtEnd = getCharacterBounds(textLength);
        if (boundsAtEnd != null) {
            return boundsAtEnd;
        }

        if (textLength == 0) {
            return getCharacterBounds(0);
        }

        Rectangle2D lastCharacterBounds = getCharacterBounds(textLength - 1);
        if (lastCharacterBounds == null) {
            return null;
        }

        return new Rectangle2D(lastCharacterBounds.getMaxX(), lastCharacterBounds.getMinY(),
                0, lastCharacterBounds.getHeight());
    }
}
