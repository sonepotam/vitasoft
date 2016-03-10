package ru.pavel2107.vitasoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavel2107.vitasoft.model.Driver;
import ru.pavel2107.vitasoft.model.Insurance;
import ru.pavel2107.vitasoft.repository.DriverRepository;
import ru.pavel2107.vitasoft.repository.InsuranceRepository;

import java.util.List;

/**
 * Created by admin on 08.03.2016.
 */
@Service
public class InsuranceServiceImpl implements InsuranceService {

    @Autowired
    InsuranceRepository repository;

    @Autowired
    DriverRepository driverRepository;


    @Override
    public Insurance save(Insurance insurance) {
        return repository.save( insurance);
    }

    @Override
    public Insurance get(Integer ID) {
        return repository.get( ID);
    }

    @Override
    public void delete(Integer ID) {
       repository.delete( ID);
    }

    @Override
    public List<Insurance> getAll() {
        return repository.getAll();
    }

    @Override
    public void deleteDriver(Integer ID, Integer driverID) {
        repository.deleteDriver( ID, driverID);
    }

    @Override
    public Driver updateDriver(Integer ID, Driver driver) {
        return repository.updateDriver( ID, driver);
    }
}
