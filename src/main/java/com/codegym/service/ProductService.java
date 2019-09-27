package com.codegym.service;

import com.codegym.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> fillAll();

    void save(Product product);

    Product fillById(int id);

    void update(int id, Product product);

    void delete(int id);

    List<Product> fillByName(String name);
}
