package seedu.address.model.parcel;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * Represents a Parcel in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Parcel implements ReadOnlyParcel {

    private ObjectProperty<TrackingNumber> trackingNumber;
    private ObjectProperty<Name> name;
    private ObjectProperty<Phone> phone;
    private ObjectProperty<Email> email;
    private ObjectProperty<Address> address;
    private ObjectProperty<DeliveryDate> deliveryDate;
    private ObjectProperty<Status> status;

    private ObjectProperty<UniqueTagList> tags;

    /**
     * Every field must be present and not null.
     */
    public Parcel(TrackingNumber trackingNumber, Name name, Phone phone, Email email, Address address,
                  DeliveryDate deliveryDate, Status status, Set<Tag> tags) {
        requireAllNonNull(trackingNumber, name, phone, email, address, deliveryDate, status, tags);
        this.trackingNumber = new SimpleObjectProperty<>(trackingNumber);
        this.name = new SimpleObjectProperty<>(name);
        this.phone = new SimpleObjectProperty<>(phone);
        this.email = new SimpleObjectProperty<>(email);
        this.address = new SimpleObjectProperty<>(address);
        this.deliveryDate = new SimpleObjectProperty<>(deliveryDate);
        this.status = new SimpleObjectProperty<>(Status.getUpdatedInstance(status, deliveryDate));
        // protect internal tags from changes in the arg list
        this.tags = new SimpleObjectProperty<>(new UniqueTagList(tags));
    }

    /**
     * Creates a copy of the given ReadOnlyParcel.
     */
    public Parcel(ReadOnlyParcel source) {
        this(source.getTrackingNumber(), source.getName(), source.getPhone(), source.getEmail(), source.getAddress(),
                source.getDeliveryDate(), Status.getUpdatedInstance(source.getStatus(), source.getDeliveryDate()),
                source.getTags());
    }

    public void setTrackingNumber(TrackingNumber trackingNumber) {
        this.trackingNumber.set(requireNonNull(trackingNumber));
    }

    @Override
    public ObjectProperty<TrackingNumber> trackingNumberProperty() {
        return trackingNumber;
    }

    @Override
    public TrackingNumber getTrackingNumber() {
        return trackingNumber.get();
    }

    public void setName(Name name) {
        this.name.set(requireNonNull(name));
    }

    @Override
    public ObjectProperty<Name> nameProperty() {
        return name;
    }

    @Override
    public Name getName() {
        return name.get();
    }

    public void setPhone(Phone phone) {
        this.phone.set(requireNonNull(phone));
    }

    @Override
    public ObjectProperty<Phone> phoneProperty() {
        return phone;
    }

    @Override
    public Phone getPhone() {
        return phone.get();
    }

    public void setEmail(Email email) {
        this.email.set(requireNonNull(email));
    }

    @Override
    public ObjectProperty<Email> emailProperty() {
        return email;
    }

    @Override
    public Email getEmail() {
        return email.get();
    }

    public void setAddress(Address address) {
        this.address.set(requireNonNull(address));
    }

    @Override
    public ObjectProperty<Address> addressProperty() {
        return address;
    }

    @Override
    public Address getAddress() {
        return address.get();
    }

    public void setStatus(Status status) {
        this.status.set(requireNonNull(status));
    }

    @Override
    public ObjectProperty<Status> statusProperty() {
        return status;
    }

    @Override
    public Status getStatus() {
        return status.get();
    }

    public void setDeliveryDate(DeliveryDate deliveryDate) {
        this.deliveryDate.set(requireNonNull(deliveryDate));
    }

    public ObjectProperty<DeliveryDate> deliveryDateProperty() {
        return deliveryDate;
    }

    public DeliveryDate getDeliveryDate() {
        return deliveryDate.get();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    @Override
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags.get().toSet());
    }

    public ObjectProperty<UniqueTagList> tagProperty() {
        return tags;
    }

    /**
     * Replaces this parcel's tags with the tags in the argument tag set.
     */
    public void setTags(Set<Tag> replacement) {
        tags.set(new UniqueTagList(replacement));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyParcel // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyParcel) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(trackingNumber, name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

    //@@author fustilio
    /**
     * We choose to order parcels first by delivery date, next by tracking number if the delivery dates
     * are the same, and lastly by name if the tracking numbers are the same as well.
     */
    @Override
    public int compareTo(Object o) {
        Parcel other = (Parcel) o;
        if (other == this) { // short circuit if same object
            return 0;
        } else if (this.getDeliveryDate().compareTo(other.getDeliveryDate()) == 0) { // delivery dates are equal
            if (this.getName().compareTo(other.getName()) == 0) { // names are equal
                return this.getTrackingNumber().compareTo(other.getTrackingNumber()); // compare tracking numbers
            } else {
                return this.getName().compareTo(other.getName()); // compare names
            }
        } else {
            return this.getDeliveryDate().compareTo(other.getDeliveryDate()); // compare delivery dates
        }
    }
    //@@author
}
