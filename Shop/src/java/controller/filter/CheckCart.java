/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter;

import entity.Carts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.ProductService;
import util.Toast;

/**
 *
 * @author PC
 */
@WebFilter(filterName = "CheckCart", urlPatterns = {"/cart", "/edit", "/cart.jsp", "/buy"})
public class CheckCart implements Filter {

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = "";
        ProductService pro = new ProductService();
        HttpSession session = req.getSession();

        List<Carts> list = (List<Carts>) session.getAttribute("listCart");
        List<Toast> listToast = (List<Toast>) session.getAttribute("listToast");
        if (listToast == null) {
            listToast = new ArrayList<>();
        }
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                int quantity = pro.checkQuantity(list.get(i).getProductID());
                if (!(quantity > 0)) {
                    Carts a = list.remove(i);
                    listToast.add(Toast.builder().content("Sản phẩm " + a.getProductName()
                            + " đã bị xóa vì không còn đủ sản phẩm để cung cấp").exist(0).build());
                } else {
                    if (quantity < list.get(i).getQuantity()) {
                        list.get(i).setQuantity(quantity);
                    }
                }
            }
            if (listToast.isEmpty()) {
                listToast = null;
            }
            if (list.isEmpty()) {
                list = null;
            }
        }

        session.setAttribute("listToast", listToast);
        session.setAttribute("listCart", list);
        chain.doFilter(request, response);
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }
}
