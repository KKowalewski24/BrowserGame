package pl.kkowalewski.board;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class SudokuColumnTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private SudokuColumn sudokuColumn;
    private SudokuColumn sudokuColumnSecond;

    /*------------------------ METHODS REGION ------------------------*/
    private SudokuColumn makeObjectWithValidList() {
        return new SudokuColumn(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
    }

    @Test
    public void CloneTest() throws CloneNotSupportedException {
        sudokuColumn = makeObjectWithValidList();
        sudokuColumnSecond = (SudokuColumn) sudokuColumn.clone();

        assertTrue(sudokuColumn.equals(sudokuColumnSecond)
                && sudokuColumnSecond.equals(sudokuColumn));
    }
}
