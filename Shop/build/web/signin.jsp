<%-- 
    Document   : signin
    Created on : Nov 3, 2019, 5:38:24 PM
    Author     : PC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" href="css/signup.css">
        <link rel="stylesheet" href="css/footer.css">
    </head>

    <body>
        <jsp:include page="common/navbar.jsp"></jsp:include>
            <!--Main-->
            <main>
                <!--Đường dẫn trang web-->
                <section id="breadcrumb" class="center">
                    <h1>Đăng ký tài khoản</h1>
                    <li class="breadcrumb-home">
                        <a href="home"><span>Trang Chủ</span></a>
                        <span>&nbsp;<i class="fa fa-angle-right"></i>&nbsp;</span>
                    </li>
                    <li class="breadcrumb-curent">
                        <strong>Sign up</strong>
                    </li>
                </section>
            <c:if test="${requestScope.mes ne null}">
                <section id="content">
                    <div class="center">
                        <h1>
                            ${requestScope.mes}
                        </h1>
                        <div class="back">
                            <c:if test="${requestScope.next}"> 
                                <a href="login.jsp"><button type="submit" class="btn btn-primary button-back"><span>Đăng nhập ngay</span></button></a>
                            </c:if>
                            <a href="home"><button type="submit" class="btn btn-primary button-back"><span>Trang chủ</span></button></a>
                        </div>
                    </div>
                </section>
            </c:if>
            <c:if test="${requestScope.mes eq null}">
                <section id="content">
                    <div class="center">
                        <h1>
                            Đăng ký tài khoản
                        </h1>
                        <form id="input" class="m-center" action="signin" method="post">
                            <div class="form-group">
                                <input type="email" class="form-control <c:if test="${requestScope.duplicate}">border-red</c:if>" 
                                       name="email" placeholder="Email" <c:if test="${requestScope.email ne null}">value="${requestScope.email}"</c:if> required onchange="changeborder()">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" name="pass" placeholder="Password"
                                       <c:if test="${requestScope.pass ne null}">value="${requestScope.pass}"</c:if> required>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" name="name" placeholder="Họ và Tên" 
                                       <c:if test="${requestScope.name ne null}">value="${requestScope.name}"</c:if> required>
                            </div>
                            <div class="form-group">
                                <input type="tel" class="form-control" name="phone" placeholder="Số điện thoại" 
                                       <c:if test="${requestScope.phone ne null}">value="${requestScope.phone}"</c:if> required>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" value="male" checked>
                                <label class="form-check-label" for="exampleRadios1">
                                    Nam
                                </label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" value="female" <c:if test="${requestScope.gender eq 'female'}">checked</c:if>>
                                <label class="form-check-label" for="gender">
                                    Nữ
                                </label>
                            </div>
                            <div class="form-group">
                                <textarea class="form-control" name="address" rows="3" placeholder="Địa chỉ" required> <c:if test="${requestScope.address ne null}">${requestScope.address}</c:if></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary  button-next"><span>Đăng ký</span></button>
                        </form>
                        <div class="back">
                            <a href="home"><button type="submit" class="btn btn-primary button-back"><span>Quay lại</span></button></a>
                        </div>
                    </div>
                </section>
            </c:if>
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
        function changeborder(){
                document.forms['input']['email'].style.border =  "1px solid #ced4da";
        }
    </script>
</html>
