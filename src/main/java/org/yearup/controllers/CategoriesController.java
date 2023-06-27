package org.yearup.controllers;

import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PrePostAnnotationSecurityMetadataSource;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class CategoriesController
{
    private CategoryDao categoryDao;
    private ProductDao productDao;


    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao){
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable int id) {
        return categoryDao.getById(id);
    }

    // the url to return all products in category 1 would look like this
    // https://localhost:8080/categories/1/products
    @GetMapping("{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId)
    {
        // get a list of product by categoryId
        return null;
    }

    @PostMapping
    @ResponseStatus(value= HttpStatus.CREATED)
    public Category createCat(@RequestBody Category cat){
        return categoryDao.create(cat);
    }

    @PutMapping("/{id}")
    public HashMap<String, String> updateCat(@PathVariable int id, @RequestBody Category cat){
        categoryDao.update(id, cat);

        HashMap<String, String> response = new HashMap<>();
        response.put("status", "Successful");
        response.put("message", "Cat updated successfully");
        return response;
    }


    @DeleteMapping("/{id}")
    public HashMap<String, String> deleteCat(@PathVariable int id){
        categoryDao.delete(id);
        HashMap<String, String> response = new HashMap<>();
        response.put("status", "Successful");
        response.put("message", "Cat deleted successfully");
        return response;
    }
}
