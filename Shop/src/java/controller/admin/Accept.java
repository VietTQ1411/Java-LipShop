/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

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
import service.OrderService;
import util.Toast;

/**
 *
 * @author PC
 */
@WebServlet(name = "Accept", urlPatterns = {"/accept"})
public class Accept extends HttpServlet {

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
            String type = request.getParameter("type");
            HttpSession session = request.getSession();

            List<Toast> listToast = (List<Toast>) session.getAttribute("listToast");
            if (listToast == null) {
                listToast = new ArrayList<>();
            }
            //check đi vào tuef trang admin or user
            if (type != null) {
                switch (type) {
                    case "user":
                        //get page of local user page
                        String page = request.getParameter("page");
                        if (page == null) {
                            listToast.add(Toast.builder().content("Lỗi xảy ra").exist(0).build());
                            response.sendRedirect("home");
                        } else {
                            //full of value para
                            //change status with admin check order for customer
                            String orderID = request.getParameter("orderID");
                            //check order
                            if (orderID != null) {
                                boolean flag = new OrderService().changeOrder(orderID, 2);
                                //notify admin
                                if (flag) {
                                    listToast.add(Toast.builder().content("Đơn hàng #" + orderID + " sẽ được ship trong vài ngày!!Cảm ơn quý khách").exist(0).build());
                                } else {
                                    listToast.add(Toast.builder().content("Xác nhận đơn hàng #" + orderID + " thất bại").exist(0).build());
                                }
                                //chuyển qua trang admin
                                session.setAttribute("listToast", listToast);
                                response.sendRedirect("user?page="+page);
                            } else {
                                //không có order
                                listToast.add(Toast.builder().content("Lỗi xảy ra").exist(0).build());
                                session.setAttribute("listToast", listToast);
                                response.sendRedirect("home");
                            }
                        }
                        break;
                    case "admin":
                        //change status with admin check order for customer
                        String orderID = request.getParameter("orderID");
                        //check order
                        if (orderID != null) {
                            boolean flag = new OrderService().changeOrder(orderID, 3);
                            //notify admin
                            if (flag) {
                                listToast.add(Toast.builder().content("Xác nhận đơn hàng #" + orderID + " thành công").exist(0).build());
                            } else {
                                listToast.add(Toast.builder().content("Xác nhận đơn hàng #" + orderID + " thất bại").exist(0).build());
                            }
                            //chuyển qua trang admin
                            session.setAttribute("listToast", listToast);
                            response.sendRedirect("admin?page=3");
                        } else {
                            //không có order
                            listToast.add(Toast.builder().content("Lỗi xảy ra").exist(0).build());
                            session.setAttribute("listToast", listToast);
                            response.sendRedirect("home");
                        }
                        break;
                    default:
                        //ko có loại trang
                        listToast.add(Toast.builder().content("Lỗi xảy ra").exist(0).build());
                        session.setAttribute("listToast", listToast);
                        response.sendRedirect("home");
                        break;
                }
            } else {
                listToast.add(Toast.builder().content("Lỗi xảy ra").exist(0).build());
                session.setAttribute("listToast", listToast);
                response.sendRedirect("home");
            }
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
