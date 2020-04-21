/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.login;

import dao.AccountDAO;
import entity.Account;
import entity.CustomerDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.AccountService;
import service.CustomerDetailService;
import util.Helper;
import util.Toast;

/**
 *
 * @author PC
 */
@WebServlet(name = "signin", urlPatterns = {"/signin"})
public class signin extends HttpServlet {

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
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            
            //get parameter for create
            AccountService acc = new AccountService();
            String email = request.getParameter("email");
            String pass = request.getParameter("pass");
            String name = new Helper().getName(request.getParameter("name"));
            String isGender = request.getParameter("gender");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            //get list notify
            HttpSession session = request.getSession();
            List<Toast> listToast = (List<Toast>) session.getAttribute("listToast");
            if (listToast == null) {//create new list
                listToast = new ArrayList<>();
            }
            if (acc.isDuplicate(email)) { 
                
                //check email- user name is exist yet
                listToast.add(Toast.builder().content("Email đã tồn tại").exist(1).build());
                request.setAttribute("duplicate", true);
                request.setAttribute("email", email);
                request.setAttribute("pass", pass);
                request.setAttribute("name", name);
                request.setAttribute("gender", isGender);
                request.setAttribute("phone", phone);
                request.setAttribute("address", address);
            } else {
                /* create account with detail 
                */
                //validate name
                name = new Helper().getName(name);
                String shortName = new Helper().getShortName(name);
                int gender = isGender.equals("male") ? 1 : 0;

                Account account = Account.builder().user(email).pass(pass).name(shortName).type(2).status(1).build();
                //insert account
                int accID = acc.addAccountReturnID(account);
                if (accID == -1) {
                    //insert account fail
                    listToast.add(Toast.builder().content("Lỗi sảy ra!! tạo tài khoản thất bại").exist(1).build());
                    request.setAttribute("mes", "Tạo tài khoản thất bại");
                    request.setAttribute("next", false);
                } else {
                    //onsert success
                    listToast.add(Toast.builder().content("Tạo tài khoản thành công").exist(1).build());
                    CustomerDetail cus = CustomerDetail.builder().accID(accID).email(email).gender(gender)
                            .mobile(phone).name(name).avatar("unknown.jpg").build();
                    new CustomerDetailService().add(cus);
                    request.setAttribute("mes", "Tạo tài khoản thành công");
                    request.setAttribute("next", true);
                }
            }
            session.setAttribute("listToast", listToast);
            request.getRequestDispatcher("signin.jsp").forward(request, response);
        }
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
        boolean isPost = (boolean) request.getSession().getAttribute("isPost");

        response.sendRedirect("signin.jsp");

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
        processRequest(request, response);
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
