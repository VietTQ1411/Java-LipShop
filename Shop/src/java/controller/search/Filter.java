/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.search;

import dao.BrandDAO;
import dao.ProductDAO;
import dao.TypeDAO;
import entity.Brand;
import util.NavMenu;
import entity.Product;
import entity.TypeStyle;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.TypeStyleService;
import util.Conditions;
import util.FilterMenu;
import util.Helper;

/**
 *
 * @author PC
 */
@WebServlet(name = "Filter", urlPatterns = {"/filter"})
public class Filter extends HttpServlet {

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
        response.setContentType("text/html; charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            /*
                get value of request and create data
             */
            //number of product
            int productSize = 9;
            //number of current page
            String isPage = request.getParameter("page");
            if (isPage == null) {
                isPage = "1";
            }
            int page = Helper.getValidateInt(isPage);
            if (page == -1) {
                page = 1;
            }
            //filter follow by
            String order = request.getParameter("order");
            if (order == null) {
                order = "ORDER BY products.id ASC";
            }
            //filter follow money

            String moneyFrom = request.getParameter("moneyFrom");
            if (moneyFrom == null || !moneyFrom.matches("\\d+")) {
                moneyFrom = null;
            }
            String moneyTo = request.getParameter("moneyTo");
            if (moneyTo == null || !moneyTo.matches("\\d+")) {
                moneyTo = null;
            }
            //parameter for filter
            //type of product
            String type = request.getParameter("type");
            if (type == null || !type.matches("\\d+")) {
                type = null;
            } 
            //type name of product
            String style = request.getParameter("style");
            if (style == null || !style.matches("\\d+")) {
                style = null;
            }
            //brand of product
            String brand = request.getParameter("brand");
            if (brand == null || !brand.matches("\\d+")) {
                brand = null;
            }
            //name of product
            String name = request.getParameter("name");
            if (name == null || name.isEmpty()) {
                name = null;
            }
          
            //create enity 
            Conditions con = Conditions.builder().productName(name).productBrand(brand).type(type).style(style)
                    .moneyFrom(moneyFrom).moneyTo(moneyTo).build();
            /*
               get data and send to web
             */
            //mark filter by money
            String markMoney = Helper.markFilterByMoney(con);
            //create query
            String where = Helper.whereQuery(con);
            //get product
            List<Product> list = new ProductDAO().Filter(where, order, page, productSize);
            //get product 
            int pageCount = new ProductDAO().count(where);
            int count;
            if (pageCount % productSize == 0) {
                count = pageCount / productSize;
            } else {
                count = pageCount / productSize + 1;
            }
            //create paging page
            String paging = Helper.pagination(con, page, count, 2);
            /*
                get link for a card
             */
            //link full name-brand-type-typename-money
            String href = Helper.href(con);

            //link full name-brand-type-typename
            String href2 = Helper.href(Conditions.builder().productName(name).productBrand(brand).type(type).style(style)
                    .build());

            //link full brand-type-typename-money
            String href3 = Helper.href(Conditions.builder().productBrand(brand).type(type).style(style)
                    .moneyFrom(moneyFrom).moneyTo(moneyTo).build());

            //link full name-typename-money
            String href4 = Helper.href(Conditions.builder().productName(name).type(type)
                    .moneyFrom(moneyFrom).moneyTo(moneyTo).build());

            //link money - name
            String href5 = Helper.href(Conditions.builder().moneyFrom(moneyFrom).moneyTo(moneyTo).build());
            
            
            List<String> mess = Helper.breadcrumb(con);

            //create menu brand with type
            List<FilterMenu> listMenu = new ArrayList<>();
            List<TypeStyle> listType = new TypeStyleService().getAll();
            for (TypeStyle string : listType) {
                List<Brand> listItems = new BrandDAO().getList(string.getId());
                listMenu.add(FilterMenu.builder().id(string.getId()).name(string.getName()).items(listItems).build());
            }

            /*
                create attribute
             */
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("listMenu", listMenu);
            request.setAttribute("markM", markMoney);
            request.setAttribute("href", href);
            request.setAttribute("breadcrumb", mess);
            request.setAttribute("style", style);
            request.setAttribute("type", type);
            request.setAttribute("brand", brand);
            request.setAttribute("moneyTo", moneyTo);
            request.setAttribute("monetFrom", moneyFrom);
            request.setAttribute("name", name);
            request.setAttribute("href2", href2);
            request.setAttribute("href3", href3);
            request.setAttribute("href4", href4);
            request.setAttribute("href5", href5);
            request.setAttribute("listProduct", list);
            request.setAttribute("paging", paging);
            request.getRequestDispatcher("filter.jsp").forward(request, response);
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
        doPost(request, response);
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
