package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.tuition.Tuition;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Tuition[] getSamplePersons() {
        return new Tuition[] {
            new Tuition(new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), "P2", new Phone("87419031"),
                "Father")),
            new Tuition(new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), "Sec 4", new Phone("90128102"),
                "Mother")),
            new Tuition(new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                "JC 2", new Phone("91109117"), "Father")),
            new Tuition(new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), "Uni CS1101S",
                new Phone("88519021"), "Mother")),
            new Tuition(new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), "P5", new Phone("95651000"),
                "Father")),
            new Tuition(new Student(new Name("Roy Balakrishnan"), new Phone("92624417"),
                new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"), "P4",
                new Phone("89651021"), "Father"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Tuition sampleTuition : getSamplePersons()) {
            sampleAb.addTuition(sampleTuition);
        }
        return sampleAb;
    }

}
