<%-- 
    Document   : home
    Created on : Oct 29, 2019, 8:14:47 AM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value = "vi_VN"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <link rel="stylesheet" href="css/home.css">
        <link rel="stylesheet" href="css/card.css">
        <link rel="stylesheet" href="css/footer.css">
    </head>

    <body>
        <!--navbar-->
        <jsp:include page="common/navbar.jsp"></jsp:include>
            <!--end navbar-->
            <!--main-->
            <main class="center">
                <section id="logo">
                    <div id="demo" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ul class="carousel-indicators">
                            <li data-target="#demo" data-slide-to="0" class="active"></li>
                            <li data-target="#demo" data-slide-to="1"></li>
                            <li data-target="#demo" data-slide-to="2"></li>
                        </ul>
                        <!-- The slideshow -->
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src="image/background/anhnen.jpg" alt="Shop1">
                            </div>
                            <div class="carousel-item">
                                <img src="image/background/nen-4.jpg" alt="Shop2">
                            </div>
                            <div class="carousel-item">
                                <img src="image/background/nen-3.png" alt="Shop3">
                            </div>
                        </div>

                        <!-- Left and right controls -->
                        <a class="carousel-control-prev" href="#demo" data-slide="prev">
                            <span class="carousel-control-prev-icon"></span>
                        </a>
                        <a class="carousel-control-next" href="#demo" data-slide="next">
                            <span class="carousel-control-next-icon"></span>
                        </a>
                    </div>
                </section>
                <section id="about" class="center">
                    <h1>
                        Chào mừng các bạn đến với Minh Nguyệt Shop
                    </h1>
                    <p>
                        Cửa hàng son môi và mỹ phẩm Minh Nguyệt cung cấp các sản phẩm chính hãng từ các thương hiệu lớn, đảm bảo chất lượng và sức khỏe cho người sử dụng
                    </p>
                </section>
                <section id="banner">
                    <div class="container">
                        <div class="title-head-section">
                            <h2>DANH MỤC NỔI BẬT</h2>
                            <p>What's hot today</p>
                        </div>
                        <div class="row center">
                            <div class="col-md-4">
                                <a href="filter?type=1">
                                    <div class="banner-lip">
                                        <img src="image/background/lip.jpg" alt="">
                                        <p class="btn btn-dark">Son Môi</p>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-4">
                                <a href="filter?type=3">
                                    <div class="banner-skin">
                                        <img src="image/background/Duong-Da-760.png" alt="">
                                        <p class="btn btn-dark">Dưỡng Da</p>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-4">
                                <a href="filter?type=2">
                                    <div class="banner-cosmetics">
                                        <img src="image/background/Trang-Diem-760.png" alt="">
                                        <p class="btn btn-dark">Mỹ Phẩm</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </section>
                <section id="section-product">
                    <div class="container center">
                        <div class="title-head-section center">
                            <h2>SẢN PHẨM MỚI NHẤT</h2>
                            <p>What's hot today</p>
                        </div>
                        <div class="row">
                        <c:forEach items="${requestScope.listProduct}" var="product">
                            <div class="col-sm-6 col-md-4 col-lg-3 card-product">
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
                        </c:forEach>
                    </div>
                </div>
            </section>

            <section id="contact">
                <h3>Hotline</h3>
                <h1>0359329688</h1>
                <p>
                    Chúng tôi cam kết 100% các sản phẩm có nguồn gốc xuất xứ rõ 
                    ràng, đảm bảo chất lượng tốt nhất. Hỗ trợ chị em phụ nữ trên
                    toàn đất nước ngày một trẻ trung và xinh đẹp hơn.
                </p>
            </section>

            <section id="hot-sale">
                <div class="container center">
                    <div class="title-head-section center">
                        <h2>SẢN PHẨM BÁN CHẠY</h2>
                        <p>What's hot today</p>
                    </div>
                    <div class="row">
                        <c:forEach items="${requestScope.listSale}" var="product">
                            <div class="col-sm-6 col-md-4 col-lg-3 card-product">
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
                        </c:forEach>
                    </div>
                </div>
            </section>

            <section id="news">
                <div class="container">
                    <div class="separator"></div>
                </div>
                <!--Tin tức-->
                <div class="container new">
                    <div class="title title-head-section">
                        <div class="sep-line"></div>
                        <h2>TIN TỨC MỚI</h2>

                    </div>
                    <div class="row news">
                        <div class="col-sm-4">
                            <div class="news-img">
                                <img src="image/background/1-5-300x225.jpg" alt="image">
                            </div>

                            <div class="news-content">
                                <p class="date">
                                    14 Tháng Tám, 2019
                                </p>
                                <h4>
                                    Chăm sóc môi đơn giản với 5 nguyên tắc sau
                                </h4>
                                <p>
                                    Một đôi môi mướt mềm và quyến rũ luôn là ao ước của mọi phụ nữ. Do thường xuyên thoa son, làn môi có thể dễ bị khô nứt, sẫm…<a href="#">Xem thêm</a>
                                </p>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="news-img">
                                <img src="image/background/3-1.jpg" alt="image">
                            </div>
                            <div class="news-content">
                                <p class="date">
                                    11 Tháng Tám, 2019
                                </p>
                                <h4>
                                    4 nguyên tắc chăm sóc da trong thời điểm giao mùa
                                </h4>
                                <p>
                                    Thời điểm giao mùa từ hè sang thu cũng chính là thời điểm làn da chịu nhiều sự thay đổi đột ngột khiến da mất đi sự cân bằng. Nếu…<a href="#">Xem thêm</a>
                                </p>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="news-img ">
                                <img src="image/background/nha-dam-lo-hoi.jpg" alt="image">
                            </div>

                            <div class="news-content">
                                <p class="date">
                                    10 Tháng Tám, 2019
                                </p>
                                <h4>
                                    5 cách làm đẹp bằng nha đam hiệu quả
                                </h4>
                                <p>
                                    Trong dân gian, nha đam là phương thuốc làm lành da tự nhiên rất hữu hiệu. Nha đam có thể làm lành vết thương, tẩy sạch tế bào sừng trên…<a href="#">Xem thêm</a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <!-- footer -->
        <jsp:include page="common/footer.jsp"></jsp:include>
            <!-- end footer -->
        </body>
    <jsp:include page="help/soldoutNotify.jsp"></jsp:include>
    <div id="notify">
        <c:if test="${sessionScope.listToast ne null}">
            <c:forEach items="${sessionScope.listToast}" var="toast">
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
            $("#soldOut").modal();
        }
    </script>
</html>