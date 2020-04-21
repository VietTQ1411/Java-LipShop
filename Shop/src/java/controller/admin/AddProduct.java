/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dao.ImageDAO;
import dao.ProductDAO;
import entity.Images;
import entity.Product;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.ProductService;
import util.Toast;

/**
 *
 * @author PC
 */
@WebServlet(name = "AddProduct", urlPatterns = {"/add-product"})
public class AddProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();

            List<Toast> listToast = (List<Toast>) session.getAttribute("listToast");
            if (listToast == null) {
                listToast = new ArrayList<>();
            }
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (!isMultiPart) {
                System.out.println("Error");
            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                List<String> lsFileName = new ArrayList<>();

                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                    System.err.println(e.getMessage());
                }

                Iterator iter = items.iterator();
                Hashtable params = new Hashtable();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        // convert to UTF-8
                        params.put(new String(item.getFieldName().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8),
                                new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                    } else {
                        try {
                            // get file name
                            String itemName = item.getName();
                            String fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                            lsFileName.add(fileName);

                            String readPart = getServletContext().getRealPath("/");
                            String partUpload = readPart.replace("build\\", "") + "image\\product\\" + fileName;

                            File saveFile = new File(partUpload);
                            item.write(saveFile);
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    }
                }

                // get Product input
                String name = (String) params.get("name");
                int brand = Integer.valueOf((String) params.get("brand"));
                double price = Double.valueOf((String) params.get("price"));
                int quantity = Integer.valueOf((String) params.get("quantity"));
                int type = Integer.valueOf((String) params.get("style"));
                String color = (String) params.get("color");
                String description = (String) params.get("note");

                Product p = Product.builder().name(name).price(price).quantity(quantity).brand(brand).color(color).
                        description(description).sale(0).type(type).status(1).build();
                int productId = new ProductService().addReturnID(p);
                boolean check = false;
                if (productId == -1) {
                    listToast.add(Toast.builder().content("Thêm sản phẩm thất bại").exist(0).build());
                    session.setAttribute("listToast", listToast);
                    response.sendRedirect("home");
                } else {
                    for (String string : lsFileName) {
                        boolean flag = false;
                        if (string.equals(lsFileName.get(0))) {
                            flag = new ImageDAO().add(Images.builder().productId(productId).name(string).cover(true).build());
                        } else {
                            flag = new ImageDAO().add(Images.builder().productId(productId).name(string).cover(false).build());
                        }
                        if (!flag) {
                            check = true;
                            listToast.add(Toast.builder().content("Thêm ảnh" +string +" thất bại").exist(0).build());
                        }
                    }
                    if(!check){
                        listToast.add(Toast.builder().content("Thêm sản phẩm thành công").exist(0).build());
                    }
                    session.setAttribute("listToast", listToast);
                    response.sendRedirect("admin?page=2");
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
