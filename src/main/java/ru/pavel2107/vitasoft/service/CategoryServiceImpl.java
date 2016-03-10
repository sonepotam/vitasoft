package ru.pavel2107.vitasoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavel2107.vitasoft.model.Category;
import ru.pavel2107.vitasoft.repository.CategoryRepository;

import java.util.List;

/**
 * Created by admin on 08.03.2016.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository repository;


    @Override
    public Category save(Category category) {
        return repository.save( category);
    }

    @Override
    public Category get(String type) {
        return repository.get( type);
    }

    @Override
    public void delete(String type) {
       repository.delete( type);
    }

    @Override
    public List<Category> getAll() {
        return repository.getAll();
    }
}
