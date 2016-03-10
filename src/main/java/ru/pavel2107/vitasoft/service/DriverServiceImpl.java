package ru.pavel2107.vitasoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavel2107.vitasoft.model.Driver;
import ru.pavel2107.vitasoft.repository.DriverRepository;

import java.util.List;

/**
 * Created by admin on 08.03.2016.
 */
@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepository repository;

    @Override
    public Driver save(Driver driver) {
        return repository.save( driver);
    }

    @Override
    public void delete(Integer ID) {
       repository.delete( ID);
    }

    @Override
    public Driver get(Integer ID) {
        return repository.get( ID);
    }

    @Override
    public List<Driver> getByName(String fio) {
        return repository.getByName( fio);
    }

    @Override
    public List<Driver> getAll() {
        return repository.getAll();
    }
}
