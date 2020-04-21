<%-- 
    Document   : cart
    Created on : Oct 31, 2019, 4:01:48 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value = "vi_VN"/>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="https://kit.fontawesome.com/fe07d3b07b.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/font.css">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="css/cart.css">
        <link rel="stylesheet" href="css/footer.css">
    </head>

    <body>
        <jsp:include page="common/navbar.jsp"></jsp:include>
            <!--Main-->
            <main>
                <!--Đường dẫn trang web-->
                <section id="breadcrumb" class="center">
                    <h1>Giỏ hàng</h1>
                    <li class="breadcrumb-home">
                        <a href="home"><span>Trang Chủ</span></a>
                        <span>&nbsp;<i class="fa fa-angle-right"></i>&nbsp;</span>
                    </li>

                    <li class="breadcrumb-curent">
                        <strong>giỏ hàng</strong>
                    </li>
                </section>
                <!--Main chính-->
                <section id="cart-table">
                    <div class="container-cart">
                    <c:if test="${sessionScope.listCart eq null}">
                        <h1>Bạn chưa có sản phẩm nào trong giỏ hàng</h1>
                    </c:if>
                    <c:if test="${sessionScope.listCart ne null}">
                        <h1>Giỏ hàng của bạn có ${sessionScope.listCart.size()} sản phẩm</h1>
                    </c:if>
                </div>
                <div class="cart-card">
                    <c:if test="${sessionScope.listCart ne null}">
                        <c:set scope="page" var="totalPrice" value="0"></c:set>
                        <c:forEach items="${sessionScope.listCart}" var="cart">
                            <div class="container-cart cart-product row">
                                <div class="col-md-9 cod-sm-12 row cart-product-detail center">
                                    <div class="col-md-3 col-3 cart-product-img">
                                        <img src="image/product/${cart.prouductAvatar}" alt="">
                                    </div>
                                    <div class="col-md-4 col-3 cart-product-name">
                                        <h6>${cart.productName}</h6>
                                        <p> <fmt:formatNumber value = "${cart.price}" type = "currency"/></p>
                                    </div>
                                    <div class="col-md-5 col-6 center cart-product-quantity">
                                        <form class="form-group cart" action="">
                                            <div class="form-inline">
                                                <div class="">
                                                    <a href="edit?type=1&productID=${cart.productID}"><i class="fas fa-minus"></i></a>
                                                    <input class="form-control input-quantity" type="text" min="0"
                                                           value="${cart.quantity}" max="productQuantity" readonly="true">
                                                    <a href="edit?type=2&productID=${cart.productID}"><i class="fas fa-plus"></i></a>
                                                </div>
                                            </div>
                                            <a href="edit?type=0&productID=${cart.productID}" class="md-hide remove">Xóa<i class="fa fa-trash"></i></a>
                                        </form>
                                    </div>
                                </div>
                                <div class="col-md-3 col-sm-12 cart-product-statistic">
                                    <p>Tổng tiền:</p>
                                    <p>
                                        <fmt:formatNumber value = "${cart.total}" type = "currency"/>
                                    </p>
                                    <a href="edit?type=0&productID=${cart.productID}" class="remove"><i class="fa fa-trash"></i>Xóa</a>
                                </div>
                                <span style="display: none">${totalPrice = totalPrice+ cart.total} </span>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>

                <div class="container-cart cart-total">
                    <c:if test="${sessionScope.listCart ne null}">
                        <h6>
                            Total: <span><h5 style="display: inline-block;">
                                    <fmt:formatNumber value="${totalPrice}" type="currency"/>
                                </h5></span>
                        </h6>
                    </c:if>
                    <a href="home" ><button class="btn btn-light btn-outline-dark" type="submit">Tiếp Tục Mua Hàng </button></a>  
                    <c:if test="${sessionScope.listCart ne null}">
                        <a href="buy" ><button class="btn btn-light btn-outline-dark" type="submit"><span>Đặt hàng ngay </span></button> </a>
                    </c:if>

                </div>
            </section>
        </main>
        <!--Footer-->
        <jsp:include page="common/footer.jsp"></jsp:include>
        </body>
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

    </script>
    
</html>
