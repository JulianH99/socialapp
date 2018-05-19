/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import database.MySQLUtil;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Post;
import models.User;
import models.managers.PostManager;

/**
 *
 * @author julian
 */
public class HomeController extends HttpServlet {
   
    
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
        //TODO: implement a valid way to handle user data
        
        System.out.println("Now we are in home controller");
        
        User user = (User) request.getSession().getAttribute("user");
        
        PostManager postManager = new PostManager(new MySQLUtil());
        
        ArrayList<Post> posts = (ArrayList) postManager.all();
        
        System.out.println(posts.size());
        
        request.getSession().setAttribute("user", user);
        
        
        
        request.getSession().setAttribute("posts", posts);
        
        request.getRequestDispatcher("pages/user/home.jsp")
                .forward(request, response);
        
        
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
