package com.example.demo.bootstrap;

import com.example.demo.model.Category;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Category> categories = new ArrayList<>();

    @PostConstruct
    public void init(){
        categories.add(new Category("Software","This is test desc for Software"));
        categories.add(new Category("Hardware","This is test desc for Hardware"));
        categories.add(new Category("Books","This is test desc for Books"));
    }

}
