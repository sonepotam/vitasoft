package ru.pavel2107.vitasoft.repository.mock;

import org.springframework.stereotype.Repository;
import ru.pavel2107.vitasoft.model.Category;
import ru.pavel2107.vitasoft.repository.CategoryRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by admin on 08.03.2016.
 */
@Repository
public class InMemoryCategoryRepositoryImpl implements CategoryRepository {

    private static Map<String, Category> repository = new ConcurrentHashMap<>();
    private static AtomicInteger counter = new AtomicInteger(0);

    {
        Category category = new Category( "A", "Категория A"); repository.putIfAbsent( category.getType(), category);
        category = new Category( "B", "Категория B"); repository.putIfAbsent( category.getType(), category);
        category = new Category( "C", "Категория C"); repository.putIfAbsent( category.getType(), category);
        category = new Category( "D", "Категория D"); repository.putIfAbsent( category.getType(), category);

    }

    @Override
    public Category save(Category category) {
       Category cat = repository.get( category.getType());
       if( cat == null){
           repository.putIfAbsent( category.getType(), category);
       }
        else {
           repository.replace( category.getType(), category);
       }
       return repository.get( category.getType());
    }

    @Override
    public Category get(String type) {
        return repository.get( type);
    }

    @Override
    public void delete(String type) {
       repository.remove( type);
    }

    @Override
    public List<Category> getAll() {
        return repository.values().stream().collect(Collectors.toList());
    }
}
