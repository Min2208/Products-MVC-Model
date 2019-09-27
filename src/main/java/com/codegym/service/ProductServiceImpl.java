package com.codegym.service;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    private static Map<Integer, Product> products;

    static {
        products=new  HashMap<>();
        products.put(1,new Product(1,"Dell","Inpiron","550 USD"));
        products.put(2,new Product(2,"Assus","Vivo book","780 USD"));
        products.put(3,new Product(3,"Lenovo","ThinkPart","640 USD"));
        products.put(4,new Product(4,"Appe","Iphone 11","1000 USD"));
        products.put(5,new Product(5,"SamSung","Note 10","980 USD"));

    }


    @Override
    public List<Product> fillAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(),product);
    }

    @Override
    public Product fillById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id,product);
    }

    @Override
    public void delete(int id) {
        products.remove(id);
    }
    public List<Product> fillByName(String name){
      List<Product> product = new ArrayList<>(products.values());
      List<Product> product1=new ArrayList<>();
      for(Product p: product){
          if (name.equals(p.getName())){
              product1.add(p);
          }
      }
        return product1;
    }
}
