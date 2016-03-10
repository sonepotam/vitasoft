package ru.pavel2107.vitasoft.repository;

import ru.pavel2107.vitasoft.model.Category;

import java.util.List;

/**
 * Created by admin on 08.03.2016.
 */
public interface CategoryRepository {
    Category save( Category category);
    Category get( String type);
    void delete( String type);
    List<Category> getAll();
}
