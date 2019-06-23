package pl.kkowalewski.dao;

import pl.kkowalewski.exception.DaoException;

public interface Dao<T> {
    T read() throws DaoException;

    void write(T object) throws DaoException;
}
