package com.witalis.jkit.usage.core.invoke.section.servlet.content;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Desc: Generic servlet
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class SimpleServlet extends GenericServlet {

    @Override
    public String getServletInfo() {
        return "Generic Servlet [simple]: " + this.hashCode();
    }

    @Override
    public void service(
        ServletRequest req,
        ServletResponse res
    ) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter writer = res.getWriter();
        writer.println("<B>It's a simple servlet.");
        writer.close();
    }
}
