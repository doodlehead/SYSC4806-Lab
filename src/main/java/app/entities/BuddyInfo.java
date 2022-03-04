package app.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class BuddyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    private String phoneNumber;

    @ManyToOne
    // @JsonBackReference
    private AddressBook book;

    public BuddyInfo(String name, String address, String phoneNum) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNum;
    }

    public BuddyInfo() {
        this("Carl", "5 Drive street", "1234567890");
    }

    public AddressBook getAddressBook() {
        return this.book;
    }
    public void setAddressBook(AddressBook book) {
        this.book = book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static BuddyInfo importBuddy(String s) {
        String[] tokens = s.split("#"); //Split by number sign

        if(tokens.length != 3) throw new IllegalArgumentException("Invalid Buddy String");
        return new BuddyInfo(tokens[0], tokens[1], tokens[2]);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        BuddyInfo other = (BuddyInfo)o;
        return this.name.equals(other.name) && this.address.equals(other.address) && this.phoneNumber.equals(other.phoneNumber);
    }

    @Override
    public String toString() {
        return String.format("%d      %s      %s      %s", this.id, this.name, this.address, this.phoneNumber);
    }

}