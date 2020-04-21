/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.login;

import dao.AccountDAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.Toast;

/**
 *
 * @author PC
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("home.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //get paramenter
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String remem = request.getParameter("remember");
        String url = "home";
        
        //get list notify
        HttpSession session = request.getSession();
        List<Toast> listToast = (List<Toast>) session.getAttribute("listToast");
        if (listToast == null) {//check list null
            listToast = new ArrayList<>();
        }
        //check data
        if (user != null && pass != null) {
            Account acc = new AccountDAO().login(user, pass);
            if (acc != null) {
                //notify that login success
                session.setAttribute("account", acc);
                listToast.add(Toast.builder().content("Đăng nhập thành công").exist(1).build());
                if (remem != null) {
                    
                    //user want to remember for the next time
                    Cookie cookieUser = new Cookie("user", user);
                    cookieUser.setMaxAge(24 * 3600);
                    Cookie cookiePassword = new Cookie("pass", pass);
                    cookiePassword.setMaxAge(24 * 3600);
                    response.addCookie(cookieUser);
                    response.addCookie(cookiePassword);
                }
            } else {
                //login false;
                listToast.add(Toast.builder().content("Sai tài khoản hoặc mật khẩu").exist(1).build());
                request.setAttribute("error", true);
                request.setAttribute("user", user);
                request.setAttribute("pass", pass);
                url = "login.jsp";
            }
        } else {
            //not inseart data
            listToast.add(Toast.builder().content("Sai tài khoản hoặc mật khẩu").exist(1).build());
            request.setAttribute("error", true);
            request.setAttribute("user", user);
            request.setAttribute("pass", pass);
            url = "login.jsp";
        }

        session.setAttribute("listToast", listToast);
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
