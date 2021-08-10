package com.example.ecommerceforwomen;

public class Category2 {
    String name;
    String ImageUrl;


    public Category2() {
    }

    public Category2(String name, String imageUrl) {
        this.name = name;
        ImageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
