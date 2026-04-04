package seedu.address.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;

/**
 * Text field that renders inline command assistance via a custom skin.
 */
public class CommandTextField extends TextField {

    private final StringProperty suggestion = new SimpleStringProperty("");

    public final String getSuggestion() {
        return suggestion.get();
    }

    public final void setSuggestion(String suggestion) {
        this.suggestion.set(suggestion);
    }

    public final StringProperty suggestionProperty() {
        return suggestion;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new CommandTextFieldSkin(this);
    }
}
