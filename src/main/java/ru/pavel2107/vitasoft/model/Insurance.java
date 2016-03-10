package ru.pavel2107.vitasoft.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 07.03.2016.
 */
public class Insurance {
    private Integer ID;

    private Set<Driver> driverList;

    public Insurance(Integer ID) {
        this.ID = ID;
        driverList = new HashSet<Driver>();
    }

    public Insurance() {
      driverList = new HashSet<>();
    }


    public Integer getID() {
        return ID;
    }

    public Set<Driver> getDriverList() {
        return driverList;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public boolean isNew(){ return  ID == null;}

    public void setDriverList( Set<Driver> driverList) {
        this.driverList = driverList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Insurance insurance = (Insurance) o;

        return ID.equals(insurance.ID);

    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "ID=" + ID +
                ", driverList=" + driverList +
                '}';
    }
}
