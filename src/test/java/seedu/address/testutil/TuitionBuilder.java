package seedu.address.testutil;

import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.tuition.Tuition;

/**
 * A utility class to help with building Tuition objects.
 */
public class TuitionBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_STUDY_LEVEL = "Sec 5";
    public static final String DEFAULT_GUARDIAN_PHONE = "33333333";
    public static final String DEFAULT_RELATIONSHIP = "Mother";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private String studyLevel;
    private Phone guardianPhone;
    private String relationship;

    /**
     * Creates a {@code TuitionBuilder} with the default details.
     */
    public TuitionBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        studyLevel = DEFAULT_STUDY_LEVEL;
        guardianPhone = new Phone(DEFAULT_GUARDIAN_PHONE);
        relationship = DEFAULT_RELATIONSHIP;
    }

    /**
     * Initializes the TuitionBuilder with the data of {@code tuitionToCopy}.
     */
    public TuitionBuilder(Tuition tuitionToCopy) {
        name = tuitionToCopy.getStudent().getName();
        phone = tuitionToCopy.getStudent().getPhone();
        email = tuitionToCopy.getStudent().getEmail();
        address = tuitionToCopy.getStudent().getAddress();
        studyLevel = tuitionToCopy.getStudent().getStudyLevel();
        guardianPhone = tuitionToCopy.getStudent().getGuardianPhone();
        relationship = tuitionToCopy.getStudent().getRelationship();
    }

    /**
     * Sets the {@code Name} of the {@code Tuition} that we are building.
     */
    public TuitionBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Tuition} that we are building.
     */
    public TuitionBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Tuition} that we are building.
     */
    public TuitionBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Tuition} that we are building.
     */
    public TuitionBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code studyLevel} of the {@code Tuition} that we are building.
     */
    public TuitionBuilder withStudyLevel(String studyLevel) {
        this.studyLevel = studyLevel;
        return this;
    }

    /**
     * Sets the {@code guardianPhone} of the {@code Tuition} that we are building.
     */
    public TuitionBuilder withGuardianPhone(String guardianPhone) {
        this.guardianPhone = new Phone(guardianPhone);
        return this;
    }

    /**
     * Sets the {@code relationship} of the {@code Tuition} that we are building.
     */
    public TuitionBuilder withRelationship(String relationship) {
        this.relationship = relationship;
        return this;
    }

    public Tuition build() {
        return new Tuition(name, phone, email, address, studyLevel, guardianPhone, relationship);
    }

}
