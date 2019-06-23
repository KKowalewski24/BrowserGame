package pl.kkowalewski.board;

import java.util.ArrayList;
import java.util.List;

public class SudokuRow extends SudokuFieldGroup {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public SudokuRow(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>(getSudokuFieldList());
        return new SudokuRow(fields);
    }
}
