/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter;

import entity.Account;
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
import util.Toast;

/**
 *
 * @author PC
 */
@WebFilter(filterName = "BuyFilter", urlPatterns = {"/buy"})
public class BuyFilter implements Filter {

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
        String url="";
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        List<Carts> list = (List<Carts>) session.getAttribute("listCart");

        List<Toast> listToast = (List<Toast>) session.getAttribute("listToast");
        if (listToast == null) {
            listToast = new ArrayList<>();
        }
        if (account != null && list != null) {
            chain.doFilter(request, response);
        } else {
            if(account==null){
                listToast.add(Toast.builder().content("Bạn phải đăng nhập để đặt hàng").exist(0).build());
                url = "login.jsp";
            }else{
                url = "cart.jsp";
            }
            session.setAttribute("listToast", listToast);
            res.sendRedirect(url);
        }
        
       
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

}
