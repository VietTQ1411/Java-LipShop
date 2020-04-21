/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.BrandDAO;
import entity.Brand;
import entity.Type;
import entity.TypeStyle;
import java.util.ArrayList;
import java.util.List;
import service.TypeService;
import service.TypeStyleService;

/**
 *
 * @author PC
 */
public class Helper {
    
    /**
     * Change String to Integer
     *
     * @param strId
     * @return integer or -1 if String can't parse to integer
     */
    public static int getValidateInt(String strId) {
        int id = -1;
        try {
            id = Integer.valueOf(strId);
        } catch (NumberFormatException e) {
            e.printStackTrace(System.out);
        }
        return id;
    }

    /**
     * create query String for database follow Conditions
     *
     * @param condition
     * @return
     */
    public static String whereQuery(Conditions condition) {
        String result = "where images.cover = 1 and products.status != -1  ";
        if (condition.getProductName() != null) {
            result = result + " and products.name like '%" + condition.getProductName() + "%'  ";
        }
        if (condition.getProductBrand() != null && condition.getProductBrand().matches("\\d+")) {
            result = result + " and products.brand_id = " + condition.getProductBrand();
        }
        if (condition.getType()!= null && condition.getType().matches("\\d+")) {
            result = result + " and types.id = " + condition.getType();
        }
        if (condition.getStyle()!= null && condition.getStyle().matches("\\d+")) {
            result = result + " and types.style = " + condition.getStyle() ;
        }
        if (condition.getMoneyFrom() != null && condition.getMoneyFrom().matches("\\d+")) {
            result = result + " and products.price >=" + condition.getMoneyFrom();
        }
        if (condition.getMoneyTo() != null && condition.getMoneyTo().matches("\\d+")) {
            result = result + " and products.price <=" + condition.getMoneyTo();
        }
        return result;
    }

    /**
     * create path follow para taken
     *
     * @param condition
     * @return
     */
    public static List<String> breadcrumb(Conditions condition) {
        TypeService type = new TypeService();
        List<String> result = new ArrayList<>();
        //breadcrumb is brand and type of the peoduct
        if (condition.getProductBrand() != null && (condition.getStyle() == null)) {
            //check brand path
            if (condition.getProductBrand()!= null && condition.getProductBrand().matches("\\d+")) {
                int check = getValidateInt(condition.getProductBrand());
                if( check >= 0){
                    Brand b = new BrandDAO().getOne(check);
                    if(b!=null){
                        result.add(b.getName());
                    }
                }
            }
            //check type path
            if (condition.getType()!= null && condition.getType().matches("\\d+")) {
                int check = getValidateInt(condition.getType());
                if( check >= 0){
                    Type t = type.getOne(check);
                    if(t!=null){
                        result.add(t.getName());
                    }
                }
            }
            //check name path
            if (condition.getProductName() != null) {
                result.add("Tìm kiếm theo tên");
            }
        } //breadcrumb is type style and type of the peoduct 
        else if (condition.getStyle()!= null && condition.getProductBrand() == null) {
            //check style path
            if (condition.getStyle()!= null && condition.getStyle().matches("\\d+")) {
                int check = getValidateInt(condition.getStyle());
                if( check >= 0){
                    TypeStyle style = new TypeStyleService().getOne(check);
                    if(style!=null){
                        result.add(style.getName());
                    }
                }
            }
            //check type path
            if (condition.getType()!= null && condition.getType().matches("\\d+")) {
                int check = getValidateInt(condition.getType());
                if( check >= 0){
                    Type t = type.getOne(check);
                    if(t!=null){
                        result.add(t.getName());
                    }
                }
            }
            //check name path
            if (condition.getProductName() != null) {
                result.add("Tìm kiếm theo tên");
            }
        } else {
            //check name path
            result.add("Tìm kiếm tùy chọn");
        }
        return result;
    }

    /**
     * create link a with href and label
     *
     * @param label
     * @param href
     * @return
     */
    public static String getLink(String label, String href) {
        return "<a href=\"" + href + "\">" + label + "</a>";
    }

    /**
     * return href for get link a
     *
     * @param condition
     * @return
     */
    public static String href(Conditions condition) {
        String result = "";
        if (condition.getProductName() != null) {
            result = result + "name=" + condition.getProductName() + "&";
        }
        if (condition.getProductBrand() != null) {
            result = result + "brand=" + condition.getProductBrand() + "&";
        }
        if (condition.getType()!= null) {
            result = result + "type=" + condition.getType() + "&";
        }
        if (condition.getStyle()!= null) {
            result = result + "style=" + condition.getStyle() + "&";
        }
        if (condition.getMoneyFrom() != null && condition.getMoneyFrom().matches("\\d+")) {
            result = result + "moneyFrom=" + condition.getMoneyFrom() + "&";
        }
        if (condition.getMoneyTo() != null && condition.getMoneyTo().matches("\\d+")) {
            result = result + "moneyTo=" + condition.getMoneyTo() + "&";
        }
        return result;
    }

    public static String markFilterByMoney(Conditions condition) {
        String result = null;
        String moneyF = "";
        String moneyT = "";
        if (condition.getMoneyFrom() != null && condition.getMoneyFrom().matches("\\d+")) {
            moneyF = condition.getMoneyFrom();
        } else {
            moneyF = "0";
        }
        if (condition.getMoneyTo() != null && condition.getMoneyTo().matches("\\d+")) {
            moneyT = condition.getMoneyTo();
        } else {
            moneyT = "0";
        }

        double MoneyFrom = Double.valueOf(moneyF);
        double MoneyTo = Double.valueOf(moneyT);
        if (MoneyFrom >= 1000000 && MoneyTo == 0) {
            result = "6";
        }
        if (MoneyFrom >= 500000 && MoneyTo <= 1000000) {
            result = "5";
        }
        if (MoneyFrom >= 200000 && MoneyTo <= 500000) {
            result = "4";
        }
        if (MoneyFrom >= 100000 && MoneyTo <= 200000) {
            result = "3";
        }
        if (MoneyFrom >= 50000 && MoneyTo <= 100000) {
            result = "2";
        }
        if (MoneyFrom == 0 && MoneyTo <= 50000) {
            result = "1";
        }
        if (MoneyFrom == 0 && MoneyTo == 0) {
            result = null;
        }
        return result;
    }

    /**
     * create paging for website follow para Conditions
     *
     * @param condition
     * @param pageindex
     * @param pagecount
     * @param gap
     * @return
     */
    public static String pagination(Conditions condition, int pageindex, int pagecount, int gap) {
        String result = "";
        if (pageindex > gap + 1) {
            result += getLink("&laquo;", "filter?" + href(condition) + "page=" + 1);
        }

        for (int i = gap; i > 0; i--) {
            if (pageindex - i > 0) {
                result += getLink("" + (pageindex - i), "filter?" + href(condition) + "page=" + (pageindex - i));
            }
        }

        result += "<a class=\"active\">" + pageindex + "</a>";

        for (int i = 1; i <= gap; i++) {
            if (pageindex + i <= pagecount) {
                result += getLink("" + (pageindex + i), "filter?" + href(condition) + "page=" + (pageindex + i));
            }
        }

        if (pageindex + gap <= pagecount - 1) {
            result += getLink("&raquo;", "filter?" + href(condition) + "page=" + pagecount);
        }
        return result;
    }

    public String getName(String name) {
        String result = "";
        String[] temp = name.trim().toUpperCase().replaceAll("[\\d\\.\\:\"\\+\\-\\_\\)\\(\\+\'\\>\\<\\?]+", "").replaceAll("\\s+", " ").split(" ");
        for (String string : temp) {
            result = result + string.charAt(0) + string.substring(1).toLowerCase()+ " ";
        }
        return result.trim();
    }

    public String getShortName(String name){
        String result = "";
        String[] temp = name.trim().toUpperCase().replaceAll("[\\d\\.\\:\"\\+\\-\\_\\)\\(\\+\'\\>\\<\\?]+", "").replaceAll("\\s+", " ").split(" ");
       
            result = result + temp[0].charAt(0) + temp[0].substring(1).toLowerCase()+ " "+temp[temp.length-1].charAt(0) + temp[temp.length-1].substring(1).toLowerCase();
        
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(new Helper().getShortName(" Trần Quan23g Việt"));
    }
}
