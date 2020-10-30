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

@WebServlet(name="test-category-servlet", urlPatterns = "/test-servlet/category")
public class TestCategoryServlet extends HttpServlet {

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

    List<Category> categories = null;

    @Override
    public void init() throws ServletException {
        super.init();
        categories = new ArrayList<>();
        categories.add(new Category("Test1"));
        categories.add(new Category("Test2"));
        categories.add(new Category("Test3"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String IP = req.getRemoteHost();

        out.write("<html>");
        out.write("<head>");
        out.write("</head>");
        out.write("<body>");
        out.write("<div>IP address for the user: "+IP+"</div>");
        out.write("<h3>This is Test Category Servlet</h3>");
        out.write("<ul>");
        categories.stream().forEach(r->out.write("<li>"+r.getName()+"</li>"));
        out.write("</ul>");

        out.write("<form method='POST' action='/test-servlet/category'>");
        out.write("<label for='name'>Insert Category Name:</label>");
        out.write("<input type='text' name='name'>");
        out.write("<input type='submit' value='Submit'>");
        out.write("</form>");

        out.write("</body>");
        out.write("</html>");

        out.flush();

    }

    public void addCategory(String name){
        if (name!=null && !name.isEmpty()){
            categories.add(new Category(name));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cat_name=req.getParameter("name");
        addCategory(cat_name);
        resp.sendRedirect("/test-servlet/category");
    }
}
