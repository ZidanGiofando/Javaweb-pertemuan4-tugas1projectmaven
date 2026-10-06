package com.budiluhur.catalog.servlet;

import com.budiluhur.catalog.model.Product;
import com.budiluhur.catalog.repository.ProductRepository;
import com.budiluhur.catalog.exception.ProductNotFoundException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() {

        // Inisialisasi Repository
        productRepository = new ProductRepository();

        // Data awal produk
        productRepository.addProduct(
            new Product(
                "PRD-01",
                "Keyboard Mechanical",
                450000.0
            )
        );

        productRepository.addProduct(
            new Product(
                "PRD-02",
                "Mouse Wireless Silent",
                175000.0
            )
        );

        productRepository.addProduct(
            new Product(
                "PRD-03",
                "Monitor Gaming 24 Inch",
                2100000.0
            )
        );
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        // 1. Ambil query parameter id
        String id = request.getParameter("id");

        // Jika ID diberikan
        if (id != null && !id.trim().isEmpty()) {

            try {
                // 2. Cari produk berdasarkan ID
                Product product = productRepository.findById(id);

                // Tampilkan detail produk
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<title>Detail Produk</title>");
                out.println("</head>");

                out.println("<body>");

                out.println("<h2>DETAIL PRODUK</h2>");

                out.println("<table border='1' cellpadding='8'>");

                out.println("<tr>");
                out.println("<th>ID Produk</th>");
                out.println("<td>" + product.getId() + "</td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<th>Nama Produk</th>");
                out.println("<td>" + product.getName() + "</td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<th>Harga</th>");
                out.println("<td>Rp " + product.getPrice() + "</td>");
                out.println("</tr>");

                out.println("</table>");

                out.println("</body>");
                out.println("</html>");

            } catch (ProductNotFoundException e) {

                // 3. Produk tidak ditemukan -> HTTP 404
                response.setStatus(
                    HttpServletResponse.SC_NOT_FOUND
                );

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<title>Produk Tidak Ditemukan</title>");
                out.println("</head>");

                out.println("<body>");

                out.println(
                    "<h2 style='color:red;'>"
                    + "Error 404 - Produk Tidak Ditemukan"
                    + "</h2>"
                );

                out.println(
                    "<p style='color:red;'>"
                    + e.getMessage()
                    + "</p>"
                );

                out.println("</body>");
                out.println("</html>");
            }

        } else {

            // Jika tidak ada parameter id,
            // tampilkan semua produk
            List<Product> products =
                productRepository.findAll();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Katalog Produk Web</title>");
            out.println("</head>");

            out.println("<body>");

            out.println(
                "<h2>DAFTAR KATALOG PRODUK (WEB)</h2>"
            );

            out.println(
                "<table border='1' cellpadding='8'>"
            );

            out.println("<tr>");
            out.println("<th>ID Produk</th>");
            out.println("<th>Nama Produk</th>");
            out.println("<th>Harga Satuan</th>");
            out.println("</tr>");

            for (Product p : products) {

                out.println("<tr>");

                out.println(
                    "<td>" + p.getId() + "</td>"
                );

                out.println(
                    "<td>" + p.getName() + "</td>"
                );

                out.println(
                    "<td>Rp " + p.getPrice() + "</td>"
                );

                out.println("</tr>");
            }

            out.println("</table>");

            out.println("</body>");
            out.println("</html>");
        }
    }
}
