/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter;

import java.io.IOException;
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
@WebFilter(filterName = "checkToast", urlPatterns = {"/home","/cart.jsp",
    "/filter","/user","/login.jsp","/change-pass","/remove","/changeInfor","/admin"})
public class checkToast implements Filter {
    
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

        HttpSession session = req.getSession();

        List<Toast> listToast = (List<Toast>) session.getAttribute("listToast");
        if(listToast!=null){
            for (int i = listToast.size()-1; i >=0; i--) {
                Toast get = listToast.get(i);
                if(get.getExist()<2){
                    get.setExist(get.getExist()+1);
                }else{
                    listToast.remove(i);
                }
            }
            if(listToast.isEmpty()){
                listToast = null;
            }
            session.setAttribute("listToast", listToast);
        }

        chain.doFilter(request, response);
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }
}
