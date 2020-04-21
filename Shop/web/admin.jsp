<%-- 
    Document   : admin
    Created on : Nov 7, 2019, 6:22:56 PM
    Author     : PC
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value = "vi_VN"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="https://kit.fontawesome.com/fe07d3b07b.js" crossorigin="anonymous"></script>
        <!-- DataTables -->

        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css">
        <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
        <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>


        <link rel="stylesheet" href="css/font.css">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="css/cus.css">

    </head>

    <body>
        <jsp:include page="common/navbar.jsp"></jsp:include>
            <!--Main-->
            <main>
            <c:if test="${requestScope.page eq 1}" >
                <jsp:include page="admin/page1.jsp"></jsp:include>
            </c:if>
            <c:if test="${requestScope.page eq 2}" >
                <jsp:include page="admin/page2.jsp"></jsp:include>
            </c:if>
            <c:if test="${requestScope.page eq 3}" >
                <jsp:include page="admin/page3.jsp"></jsp:include>
            </c:if>
            <c:if test="${requestScope.page eq 4}" >
                <jsp:include page="customer/page4.jsp"></jsp:include>
            </c:if>  
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
                $('#example').DataTable();
                $('[data-toggle="tooltip"]').tooltip()
                $('.toast').toast('show');
            });
            function show() {
                var x = document.getElementById("form-change").style.display;
                if (x == "block") {
                    document.getElementById("form-change").style.display = 'none';
                } else {
                    document.getElementById("form-change").style.display = 'block';
                }
            }

            function show2() {
                var x = document.getElementById("form-address").style.display;
                if (x == "block") {
                    document.getElementById("form-address").style.display = 'none';
                } else {
                    document.getElementById("form-address").style.display = 'block';
                }
            }
            function show3() {
                var x = document.getElementById("addPro").style.display;
                if (x == "block") {
                    document.getElementById("addPro").style.display = 'none';
                } else {
                    document.getElementById("addPro").style.display = 'block';
                }
            }
        </script>

    </body>

</html>
