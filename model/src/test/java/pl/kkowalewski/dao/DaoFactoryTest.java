package pl.kkowalewski.dao;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DaoFactoryTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private DaoFactory factory = new DaoFactory();

    /*------------------------ METHODS REGION ------------------------*/
    @Test
    public void makeFileDaoTest() {
        assertNotNull(factory.makeFileDao("abc.txt"));
    }
}
