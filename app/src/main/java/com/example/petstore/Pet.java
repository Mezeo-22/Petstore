package com.example.petstore;

public class Pet {
    private String name;
    private String photoUrls;

    private int id;
    public int getPetId() { return id; }
    public void setPetId(int petId) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoto() { return photoUrls; }
    public void setPhoto(String photo) { this.photoUrls = photo; }

    @Override
    public String toString() { return name; }
}
