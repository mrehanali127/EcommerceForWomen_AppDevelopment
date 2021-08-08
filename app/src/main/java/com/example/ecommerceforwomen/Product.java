package com.example.ecommerceforwomen;

public class Product {
    String product_id;
    String Artist_id;
    String category;
    String title;
    Long price;
    String imageurl;
    String description;

    public Product(){

    }

    public Product(String product_id, String artist_id, String category, String title, Long price, String imageurl, String description) {
        this.product_id = product_id;
        Artist_id = artist_id;
        this.category = category;
        this.title = title;
        this.price = price;
        this.imageurl = imageurl;
        this.description = description;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getArtist_id() {
        return Artist_id;
    }

    public void setArtist_id(String artist_id) {
        Artist_id = artist_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
