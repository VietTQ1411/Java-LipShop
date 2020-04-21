<%-- 
    Document   : error
    Created on : Oct 29, 2019, 10:31:42 PM
    Author     : PC
--%>

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
        <link rel="stylesheet" href="css/error.css">
        <link rel="stylesheet" href="css/footer.css">

    </head>

    <body>
        <jsp:include page="common/navbar.jsp"></jsp:include>
            <!--Main-->
            <main>
                <!--Đường dẫn trang web-->
                <section id="breadcrumb" class="center">
                    <h1>Đặt hàng Thành công</h1>
                </section>
                <section id="content">
                    <div class="center">
                        <h2>
                        ${requestScope.notify}
                    </h2>
                    <a href="home"> <button type="submit" class="btn btn-primary button-back"><span>Trang chủ</span></button></a>
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