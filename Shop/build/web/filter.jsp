<%-- 
    Document   : filter
    Created on : Nov 1, 2019, 8:58:33 PM
    Author     : PC
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

%>
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
        <link rel="stylesheet" href="css/category.css">
        <link rel="stylesheet" href="css/card.css">
        <link rel="stylesheet" href="css/footer.css">
    </head>

    <body>
        <jsp:include page="common/navbar.jsp"></jsp:include>
            <!--Main-->
            <main class="center">
                <!--Đường dẫn trang web-->
                <section id="breadcrumb">
                    <h1>Son môi</h1>
                    <li class="breadcrumb-home">
                        <a href="home"><span>Trang chủ</span></a>
                        <span>&nbsp;<i class="fa fa-angle-right"></i>&nbsp;</span>
                    </li>
                <c:forEach items="${requestScope.breadcrumb}" var="mess" varStatus="loop">
                    <c:if test="${loop.index+1 ne requestScope.breadcrumb.size()}">
                        <li class="breadcrumb-home">
                            <a href="home"><span>${mess}</span></a>
                            <span>&nbsp;<i class="fa fa-angle-right"></i>&nbsp;</span>
                        </li>
                    </c:if>
                    <c:if test="${loop.index+1 eq requestScope.breadcrumb.size()}">
                        <li class="breadcrumb-curent">
                            <strong>${mess}</strong>
                        </li></c:if>
                </c:forEach>
            </section>
            <!--Main chính-->
            <section id="maintain">
                <div class="container row">
                    <!--Slider bar-->
                    <div class="col-lg-3 order-lg-12">
                        <a role="button" class="collapsed lg-hide toolbar-btn" data-toggle="collapse" aria-expanded="false" data-target="#slider-bar">
                            Tool Bar
                        </a>
                        <div class="collapse" id="slider-bar">
                            <div>
                                <h3 class="widget-title">Search</h3>
                                <div class="toolbar-search">
                                    <form class="form-group my-2 my-lg-0" id="searchNameInside" method="post" action="filter">
                                        <input class="form-control display-none" type="search" name="style" value="${requestScope.style}">
                                        <input class="form-control display-none" type="search" name="type" value="${requestScope.type}">
                                        <input class="form-control display-none" type="search" name="brand" value="${requestScope.brand}">
                                        <input class="form-control display-none" type="search" name="moneyTo" value="${requestScope.moneyTo}">
                                        <input class="form-control display-none" type="search" name="moneyFrom" value="${requestScope.moneyFrom}">
                                        <input class="form-control mr-sm-2" type="search" 
                                               placeholder="<c:if test="${requestScope.name eq null}">Search</c:if><c:if test="${requestScope.name ne null}">${requestScope.name}</c:if>"
                                                   name="name" aria-label="Search" onchange="getLink()">
                                               <a href="" id="searchNameOutside" onclick="document.forms['searchNameInside'].submit()"><img src="image/background/search_26px.png"></a>
                                        </form>
                                    </div>
                                </div>
                                <div class="toolbar">
                                    <ul class="fontlist toolbar-breadcrumb border-bottom">
                                        <h4 class="title-menu border-bottom">
                                                <li class="li_menu "><a href="filter?${requestScope.href5}">Tất cả</a></li>
                                    </h4>
                                    <c:forEach items="${requestScope.listMenu}" var="menu" varStatus="loop">
                                        <li>
                                            <h4 class="title-menu">
                                                <a role="button" class="collapsed" data-toggle="collapse" data-target="#slide-${loop.index}">
                                                    ${menu.name}
                                                </a>
                                            </h4>
                                            <div class="collapse" id="slide-${loop.index}">
                                                <ul class="list-menu">
                                                    <c:forEach items="${menu.items}" var="items">
                                                        <li class="li_menu ">
                                                            <a href="filter?${requestScope.href4}&style=${menu.id}&brand=${items.id}">${items.name}
                                                            </a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                            <div class=" fillter">
                                <h3 class="widget-title">Fillter by price</h3>
                                <div>
                                    <ul class=" toolbar-fillter">
                                        <li class="<c:if test="${requestScope.markM eq null}">active</c:if>"><a href="filter?${requestScope.href2}">Mặc Định</a></li>
                                        <li class="<c:if test="${requestScope.markM eq 1}">active</c:if>"><a href="filter?${requestScope.href2}&moneyTo=50000"  >nhỏ hơn 50k</a></li>
                                        <li class="<c:if test="${requestScope.markM eq 2}">active</c:if>"><a href="filter?${requestScope.href2}&moneyFrom=50000&moneyTo=100000">50k-100k</a></li>
                                        <li class="<c:if test="${requestScope.markM eq 3}">active</c:if>"><a href="filter?${requestScope.href2}&moneyFrom=100000&moneyTo=200000">100k-200k</a></li>
                                        <li class="<c:if test="${requestScope.markM eq 4}">active</c:if>"><a href="filter?${requestScope.href2}&moneyFrom=200000&moneyTo=500000">200k-500k</a></li>
                                        <li class="<c:if test="${requestScope.markM eq 5}">active</c:if>"><a href="filter?${requestScope.href2}&moneyFrom=500000&moneyTo=1000000">500k-1000k</a></li>
                                        <li class="<c:if test="${requestScope.markM eq 6}">active</c:if>"><a href="filter?${requestScope.href2}&moneyFrom=1000000">lớn hơn 1000k</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--sản phẩm-->
                        <div class="col-md-12 col-lg-9 ">
                            <div class="product-count clearfix">
                                <!--Thống kê-->
                                <div class="count pull-left">
                                    <span>Hiển thị ${requestScope.listProduct.size() eq null?0:requestScope.listProduct.size()}/${requestScope.pageCount} kết quả</span>
                            </div>
                            <div class="order pull-right dropdown">
                                <a href="#" data-toggle="dropdown" aria-expanded="false">
                                    <span>Sắp xếp theo:</span>
                                    <strong>Thứ tự mặc định</strong>
                                    <i class="fa fa-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="filter?${requestScope.href}&order=ORDER BY products.id ASC">Thứ tự mặc định</a></li>
                                    <li><a href="filter?${requestScope.href}&order=ORDER BY products.sold DESC">Thứ tự theo mức độ phổ biến</a></li>
                                    <li><a href="filter?${requestScope.href}&order=ORDER BY products.rate DESC">Thứ tự theo điểm đánh giá</a></li>
                                    <li><a href="filter?${requestScope.href}&order=ORDER BY products.id DESC">Mới nhất</a></li>
                                    <li><a href="filter?${requestScope.href}&order=ORDER BY products.price ASC">Thứ tự theo giá: thấp đến cao</a></li>
                                    <li><a href="filter?${requestScope.href}&order=ORDER BY products.price DESC">Thứ tự theo giá: cao xuống thấp</a></li>
                                </ul>
                            </div>
                        </div>
                        <!--Hiển thị sản phẩm-->
                        <c:if test="${requestScope.listProduct eq null}">
                            <h3 >Không tìm thấy sản phẩm !! </h3>
                            <h3>Vui lòng tìm kiếm sản phẩm khác!!</h3>
                        </c:if>
                        <div class="row">

                            <c:if test="${requestScope.listProduct ne null}">
                                <c:forEach items="${requestScope.listProduct}" var="product">
                                    <div class="col-sm-6 col-md-4  card-product">
                                        <a href="detail?productID=${product.id}">
                                            <div class="card">
                                                <div class="card-img">
                                                    <img src="image/product/${product.avatar}" alt="image">
                                                </div>
                                                <div class="card-body">
                                                    <c:if test="${product.sale ne 0}">
                                                        <div class="card-sale">
                                                            <h2><fmt:formatNumber type="number" maxIntegerDigits="2" value=" ${product.sale*100}" /></p></h2>%
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${product.quantity lt 1}"><div class="sold-out"></div></c:if>
                                                    <h3 class="card-name">${product.name}</h3>
                                                    <div>
                                                        <span class=" card-price px-3">
                                                            <fmt:formatNumber value = "${product.price - product.price*product.sale}" type = "currency"/>
                                                        </span>
                                                        <del class="card-old-price ">
                                                            <c:if test="${product.sale ne 0}"><fmt:formatNumber value = "${product.price}" type = "currency"/></c:if>
                                                            </del>
                                                        </div>
                                                    </div>
                                                    <div class="card-footer">
                                                        <a <c:if test="${product.quantity gt 0}">href="cart?page=home&productID=${product.id}"</c:if><c:if test="${product.quantity lt 1}">onclick="solout()"</c:if> >
                                                            <p>
                                                                <img src="image/background/add_shopping_cart_24px.png" />Add To Cart
                                                            </p>
                                                        </a>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                </c:forEach></c:if>
                            </div>
                        </div>
                        <!--End of showing product-->
                    </div>
                    <div class="pagination">
                    ${requestScope.paging}
                </div>
            </section>
        </main>
        <!--Footer-->
        <jsp:include page="common/footer.jsp"></jsp:include>
        </body>
        <div class="modal fade" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Sản phẩm đã hết</h4>
                        <button type="button" class="close" data-dismiss="modal">×</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        Xin lỗi!! chúng tôi không còn mặt hàng này!! Xin vui lòng thử lại sau...
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>

                </div>
            </div>
        </div>
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

        function solout() {
            $("#myModal").modal();
        }
        
        function getLink() {
            var x = document.forms['searchNameInside']['name'].value;
            document.getElementById("searchNameOutside").href = "filter?${requestScope.href3}" + "name=" + x;
        }
    </script>
</html>