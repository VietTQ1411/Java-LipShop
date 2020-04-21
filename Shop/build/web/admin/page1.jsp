<%-- 
    Document   : page1
    Created on : Nov 11, 2019, 12:44:05 AM
    Author     : PC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value = "vi_VN"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <section id="order">
            <div class="">
                <div class="row">
                    <div class="col-lg-3 col-md-4">
                        <div class="title">
                            <h4>Danh mục quản lý</h4>
                        </div>
                        <div class="slider-bar">
                            <ul>
                                <a class="current">
                                    Dark board
                                </a>
                                <a href="admin?page=2" >
                                    Quản lý sản phẩm
                                </a>
                                <a  href="admin?page=3">
                                    Quản lý đơn hàng
                                </a>
                                <a href="admin?page=4">
                                    Quản lý tài khoản
                                </a>
                            </ul>
                        </div>
                    </div>
                    <div class=" col-lg-9 col-md-8">
                        <h1 style="margin-top: 80px;padding-left: 60px">Coming soon</h1>
                    </div>
                </div>
        </section>

    </body>
</html>
