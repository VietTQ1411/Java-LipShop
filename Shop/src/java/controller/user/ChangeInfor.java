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
import service.CustomerDetailService;
import util.Toast;

/**
 *
 * @author PC
 */
@WebServlet(name = "ChangeInfor", urlPatterns = {"/changeInfor"})
public class ChangeInfor extends HttpServlet {

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
        listToast.add(Toast.builder().content("Lỗi sảy ra").exist(0).build());
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
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");

        String url = "user?page=1";

        HttpSession session = request.getSession();
        List<Toast> listToast = (List<Toast>) session.getAttribute("listToast");
        if (listToast == null) {
            listToast = new ArrayList<>();
        }
        Account acc = (Account) request.getSession().getAttribute("account");
        if (acc != null) {
            CustomerDetail cus = new CustomerDetailDAO().getOneByAccID(acc.getId());
            boolean flag = new CustomerDetailService().update(cus.getId(), CustomerDetail.builder().name(name)
                    .mobile(phone).gender(gender.equals("male") ? 1 : 0).address(address).build());
            if (flag) {
                listToast.add(Toast.builder().content("Đổi thông tin thành công").exist(0).build());
            } else {
                listToast.add(Toast.builder().content("Đổi thông tin thất bại").exist(0).build());
            }
        } else {
            listToast.add(Toast.builder().content("Có lỗi sảy ra").exist(0).build());
            url = "home";
        }
        session.setAttribute("listToast", listToast);
        response.sendRedirect("user");

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
