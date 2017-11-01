package seedu.address.ui;

import seedu.address.logic.parser.Prefix;

import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Represents the states of the autcompleter
 */
public enum AutocompleteState {
    COMMAND,
    COMMAND_NEXT_PREFIX,
    COMMAND_CYCLE_PREFIX,
    EMPTY,
    MULTIPLE_COMMAND,
    NO_RESULT,
    INDEX
}