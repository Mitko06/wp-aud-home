package com.example.demo.web.servlet;

import com.example.demo.repository.InMemoryCategoryRepository;
import com.example.demo.service.CategoryService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="tymeleaf-category-servlet",urlPatterns="/servlet/tyhmeleaf/category")
public class TymeLeafCategoryServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final CategoryService categoryService;

    public TymeLeafCategoryServlet(SpringTemplateEngine springTemplateEngine, CategoryService categoryService) {
        this.springTemplateEngine = springTemplateEngine;
        this.categoryService = categoryService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());

        context.setVariable("ipAddress",req.getRemoteAddr());
        context.setVariable("userAgent",req.getHeader("user-agent"));
        context.setVariable("categories",this.categoryService.listCategories());

        this.springTemplateEngine.process("categories.html", context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        categoryService.create(name,description);
        resp.sendRedirect("/servlet/tyhmeleaf/category");
    }
}
