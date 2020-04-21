/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cart;

import dao.ProductDAO;
import entity.Carts;
import entity.Product;
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
import util.Helper;
import util.Toast;

/**
 *
 * @author PC
 */
@WebServlet(name = "cart", urlPatterns = {"/cart"})
public class cart extends HttpServlet {

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
            String product = request.getParameter("productID");
            if (product == null) {
                product = "-1";
            }
            int productID = Helper.getValidateInt(product);
            String isQuantity = request.getParameter("quantity");
            if (isQuantity == null || !isQuantity.matches("\\d+")) {
                isQuantity = "-1";
            }
            int quantity = Helper.getValidateInt(isQuantity);
            if (quantity == -1) {
                quantity = 1;
            }
            List<Toast> listToast = (List<Toast>) request.getSession().getAttribute("listToast");
            if (listToast == null) {
                listToast = new ArrayList<>();
            }

            //check id is true value
            if (productID != -1) {

                Product pro = new ProductDAO().getOne(productID);
                //check product already exist
                if (pro != null) {
                    if (pro.getQuantity() < 1) {
                        listToast.add(Toast.builder().content("Sản phẩm " + pro.getName() + " đã hết!!vui lòng đặt hàng sau.").exist(0).build());
                        request.getSession().setAttribute("listToast", listToast);
                        response.sendRedirect("home");
                    }
                    //create cart items
                    Carts cart = Carts.builder().
                            productID(productID).
                            prouductAvatar(pro.getAvatar()).
                            productName(pro.getName()).
                            price(pro.getRealPrice()).
                            productQuantity(pro.getQuantity()).
                            quantity(quantity).
                            total(pro.getRealPrice() * quantity).
                            build();
                    //get session
                    HttpSession session = request.getSession();
                    List<Carts> listCarts = (List<Carts>) session.getAttribute("listCart");
                    //check have list in session    
                    if (listCarts == null) {
                        listCarts = new ArrayList<>();
                        listCarts.add(cart);
                    } else {
                        //check cart adready exist in session
                        boolean flag = true;
                        for (Carts carts : listCarts) {
                            if (carts.getProductID() == productID) {
                                carts.setQuantity(carts.getQuantity() + quantity);
                                carts.setTotal(carts.getQuantity() * carts.getPrice());
                                flag = false;
                            }
                        }
                        if (flag) {
                            listCarts.add(cart);
                        }
                    }
                    listToast.add(Toast.builder().content("Thêm sản phẩm " + pro.getName() + " thành công").exist(0).build());
                        request.getSession().setAttribute("listToast", listToast);
                    session.setAttribute("listCart", listCarts);
                    response.sendRedirect("home");
                } else {
                    response.sendRedirect("error.jsp");
                }
            } else {
                response.sendRedirect("error.jsp");
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
