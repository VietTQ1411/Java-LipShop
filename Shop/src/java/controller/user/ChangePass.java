/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import dao.CustomerDetailDAO;
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
import util.Toast;

/**
 *
 * @author PC
 */
@WebServlet(name = "ChangePass", urlPatterns = {"/change-pass"})
public class ChangePass extends HttpServlet {

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
        HttpSession session = request.getSession();
        List<Toast> listToast = (List<Toast>) session.getAttribute("listToast");
        if (listToast == null) {
            listToast = new ArrayList<>();
        }
        listToast.add(Toast.builder().content("Lỗi sảy ra").exist(1).build());
        session.setAttribute("listToast", listToast);
        response.sendRedirect("home");
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String pass = request.getParameter("olderPass");
        String newPass = request.getParameter("newPass");

        String url = "user?page=1";

        HttpSession session = request.getSession();
        List<Toast> listToast = (List<Toast>) session.getAttribute("listToast");
        if (listToast == null) {
            listToast = new ArrayList<>();
        }
        Account acc = (Account) request.getSession().getAttribute("account");
        if (acc != null) {
            if (pass.trim().equals(acc.getPass().trim())) {
                boolean flag = new AccountService().ChangePass(acc.getId(), newPass);
                if (flag) {
                    listToast.add(Toast.builder().content("Đổi mật khẩu thành công").exist(1).build());
                } else {
                    listToast.add(Toast.builder().content("Đổi mật khẩu thất bại").exist(1).build());
                }
            } else {
                listToast.add(Toast.builder().content("Sai mật khẩu").exist(1).build());
            }

        } else {
            listToast.add(Toast.builder().content("Có lỗi sảy ra").exist(1).build());
            url = "home";
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
