package com.filters;

import com.mb.UserMB;
import com.model.User;

import javax.faces.application.ResourceHandler;
import javax.faces.context.FacesContext;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by Gruby on 24.04.2017.
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //It will not create new session
        HttpSession session = request.getSession(false);
        boolean loggedIn = (session != null) && (session.getAttribute("user") != null);
        if (loggedIn) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("/pages/public/login.xhtml");
        }
    }

    @Override
    public void destroy() {

    }
}
