/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cofobookstorenms;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author 221384
 */
public class DBDataSource {

    private Connection conn = null;

    public DBDataSource() {
    }

    public boolean connectDB() {
        //Create and return a connection to the database
        String connectionString = "jdbc:mysql://192.168.2.15/nstahl";
        String userID = "nstahl";
        String passWord = "Xnstahl2023!";

        try {
            conn = DriverManager.getConnection(connectionString, userID, passWord);
            return true;
        } catch (SQLException ex) {
            System.out.println("I'm sorry, the system is not currently available.");
            System.out.println("Please try again later.");
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public void disConnectDB() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Database close failed.");
            System.out.println(ex.getMessage());
        }
    }

    public Customer getCustomer(String email) {
        //Reference variable for Customer
        Customer cust = null;

        //Build query, execute, store results in the list as objects, return the list 
        try {

            PreparedStatement ps = conn.prepareStatement("select customerID, firstName, "
                    + "lastName, address, city, state, zipCode, phoneNbr, email, password,"
                    + "creditCardCode, creditCardNbr "
                    + "from Customer "
                    + "where email = '" + email + "';");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cust = new Customer(rs.getInt("customerID"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zipCode"),
                        rs.getString("phoneNbr"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("creditCardCode"),
                        rs.getString("creditCardNbr"));
            }

        } catch (SQLException ex) {
            System.out.println("Database fetch failed.");
            System.out.println(ex.getMessage());
        }

        return cust;

    }

    public int insertNewCustomer(Customer newCust) {
        //Create connection to database
        int rowsInserted = -1;
        //Build query
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement("INSERT into Customer "
                    + "(firstName, lastName, address, city, state, zipCode, "
                    + "phoneNbr, email, password,"
                    + "creditCardCode, creditCardNbr) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, newCust.getFirstName());
            ps.setString(2, newCust.getLastName());
            ps.setString(3, newCust.getAddress());
            ps.setString(4, newCust.getCity());
            ps.setString(5, newCust.getState());
            ps.setString(6, newCust.getZipCode());
            ps.setString(7, newCust.getPhoneNbr());
            ps.setString(8, newCust.getEmail());
            ps.setString(9, newCust.getPassword());
            ps.setString(10, newCust.getCreditCardCode());
            ps.setString(11, newCust.getCreditCardNbr());

            rowsInserted = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Database insert failed.");
            System.out.println(ex.getMessage());
        }

        return rowsInserted;

    }

    public ArrayList<Genre> getGenres() {
        ArrayList<Genre> genre = new ArrayList();

        try {

            Statement sqlStmt = conn.createStatement();

            ResultSet rs = sqlStmt.executeQuery("select genreID, genreName, "
                    + "genreDesc "
                    + "from Genre "
                    + ";");

            while (rs.next()) {
                genre.add(new Genre(rs.getInt("genreID"),
                        rs.getString("genreName"),
                        rs.getString("genreDesc")));
            }

        } catch (SQLException ex) {
            System.out.println("Database fetch for Genre failed.");
            System.out.println(ex.getMessage());
        }

        return genre;
    }

    public ArrayList<Book> getBooksByGenre(int userSelection) {
        ArrayList<Book> books = new ArrayList();

        //Build query, execute, store results in the list as objects, return the list 
        try {

            PreparedStatement ps = conn.prepareStatement("select ISBN, author, "
                    + "title, bookDesc, genreID, price "
                    + "from Book "
                    + "where genreID = ? "
                    + ";");

            ps.setInt(1, userSelection);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                books.add(new Book(rs.getString("ISBN"),
                        rs.getString("author"),
                        rs.getString("title"),
                        rs.getString("bookDesc"),
                        rs.getInt("genreID"),
                        rs.getDouble("price")));
            }

        } catch (SQLException ex) {
            System.out.println("Database fetch for Book failed.");
            System.out.println(ex.getMessage());
        }

        return books;
    }

}
