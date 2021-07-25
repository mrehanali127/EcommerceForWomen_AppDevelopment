package com.example.ecommerceforwomen;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class User {

    String client_id;
    String artist_id;
    ArrayList<String> skills;
    String firstName;
    String lastName;
    Long phone;
    String dob;
    String gender;
    String city;
    String tehsil;
    String complete_address;



    public User(String client_id,String firstName, String lastName, Long phone, String dob, String gender, String city, String tehsil, String complete_address) {
        this.client_id=client_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.city = city;
        this.tehsil = tehsil;
        this.complete_address = complete_address;
    }

    public User(String artist_id,String firstName, String lastName, Long phone, String dob, String gender, String city, String tehsil, String complete_address,ArrayList<String> skills) {
        this.artist_id=artist_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.city = city;
        this.tehsil = tehsil;
        this.complete_address = complete_address;
        this.skills=skills;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTehsil() {
        return tehsil;
    }

    public void setTehsil(String tehsil) {
        this.tehsil = tehsil;
    }

    public String getComplete_address() {
        return complete_address;
    }

    public void setComplete_address(String complete_address) {
        this.complete_address = complete_address;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }
}
