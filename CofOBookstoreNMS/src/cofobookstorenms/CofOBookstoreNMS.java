/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cofobookstorenms;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 221384
 */
public class CofOBookstoreNMS {

    static Customer currentCustomer;
    static Scanner input = new Scanner(System.in);
    static DBDataSource ds;

    public static void main(String[] args) {

        int userSelection = 0;
        String userString;

        ds = new DBDataSource();

        while (userSelection != 3) {
            displayLoginMenu();
            try {
                userString = input.nextLine();
                userSelection = Integer.parseInt(userString);
                switch (userSelection) {
                    case 1:
                        if (logUserIn()) {
                            System.out.println("Welcome, " + currentCustomer.getFirstName());
                            memberMenu();
                        } else {
                            System.out.println("No account found, please register.");
                        }
                        break;
                    case 2:
                        if (createNewUser()) {
                            System.out.println("Welcome new customer, " + currentCustomer.getFirstName());
                            System.out.println("Please log in.");
                        } else {
                            System.out.println("I'm sorry, the account could not be created.");
                        }
                        break;
                    case 3:
                        logUserOut();
                        break;
                    default:
                        System.out.println("Sorry, that is an invalid response. Try again.");
                        break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter an integer value between 1 and 3");
            }
        }
    }

    private static void displayLoginMenu() {
        System.out.println("**********************************************************************");
        System.out.println("***                                                                ***");
        System.out.println("***             Welcome to the CofO Book Store                      ***");
        System.out.println("***                                                                ***");
        System.out.println("**********************************************************************");

        System.out.println(" 1. Member Login");
        System.out.println(" 2. New Member Registration");
        System.out.println(" 3. Exit");
    }

    private static boolean logUserIn() {
        String userEmail;
        boolean loginSuccess = false;

        System.out.println("Welcome to the CofO Bookstore");
        System.out.println("Please enter your email address: ");

        userEmail = input.nextLine();

        if (ds.connectDB()) {
            currentCustomer = ds.getCustomer(userEmail);
            ds.disConnectDB();
            if (currentCustomer != null) {
                loginSuccess = true;
            }
            ds.disConnectDB();
        } else {
            System.out.println("System not currently available.");
        }
        return loginSuccess;
    }

    private static void memberMenu() {
        int userSelection = -1;
        String userString;

        while (userSelection != 6) {
            displayMemberMenu();
            try {
                userString = input.nextLine();
                userSelection = Integer.parseInt(userString);

                switch (userSelection) {
                    case 1:
                        browseByGenre();
                        break;
                    case 2:
                        searchForBooks();
                        break;
                    case 3:
                        viewShoppingCart();
                        break;
                    case 4:
                        oneClickCheckOut();
                        break;
                    case 5:
                        viewPersonalInfo();
                        break;
                    case 6:
                        System.out.println("Returning to previous memu.");
                        break;
                    default:
                        System.out.println("Sorry, that is an invalid response. Try again.");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Please enter an integer value between 1 and 6");
            }
        }

    }

    private static void displayMemberMenu() {
        System.out.println("**********************************************************************");
        System.out.println("***                                                                ***");
        System.out.println("***             CofO Book Store Member Access                      ***");
        System.out.println("***                                                                ***");
        System.out.println("**********************************************************************");

        System.out.println(" 1. Browse by Genre");
        System.out.println(" 2. Search by Author/Title/Genre");
        System.out.println(" 3. View/Edit Shopping Cart");
        System.out.println(" 4. One Click Check Out");
        System.out.println(" 5. View Personal Information");
        System.out.println(" 6. Return to previous menu");
    }

    private static boolean createNewUser() {
        String userEmail;
        boolean userAdded = false;
        Customer newCustomer = new Customer();

        System.out.print("Please Input your email address: ");
        userEmail = input.nextLine();

        if (ds.connectDB()) {
            if (ds.getCustomer(userEmail) != null) {
                System.out.println("Email is already registered.");

            } else {
                //add user input checks
                newCustomer.setEmail(userEmail);
                System.out.print("\nPlease Input your first name (30 chars max): ");
                newCustomer.setFirstName(input.nextLine());
                System.out.print("\nPlease Input your last name (50 chars max): ");
                newCustomer.setLastName(input.nextLine());
                System.out.print("\nPlease Input your address (50 chars max): ");
                newCustomer.setAddress(input.nextLine());
                System.out.print("\nPlease Input your city (30 chars max): ");
                newCustomer.setCity(input.nextLine());
                System.out.print("\nPlease Input your state (2 chars max): ");
                newCustomer.setState(input.nextLine());
                System.out.print("\nPlease Input your zip code (5 chars max): ");
                newCustomer.setZipCode(input.nextLine());
                System.out.print("\nPlease Input your phone number (10 chars max): ");
                newCustomer.setPhoneNbr(input.nextLine());
                System.out.print("\nPlease Input your password (20 chars max): ");
                newCustomer.setPassword(input.nextLine());
                System.out.print("\nPlease Input your credit card company (AMEX, MCRD, or VISA - 4 chars only): ");
                newCustomer.setCreditCardCode(input.nextLine());
                System.out.print("\nPlease Input your credit card number (16 chars max): ");
                newCustomer.setCreditCardNbr(input.nextLine());

                userAdded = ds.insertNewCustomer(newCustomer) > 0;
                if (userAdded) {
                    currentCustomer = newCustomer;
                }

            }
            ds.disConnectDB();
        } else {
            System.out.println("System not currently available.");
        }
        return userAdded;
    }

    private static boolean logUserOut() {
        System.out.print("Thanks for visiting");
        if (currentCustomer != null) {
            System.out.println(", " + currentCustomer.getFirstName());
        }
        return true;
    }

    private static void browseByGenre() {
        int userSelection = -1;
        String userString, ISBNSelection;
        int bookQty = 0;
        ArrayList<Book> books;

        while (userSelection != 0) {
            listGenres();
            System.out.println("Please enter the Genre ID to browse or '000' to Quit");
            try {
                userString = input.nextLine();
                userSelection = Integer.parseInt(userString);
                if (userSelection != 0) {
                    if (ds.connectDB()) {
                        books = ds.getBooksByGenre(userSelection);
                        ds.disConnectDB();

                        if (!books.isEmpty()) {
                            for (int i = 0; i < books.size(); i++) {
                                System.out.println("Title: " + books.get(i).getTitle());
                                System.out.println("Author: "
                                        + books.get(i).getAuthor()
                                        + " Price: "
                                        + books.get(i).getPrice()
                                        + " ISBN: " + books.get(i).getISBN());
                                System.out.println();
                                System.out.println("Enter ISBN to add to Shopping Cart or hit enter to select another genre.");
                                ISBNSelection = input.nextLine();
                                if (ISBNSelection.length() == 13) {
                                    System.out.print("Please enter quantity: ");
                                    try {
                                        bookQty = Integer.parseInt(input.nextLine());
                                        if (bookQty > 0) {
                                            if (addBookToCart(books.get(i), bookQty)) {
                                                System.out.println("Book added");
                                            } else {
                                                System.out.println("Error adding book to Cart.");
                                            }
                                        }
                                    } catch (NumberFormatException ex) {
                                        System.out.println("Invalid quantity entered.");
                                    }
                                }
                                System.out.println("Press enter to continue...");
                                input.nextLine();
                            }
                        } else {
                            System.out.println("Sorry, no books in that Genre available.");
                        }
                    }
                }

            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid Genre ID or '000' to exit");
            }
        }
    }

    private static void listGenres() {
        ArrayList<Genre> genreList;

        System.out.println("Available Genres:");
        if (ds.connectDB()) {
            genreList = ds.getGenres();
            if (!genreList.isEmpty()) {
                System.out.printf("%5s     %50s\n", "Genre ID", "Name");
                for (int i = 0; i < genreList.size(); i++) {
                    System.out.printf("%5d     %50s\n",
                            genreList.get(i).getGenreID(),
                            genreList.get(i).getName());
                }
            } else {
                System.out.printf("No Genres available at this time.");
            }
            ds.disConnectDB();
        }
    }

    private static boolean addBookToCart(Book bookToAdd, int bookQty) {
        boolean addToCart = false;
        return addToCart;
    }

    private static void searchForBooks() {
        //if author or title is valid return book else return invalid author name
        String bookAuthor;
        String bookTitle;
        ArrayList<Book> searchResult;

        System.out.print("Enter the author or title of a book: ");
        String search = input.nextLine();


    }
    /*    String bookAuthor;
    String bookTitle;
    ArrayList<Book> searchResults;

    System.out.print("Enter the author or title of a book: ");
    String searchTerm = input.nextLine();

    if (ds.connectDB()) {
        searchResults = ds.searchBooks(searchTerm);
        ds.disConnectDB();

        if (!searchResults.isEmpty()) {
            System.out.println("Search Results:");
            for (int i = 0; i < searchResults.size(); i++) {
                System.out.println("Title: " + searchResults.get(i).getTitle());
                System.out.println("Author: "
                        + searchResults.get(i).getAuthor()
                        + " Price: "
                        + searchResults.get(i).getPrice()
                        + " ISBN: " + searchResults.get(i).getISBN());
                System.out.println();
            }
        } else {
            System.out.println("No books found matching the search term: " + searchTerm);
        }
    }
    }*/
    private static void viewShoppingCart() {
        System.out.println("In viewShoppingCart");
    }

    private static void oneClickCheckOut() {
        System.out.println("In oneClickCheckOut");
    }

    private static void viewPersonalInfo() {
        System.out.println("**********************************************************************");
        System.out.println("***                                                                ***");
        System.out.println("***             CofO Book Store Personal Information               ***");
        System.out.println("***                                                                ***");
        System.out.println("**********************************************************************");
        System.out.println("\nCustomer ID: " + currentCustomer.getCustomerID());
        System.out.println("Customer First Name: " +currentCustomer.getFirstName()) ;
        System.out.println("Customer Last Name: " +currentCustomer.getLastName());
        System.out.println("Customer Address: " + currentCustomer.getAddress());
        System.out.println("Customer City: " + currentCustomer.getCity());
        System.out.println("Customer State: " + currentCustomer.getState());
        System.out.println("Customer Zip: " + currentCustomer.getZipCode());
        System.out.println("Customer Phone Number: " + currentCustomer.getPhoneNbr());
        System.out.println("Customer Email: " + currentCustomer.getEmail());
        System.out.println("Customer Password: " +currentCustomer.getPassword()); 
        System.out.println("Customer Credit Card Type: " + currentCustomer.getCreditCardCode());
        System.out.println("Customer Credit Card Number: " + currentCustomer.getCreditCardNbr()+ "\n"); 
    }

}
