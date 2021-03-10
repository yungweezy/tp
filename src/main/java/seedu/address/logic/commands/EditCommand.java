package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GUARDIAN_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RELATIONSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDY_LEVEL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.tuition.Tuition;

/**
 * Edits the details of an existing tuition student in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_STUDY_LEVEL + "STUDY LEVEL] "
            + "[" + PREFIX_GUARDIAN_PHONE + "GUARDIAN PHONE] "
            + "[" + PREFIX_RELATIONSHIP + "RELATIONSHIP] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in the address book.";

    private final Index index;
    private final EditTuitionDescriptor editTuitionDescriptor;

    /**
     * @param index of the student in the filtered student list to edit
     * @param editTuitionDescriptor details to edit the student with
     */
    public EditCommand(Index index, EditTuitionDescriptor editTuitionDescriptor) {
        requireNonNull(index);
        requireNonNull(editTuitionDescriptor);

        this.index = index;
        this.editTuitionDescriptor = new EditTuitionDescriptor(editTuitionDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tuition> lastShownList = model.getFilteredTuitionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Tuition tuitionToEdit = lastShownList.get(index.getZeroBased());
        Tuition editedTuition = createEditedPerson(tuitionToEdit, editTuitionDescriptor);

        if (!tuitionToEdit.isSameTuition(editedTuition) && model.hasTuition(editedTuition)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setTuition(tuitionToEdit, editedTuition);
        model.updateFilteredTuitionList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedTuition));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Tuition createEditedPerson(Tuition tuitionToEdit, EditTuitionDescriptor editTuitionDescriptor) {
        assert tuitionToEdit != null;

        Name updatedName = editTuitionDescriptor.getName().orElse(tuitionToEdit.getName());
        Phone updatedPhone = editTuitionDescriptor.getPhone().orElse(tuitionToEdit.getPhone());
        Email updatedEmail = editTuitionDescriptor.getEmail().orElse(tuitionToEdit.getEmail());
        Address updatedAddress = editTuitionDescriptor.getAddress().orElse(tuitionToEdit.getAddress());
        String updatedStudyLevel = editTuitionDescriptor.getStudyLevel()
                .orElse(tuitionToEdit.getStudyLevel());
        Phone updatedGuardianPhone = editTuitionDescriptor.getGuardianPhone()
                .orElse(tuitionToEdit.getGuardianPhone());
        String updatedRelationship = editTuitionDescriptor.getRelationship()
                .orElse(tuitionToEdit.getRelationship());

        return new Tuition(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedStudyLevel,
            updatedGuardianPhone, updatedRelationship);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editTuitionDescriptor.equals(e.editTuitionDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditTuitionDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private String studyLevel;
        private Phone guardianPhone;
        private String relationship;

        public EditTuitionDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditTuitionDescriptor(EditTuitionDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setStudyLevel(toCopy.studyLevel);
            setGuardianPhone(toCopy.guardianPhone);
            setRelationship(toCopy.relationship);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setStudyLevel(String studyLevel) {
            this.studyLevel = studyLevel;
        }

        public Optional<String> getStudyLevel() {
            return Optional.ofNullable(studyLevel);
        }

        public void setGuardianPhone(Phone guardianPhone) {
            this.guardianPhone = guardianPhone;
        }

        public Optional<Phone> getGuardianPhone() {
            return Optional.ofNullable(guardianPhone);
        }

        public void setRelationship(String relationship) {
            this.relationship = relationship;
        }

        public Optional<String> getRelationship() {
            return Optional.ofNullable(relationship);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTuitionDescriptor)) {
                return false;
            }

            // state check
            EditTuitionDescriptor e = (EditTuitionDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getStudyLevel().equals(e.getStudyLevel())
                    && getGuardianPhone().equals(e.getGuardianPhone())
                    && getRelationship().equals(e.getRelationship());
        }
    }
}
