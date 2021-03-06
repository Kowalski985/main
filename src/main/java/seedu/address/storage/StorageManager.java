package seedu.address.storage;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.AddressBookChangedEvent;
import seedu.address.commons.events.storage.DataSavingExceptionEvent;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager extends ComponentManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;

        backup(addressBookStorage);
    }

    // ================ UserPrefs methods ==============================
    @Override
    public String getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(UserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ AddressBook methods ==============================
    @Override
    public String getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(String filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, String filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    //@@author kennard123661
    /**
     * Backup {@code addressBookStorage} and stores the file into the backup file.
     *
     * @param addressBookStorage the storage data to backup.
     */
    private void backup(AddressBookStorage addressBookStorage) {
        Optional<ReadOnlyAddressBook> addressBookOptional;

        try {
            addressBookOptional = addressBookStorage.readAddressBook();

            if (addressBookOptional.isPresent()) {
                backup(addressBookOptional.get());
                logger.info("Storage file present, back up success!");
            } else {
                logger.warning("Storage file not present, backup not possible.");
            }

        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. "
                    + "Backup of Ark not available for this instance.");
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. "
                    + "Backup of Ark not available for this instance.");
        }
    }

    /**
     * Saves the {@code addressBook} into backup file given by {@link StorageManager#getBackupStorageFilePath()}
     *
     * @param addressBook the backup addressBook
     * @throws IOException if there is an issue saving {@code addressBook} into the backup file.
     */
    public void backup(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, getBackupStorageFilePath());
    }

    /**
     * Returns backup file path.
     */
    public String getBackupStorageFilePath() {
        return addressBookStorage.getAddressBookFilePath() + "-backup.xml";
    }
    //@@author

    @Override
    @Subscribe
    public void handleAddressBookChangedEvent(AddressBookChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Local data changed, saving to file"));
        try {
            saveAddressBook(event.data);
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }

}
