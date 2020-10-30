package com.example.demo.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="webservlethelloworld",urlPatterns = "/test")
public class TestWorldServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        PrintWriter out = resp.getWriter();
        if (username!=null){
            out.write("<html><head></head><body>Hello World!"+username+"</body></html>");
        }
        else
        {
            out.write("<html><head></head><body>Hello World!</body></html>");
        }
        out.flush();


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
