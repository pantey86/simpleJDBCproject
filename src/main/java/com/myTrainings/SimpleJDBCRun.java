package com.myTrainings;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class SimpleJDBCRun {

    /*To create simple java application with JDBC:
    * - create simple JAVA application
    * - create data base and a table inside
    * - import java.sql.*;
    * - use JDBC driver
    * - create connection
    * - create a request
    * - get data from data base
    * - close the connection
    * */

    private static  String JDBC_DRIVER ;
    private static  String DATABASE_URL;
    private static  String USER;
    private static  String PASSWORD;

    private static void initConfig () throws IOException {

        try {
            Properties   config=Configurations.getConfig();
            JDBC_DRIVER = config.getProperty("JDBC_DRIVER");
            DATABASE_URL= config.getProperty("DATABASE_URL");
            USER= config.getProperty("USER");
            PASSWORD= config.getProperty("PASSWORD");
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        try {
            initConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        Statement statement = null;

        System.out.println("Registering JDBC driver...");

        Class.forName(JDBC_DRIVER);

        System.out.println("Creating database connection...");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement...");
        statement = connection.createStatement();

        String sqlQueryShowAllInTable;
        sqlQueryShowAllInTable = "SELECT * FROM developers";

        ResultSet resultSet = statement.executeQuery(sqlQueryShowAllInTable);

        System.out.println("Retrieving data from database...");
        System.out.println("\nDevelopers: ");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String specialty = resultSet.getString("specialty");
            int salary = resultSet.getInt("salary");

            System.out.println("\n================\n");
            System.out.println("id: " + id);
            System.out.println("Name: " + name);
            System.out.println("Specialty: " + specialty);
            System.out.println("Salary: $" + salary);
        }

        System.out.println("Closing connection and releasing resources...");
        resultSet.close();
        statement.close();
        connection.close();

    }
}
