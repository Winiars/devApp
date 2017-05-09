package com.filters;


import com.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Gruby on 24.04.2017.
 */
public class AdminFilter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //It will not create new session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        boolean isLoggedAsAdmin = user.isAdmin();
        if (isLoggedAsAdmin) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("/pages/public/accessDenied.xhtml");
        }
    }

    @Override
    public void destroy() {

    }
}
