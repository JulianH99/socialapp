/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import database.MySQLUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lib.Validator;
import models.User;
import models.managers.UserManager;

/**
 *
 * @author julian
 */
public class LoginController extends HttpServlet {
   
    
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        request.getRequestDispatcher("pages/auth/login.jsp").forward(request, response);
        
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password").trim();
        System.out.println("Logging in");
        
        boolean validUsername = Validator.applyRule(username, "string");
        
        if(!validUsername) {
            request.getSession()
                    .setAttribute("username-error", "Namae invalido we");
        }
        
        
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        
        UserManager userManager = new UserManager(new MySQLUtil());
        
        if(userManager.login(user)) {
            response.getWriter().write("Hola");
            request.getRequestDispatcher("pages/user/home.jsp")
                    .forward(request, response);
        }else{
            request.getSession().setAttribute("error", "Usuario o "
                    + "contraseña erroneos");
            response.getWriter().write("Datos erroneos");
        }
        
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
