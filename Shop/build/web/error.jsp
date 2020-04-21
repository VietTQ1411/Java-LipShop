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
                    <h1>Không thể tìm thấy trang</h1>
                    <li class="breadcrumb-home">
                        <a href="home"><span>Trang Chủ</span></a>
                        <span>&nbsp;<i class="fa fa-angle-right"></i>&nbsp;</span>
                    </li>
                    <li class="breadcrumb-curent">
                        <strong>Page not found</strong>
                    </li>
                </section>
                <section id="content">
                    <div class="center">
                        <h1>
                            404
                        </h1>
                        <p>Xảy ra lỗi trong quá trình tìm kiếm</p>
                        <p>Không thể tìm thấy trang bạn yêu cầu.Vui lòng quay trở về trang chủ.</p>
                        <a href="home"> <button type="submit" class="btn btn-primary button-back"><span>Trang chủ</span></button></a>
                    </div>
                </section>
            </main>
            <!--Footer-->
        <jsp:include page="common/footer.jsp"></jsp:include>
    </body>

</html>