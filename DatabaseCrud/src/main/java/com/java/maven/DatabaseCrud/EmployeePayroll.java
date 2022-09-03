package com.java.maven.DatabaseCrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import org.jetbrains.annotations.NotNull;

public class EmployeePayroll {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        MySQLConnection mySQLConnection = new MySQLConnection();
        Connection connection = mySQLConnection.getConnection();
        /*** use case 1 to add new employees */
       addNewEmployees(connection);

    }
 private static void addNewEmployees(@NotNull Connection connection) {
        System.out.print("Enter id = ");
        int id = sc.nextInt();
        System.out.print("Enter name = ");
        String name = sc.next();
        System.out.print("Enter salary = ");
        int salary = sc.nextInt();
        System.out.print("Enter start date (YYYY-MM-DD) = ");
        String startDate = sc.next();

        String sql = "insert into EmployeePayroll(id, name , salary ,startDate ) values (?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setDouble(3, salary);
            preparedStatement.setString(4, startDate);
            int i = preparedStatement.executeUpdate();
            System.out.println("The data inserted successfully ");
            System.out.println("rows affected = " + i);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) connection.close();
                if (connection != null) connection.close();

            }catch (SQLException e){
                e.printStackTrace();
            }

        }
    }
}
