/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter;

import entity.Type;
import entity.TypeStyle;
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
import service.TypeService;
import service.TypeStyleService;
import util.NavMenu;
import util.Toast;

/**
 *
 * @author PC
 */
@WebFilter(filterName = "LoadingAll", urlPatterns = {"/*"})
public class LoadingAll implements Filter {

    /**
     *  Kiểm tra các tham số cần thiết cho mọi website
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
        //xóa các thông báo đã hiển thị
        List<NavMenu> list = (List<NavMenu>) session.getAttribute("navMenu");
        List<Toast> listToast = (List<Toast>) session.getAttribute("listToast");
        if (listToast != null) {
            for (int i = listToast.size() - 1; i >= 0; i--) {
                Toast get = listToast.get(i);
                if (get.getExist() == 2) {
                    listToast.remove(i);
                }
            }
            if (listToast.isEmpty()) {
                listToast = null;
            }
            session.setAttribute("listToast", listToast);
        }
        //kiểm  tra menu nav đã có hay chưa
        if (list == null) {
            list = new ArrayList<>();
            TypeService type = new TypeService();
            TypeStyleService style = new TypeStyleService();
            List<TypeStyle> listType = style.getAll();
            for (TypeStyle typeStyle : listType) {
                List<Type> listItems = type.getTypeByStyle(typeStyle.getId());
                list.add(NavMenu.builder().styleId(typeStyle.getId()).styleName(typeStyle.getName()).items(listItems).build());
            }
            session.setAttribute("navMenu", list);
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
