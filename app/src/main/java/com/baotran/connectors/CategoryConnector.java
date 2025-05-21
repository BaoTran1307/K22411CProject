//package com.baotran.connectors;
//
//import com.baotran.models.Category;
//import com.baotran.models.ListCategory;
//
//import java.util.ArrayList;
//
//public class CategoryConnector {
//    ListCategory listCategory;
//
//    public CategoryConnector() {
//        listCategory = new ListCategory();
//        listCategory.generate_sample_dataset();
//    }
//
//    public ArrayList<Category> get_all_categories() {
//        if (listCategory == null) {
//            listCategory = new ListCategory();
//            listCategory.generate_sample_dataset();
//        }
//        return listCategory.getCategories();
//    }
//
//    public ArrayList<Category> get_categories_by_keyword(String keyword) {
//        if (listCategory == null) {
//            listCategory = new ListCategory();
//            listCategory.generate_sample_dataset();
//        }
//        ArrayList<Category> results = new ArrayList<>();
//        for (Category c : listCategory.getCategories()) {
//            if (c.getName().toLowerCase().contains(keyword.toLowerCase())) {
//                results.add(c);
//            }
//        }
//        return results;
//    }
//}
