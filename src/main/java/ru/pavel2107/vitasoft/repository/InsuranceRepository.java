package ru.pavel2107.vitasoft.repository;

import ru.pavel2107.vitasoft.model.Driver;
import ru.pavel2107.vitasoft.model.Insurance;

import java.util.List;

/**
 * Created by admin on 07.03.2016.
 */
public interface InsuranceRepository {
    Insurance save( Insurance insurance);
    Insurance get( Integer ID);
    void delete( Integer ID);
    List<Insurance> getAll();

    void deleteDriver( Integer ID, Integer driverID);
    Driver updateDriver(Integer ID, Driver driver);

}
