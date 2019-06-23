package pl.kkowalewski.board;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class SudokuBoard implements Serializable, Cloneable {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final int SMALL_SIZE = 3;
    public static final int SIZE = 9;
    public static final int NUMBER_OF_CELLS = SIZE * SIZE;

    private List<List<SudokuField>> board;

    /*------------------------ METHODS REGION ------------------------*/

    /**
     * Constructor of the class.
     */
    public SudokuBoard() {
        board = Arrays.asList(new List[SIZE]);

        for (int i = 0; i < SIZE; i++) {
            board.set(i, Arrays.asList(new SudokuField[SIZE]));
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.board.get(i).set(j, new SudokuField());
            }
        }
    }

    public final int get(final int i, final int j) {
        return board.get(i).get(j).getFieldValue();
    }

    public final void set(final int i, final int j, final int value) {
        this.board.get(i).get(j).setFieldValue(value);
    }

    /**
     * Return a chosen row of matrix.
     *
     * @param row number of chosen row
     * @return SudokuRow object
     */
    public final SudokuRow getRow(final int row) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SIZE]);
        for (int i = 0; i < SIZE; i++) {
            fields.set(i, board.get(row).get(i));
        }

        return new SudokuRow(fields);
    }

    /**
     * Return a chosen column of matrix.
     *
     * @param column number of chosen column
     * @return SudokuColumn object
     */
    public final SudokuColumn getColumn(final int column) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SIZE]);
        for (int i = 0; i < SIZE; i++) {
            fields.set(i, board.get(i).get(column));
        }

        return new SudokuColumn(fields);
    }

    /**
     * Return a chosen small square of matrix.
     *
     * @param rowIndex    number of chosen row
     * @param columnIndex number of chosen column
     * @return SudokuBox object
     */
    public final SudokuBox getBox(final int rowIndex, final int columnIndex) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SIZE]);
        int index = 0;
        for (int i = 0; i < SMALL_SIZE; i++) {
            for (int j = 0; j < SMALL_SIZE; j++) {
                fields.set(index++, board.get(rowIndex * SMALL_SIZE + i).get(columnIndex * SMALL_SIZE + j));
            }
        }

        return new SudokuBox(fields);
    }

    public final boolean isEditableField(final int axisX, final int axisY) {
        return board.get(axisX).get(axisY).isEmptyField();
    }

    public final void setEditableField(final int axisX, final int axisY) {
        board.get(axisX).get(axisY).setEmptyField();
    }

    /**
     * Check if board is correct.
     *
     * @return boolean - if board is correct the true
     */
    public final boolean checkBoard() {
        boolean isCorrect = true;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int j2 = j + 1; j2 < SIZE; j2++) {
                    if (board.get(i).get(j).getFieldValue()
                            == board.get(i).get(j2).getFieldValue()) {
                        isCorrect = false;
                    }
                }
            }
        }

        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                for (int i2 = i + 1; i2 < SIZE; i2++) {
                    if (board.get(i).get(j).getFieldValue()
                            == board.get(i2).get(j).getFieldValue()) {
                        isCorrect = false;
                    }
                }
            }
        }

        for (int i = 0; i < SMALL_SIZE; i++) {
            for (int j = 0; j < SMALL_SIZE; j++) {
                for (int checked = 0; checked < SIZE; checked++) {
                    for (int compared = checked + 1;
                         compared < SIZE; compared++) {
                        if (board.get(i * SMALL_SIZE + (checked / SMALL_SIZE))
                                .get(j * SMALL_SIZE + (checked % SMALL_SIZE)).getFieldValue()
                                == board.get(i * SMALL_SIZE + (compared / SMALL_SIZE))
                                .get(j * SMALL_SIZE + (compared % SMALL_SIZE)).getFieldValue()) {
                            isCorrect = false;
                        }
                    }
                }
            }
        }

        return isCorrect;
    }

    @Override
    public boolean equals(final Object o) {
        return new EqualsBuilder()
                .append(board, ((SudokuBoard) o).board)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(board)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("board", board)
                .toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SudokuBoard sudokuBoard = new SudokuBoard();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sudokuBoard.set(i, j, get(i, j));
            }
        }

        return sudokuBoard;
    }
}
