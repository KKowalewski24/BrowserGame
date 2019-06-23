package pl.kkowalewski.solver;

import pl.kkowalewski.board.SudokuBoard;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public final void solve(final SudokuBoard board) {
        Random rand = new Random();
        List<Integer> startNumbers = Arrays.asList(new Integer[SudokuBoard.NUMBER_OF_CELLS]);
        for (int i = 0; i < startNumbers.size(); i++) {
            startNumbers.set(i, 0);
        }

        for (int i = 0; i < SudokuBoard.NUMBER_OF_CELLS; i++) {
            int rowNumber = i / SudokuBoard.SIZE;
            int columnNumber = i % SudokuBoard.SIZE;

            boolean flagOK = false;
            if (startNumbers.get(i) == 0) {
                startNumbers.set(i, rand.nextInt(SudokuBoard.SIZE) + 1);
                board.set(rowNumber, columnNumber, startNumbers.get(i));

                do {
                    if (isValid(i, board)) {
                        flagOK = true;
                        break;
                    }
                    board.set(rowNumber, columnNumber,
                            (board.get(rowNumber, columnNumber) % SudokuBoard.SIZE + 1));
                } while (board.get(rowNumber, columnNumber) != startNumbers.get(i));

            } else {
                board.set(rowNumber, columnNumber, (board.get(rowNumber, columnNumber) % SudokuBoard.SIZE + 1));
                while (board.get(rowNumber, columnNumber) != startNumbers.get(i)) {
                    if (isValid(i, board)) {
                        flagOK = true;
                        break;
                    }
                    board.set(rowNumber, columnNumber,
                            (board.get(rowNumber, columnNumber) % SudokuBoard.SIZE + 1));
                }
            }

            if (!flagOK) {
                startNumbers.set(i, 0);
                board.set(rowNumber, columnNumber, 0);
                i -= 2;
            }
        }
    }

    private boolean isValid(final int index, final SudokuBoard board) {
        int rowNumber = index / SudokuBoard.SIZE;
        int columnNumber = index % SudokuBoard.SIZE;
        int number = board.get(rowNumber, columnNumber);

        for (int i = 0; i < columnNumber; i++) {
            if (number == board.get(rowNumber, i)) {
                return false;
            }
        }

        for (int i = 0; i < rowNumber; i++) {
            if (number == board.get(i, columnNumber)) {
                return false;
            }
        }

        int bigRowIndex = rowNumber / (SudokuBoard.SMALL_SIZE);
        int bigColumnIndex = columnNumber / (SudokuBoard.SMALL_SIZE);
        for (int i = 0; i < SudokuBoard.SMALL_SIZE; i++) {
            for (int j = 0; j < SudokuBoard.SMALL_SIZE; j++) {
                int realRowIndex = i + bigRowIndex * SudokuBoard.SMALL_SIZE;
                int realColumnIndex = j + bigColumnIndex * SudokuBoard.SMALL_SIZE;
                if (board.get(realRowIndex, realColumnIndex) == number
                        && (realRowIndex * SudokuBoard.SIZE + realColumnIndex) < index) {
                    return false;
                }
            }
        }

        return true;
    }
}

