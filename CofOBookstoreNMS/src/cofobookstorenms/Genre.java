/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cofobookstorenms;

/**
 *
 * @author 221384
 */
public class Genre {
    private int genreID;
    private String name;
    private String description;

    public Genre() {
    }

    public Genre(int genreID, String name) {
        this.genreID = genreID;
        this.name = name;
    }

    public Genre(int genreID, String name, String description) {
        this.genreID = genreID;
        this.name = name;
        this.description = description;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Genre{" + "genreID=" + genreID + ", name=" + name + ", description=" + description + '}';
    }
    
    
}

