package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GUARDIAN_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RELATIONSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDY_LEVEL;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.EditCommand.EditTuitionDescriptor;
import seedu.address.model.tuition.Tuition;

/**
 * A utility class for Tuition.
 */
public class TuitionUtil {

    /**
     * Returns an add command string for adding the {@code tuition}.
     */
    public static String getAddCommand(Tuition tuition) {
        return AddStudentCommand.COMMAND_WORD + " " + getPersonDetails(tuition);
    }

    /**
     * Returns the part of command string for the given {@code tuition}'s details.
     */
    public static String getPersonDetails(Tuition tuition) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + tuition.getName().fullName + " ");
        sb.append(PREFIX_PHONE + tuition.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + tuition.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + tuition.getAddress().value + " ");
        sb.append(PREFIX_STUDY_LEVEL + tuition.getStudyLevel() + " ");
        sb.append(PREFIX_GUARDIAN_PHONE + tuition.getGuardianPhone().value + " ");
        sb.append(PREFIX_RELATIONSHIP + tuition.getRelationship() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditTuitionDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditTuitionDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getStudyLevel().ifPresent(studyLevel ->
            sb.append(PREFIX_STUDY_LEVEL).append(studyLevel).append(" "));
        descriptor.getGuardianPhone().ifPresent(guardianPhone ->
            sb.append(PREFIX_GUARDIAN_PHONE).append(guardianPhone.value).append(" "));
        descriptor.getRelationship().ifPresent(relationship ->
            sb.append(PREFIX_RELATIONSHIP).append(relationship).append(" "));

        return sb.toString();
    }
}
