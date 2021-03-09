package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditTuitionDescriptor;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;

/**
 * A utility class to help with building EditStudentDescriptor objects.
 */
public class EditStudentDescriptorBuilder {

    private EditCommand.EditTuitionDescriptor descriptor;

    public EditStudentDescriptorBuilder() {
        descriptor = new EditCommand.EditTuitionDescriptor();
    }

    public EditStudentDescriptorBuilder(EditCommand.EditTuitionDescriptor descriptor) {
        this.descriptor = new EditTuitionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStudentDescriptor} with fields containing {@code student}'s details
     */
    public EditStudentDescriptorBuilder(Student student) {
        descriptor = new EditCommand.EditTuitionDescriptor();
        descriptor.setName(student.getName());
        descriptor.setPhone(student.getPhone());
        descriptor.setEmail(student.getEmail());
        descriptor.setAddress(student.getAddress());
        descriptor.setStudyLevel(student.getStudyLevel());
        descriptor.setGuardianPhone(student.getGuardianPhone());
        descriptor.setRelationship(student.getRelationship());
    }

    /**
     * Sets the {@code Name} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Study Level} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withStudyLevel(String studyLevel) {
        descriptor.setStudyLevel(studyLevel);
        return this;
    }

    /**
     * Sets the {@code Guardian Phone} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withGuardianPhone(String guardianPhone) {
        descriptor.setGuardianPhone(new Phone(guardianPhone));
        return this;
    }

    /**
     * Sets the {@code Relationship} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withRelationship(String relationship) {
        descriptor.setStudyLevel(relationship);
        return this;
    }

    public EditTuitionDescriptor build() {
        return descriptor;
    }
}
