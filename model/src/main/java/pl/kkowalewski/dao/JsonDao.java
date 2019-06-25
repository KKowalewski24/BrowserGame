package pl.kkowalewski.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.kkowalewski.board.SudokuBoard;
import pl.kkowalewski.exception.DaoException;
import pl.kkowalewski.exception.JsonException;

public class JsonDao implements Dao<SudokuBoard> {

    /*------------------------ FIELDS REGION ------------------------*/
    private String filename;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /*------------------------ METHODS REGION ------------------------*/
    JsonDao(String filename) {
        this.filename = filename;
    }

    @Override
    public SudokuBoard read() throws DaoException {
        SudokuBoard object;

        try (FileReader fileReader = new FileReader(filename)) {
            object = gson.fromJson(fileReader, SudokuBoard.class);
        } catch (IOException e) {
            throw new JsonException(e);
        }

        return object;
    }

    @Override
    public void write(final SudokuBoard object) throws DaoException {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            gson.toJson(object, fileWriter);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }
}
