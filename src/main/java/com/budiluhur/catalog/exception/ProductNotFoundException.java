package com.budiluhur.catalog.exception;

// Kelas ini mewarisi Exception (Checked Exception)
public class ProductNotFoundException extends Exception {
    
    // Constructor untuk mengirim pesan error kustom
    public ProductNotFoundException(String message) {
        super(message);
    }
}
