<%-- 
    Document   : login
    Created on : Nov 3, 2019, 5:36:26 PM
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
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="css/footer.css">
        <link rel="stylesheet" href="css/animation.css">
    </head>

    <body>
        <jsp:include page="common/navbar.jsp"></jsp:include>
            <!--Main-->
            <main>
                <!--Đường dẫn trang web-->
                <section id="breadcrumb" class="center">
                    <h1>Đăng Nhập</h1>
                    <li class="breadcrumb-home">
                        <a href="home"><span>Trang Chủ</span></a>
                        <span>&nbsp;<i class="fa fa-angle-right"></i>&nbsp;</span>
                    </li>
                    <li class="breadcrumb-curent">
                        <strong>Đăng nhập</strong>
                    </li>
                </section>
                <section id="content">
                    <div class="center">
                    <c:if test="${requestScope.notify ne null}"><h1>${requestScope.notify}</h1></c:if>
                        <h1>Đăng nhập tài khoản</h1>
                        <form id="input" class="form-group m-center" method="post" action="login">
                            <div class="form-group">
                                <input type="text" class="form-control <c:if test="${requestScope.error}">border-red</c:if>" 
                                       name="user" placeholder="   Tên đăng nhập" onchange="changeborder()" 
                                <c:if test="${requestScope.user ne null}">value="${requestScope.user}"</c:if>>
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" name="pass" placeholder="   Mật Khẩu" 
                                <c:if test="${requestScope.pass ne null}">value="${requestScope.pass}"</c:if>>
                            </div>
                            <div class="custom-control custom-checkbox mt-1">
                                <input type="checkbox" class="custom-control-input" id="customCheck" name="remember">
                                <label class="custom-control-label" for="customCheck">Remember me</label>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary button-next"><span>Đăng nhập</span></button>
                            </div>
                        </form>
                        <h6>
                            Bạn chưa có tài khoản, vui lòng đăng ký <a href="signin.jsp"><span>Tại đây</span></a>
                        </h6>
                        <h6>
                            <a href="http://">  Quên mật khẩu?</a>

                        </h6>
                    </div>
                </section>
            </main>
            <!--Footer-->
        <jsp:include page="common/footer.jsp"></jsp:include> 
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

        function changeborder() {
            document.forms['input']['user'].style.border = "1px solid #ced4da";
            document.forms['input']['pass'].value = '';
        }
    </script>
    </body>
   
</html>
