<%-- 
    Document   : detail
    Created on : Oct 29, 2019, 4:11:04 PM
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
        <link rel="stylesheet" href="css/animation.css">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="css/detail.css">
        <link rel="stylesheet" href="css/card.css">
        <link rel="stylesheet" href="css/footer.css">
    </head>

    <body>
        <jsp:include page="common/navbar.jsp"></jsp:include>
            <!--Main-->
            <main>

                <!--Đường dẫn trang web-->
                <section id="breadcrumb" class="center">
                    <h1>Son môi</h1>
                    <li class="breadcrumb-home">
                        <a href="home"><span>Trang Chủ</span></a>
                        <span>&nbsp;<i class="fa fa-angle-right"></i>&nbsp;</span>
                    </li>
                    <li class="breadcrumb-home">
                        <a href="filter?type=${requestScope.type.id}"><span>${requestScope.type.name}</span></a>
                    <span>&nbsp;<i class="fa fa-angle-right"></i>&nbsp;</span>
                </li>
                <li class="breadcrumb-curent">
                    <strong>${product.name}</strong>
                </li>
            </section>
            <!--Main chính-->
            <!--Thông tin sản phẩm-->
            <section id="product-detail">
                <div class="row container m-center">
                    <!--Ảnh-->
                    <div id="product-img" class="col-md-6">
                        <div class="container-img">
                            <c:forEach items="${listImage}" var="image">
                                <div class="slides-img">
                                    <img src="image/product/${image.name}">
                                </div>
                            </c:forEach>
                            <a class="prev" onclick="plusSlides(-1)">❮</a>
                            <a class="next" onclick="plusSlides(1)">❯</a>
                            <div class="row">
                                <c:forEach items="${listImage}" var="image" varStatus="loop">
                                    <div class="col-2">
                                        <img class="demo cursor" src="image/product/${image.name}" onclick="currentSlide(${loop.index+1})" alt="son">
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <!--Thông tin chi tiết-->
                    <div class="col-md-6 detail">
                        <!--Tên-->
                        <h1 class="product-title">${product.name}</h1>
                        <div class="product-brand">
                            <div class="product-status"><span>Thương hiệu: <a href="#"><strong>${requestScope.brand.name}</strong></a></span></div>
                            <span class="product-status-spec">|</span>
                            <div class="product-status">Tình Trạng: <span class="${product.quantity gt 0 ? "product-still" : "product-over"}">
                                    ${product.quantity gt 0 ? "Còn hàng" : " Đã hết "}</span></div>
                            <div class="product-rate" title="450 lượt đánh giá">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star-o"></i>
                            </div>
                        </div>
                        <!--Giá-->
                        <div class="pt-4 ">
                            <h3 class="product-price pr-2 current-money">
                                <fmt:formatNumber value = "${product.price - product.price*product.sale}" type = "currency"/>
                            </h3>
                            <h3 class="product-price after-money"  >
                                <c:if test="${product.sale ne 0}">
                                    <del><fmt:formatNumber value = "${product.price}" type = "currency"/></del>
                                </c:if>
                            </h3>
                        </div>
                        <div class="pt-4 product-description">
                            <p>Thể Loại : ${requestScope.type.name}</p>
                            <p class="description">Mô tả : ${product.description}</p>
                        </div>
                        <div class="pt-4 cart">
                            <form id="input-quantity" class="form-group " action="cart" method="post">
                                <p>Số lượng còn lại: ${product.quantity}</p>
                                <div class="form-inline ">
                                    <label for="example-number-input" class=" col-form-label pr-2">Số lượng:</label>
                                    <div class=" center input">
                                        <input type="text" name="productID" style="display: none" value="${product.id}">
                                        <span> <i class="fas fa-minus" onclick="increase(false)"></i></span>
                                        <input class="form-control input-quantity" onchange="checkinput()" type="number" min="0" max="${product.quantity}" 
                                               <c:if test="${product.quantity gt 0}"> value="1" </c:if><c:if test="${product.quantity lt 1}"> value="0" </c:if> name="quantity">
                                               <span> <i class="fas fa-plus " onclick="increase(true)"></i></span>
                                        </div>
                                    </div>

                                </form>
                                <button type="submit" class="btn btn-primary" value="send" 
                                <c:if test="${product.quantity gt 0}">  onclick="document.forms['input-quantity'].submit()"
                                </c:if><c:if test="${product.quantity lt 1}">  onclick="solout()" </c:if> >Add to cart
                                </button>


                                <p>Gọi đặt mua: 0365469543 để nhanh chóng đặt hàng</p>
                            </div>

                        </div>
                    </div>
                </section>
                <!--Thông tin review và đánh giá sản phẩm-->
                <section id="product-review">
                    <div class="infor container">
                        <ul class="nav nav-tabs center" role="tablist">
                            <li class="nav-item tab-header">
                                <a class="nav-link active" data-toggle="tab" href="#detail">Detail</a>
                            </li>
                            <li class="nav-item tab-header">
                                <a class="nav-link" data-toggle="tab" href="#review">Review</a>
                            </li>
                        </ul>
                        <!--detail-->
                        <div class="tab-content mt-3">
                            <div id="detail" class=" tab-pane  active">
                                <p>Son YSL màu 13 Le Orange đỏ cam tươi tắn, trẻ trung luôn nằm trong top những màu son bán chạy nhất của YSL. YSL 13 Le Orange ấn tượng với màu đỏ cam rạng rỡ, chất son mềm mượt, lâu trôi cùng thiết kế sang trọng và đẳng cấp. YSL
                                    Rouge Pur 13 lên màu tốt, không hề kén môi, kể cả với đôi môi sậm màu cũng không cần dùng lót môi, chỉ lướt nhẹ với một lớp YSL Rouge Pur 13 bạn đã hoàn toàn sở hữu một bờ môi tươi tắn đầy sức sống!.</p>
                            </div>
                            <!--review-->
                            <div id="review" class=" tab-pane fade">
                                <div class="container mt-3">
                                    <div class="media border p-3">
                                        <img src="image/admin.jpg" alt="John Doe" class="mr-3 mt-3 rounded-circle">
                                        <div class="media-body">
                                            <h4>John Doe <small><i>Posted on February 19, 2016</i></small></h4>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                            <div class="media p-3">
                                                <img src="image/admin.jpg" alt="Jane Doe" class="mr-3 mt-3 rounded-circle">
                                                <div class="media-body">
                                                    <h4>Jane Doe <small><i>Posted on February 20 2016</i></small></h4>
                                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                                </div>
                                            </div>
                                            <div class="media p-3">
                                                <img src="image/admin.jpg" alt="Jane Doe" class="mr-3 mt-3 rounded-circle">
                                                <div class="media-body">
                                                    <h4>Jane Doe <small><i>Posted on February 20 2016</i></small></h4>
                                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                </section>
                <!--Sản Phẩm Liên Quan-->
                <div class="container">
                    <div class="separator"></div>
                </div>
                <section id="product-related" class="center">

                    <div class="title-head-section center">
                        <h2>SẢN PHẨM BÁN CHẠY</h2>
                        <p>What's hot today</p>
                    </div>

                    <div class="row container m-center">

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
                                        <h3 class="card-name">${product.name}</h3>
                                        <div>
                                            <span class=" card-price px-3">
                                                <fmt:formatNumber value = "${product.price - product.price*product.sale}" type = "currency"/>
                                            </span>
                                            <del class="card-old-price ">
                                                <c:if test="${product.sale ne 0}">
                                                    <fmt:formatNumber value = "${product.price}" type = "currency"/>
                                                </c:if>
                                            </del>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <a href="cart?page=home&productID=${product.id}">
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
            </section>
        </main>

        <!--Footer-->
        <jsp:include page="common/footer.jsp"></jsp:include>
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
            var slideIndex = 1;
            showSlides(slideIndex);

            function plusSlides(n) {
                showSlides(slideIndex += n);
            }

            function currentSlide(n) {
                showSlides(slideIndex = n);
            }

            function showSlides(n) {
                var i;
                var slides = document.getElementsByClassName("slides-img");
                var dots = document.getElementsByClassName("demo");

                if (n > slides.length) {
                    slideIndex = 1
                }
                if (n < 1) {
                    slideIndex = slides.length
                }
                for (i = 0; i < slides.length; i++) {
                    slides[i].style.display = "none";
                }
                for (i = 0; i < dots.length; i++) {
                    dots[i].className = dots[i].className.replace(" active", "");
                }
                slides[slideIndex - 1].style.display = "block";
                dots[slideIndex - 1].className += " active";

            }

            function checkinput() {
                var num = Number.parseInt(document.forms["input-quantity"]["quantity"].value);
                console.log(num);
                if (!(num > 0)) {
                    document.forms["input-quantity"]["quantity"].value = 1;
                }
                if (num >${product.quantity}) {
                    document.forms["input-quantity"]["quantity"].value = ${product.quantity};
                }
                ;
            }
            function increase(x) {
                var num = Number.parseInt(document.forms["input-quantity"]["quantity"].value);
                if (x) {
                    if (num < Number.parseInt(document.forms["input-quantity"]["quantity"].max))
                        document.forms["input-quantity"]["quantity"].value = num + 1;
                } else {
                    if ((num > 1)) {
                        document.forms["input-quantity"]["quantity"].value = num - 1;
                    }
                }
                ;
            }
            function solout() {
                $("#myModal").modal();
            }
        </script>

    </body>

</html>