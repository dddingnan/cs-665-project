/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: IRepository.java
 * Description: Defines the contract for repository classes.
 * It outlines the essential CRUD(Create, Read, Update, Delete) 
 * operations for managing database records.
 */
package edu.bu.met.cs665.database.repository;

import java.sql.SQLException;
import java.util.List;

import edu.bu.met.cs665.exception.InvalidDataException;

public interface IRepository<T> {
    /**
     * Creates a table in the database specific to the object type T.
     * Implementations of this method should contain SQL statements to
     * initialize the necessary table structure for storing objects of type T.
     * 
     * @throws SQLException If a database access error occurs or the SQL statement
     *                      is not valid.
     */
    void createTable() throws SQLException;

    /**
     * Inserts a list of objects of type T into the corresponding table in the
     * database.
     * Implementations should convert each object in the list into a suitable format
     * for
     * storage in the database.
     *
     * @param dataList The list of objects to be inserted into the database.
     * @throws SQLException If a database access error occurs or the SQL statement
     *                      is not valid.
     */
    void insertData(List<T> dataList) throws SQLException;

    /**
     * Retrieves all records from the table corresponding to the object type T and
     * converts them into a list of objects of type T.
     * 
     * @return A list of objects of type T, representing all records in the
     *         corresponding database table.
     * @throws SQLException         If a database access error occurs or the SQL
     *                              statement is not valid.
     * @throws InvalidDataException If the data retrieved from the database is
     *                              invalid or corrupt.
     */
    List<T> selectAll() throws SQLException, InvalidDataException;
}
