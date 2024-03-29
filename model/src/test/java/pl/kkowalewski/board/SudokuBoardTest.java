package pl.kkowalewski.board;

import org.junit.Before;
import org.junit.Test;
import pl.kkowalewski.solver.BacktrackingSudokuSolver;

import static org.junit.Assert.*;

public class SudokuBoardTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private SudokuBoard sudokuBoard;
    private SudokuBoard sudokuBoardSecond;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        sudokuBoard = new SudokuBoard();
    }

    @Test
    public void checkBoardTest() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudokuBoard);
        assertTrue(sudokuBoard.checkBoard());
    }

    @Test
    public void checkBoardFailTest() {
        assertFalse(sudokuBoard.checkBoard());
    }

    @Test
    public void getSetMethodsTest() {
        assertEquals(sudokuBoard.get(0, 0), 0);
        sudokuBoard.set(0, 0, 5);
        assertEquals(sudokuBoard.get(0, 0), 5);
    }

    @Test
    public void getRowTest() {
        assertNotNull(sudokuBoard.getRow(2));
    }

    @Test
    public void getColumnTest() {
        assertNotNull(sudokuBoard.getColumn(2));
    }

    @Test
    public void getBoxTest() {
        assertNotNull(sudokuBoard.getBox(1, 1));
    }

    @Test
    public void isEditableFieldTest() {
        assertFalse(sudokuBoard.isEditableField(1, 1));
    }

    @Test
    public void setEditableFieldTest() {
        sudokuBoard.setEditableField(1, 1);
        assertTrue(sudokuBoard.isEditableField(1, 1));
    }

    @Test
    public void toStringTest() {
        assertNotNull(sudokuBoard.toString());
    }

    @Test
    public void equalsTest() {
        sudokuBoardSecond = new SudokuBoard();
        assertTrue(sudokuBoard.equals(sudokuBoardSecond)
                && sudokuBoardSecond.equals(sudokuBoard));
    }

    @Test
    public void hashCodeTest() {
        sudokuBoardSecond = new SudokuBoard();
        assertEquals(sudokuBoard.hashCode(), sudokuBoardSecond.hashCode());
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudokuBoard);
        sudokuBoardSecond = (SudokuBoard) sudokuBoard.clone();

        assertTrue(sudokuBoard.equals(sudokuBoardSecond)
                && sudokuBoardSecond.equals(sudokuBoard));
    }
}
