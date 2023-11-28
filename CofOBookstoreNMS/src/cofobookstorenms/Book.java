/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cofobookstorenms;

/**
 *
 * @author 221384
 */
public class Book {
    
    private String ISBN;
    private String author;
    private String title;
    private String bookDesc;
    private int genreID;
    private double price;

    public Book() {
    }

    public Book(String ISBN, String author, String title, String bookDesc, int genreID, double price) {
        this.ISBN = ISBN;
        this.author = author;
        this.title = title;
        this.bookDesc = bookDesc;
        this.genreID = genreID;
        this.price = price;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" + "ISBN=" + ISBN + ", author=" + author + ", title=" + title + ", bookDesc=" + bookDesc + ", genre=" + genreID + ", price=" + price + '}';
    }

    
    
}

