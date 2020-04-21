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
import service.OrderDetailService;
import service.OrderService;
import service.ProductService;
import service.ReceiverService;
import util.Helper;

/**
 * when buyer use his-self for address of ship
 *
 * @author PC
 */
@WebServlet(name = "CartOut2", urlPatterns = {"/cart-now"})
public class CartOut2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("cart.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        List<Carts> list = (List<Carts>) session.getAttribute("listCart");
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
            Receiver buyer = Receiver.builder().mobile(cus.getMobile()).name(cus.getName()).address(cus.getAddress()).build();
            int receiverID = new ReceiverService().addReceiverReturnID(buyer);

            if (receiverID == -1) {
                request.setAttribute("notify", "Đặt hàng thất bại");
            } else {
                String note = request.getParameter("note");
                if (note == null) {
                    note = "nothing";
                }
                double total = 0;
                List<OrderDetail> listOrder = new ArrayList<>();
                for (Carts carts : list) {
                    total += carts.getTotal();
                }
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
