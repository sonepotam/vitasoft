package ru.pavel2107.vitasoft.model;

/**
 * Created by admin on 07.03.2016.
 */
public class Category {
    private String type;
    private String name;

    public Category() {
    }

    public Category(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (!type.equals(category.type)) return false;
        return name.equals(category.name);

    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }


    @Override
    public String toString() {
        return "Category{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
