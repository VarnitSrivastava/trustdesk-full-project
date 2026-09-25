package com.airtribe.trustdesk.config;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;
import java.io.IOException;
@Component
public class DemoAuthFilter implements Filter {
    public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain)
            throws IOException,ServletException{HttpServletRequest r=(HttpServletRequest)req;HttpServletResponse p=(HttpServletResponse)res;String path=r.getRequestURI();
        if(path.startsWith("/api/")&&!"demo-token".equals(r.getHeader("X-Demo-Token"))){p.setStatus(401);p.setContentType("application/json");p.getWriter().write("{\"error\":\"Use X-Demo-Token: demo-token\"}");
            return;}chain.doFilter(req,res);} }
