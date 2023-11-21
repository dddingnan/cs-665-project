package edu.bu.met.cs665.database.repository;

import java.sql.SQLException;
import java.util.List;

public interface IRepository<T> {
    void createTable() throws SQLException;

    void insertData(List<T> dataList) throws SQLException;

    List<T> selectAll() throws SQLException;
}
