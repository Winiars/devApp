package com.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


/**
 * Created by Gruby on 20.04.2017.
 */
@Entity

//@NamedQuery(name = "Flat.findFlatByIdWithTenants", query = "select f from Flat f left join fetch  f.tenants where f.id=:flatId")
public class Flat {

    public static final String FIND_FLAT_BY_ID_WITH_TENANTS="Flat.findFlatByIdWithTenants";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Lob
    private String description;
    private String street;
    private Date startDate;
    private Date finishDate;

    @OneToOne(optional = false)
    @JoinColumn(name = "cityID")
    private City city;


    @OneToMany(mappedBy = "flat", cascade = CascadeType.ALL)
    private List<Rent> rents;

    public void addRent(Rent rent) {
        rents.add(rent);
        rent.setFlat(this);
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    //    @ManyToMany
//    private List<User> tenants;

//    public List<User> getTenants() {
//        return tenants;
//    }

//    public void setTenants(List<User> tenants) {
//        this.tenants = tenants;
//    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flat flat = (Flat) o;

        return id == flat.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
