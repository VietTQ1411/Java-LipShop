/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.buy;

import entity.Account;
import entity.Carts;
import entity.Coupons;
import entity.CustomerDetail;
import entity.Order;
import entity.OrderDetail;
import entity.Receiver;
import java.io.IOException;
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
import service.OrderDetailService;
import service.OrderService;
import service.ProductService;
import service.ReceiverService;
import util.Helper;
import util.Toast;

/**
 *
 * @author PC
 */
@WebServlet(name = "CartOut", urlPatterns = {"/cartout"})
public class CartOut extends HttpServlet {

    
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
        //get paramenter
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String note = request.getParameter("note");
        if (note == null) {//check note not empty
            note = "nothing";
        }

        //get list notify and list cart
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        List<Carts> list = (List<Carts>) session.getAttribute("listCart");
        List<Toast> listToast = (List<Toast>) session.getAttribute("listToast");
        
        //check list
        if (listToast == null) {
            listToast = new ArrayList<>();
        }
        Coupons c = null;
        if (account == null || list == null) {
            response.sendRedirect("cart.jsp");
        } else {

            /*
                get coupons of buyer
             */
            String couponsID = request.getParameter("couponsID");
            if (couponsID == null || couponsID.isEmpty() || !couponsID.matches("\\d+")) {
                couponsID = null;
            } else {
                c = new CouponsService().getOne(Helper.getValidateInt(couponsID));
            }

            /*
                get address to ship
             */
            CustomerDetail cus = new CustomerDetailService().getOneByAccID(account.getId());
            //taker information
            Receiver buyer = Receiver.builder().mobile(phone).name(name).address(address).build();
            int receiverID = new ReceiverService().addReceiverReturnID(buyer);

            if (receiverID == -1) {
                request.setAttribute("notify", "Đặt hàng thất bại");
            } else {
                double total = 0;
                List<OrderDetail> listOrder = new ArrayList<>();
                total = list.stream().map((carts) -> carts.getTotal()).reduce(total, (accumulator, _item) -> accumulator + _item);
                if (c != null) {
                    
                    //cal total ( ship , price, sale)
                    total = total - total * c.getValue() * 100 + 30000 - c.getDevideShip();
                    Order order = Order.builder().couponsID(c.getId()).customerID(cus.getId()).receiverID(receiverID)
                            .note(note).status(4).total(total).build();
                    
                    //insert order
                    int orderID = new OrderService().addOrderReturnID(order);

                    if (orderID == -1) {
                        request.setAttribute("notify", "Đặt hàng thất bại");
                    } else {
                        
                        //using coupons to device price
                        new CouponsService().usingCoupons(c.getId());
                        new OrderDetailService().addListCart(list, orderID);
                        new ProductService().buyingProduct(list);
                        session.setAttribute("listCart", null);
                        request.setAttribute("notify", "Đặt hàng thành công");
                    }
                } else {
                    Order order = Order.builder().couponsID(1).customerID(cus.getId()).receiverID(receiverID)
                            .note(note).status(4).total(total).build();
                    //insert order
                    int orderID = new OrderService().addOrderReturnID(order);
                    if (orderID == -1) {
                        request.setAttribute("notify", "Đặt hàng thất bại");
                    } else {
                        //using coupons to device price
                        new OrderDetailService().addListCart(list, orderID);
                        new ProductService().buyingProduct(list);
                        session.setAttribute("listCart", null);
                        request.setAttribute("notify", "Đặt hàng thành công");
                    }
                }
            }
            request.getRequestDispatcher("thank.jsp").forward(request, response);
        }

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
