package seedu.address.testutil;

import seedu.address.logic.commands.EditStudentCommand;
import seedu.address.logic.commands.EditStudentCommand.EditTuitionDescriptor;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.tuition.Tuition;

/**
 * A utility class to help with building EditTuitionDescriptor objects.
 */
public class EditTuitionDescriptorBuilder {

    private EditStudentCommand.EditTuitionDescriptor descriptor;

    public EditTuitionDescriptorBuilder() {
        descriptor = new EditStudentCommand.EditTuitionDescriptor();
    }

    public EditTuitionDescriptorBuilder(EditStudentCommand.EditTuitionDescriptor descriptor) {
        this.descriptor = new EditTuitionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTuitionDescriptor} with fields containing {@code tuition}'s details
     */
    public EditTuitionDescriptorBuilder(Tuition tuition) {
        descriptor = new EditStudentCommand.EditTuitionDescriptor();
        descriptor.setName(tuition.getName());
        descriptor.setPhone(tuition.getPhone());
        descriptor.setEmail(tuition.getEmail());
        descriptor.setAddress(tuition.getAddress());
        descriptor.setStudyLevel(tuition.getStudyLevel());
        descriptor.setGuardianPhone(tuition.getGuardianPhone());
        descriptor.setRelationship(tuition.getRelationship());
    }

    /**
     * Sets the {@code Name} of the {@code EditTuitionDescriptor} that we are building.
     */
    public EditTuitionDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditTuitionDescriptor} that we are building.
     */
    public EditTuitionDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditTuitionDescriptor} that we are building.
     */
    public EditTuitionDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditTuitionDescriptor} that we are building.
     */
    public EditTuitionDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Study Level} of the {@code EditTuitionDescriptor} that we are building.
     */
    public EditTuitionDescriptorBuilder withStudyLevel(String studyLevel) {
        descriptor.setStudyLevel(studyLevel);
        return this;
    }

    /**
     * Sets the {@code Guardian Phone} of the {@code EditTuitionDescriptor} that we are building.
     */
    public EditTuitionDescriptorBuilder withGuardianPhone(String guardianPhone) {
        descriptor.setGuardianPhone(new Phone(guardianPhone));
        return this;
    }

    /**
     * Sets the {@code Relationship} of the {@code EditTuitionDescriptor} that we are building.
     */
    public EditTuitionDescriptorBuilder withRelationship(String relationship) {
        descriptor.setStudyLevel(relationship);
        return this;
    }

    public EditTuitionDescriptor build() {
        return descriptor;
    }
}
