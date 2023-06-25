package com.witalis.jkit.usage.core.invoke.section.servlet.content;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Desc: HTTP servlet
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class LifeCycleServlet extends HttpServlet {
    private static final Object lock = new Object();
    private Integer sharedCounter;

    @Override
    public String getServletInfo() {
        return "HTTP Servlet [lifecycle]: " + this.hashCode();
    }

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        getServletContext().log("init() called");
        sharedCounter = 0;
    }

    @Override
    protected void service(
        final HttpServletRequest request,
        final HttpServletResponse response
    ) throws ServletException, IOException {
        getServletContext().log("service() called");
        int localCounter;
        synchronized (lock) {
            sharedCounter++;
            localCounter = sharedCounter;
        }
        doPost(request, response);
        response.getWriter().write("Incrementing the count to " + localCounter);
    }

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        getServletContext().log("get() called");
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        getServletContext().log("post() called");
        doGet(request, response);
    }

    @Override
    public void destroy() {
        getServletContext().log("destroy() called");
    }
}
