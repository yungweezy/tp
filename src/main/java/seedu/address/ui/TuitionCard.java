package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.tuition.Tuition;

/**
 * An UI component that displays information of a {@code Tuition}.
 */
public class TuitionCard extends UiPart<Region> {

    private static final String FXML = "TuitionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Tuition tuition;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label studyLevel;
    @FXML
    private Label guardianPhone;
    @FXML
    private Label relationship;

    /**
     * Creates a {@code PersonCode} with the given {@code Tuition} and index to display.
     */
    public TuitionCard(Tuition tuition, int displayedIndex) {
        super(FXML);
        this.tuition = tuition;
        id.setText(displayedIndex + ". ");
        name.setText(tuition.getName().fullName);
        phone.setText(tuition.getPhone().value);
        address.setText(tuition.getAddress().value);
        email.setText(tuition.getEmail().value);
        studyLevel.setText(tuition.getStudyLevel());
        guardianPhone.setText(tuition.getGuardianPhone().value);
        relationship.setText(tuition.getRelationship());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TuitionCard)) {
            return false;
        }

        // state check
        TuitionCard card = (TuitionCard) other;
        return id.getText().equals(card.id.getText())
                && tuition.equals(card.tuition);
    }
}
