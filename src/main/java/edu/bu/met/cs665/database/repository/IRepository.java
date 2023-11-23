package edu.bu.met.cs665.database.repository;

import java.sql.SQLException;
import java.util.List;

import edu.bu.met.cs665.exception.InvalidDataException;

public interface IRepository<T> {
    void createTable() throws SQLException;

    void insertData(List<T> dataList) throws SQLException;

    List<T> selectAll() throws SQLException, InvalidDataException;
}
