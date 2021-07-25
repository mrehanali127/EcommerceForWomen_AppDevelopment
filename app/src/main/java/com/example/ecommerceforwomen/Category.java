package com.example.ecommerceforwomen;

public class Category {
    String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public  Category(){

    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
