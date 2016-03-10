package ru.pavel2107.vitasoft.service;

import ru.pavel2107.vitasoft.model.Driver;

import java.util.List;

/**
 * Created by admin on 08.03.2016.
 */
public interface DriverService {
    Driver save(Driver driver);
    void delete(Integer ID);
    Driver get( Integer ID);
    List<Driver> getByName(String fio);
    List<Driver> getAll();
}
