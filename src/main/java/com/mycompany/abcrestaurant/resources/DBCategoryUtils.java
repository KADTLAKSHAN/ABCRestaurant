package com.mycompany.abcrestaurant.resources;

import java.util.List;

/**
 *
 * @author tharindulakshan
 */
public interface DBCategoryUtils {
    
    public boolean addCategory(Category category);
    
    public boolean updateCategory(Category category);
    
    public boolean deleteCategory(String categoryID);
    
    public Category searchCategory(String categoryID);
    
    public List<Category> getAllCategories();
    
}
