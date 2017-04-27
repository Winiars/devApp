package com.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Gruby on 10.04.2017.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findUserByEmail", query = "select u from User u where u.email= :email"),
        @NamedQuery(name = "User.findUserByNick", query = "select u from User u where u.nick= :nick")

})
public class User implements Serializable {
    public static final String FIND_BY_EMAIL = "User.findUserByEmail";
    public static final String FIND_BY_NICK = "User.findUserByNick";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Column(unique = true)
    private String email;
    private String password;
    @Column(unique = true)
    private String nick;
    private String name;
    private String surname;
    private String phoneNumber;
    private String postCode;
    private String city;
    private String street;
    private int age;
    private String info;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany(mappedBy = "tenants")
    private List<Flat> listOfFlats;

    public List<Flat> getListOfFlats() {
        return listOfFlats;
    }

    public void setListOfFlats(List<Flat> listOfFlats) {
        this.listOfFlats = listOfFlats;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Id == user.Id;


    }

    @Override
    public int hashCode() {

        return Id;
    }

    public int getId() {

        return Id;
    }

    public void setId(int id) {

        Id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isAdmin() {
        return role.equals(Role.ADMIN);
    }

    public boolean isUser() {
        return role.equals(Role.USER);
    }


}
