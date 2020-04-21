/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dao.BrandDAO;
import dao.CustomerDetailDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import entity.Account;
import entity.Brand;
import entity.CustomerDetail;
import entity.Order;
import entity.Product;
import entity.Type;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.CustomerDetailService;
import service.OrderService;
import service.ProductService;
import service.ReceiverService;
import service.TypeService;
import util.Bill;

/**
 *
 * @author PC
 */
@WebServlet(name = "Admin", urlPatterns = {"/admin"})
public class Admin extends HttpServlet {

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
            CustomerDetailService cds = new CustomerDetailService();
            ReceiverService recei = new ReceiverService();
            if (acc == null) {
                response.sendRedirect("home");
            } else {
                String page = request.getParameter("page");
                if (page == null) {
                    page = "1";
                }
                if (page.equals("2")) {
                    List<Type> listType = new TypeService().getAll();
                    List<Brand> listBrand = new BrandDAO().getAll();
                    List<Product> listProduct = new ProductService().getAll();
                    request.setAttribute("listType", listType);
                    request.setAttribute("listBrand", listBrand);
                    request.setAttribute("listProduct", listProduct);
                } else if (page.equals("3")) {
                    List<Order> list = new OrderDAO().getAll();
                    List<Bill> listBill = new ArrayList<>();
                    if (list == null || list.isEmpty()) {
                        listBill = null;
                    } else {
                        for (Order order : list) {
                            listBill.add(Bill.builder().order(order)
                                    .recei(recei.getOne(order.getReceiverID()))
                                    .customer(cds.getOne(order.getCustomerID()))
                                    .list(oddao.getAllbyOrderID(order.getId())).build());
                        }
                    }
                    request.setAttribute("listBill", listBill);
                }
                request.setAttribute("page", page);
            }
            request.getRequestDispatcher("admin.jsp").forward(request, response);
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
