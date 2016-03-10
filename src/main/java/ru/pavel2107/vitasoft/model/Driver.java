package ru.pavel2107.vitasoft.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Created by admin on 07.03.2016.
 */
public class Driver {

    private String name;
    private String surname;
    private String secondname;

    private SEX sex;

    @DateTimeFormat( iso= DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
    private Category category;
    private Integer ID;


    public Driver() {}


    public Driver(Integer ID, String surname, String name, String secondname, SEX sex, LocalDate birthDate, Category category) {
        this.name       = name;
        this.surname    = surname;
        this.secondname = secondname;

        this.sex = sex;
        this.birthDate = birthDate;
        this.category = category;
        this.ID = ID;
    }

    public Driver( String surname, String name, String secondname, SEX sex, LocalDate birthDate, Category category) {
        this.name       = name;
        this.surname    = surname;
        this.secondname = secondname;

        this.sex = sex;
        this.birthDate = birthDate;
        this.category = category;
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

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public boolean isNew(){
        return ID == null;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }


    @Override
    public String toString() {
        return "Driver{" +
                "fio='" + surname + " " + name + " "+ secondname + '\'' +
                ", sex=" + sex +
                ", birthDate=" + birthDate +
                ", category=" + category +
                ", ID=" + ID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Driver driver = (Driver) o;

        return ID.equals(driver.ID);

    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    public SEX getSex() {
        return sex;
    }

    public void setSex(SEX sex) {
        this.sex = sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
