package com.budiluhur.catalog.repository;

import com.budiluhur.catalog.model.Product;
import com.budiluhur.catalog.exception.ProductNotFoundException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void save(Product product) {
        products.add(product);
    }

    public List<Product> findAll() {
        return products;
    }

    // Mencari produk berdasarkan ID
    public Product findById(String id)
            throws ProductNotFoundException {

        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }

        throw new ProductNotFoundException(
            "Produk dengan ID " + id + " tidak ditemukan."
        );
    }

    // Menghapus produk berdasarkan ID
    public boolean deleteById(String id)
            throws ProductNotFoundException {

        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();

            if (product.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }

        throw new ProductNotFoundException(
            "Produk dengan ID " + id + " tidak ditemukan."
        );
    }
}
