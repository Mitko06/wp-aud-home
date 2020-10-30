package com.example.demo.web.servlet;

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

    class Category{
        private String name;

        public Category(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private List<Category> categoryList = null;

    @Override
    public void init() throws ServletException {
        super.init();
        this.categoryList=new ArrayList<>();
        this.categoryList.add(new Category("Software"));
        this.categoryList.add(new Category("Hardware"));
        this.categoryList.add(new Category("Books"));
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
        this.categoryList.stream().forEach(r->
                out.write("<li>"+r.getName()+"</li>")
        );
        out.write("</ul>");
        out.write("<form method='POST' action='/servlet/category'>");
        out.write("<label for='name'>Insert Category Name:</label>");
        out.write("<input type='text' name='name'>");
        out.write("<input type='submit' value='Submit'>");
        out.write("</form>");
        out.write("</body>");
        out.write("</html>");
    }

    public void addCategory(String categoryName){
        if (categoryName!=null && !categoryName.isEmpty())
        {
            categoryList.add(new Category(categoryName));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("name");
        addCategory(categoryName);
        resp.sendRedirect("/servlet/category");
    }
}
