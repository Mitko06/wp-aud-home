package com.example.demo.repository;

import com.example.demo.bootstrap.DataHolder;
import com.example.demo.model.Category;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryCategoryRepository {

    //listanje
    public List<Category> findAll(){
        return DataHolder.categories;
    }

    public Category saveCategory(Category category){
        if (category!=null && !category.getName().isEmpty() && category.getName()!=null)
        {
            DataHolder.categories.removeIf(r->r.getName().equals(category.getName()));
            DataHolder.categories.add(category);
        }
        else
        {
            return null;
        }
        return category;
    }
    public Optional findByName(String categoryName){
        return DataHolder.categories.stream().filter(r->r.getName().equals(categoryName)).findFirst();
    }
    public List<Category> search(String stringQuery){
        return DataHolder.categories.stream().filter(r->r.getName().contains(stringQuery) || r.getDescription().contains(stringQuery)).collect(Collectors.toList());
    }
    public void delete(String name){
        if (name!=null)
        {
            DataHolder.categories.removeIf(r->r.getName().equals(name));
        }
    }



    //azuriranje
    //kreiranje
    //brishanje



}
