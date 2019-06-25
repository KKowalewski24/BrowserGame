package pl.kkowalewski.dao;

import pl.kkowalewski.board.SudokuBoard;

public class DaoFactory {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public final Dao<SudokuBoard> makeFileDao(final String filename) {
        return new JsonDao(filename);
    }
}
