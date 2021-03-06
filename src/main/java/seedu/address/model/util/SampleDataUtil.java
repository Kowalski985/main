package seedu.address.model.util;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.parcel.Address;
import seedu.address.model.parcel.DeliveryDate;
import seedu.address.model.parcel.Email;
import seedu.address.model.parcel.Name;
import seedu.address.model.parcel.Parcel;
import seedu.address.model.parcel.Phone;
import seedu.address.model.parcel.Status;
import seedu.address.model.parcel.TrackingNumber;
import seedu.address.model.parcel.exceptions.DuplicateParcelException;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Parcel[] getSampleParcels() {
        try {
            return new Parcel[] {
                new Parcel(new TrackingNumber("RR999999999SG"), new Name("Alex Yeoh"), new Phone("87438807"),
                        new Email("alexyeoh@example.com"), new Address("Blk 29 Lor 30 Geylang, #06-40 s398362"),
                        new DeliveryDate("01-01-2001"), Status.getInstance("PENDING"),
                        getTagSet(Tag.FLAMMABLE.toString())),
                new Parcel(new TrackingNumber("RR111111111SG"), new Name("Bernice Yu"), new Phone("99272758"),
                        new Email("berniceyu@example.com"), new Address("Blk 326 Serangoon Ave 3, #07-18 S550326"),
                        new DeliveryDate("02-02-2002"), Status.getInstance("PENDING"),
                        getTagSet(Tag.FROZEN.toString(), Tag.FLAMMABLE.toString())),
                new Parcel(new TrackingNumber("RR222222222SG"), new Name("Charlotte Oliveiro"), new Phone("93210283"),
                        new Email("charlotte@example.com"), new Address("Blk 512 Ang Mo Kio Ave 8, #11-04 s560512"),
                        new DeliveryDate("03-03-2003"), Status.getInstance("DELIVERING"),
                        getTagSet(Tag.HEAVY.toString())),
                new Parcel(new TrackingNumber("RR123456789SG"), new Name("David Li"), new Phone("91031282"),
                        new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43 "
                        + "s558675"), new DeliveryDate("04-04-2004"), Status.getInstance("COMPLETED"),
                        getTagSet(Tag.FRAGILE.toString())),
                new Parcel(new TrackingNumber("RR987654321SG"), new Name("Irfan Ibrahim"), new Phone("92492021"),
                        new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35 s535070"),
                        new DeliveryDate("05-05-2005"), Status.getInstance("OVERDUE"),
                        getTagSet(Tag.FRAGILE.toString())),
                new Parcel(new TrackingNumber("RR123789456SG"), new Name("Roy Balakrishnan"), new Phone("92624417"),
                        new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31 S389825"),
                        new DeliveryDate("06-06-2006"), Status.getInstance("DELIVERING"),
                        getTagSet(Tag.FLAMMABLE.toString()))
            };
        } catch (IllegalValueException e) {
            throw new AssertionError("sample data cannot be invalid", e);
        }
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        try {
            AddressBook sampleAb = new AddressBook();
            for (Parcel sampleParcel : getSampleParcels()) {
                sampleAb.addParcel(sampleParcel);
            }
            return sampleAb;
        } catch (DuplicateParcelException e) {
            throw new AssertionError("sample data cannot contain duplicate parcels", e);
        }
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) throws IllegalValueException {
        HashSet<Tag> tags = new HashSet<>();
        for (String s : strings) {
            tags.add(Tag.getInstance(s));
        }

        return tags;
    }

}
