package ru.pavel2107.vitasoft.service;

import ru.pavel2107.vitasoft.model.Category;

import java.util.List;

/**
 * Created by admin on 08.03.2016.
 */
public interface CategoryService {
    Category save(Category category);
    Category get( String type);
    void delete( String type);
    List<Category> getAll();
}
