package com.example.ecommerceforwomen;

public class Category2 {
    String categoryname;
    String ImageUrl;

    public Category2(){

    }

    public Category2(String categoryName, String imageUrl) {
        this.categoryname = categoryName;
        ImageUrl = imageUrl;
    }

    public String getCategoryName() {
        return categoryname;
    }

    public void setCategoryName(String categoryName) {
        this.categoryname = categoryName;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
