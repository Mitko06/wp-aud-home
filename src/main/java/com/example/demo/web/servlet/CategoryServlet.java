package com.example.demo.web.servlet;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "category-Servlet", urlPatterns = "/servlet/category")
public class CategoryServlet extends HttpServlet {

    private final CategoryService categoryService;

    public CategoryServlet(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.write("<html>");
        out.write("<head>");
        out.write("</head>");
        out.write("<body>");
        out.write("IP address of a client: "+req.getRemoteAddr());
        out.write("<h3>Category List</h3>");
        out.write("<ul>");
        this.categoryService.listCategories().stream().forEach(r->
                out.write("<li>"+r.getName()+" ("+r.getDescription()+")</li>")
        );
        out.write("</ul>");
        out.write("<form method='POST' action='/servlet/category'>");
        out.write("<label for='name'>Insert Category Name:</label>");
        out.write("<input type='text' name='name'>");
        out.write("<input type='text' name='description'>");
        out.write("<input type='submit' value='Submit'>");
        out.write("</form>");
        out.write("</body>");
        out.write("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("name");
        String categoryDescription = req.getParameter("description");
        categoryService.create(categoryName,categoryDescription);
        resp.sendRedirect("/servlet/category");
    }
}
