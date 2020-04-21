/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import dao.CustomerDetailDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import entity.Account;
import entity.CustomerDetail;
import entity.Order;
import entity.OrderDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.OrderService;
import service.ReceiverService;
import util.Bill;

/**
 *
 * @author PC
 */
@WebServlet(name = "User", urlPatterns = {"/user"})
public class User extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Account acc = (Account) request.getSession().getAttribute("account");
            OrderService odao = new OrderService();
            OrderDetailDAO oddao = new OrderDetailDAO();
            ReceiverService recei = new ReceiverService();
            
            String page = request.getParameter("page");
                if (page == null) {
                    page = "1";
                }
            if (acc != null) {
                CustomerDetail cus = new CustomerDetailDAO().getOneByAccID(acc.getId());
                if (page.equals("2")) {
                    List<Order> list = new OrderDAO().getAllByCus(cus.getId(), false);
                    List<Bill> listBill = new ArrayList<>();
                    if (list == null) {
                        listBill = null;
                    } else {
                        for (Order order : list) {
                            listBill.add(Bill.builder().order(order).recei(recei.getOne(order.getReceiverID()))
                                    .list(oddao.getAllbyOrderID(order.getId())).build());
                        }
                    }

                    request.setAttribute("listBill", listBill);
                } else if (page.equals("3")) {
                    List<Order> list = new OrderDAO().getAllByCus(cus.getId(), true);
                    List<Bill> listBill = new ArrayList<>();
                    if (list == null) {
                        listBill = null;
                    } else {
                        for (Order order : list) {
                            listBill.add(Bill.builder().order(order).recei(recei.getOne(order.getReceiverID()))
                                    .list(oddao.getAllbyOrderID(order.getId())).build());
                        }
                    }
                    request.setAttribute("listBill", listBill);
                }
               
                request.setAttribute("customer", cus);
            } 
            request.setAttribute("page", page);
            request.getRequestDispatcher("customer.jsp").forward(request, response);
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
