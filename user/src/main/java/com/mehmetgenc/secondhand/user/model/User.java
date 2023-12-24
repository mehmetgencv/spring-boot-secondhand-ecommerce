package com.mehmetgenc.secondhand.user.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String mail;
    private String firstName;
    private String middleName;
    private String lastName;
    private Boolean isActive;

    public User(Long id, String mail, String firstName, String middleName, String lastName, Boolean isActive) {
        this.id = id;
        this.mail = mail;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.isActive = isActive;
    }

    public User(String mail, String firstName, String middleName, String lastName, Boolean isActive) {
        this.mail = mail;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.isActive = isActive;
    }
    public User(Long id, String mail, String firstName, String middleName, String lastName) {
        this.id = id;
        this.mail = mail;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean getActive() {
        return isActive;
    }

}