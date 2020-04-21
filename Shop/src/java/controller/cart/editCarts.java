/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cart;

import entity.Carts;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.Helper;

/**
 *
 * @author PC
 */
@WebServlet(name = "editCarts", urlPatterns = {"/edit"})
public class editCarts extends HttpServlet {

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
            int productID = Helper.getValidateInt(request.getParameter("productID"));
            int type = Helper.getValidateInt(request.getParameter("type"));
            //check product and type of edit 1: remove cart,2: divide cart,3 increase cart
            if (productID > -1 && (type > -1 && 3 > type)) {
                HttpSession session = request.getSession();
                if (productID > 0) {
                    //get list cart
                    List<Carts> list = (List<Carts>) session.getAttribute("listCart");
                    if (list != null) {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getProductID() == productID) {
                                switch (type) {
                                    case 0://remove cart
                                        list.remove(i);
                                        break;
                                    case 1://devide cart
                                        list.get(i).setQuantity(list.get(i).getQuantity() - 1);
                                        if (list.get(i).getQuantity() < 0) {
                                            list.remove(i);
                                        } else {//remove if cart have 0 quantity
                                            list.get(i).setTotal(list.get(i).getQuantity() * list.get(i).getPrice());
                                        }
                                        break;
                                    case 2://increase cart if have quantity
                                        if (list.get(i).getQuantity() < list.get(i).getProductQuantity()) {
                                            list.get(i).setQuantity(list.get(i).getQuantity() + 1);
                                        }
                                        list.get(i).setTotal(list.get(i).getQuantity() * list.get(i).getPrice());
                                        break;
                                }
                                break;
                            }
                        }
                        if (list.size() >= 1) {
                            session.setAttribute("listCart", list);
                        } else {
                            session.setAttribute("listCart", null);
                        }
                        response.sendRedirect("cart.jsp");
                    }
                } else if (productID == 0 && type == 0) {
                    session.removeAttribute("listCart");
                    response.sendRedirect("cart.jsp");
                } else {
                    response.sendRedirect("error.jsp");
                }
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
