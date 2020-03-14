package com.tchristofferson.persistentstorageapi.examples;

import com.tchristofferson.persistentstorageapi.SingleStorage;

import java.sql.*;

public class PersonDatabaseStorage implements SingleStorage<Person, String> {

    private static final String TABLE = "PEOPLE";
    private static final String SSN_COLUMN = "ssn";
    private static final String FIRST_NAME_COLUMN = "first_name";
    private static final String LAST_NAME_COLUMN = "last_name";

    private final String databaseURL;
    private final String username;
    private final String password;

    public PersonDatabaseStorage(String databaseURL, String username, String password) {
        this.databaseURL = databaseURL;
        this.username = username;
        this.password = password;
    }

    public Person get(String ssn) throws SQLException {
        PreparedStatement statement = getPreparedStatement("SELECT * FROM " + TABLE + " WHERE " + SSN_COLUMN + " = '" + ssn + "';");
        ResultSet resultSet = statement.executeQuery();

        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        statement.getConnection().close();

        return new Person(ssn, firstName, lastName);
    }

    public void save(Person obj) throws SQLException {
        executeUpdate("INSERT INTO " + TABLE + " VALUES('" + obj.getSsn() + "'" +
                ", '" + obj.getFirstName() + "', '" + obj.getLastName() + "');");
    }

    public void update(String ssn, Person newObj) throws SQLException {
        if (!newObj.getSsn().equals(ssn))
            throw new IllegalArgumentException("The ssn doesn't match the new person's ssn!");

        executeUpdate("UPDATE " + TABLE + " SET " + FIRST_NAME_COLUMN + " = '" + newObj.getFirstName() + "', " +
                LAST_NAME_COLUMN + " = '" + newObj.getLastName() + "' WHERE " + SSN_COLUMN + " = '" + ssn + "';");
    }

    public void delete(String ssn) throws SQLException {
        executeUpdate("DELETE FROM " + TABLE + " WHERE " + SSN_COLUMN + " = '" + ssn + "';");
    }

    private void executeUpdate(String sql) throws SQLException {
        PreparedStatement statement = getPreparedStatement(sql);
        statement.executeUpdate();
        statement.getConnection().close();
    }

    private PreparedStatement getPreparedStatement(String sql) throws SQLException {
        Connection connection = getConnection();
        return connection.prepareStatement(sql);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseURL, username, password);
    }
}
