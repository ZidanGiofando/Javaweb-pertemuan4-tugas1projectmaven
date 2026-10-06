package com.budiluhur.catalog;

import com.budiluhur.catalog.servlet.ProductServlet;
import com.budiluhur.catalog.servlet.CategoryServlet;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class App {

    public static void main(String[] args) throws Exception {

        System.out.println(
            "Menjalankan Embedded Tomcat Server..."
        );

        // Membuat Tomcat
        Tomcat tomcat = new Tomcat();

        // Menentukan port
        tomcat.setPort(8080);

        // Membuat connector
        tomcat.getConnector();

        // Membuat context
        String docBase = new File(".").getAbsolutePath();

        Context ctx = tomcat.addContext("", docBase);

        // Mendaftarkan servlet
        Tomcat.addServlet(
            ctx,
            "ProductServlet",
            new ProductServlet()
        );

        // Mapping URL
        ctx.addServletMappingDecoded(
            "/products",
            "ProductServlet"
        );

        Tomcat.addServlet(ctx, "CategoryServlet",
        new CategoryServlet());

        ctx.addServletMappingDecoded(
        "/categories", "CategoryServlet");

        System.out.println(
            "Server berhasil berjalan di:"
        );

        System.out.println(
            "http://localhost:8080/products"
        );

        // Menjalankan Tomcat
        tomcat.start();

        // Menjaga server tetap berjalan
        tomcat.getServer().await();
    }
}
