package com.example.petstore;

public class Pet {
    private String name;
    private String photoUrls;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    /*public String getPhoto() { return photoUrls; }
    public void setPhoto(String photoUrls) { this.photoUrls = photoUrls; }*/

    @Override
    public String toString() { return name; }
}
