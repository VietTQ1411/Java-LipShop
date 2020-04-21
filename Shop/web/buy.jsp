<%-- 
    Document   : buy
    Created on : Nov 4, 2019, 12:44:26 AM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value = "vi_VN"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="https://kit.fontawesome.com/fe07d3b07b.js" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="css/font.css">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="css/buy.css">

    </head>

    <body>
        <jsp:include page="common/navbar.jsp"></jsp:include>
            <main>
                <section id="over-view">
                    <div class="container">
                        <div class="section-title">
                            <h1>Thông tin sản phẩm</h1>
                        </div>
                        <div class="row cart">
                            <div class="col-md-6 cart-view">
                            <c:set scope="page" var="totalPrice" value="0"></c:set>
                            <c:forEach items="${sessionScope.listCart}" var="product">
                                <div class="row cart-items">
                                    <div class="col-2">
                                        <img src="image/product/${product.prouductAvatar}">
                                    </div>
                                    <div class="col-6 cart-body">
                                        <span>${product.productName}</span>
                                    </div>
                                    <div class="col-4 cart-body">
                                        <p>Số lượng : ${product.quantity}</p>
                                        <h6><fmt:formatNumber value = "${product.total}" type = "currency"/></h6>
                                    </div>
                                </div>
                                <span style="display: none">${totalPrice = totalPrice+ product.total} </span>
                            </c:forEach>
                        </div>
                        <!-- mã giảm giá và thống kê-->
                        <div class="offset-lg-1 col-lg-5 col-md-6 total">
                            <div class="coupons">
                                <h5>Sử dụng mã giảm giá</h5>
                                <div class="my-3">
                                    <form id="coupons-form" action="coupon" method="post">
                                        <input type="text" class="form-control" id="coupons" name="coupons" required="true" placeholder="Nhập mã giảm giá"
                                               <c:if test="${requestScope.coupons ne null}">value="${requestScope.coupons.code}"</c:if> >
                                        </form>
                                        <button type="submit" class="btn btn-primary button-next" onclick="document.forms['coupons-form'].submit()"><span>Kiểm Tra</span></button>
                                    </div>
                                <c:if test="${requestScope.couponsError ne null}"><h5>${requestScope.couponsError}</h5> </c:if>
                                <h6>Bạn được giảm : <c:if test="${requestScope.coupons ne null}">${requestScope.coupons.value*100}</c:if>
                                    <c:if test="${requestScope.coupons eq null}">0</c:if>%</h6>
                                </div>
                                <div class="sumary-total">
                                    <h5>Phí giao hàng (toàn quốc):<span> <fmt:formatNumber value = "${30000 - requestScope.coupons.devideShip}" type = "currency"/></span>
                                    <c:if test="${requestScope.coupons.devideShip gt 0}" ><del><fmt:formatNumber value = "${30000}" type = "currency"/></del></c:if></h5>

                                    <h5>Tiền sản phẩm:<span> <fmt:formatNumber value = "${totalPrice - coupons.value*totalPrice}" type = "currency"/></span>
                                    <del> <c:if test="${requestScope.coupons.value gt 0}" ><del><fmt:formatNumber value = "${totalPrice}" type = "currency"/></del></c:if></del></h5>
                                <h3>Thành Tiền: <fmt:formatNumber value = "${totalPrice + 30000 - coupons.value*totalPrice  - requestScope.coupons.devideShip}" type = "currency"/></h3>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section id="ship">
                <div class="container">
                    <div class="section-title">
                        <h1>Thông tin người mua</h1>
                    </div>
                    <div class="row">
                        <div class=" col-md-6 customers-infor">
                            <div class="row">
                                <div class="col-5 col-sm-3"><span>Họ Tên :</span></div>
                                <div class="col-7 col-sm-9"><span>${requestScope.customer.name}</span></div>
                            </div>
                            <div class="row">
                                <div class="col-5 col-sm-3"><span>Số Điện Thoại :</span></div>
                                <div class="col-7 col-sm-9"><span>${requestScope.customer.mobile}</span></div>
                            </div>
                            <div class="row">
                                <div class="col-5 col-sm-3"><span>Giới tính :</span></div>
                                <div class="col-7 col-sm-9"><span>${requestScope.customer.getDisplayGender()}</span></div>
                            </div>
                            <div class="row">
                                <div class="col-5 col-sm-3"><span>Địa Chỉ :</span></div>
                                <div class="col-7 col-sm-9"><span>${requestScope.customer.address}</span></div>
                            </div>
                        </div>
                        <div class="offset-md-1 col-md-5 btn-submit">
                            <form action="cart-now" id="take-note" method="post">
                                <div class="form-group">
                                    <c:if test="${requestScope.coupons ne null}"> 
                                        <input type="text" class="form-control display-none" name="couponsID" 
                                               value="${requestScope.coupons.id}"></c:if>
                                        <textarea class="form-control" name="note" placeholder="Ghi chú" rows="2"></textarea>
                                    </div> 
                                    <button type="submit" class="btn btn-primary  button-next"><span>Tiếp tục</span></button>
                                </form>
                                <button type="submit" id="buttom-change" class="btn btn-primary button-next" onclick="show()">Giao hàng đến địa chỉ khác</button>
                            </div>
                        </div>
                    </div>
                </section>
                <section id="other-address">
                    <div class="container " id="new-address">
                        <div class="section-title">
                            <h4>Thông tin người nhận</h4>
                        </div>
                        <div class="row">
                            <div class=" col-md-6 new-take">
                                <form action="cartout" method="post">
                                <c:if test="${requestScope.coupons ne null}"> 
                                    <input type="text" class="form-control display-none" name="couponsID" 
                                           value="${requestScope.coupons.id}"></c:if>
                                    <div class="form-group">
                                        <label for="name">Họ tên</label>
                                        <input type="text" class="form-control" name="name" placeholder="Họ và Tên" required="true">
                                    </div>
                                    <div class="form-group">
                                        <label for="phone">Số điện thoại</label>
                                        <input type="tel" class="form-control" name="phone" placeholder="số điện thoại" required="true">
                                    </div>
                                    <div class="form-group">
                                        <label for="address">Địa Chỉ</label>
                                        <textarea class="form-control" name="address" placeholder="Địa chỉ" rows="2" required="true"></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="note">Ghi Chú</label>
                                        <textarea class="form-control" name="note" placeholder="Ghi chú" rows="2"></textarea>
                                    </div>
                                    <button type="submit" class="btn btn-primary button-next"><span>Đồng ý</span></button>
                                </form>
                            </div>
                        </div>
                    </div>
                </section>
            </main>

            <div id="notify">
            <c:if test="${listToast ne null}">
                <c:forEach items="${listToast}" var="toast">
                    <c:if test="${toast.exist == 1}">
                        <div class="toast" role="alert" aria-live="polite" aria-atomic="true" data-delay="5000">
                            <div class="toast-header">
                                <strong class="mr-auto text-primary">Thông báo</strong>
                                <button type="button" class="ml-2 mb-1 close" data-dismiss="toast">&times;</button>
                            </div>
                            <div class="toast-body">
                                ${toast.content}
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </c:if>
        </div>

        <script>
            $(document).ready(function () {
                $('.toast').toast('show');
            });
            function show() {
                var x = document.getElementById("new-address").style.display;
                if (x == "block") {
                    document.getElementById("new-address").style.display = 'none';
                    document.getElementById("take-note").style.display = 'block';
                    document.getElementById("buttom-change").innerHTML = "Giao hàng đến địa chỉ khác";
                } else {
                    document.getElementById("new-address").style.display = 'block';
                    document.getElementById("take-note").style.display = 'none';
                    document.getElementById("buttom-change").innerHTML = "Sử dụng địa chỉ này";
                }
            }
        </script>
    </body>

</html>