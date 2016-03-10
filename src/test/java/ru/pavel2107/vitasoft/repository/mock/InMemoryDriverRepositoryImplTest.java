package ru.pavel2107.vitasoft.repository.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.pavel2107.vitasoft.model.Driver;
import ru.pavel2107.vitasoft.repository.DriverRepository;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by admin on 08.03.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-db.xml"
})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class InMemoryDriverRepositoryImplTest {

    @Autowired
    DriverRepository repository;

    @Test
    public void testSave() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {
    }

    @Test
    public void testGet() throws Exception {

        System.out.println( repository.get(1));
    }

    @Test
    public void testGetByName() throws Exception {
        List<Driver> driverList = repository.getByName( "петров");
        System.out.println( driverList);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Driver> driverList = repository.getAll();
        System.out.println( driverList);
    }
}