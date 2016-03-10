package ru.pavel2107.vitasoft.repository.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;
import ru.pavel2107.vitasoft.model.Driver;
import ru.pavel2107.vitasoft.model.Insurance;
import ru.pavel2107.vitasoft.repository.DriverRepository;
import ru.pavel2107.vitasoft.repository.InsuranceRepository;

import javax.servlet.ServletConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by admin on 08.03.2016.
 */
@Repository
public class InMemoryInsuranceRepositoryImpl implements InsuranceRepository {


    private static Map<Integer, Insurance> repository = new ConcurrentHashMap<>();
    private static AtomicInteger counter = new AtomicInteger(0);

    @Autowired
    DriverRepository driverRepository;


    @Override
    public Insurance save(Insurance insurance) {
        fillMap();
        Integer insuranceID = insurance.getID();
        if( insurance.isNew()){
            insuranceID = counter.incrementAndGet();
            insurance.setID( insuranceID);
            insurance = repository.putIfAbsent( insuranceID, insurance);
        } else {
            insurance = repository.replace( insuranceID, insurance);
        }
        return insurance;
    }


    @Override
    public Insurance get(Integer ID) {
        fillMap();
        return repository.get( ID);
    }




    @Override
    public void delete(Integer ID) {
       fillMap();
       repository.remove( ID);
    }

    @Override
    public List<Insurance> getAll() {

       fillMap();
       return repository
                .values()
                .stream()
                .collect(Collectors.toList());
    }


    private void fillMap(){
        if( repository.size() >0) return;
        Insurance insurance = new Insurance( counter.incrementAndGet());
        insurance.getDriverList().add( driverRepository.get(1));
        insurance.getDriverList().add( driverRepository.get(2));
        repository.putIfAbsent( insurance.getID(), insurance);

        insurance = new Insurance( counter.incrementAndGet());
        insurance.getDriverList().add( driverRepository.get(0));
        insurance.getDriverList().add( driverRepository.get(2));
        insurance.getDriverList().add( driverRepository.get(3));
        repository.putIfAbsent( insurance.getID(), insurance);
    }

    @Override
    public void deleteDriver(Integer ID, Integer driverID) {
        fillMap();
        Insurance insurance = repository.get( ID);
        Set<Driver> driverList = insurance.getDriverList();
        Driver deletedDriver = driverList.stream().filter(new Predicate<Driver>() {
            @Override
            public boolean test(Driver driver) {
                return driver.getID().compareTo( driverID) == 0;
            }
        }).findFirst().get();

        if( deletedDriver != null) {
            driverList.remove(deletedDriver);
            save( insurance);
        }
    }

    @Override
    public Driver updateDriver(Integer ID, Driver driver) {
        fillMap();
        Insurance insurance = repository.get( ID);
        Set<Driver> driverList = insurance.getDriverList();
        driverList.remove( driver);
        driverList.add( driver);
        save( insurance);
        //TODO: исправить данные о водителе в справочнике водителей !!!
        return driver;
    }
}
