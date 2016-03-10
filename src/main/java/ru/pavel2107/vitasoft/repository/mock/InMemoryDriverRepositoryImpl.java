package ru.pavel2107.vitasoft.repository.mock;

import org.springframework.stereotype.Repository;
import ru.pavel2107.vitasoft.model.Category;
import ru.pavel2107.vitasoft.model.Driver;
import ru.pavel2107.vitasoft.model.SEX;
import ru.pavel2107.vitasoft.repository.DriverRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by admin on 07.03.2016.
 */
@Repository
public class InMemoryDriverRepositoryImpl implements DriverRepository {


    private static  Map<Integer, Driver> repository = new ConcurrentHashMap();
    private static AtomicInteger counter = new AtomicInteger(0);


    {
        Category categA = new Category( "A", "Категория A");
        Category categB = new Category( "B", "Категория B");
        Category categC = new Category( "C", "Категория C");
        Category categD = new Category( "D", "Категория D");



        save( new Driver( "Сергеев", "Петр", "Сергеевич",      SEX.MALE,   LocalDate.of(1990, Month.MAY, 30), categA));
        save( new Driver( "Сергееа", "Анна", "Васильевна",     SEX.FEMALE, LocalDate.of(1992, Month.AUGUST, 12), categB));
        save( new Driver( "Петров", "Николай", "Валентинович", SEX.MALE,   LocalDate.of(1980, Month.SEPTEMBER, 2), categD));

        save( new Driver( "Петрова", "Ольга", "Николаевна",    SEX.FEMALE, LocalDate.of(1950, Month.SEPTEMBER, 24), categB));
        save( new Driver( "Львов", "Степан", "Степанович",     SEX.MALE,   LocalDate.of(1970, Month.DECEMBER,  22), categB));
        save( new Driver( "Львова", "Татьяна", "Степановна",   SEX.FEMALE, LocalDate.of(1960, Month.OCTOBER,   12), categC));
    }


    @Override
    public Driver save(Driver driver) {
        Integer driverID = driver.getID();
        if( driver.isNew() ){
            driverID = counter.incrementAndGet();
            driver.setID( driverID);
            driver = repository.putIfAbsent( driverID, driver);
        }
        else {
            driver = repository.replace( driverID, driver);
        }
        return driver;
    }

    @Override
    public void delete(Integer ID) {
        repository.remove( ID);
    }

    @Override
    public Driver get(Integer ID) {
        return repository.get( ID);
    }

    @Override
    public List<Driver> getByName(String fio) {
        SimpleDateFormat sdf = new SimpleDateFormat( "dd.MM.yyyy");
        return repository
                .values()
                .stream()
                .filter(driver -> (
                        String.format(driver.getSurname() + " " + driver.getName() + " " + driver.getSecondname() + " - " +
                                sdf.format( driver.getBirthDate()) )

                ).toLowerCase().startsWith( fio.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Driver> getAll() {
        return repository
                .values()
                .stream()
                .collect(Collectors.toList());
    }
}
