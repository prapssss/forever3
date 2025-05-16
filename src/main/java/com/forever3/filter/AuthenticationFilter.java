package com.forever3.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.forever3.util.CookieUtil;
import com.forever3.util.SessionUtil;

@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    // Public pages
    private static final String LOGIN = "/login";
    private static final String REGISTER = "/register";
    private static final String HOME = "/home";
    private static final String ROOT = "/";
    private static final String SKINCARE = "/skincare";
    private static final String MAKEUP = "/makeup";
    private static final String ABOUTUS = "/aboutus";
    private static final String CONTACTUS = "/contactus";
    
    // User pages
    private static final String CART = "/cart";
    private static final String PROFILE = "/profile";
    private static final String LOGOUT = "/logout";
    
    // Admin pages
    private static final String DASHBOARD = "/dashboard";
    private static final String ORDERS = "/order";
    private static final String CUSTOMERS = "/customers";
    private static final String PRODUCTS = "/products";
    private static final String PRODUCT_MANAGEMENT = "/productmanagement";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Optional initialization
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        
        System.out.println("Requested URI: " + uri);

        if (uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".css") || uri.endsWith(".js")) {
            chain.doFilter(request, response);
            return;
        }

        // ✅ Always allow logout, regardless of login state
        if (uri.endsWith(LOGOUT)) {
            chain.doFilter(request, response);
            return;
        }

        boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;
        String userRole = CookieUtil.getCookie(req, "role") != null
                ? CookieUtil.getCookie(req, "role").getValue()
                : null;

        System.out.println("isLoggedIn: " + isLoggedIn + ", userRole: " + userRole);

        if (isLoggedIn && userRole != null) {
            if ("admin".equals(userRole)) {
                if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
                    res.sendRedirect(req.getContextPath() + DASHBOARD);
                } else if (isAdminPage(uri)) {
                    chain.doFilter(request, response);
                } else if (isPublicPage(uri)) {
                    chain.doFilter(request, response);
                } else if (isUserPage(uri)) {
                    res.sendRedirect(req.getContextPath() + DASHBOARD);
                } else {
                    res.sendRedirect(req.getContextPath() + DASHBOARD);
                }
            } else if ("customer".equals(userRole)) {
                if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
                    res.sendRedirect(req.getContextPath() + HOME);
                } else if (isAdminPage(uri)) {
                    res.sendRedirect(req.getContextPath() + HOME);
                } else {
                    chain.doFilter(request, response);
                }
            }
        } else {
            if (isPublicPage(uri)) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getContextPath() + LOGIN);
            }
        }
    }
    
    private boolean isAdminPage(String uri) {
        boolean result = uri.endsWith(DASHBOARD) || 
               uri.endsWith(ORDERS) || 
               uri.endsWith(CUSTOMERS) || 
               uri.endsWith(PRODUCTS) ||
               uri.endsWith(PRODUCT_MANAGEMENT);
        
        if (result) {
            System.out.println("Identified as admin page: " + uri);
        }
        
        return result;
    }

    private boolean isPublicPage(String uri) {
        return uri.endsWith(LOGIN) || 
               uri.endsWith(REGISTER) ||
               uri.endsWith(HOME) || 
               uri.endsWith(ROOT) || 
               uri.endsWith(SKINCARE) || 
               uri.endsWith(MAKEUP) ||
               uri.endsWith(ABOUTUS) || 
               uri.endsWith(CONTACTUS);
    }

    private boolean isUserPage(String uri) {
        return uri.endsWith(CART) || 
               uri.endsWith(PROFILE);
    }

    @Override
    public void destroy() {
        // cleanup
    }
}
