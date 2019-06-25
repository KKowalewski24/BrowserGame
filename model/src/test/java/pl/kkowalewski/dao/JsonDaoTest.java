package pl.kkowalewski.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import pl.kkowalewski.board.SudokuBoard;
import pl.kkowalewski.exception.DaoException;
import pl.kkowalewski.exception.JsonException;

import static org.junit.Assert.assertEquals;

public class JsonDaoTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private DaoFactory factory = new DaoFactory();
    private Dao<SudokuBoard> jsonDao;
    private SudokuBoard sudokuBoard = new SudokuBoard();
    private SudokuBoard sudokuBoardSecond = new SudokuBoard();

    /*------------------------ METHODS REGION ------------------------*/
    @Test
    public void writeReadTest() throws IOException {
        jsonDao = factory.makeFileDao("xxx.json");
        jsonDao.write(sudokuBoard);
        sudokuBoardSecond = jsonDao.read();

        assertEquals(sudokuBoard, sudokuBoardSecond);

        Files.deleteIfExists(Paths.get("./xxx.json"));
    }

    @Test(expected = JsonException.class)
    public void readExceptionTest() throws IOException {
        Files.deleteIfExists(Paths.get("./xxx.json"));
        jsonDao = factory.makeFileDao("xxx.json");
        jsonDao.read();
    }

    @Test(expected = JsonException.class)
    public void writeExceptionTest() throws DaoException {
        if (SystemUtils.IS_OS_WINDOWS) {
            jsonDao = factory.makeFileDao("?");
        } else if (SystemUtils.IS_OS_LINUX) {
            jsonDao = factory.makeFileDao("/");
        } else {
            jsonDao = factory.makeFileDao("?");
        }

        jsonDao.write(sudokuBoard);
    }
}