package com.budiluhur.catalog.servlet;

import com.budiluhur.catalog.model.Category;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {

    private List<Category> categories;

    @Override
    public void init() {
        categories = new ArrayList<>();

        categories.add(new Category("CAT-01", "Elektronik"));
        categories.add(new Category("CAT-02", "Aksesoris"));
        categories.add(new Category("CAT-03", "Komputer"));
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Daftar Kategori</title>");
        out.println("</head>");

        out.println("<body>");

        out.println("<h2>=== DAFTAR KATEGORI ===</h2>");

        out.println("<ul>");

        for (Category category : categories) {
            out.println("<li>");
            out.println(category.getId() + " - " + category.getName());
            out.println("</li>");
        }

        out.println("</ul>");

        out.println("</body>");
        out.println("</html>");
    }
}
