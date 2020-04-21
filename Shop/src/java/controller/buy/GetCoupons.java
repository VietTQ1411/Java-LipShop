/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.buy;

import entity.Account;
import entity.Coupons;
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
import service.CouponsService;
import service.CustomerDetailService;
import util.Toast;

/**
 *
 * @author PC
 */
@WebServlet(name = "Coupons", urlPatterns = {"/coupon"})
public class GetCoupons extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String couponsCode = request.getParameter("coupons");
            List<Toast> listToast = (List<Toast>) session.getAttribute("listToast");

            //check list
            if (listToast == null) {
                listToast = new ArrayList<>();
            }
            // get coupons code
            if (couponsCode != null && !couponsCode.isEmpty()) {
                Coupons coupons = new CouponsService().getOneByCode(couponsCode);
                if (coupons != null) {//check coupons is exist or adready to use
                    request.setAttribute("coupons", coupons);
                } else { //coupon not exist
                    listToast.add(Toast.builder().content("Mã giảm giá không tồn tại hoặc đã bị xóa").exist(1).build());
                    request.setAttribute("couponsError", "Mã giảm giá không hợp lệ!!");
                }

                Account account = (Account) session.getAttribute("account");
                CustomerDetail cus = new CustomerDetailService().getOneByAccID(account.getId());
                if (cus != null) {
                    request.setAttribute("customer", cus);
                }
            } else {
                listToast.add(Toast.builder().content("Mã giảm giá không tồn tại hoặc đã bị xóa").exist(1).build());
                request.setAttribute("couponsError", "Mã giảm giá không hợp lệ!!");
            }
            session.setAttribute("listToast", listToast);
            request.getRequestDispatcher("buy.jsp").forward(request, response);
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
        processRequest(request, response);
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
