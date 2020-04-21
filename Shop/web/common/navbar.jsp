<%-- 
    Document   : navbar
    Created on : Nov 3, 2019, 2:11:08 AM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light center">
        <!--logo-->
        <a href="home" class="navbar-brand logo">
            <img src="image/background/mn-logo-2.png" alt="logo" class="navbar-brand" />
        </a>
        <!--zoom out-->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <!--menu option-->
        <div class="collapse navbar-collapse" id="navbarToggler">
            <ul class="navbar-nav  text-15-16-17-17-18">
                <!--Drop Links 1-->
                <c:forEach items="${sessionScope.navMenu}" var="menu">
                    <li class="nav-item">
                        <a class="nav-link" href="filter?style=${menu.styleId}">${menu.styleName}</a>
                        <ul class="drop-link">
                            <c:forEach items="${menu.items}" var="item">
                                <li><a href="filter?style=${menu.styleId}&type=${item.id}">${item.name}</a></li>
                                </c:forEach>
                        </ul>
                    </li>
                </c:forEach>
            </ul>
            <!--nav-->
            <ul class="navbar-nav ml-auto center">
                <!--search value-->
                <form class="form-inline my-1 nav-search center" action="filter" method="post">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search by Name" name="name">
                    <button class="btn btn-outline-success my-2 my-sm-0 search" type="submit">Search<i class="fa fa-fw fa-search"></i></button>
                </form>
                <!--Giỏ Hàng-->
                <li class="nav-item mx-2" id="cart">
                    <a class="nav-link" href="cart.jsp">
                        <button class="btn btn-light" type="submit">
                            <img src="image/background/shopping_cart_32px.png" alt="">
                            <span class="text-13-14"> Giỏ Hàng
                                <c:if test="${sessionScope.listCart ne null}">
                                    <div class="cart-quantity">${sessionScope.listCart.size()}</div></c:if>
                                </span>
                            </button>
                        </a>
                    </li>
                    <!--Contact user-->
                <c:if test="${sessionScope.account eq null}">
                    <li id="login" class="nav-item">
                        <div class="nav-link">
                            <button class="btn btn-dark dropdown-toggle btn-login" type="button" id="dropdownMenuButton" data-toggle="dropdown">
                                Đăng Nhập
                            </button>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                                <div id="login-form" class="py-1 px-3">
                                    <a href="login.jsp"><h3 class="small-hide">Đăng Nhập</h3></a>
                                    <form class="form-group text-14-15-15-16-16 small-login" action="login" method="post">
                                        <div class="dropdown-divider small-hide"></div>
                                        <label class="pl-2" for="user">Email</label>
                                        <input class="form-control mr-sm-2" type="text" placeholder="Dương@gmail.com" name="user">
                                        <label class="pt-1 pl-2" for="user">Password</label>
                                        <input class="form-control mr-sm-2" type="password" placeholder="******" name="pass">
                                        <div class="custom-control custom-checkbox mt-2">
                                            <input type="checkbox" class="custom-control-input" id="customCheck" name="remember">
                                            <label class="custom-control-label" for="customCheck">Remember me</label>
                                        </div>
                                        <div class="dropdown-divider"></div>
                                        <button class="btn btn-dark my-2 my-sm-0 " style="width: 100%;" type="submit">Đăng nhập</button>
                                    </form>
                                    <p class="center pt-1 small-p">
                                        Chưa có tài khoản <strong><a href="signin.jsp">Đăng Ký Ngay</a></strong>
                                    </p>
                                    <p class="center small-p">
                                        <a href="#">Quên Mật Khẩu?</a>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </li></c:if>
                <c:if test="${sessionScope.account ne null}">
                    <li class="nav-item nav-user">
                        <div class="dropdown nav-link">
                            <button class="btn btn-light dropdown-toggle" type="button" id="navbarDropdown" data-toggle="dropdown">
                                ${sessionScope.account.name}
                            </button>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="user">Thông Tin Người Dùng</a>
                                <c:if test="${sessionScope.account.type eq 0}"><a class="dropdown-item" href="admin">Console</a></c:if> 
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="logout">Đăng Xuất</a>
                            </div>
                        </div>
                    </li>
                </c:if>
            </ul>
        </div>
    </nav>
</html>
