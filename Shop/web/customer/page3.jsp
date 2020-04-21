<%-- 
    Document   : page3
    Created on : Nov 7, 2019, 6:20:44 PM
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
                                <a href="user?page=1">
                                    Thông Tin Chung
                                </a>
                                <a href="user?page=2" >
                                    Đơn hàng
                                </a>
                                <a  class="current">
                                    Lịch sử mua hàng
                                </a>
                                <a href="user?page=4">
                                    Sản phẩm yêu thích
                                </a>
                            </ul>
                        </div>
                    </div>
                    <div class=" col-lg-9 col-md-8">
                        <div class="title">
                            <h4> Đơn hàng của bạn</h4>
                        </div>
                        <div class="order">
                            <c:if test="${requestScope.listBill eq null}">
                                <h1>Bạn không có đơn hàng nào đang xử lý</h1>
                            </c:if>
                            <c:if test="${requestScope.listBill ne null}">
                                <div class="table-responsive-xl table-data">
                                    <table id="example" class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>Mã Đơn Hàng</th>
                                                <th>Ngày Tạo</th>
                                                <th>Tổng Tiền</th>
                                                <th>Trạng Thái</th>
                                                <th>Chi Tiết</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.listBill}" var="bill" varStatus="loop">
                                                <tr>
                                                    <td>
                                                        <p>${loop.index+1}</p>
                                                    </td>
                                                    <td>
                                                        <h6>
                                                            Đơn hàng #${bill.order.id}
                                                        </h6>
                                                    </td>
                                                    <td>
                                                        <p> <fmt:formatDate value="${bill.order.createDate}" pattern="dd/MM/yyyy"></fmt:formatDate></p>
                                                        </td>
                                                        <td>
                                                            <p> <fmt:formatNumber value = "${bill.order.total}" type = "currency"/></p>
                                                    </td>
                                                    <td class="status"><span class="${bill.order.statusView}" 
                                                                             data-toggle="tooltip" data-placement="top" 
                                                                             title="${bill.order.statusValue}"></span></td>
                                                    <td>
                                                        <button type="button" class="btn btn-dark" data-toggle="modal" data-target="#model${loop.index}">
                                                            <span>Xem chi tiết</span>
                                                        </button>
                                                    </td>
                                            <div class="modal" id="model${loop.index}">
                                                <div class="modal-dialog modal-dialog-scrollable">
                                                    <div class="modal-content">
                                                        <!-- Modal Header -->
                                                        <div class="modal-header">
                                                            <h4 class="modal-title">Đơn hàng #${bill.order.id}</h4>
                                                            <button type="button" class=" btn btn-danger" data-dismiss="modal">&times;</button>
                                                        </div>
                                                        <!-- Modal body -->
                                                        <div class="modal-body">
                                                            <div class="container">
                                                                <div class="cart-product">
                                                                    <c:forEach items="${bill.list}" var="line">
                                                                        <div class="row cart-items">
                                                                            <div class="col-sm-2 col-4">
                                                                                <img src="image/product/${line.avatar}" width="80%">
                                                                            </div>
                                                                            <div class="col-sm-10 col-8 row">
                                                                                <div class="col-sm-6 col-12 cart-body">
                                                                                    <p>${line.productName}</p>
                                                                                </div>
                                                                                <div class="col-sm-3 col-12 cart-body">
                                                                                    <p>Số lượng: ${line.quantity}</p>
                                                                                </div>
                                                                                <div class="col-sm-3 col-12 cart-body">
                                                                                    <p>
                                                                                        <span>Thành tiền: </span><fmt:formatNumber value = "${line.quantity*line.productPrice}" type = "currency"/>
                                                                                    </p>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:forEach>
                                                                </div>
                                                            </div>
                                                            <div class="cart-detail">
                                                                <div>
                                                                    Ngày Mua Hàng: <fmt:formatDate value="${bill.order.createDate}" pattern="dd/MM/yyyy"></fmt:formatDate>
                                                                    </div> 
                                                                    <div>
                                                                        Tình Trạng: ${bill.order.statusValue}
                                                                </div>
                                                            </div>
                                                            <div class="cart-buyer">
                                                                <div class="row">
                                                                    <div class="col-md-6 buyer">
                                                                        <p>Người mua hàng : ${customer.name} </p>
                                                                        <p>Số điện thoại :${customer.mobile}</p>
                                                                        <p>Địa chỉ: ${customer.address}</p>
                                                                    </div>
                                                                    <div class="col-md-6 taker">
                                                                        <p>Người nhận hàng : ${bill.recei.name} </p>
                                                                        <p>Số điện thoại:${bill.recei.mobile}</p>
                                                                        <p>Địa chỉ:  ${bill.recei.address}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <!-- Modal footer -->
                                                        <div class="modal-footer">
                                                            <h4 class="money">Tổng Tiền: <fmt:formatNumber value = "${bill.order.total}" type = "currency"/></h4>
                                                            <div> 
                                                                <c:if test="${bill.order.status eq 3}">
                                                                    <a href="accept?type=user&page=3&orderID=${bill.order.id}"><button type="button" class="btn btn-warning">Xác nhận lại đơn</button></a>
                                                                </c:if>
                                                                <c:if test="${bill.order.status eq 4 or bill.order.status eq 3}">
                                                                    <a href="remove?type=user&page=3&orderID=${bill.order.id}"><button type="button" class="btn btn-danger">Hủy đơn hàng</button></a>
                                                                </c:if>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
        </section>
    </body>
</html>
