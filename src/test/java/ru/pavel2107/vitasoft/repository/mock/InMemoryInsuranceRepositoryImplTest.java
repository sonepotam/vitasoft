package ru.pavel2107.vitasoft.repository.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.pavel2107.vitasoft.model.Driver;
import ru.pavel2107.vitasoft.model.Insurance;
import ru.pavel2107.vitasoft.repository.DriverRepository;
import ru.pavel2107.vitasoft.repository.InsuranceRepository;

import java.util.List;

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
public class InMemoryInsuranceRepositoryImplTest {

    @Autowired
    InsuranceRepository repository;

    @Test
    public void testSave() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

        System.out.println( repository.get(1));
    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {
        System.out.println( repository.getAll());
    }


}