package seedu.address.model;

import static org.junit.Assert.fail;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.exceptions.TagNotFoundException;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void addPerson(ReadOnlyPerson person) throws DuplicatePersonException {
        fail("This method should not be called.");
    }

    @Override
    public void resetData(ReadOnlyAddressBook newData) {
        fail("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        fail("This method should not be called.");
        return null;
    }

    @Override
    public void deletePerson(ReadOnlyPerson target) throws PersonNotFoundException {
        fail("This method should not be called.");
    }

    @Override
    public void deleteTag(Tag target) throws TagNotFoundException {
        fail("This method should not be called.");
    }

    @Override
    public void updatePerson(ReadOnlyPerson target, ReadOnlyPerson editedPerson)
            throws DuplicatePersonException {
        fail("This method should not be called.");
    }

    @Override
    public ObservableList<ReadOnlyPerson> getFilteredPersonList() {
        fail("This method should not be called.");
        return null;
    }

    @Override
    public void updateFilteredPersonList(Predicate<ReadOnlyPerson> predicate) {
        fail("This method should not be called.");
    }
}
