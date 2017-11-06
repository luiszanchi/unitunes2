/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unitunes.filter;

/**
 *
 * @author LuisFernandoTorriani
 */
import br.com.unitunes.session.SessionContext;
import br.com.unitunes.session.User;
import java.io.IOException;
import javax.faces.application.ResourceHandler;
   
  import javax.servlet.Filter;
  import javax.servlet.FilterChain;
  import javax.servlet.FilterConfig;
  import javax.servlet.ServletException;
  import javax.servlet.ServletRequest;
  import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
  import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
  import javax.servlet.http.HttpSession;
   
@WebFilter("/*")
  public class PageFilter implements Filter {
   private static final String AJAX_REDIRECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+ "<partial-response><redirect url=\"%s\"></redirect></partial-response>";

      public void destroy() {
         // TODO Auto-generated method stub
   
      }
   
      public void doFilter(ServletRequest req, ServletResponse res,
             FilterChain chain) throws IOException, ServletException {
         HttpServletRequest request = (HttpServletRequest) req;
         HttpServletResponse response = (HttpServletResponse) res;
         HttpSession session = request.getSession(false);
         String loginURL = request.getContextPath()+"/login.xhtml";
         
         Boolean loggedIn = (session != null) && (session.getAttribute("user") != null);
         Boolean loginRequest = request.getRequestURI().equals(loginURL);
         Boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER+"/");
         Boolean ajaxRequest = "partial/ajax".equals(request.getHeader("Faces-Request"));
         Boolean jsRequest = request.getRequestURI().toLowerCase().endsWith(".js");
         Boolean cssRequest = request.getRequestURI().toLowerCase().endsWith(".css");
         
         //System.out.println(request.getContextPath());
         //System.out.println(request.getRequestURI());
         
         String newCurrentPage = ((HttpServletRequest) request).getServletPath();
         if (loggedIn || loginRequest || resourceRequest || jsRequest || cssRequest) {
            User user = null;
            
            if (!resourceRequest) { // Prevent browser from caching restricted resources. See also https://stackoverflow.com/q/4194207/157882
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                response.setDateHeader("Expires", 0); // Proxies.
            }
            chain.doFilter(request, response); // So, just continue request.
        }
        else if (ajaxRequest) {
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().printf(AJAX_REDIRECT_XML, loginURL); // So, return special XML response instructing JSF ajax to send a redirect.
        }
        else {
            response.sendRedirect(loginURL); // So, just perform standard synchronous redirect.
        }
         
      }
   
      public void init(FilterConfig arg0) throws ServletException {
         // TODO Auto-generated method stub
   
      }
   
  }