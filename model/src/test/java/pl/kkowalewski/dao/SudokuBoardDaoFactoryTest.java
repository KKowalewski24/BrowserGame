package pl.kkowalewski.dao;

import org.junit.Test;

import static org.junit.Assert.assertNull;

public class SudokuBoardDaoFactoryTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

    /*------------------------ METHODS REGION ------------------------*/
    @Test
    public void makeFileDaoTest() {
        assertNull(factory.makeFileDao("abc.txt"));
    }
}
