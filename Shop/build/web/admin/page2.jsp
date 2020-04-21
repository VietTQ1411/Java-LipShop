<%-- 
    Document   : product
    Created on : Nov 7, 2019, 6:28:38 PM
    Author     : PC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value = "vi_VN"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <section id="order">
        <div class="">
            <div class="row">
                <div class="col-lg-3 col-md-4">
                    <div class="title">
                        <h4>Danh mục quản lý</h4>
                    </div>
                    <div class="slider-bar">
                        <ul>
                            <a  href="admin?page=2" >
                                Dark board
                            </a>
                            <a class="current">
                                Quản lý sản phẩm
                            </a>
                            <a  href="admin?page=3">
                                Quản lý đơn hàng
                            </a>
                            <a href="admin?page=4">
                                Quản lý tài khoản
                            </a>
                        </ul>
                    </div>
                </div>
                <div class=" col-lg-9 col-md-8">
                    <div>
                        <div class="title">
                            <h4> Thêm sản phẩm mới</h4>
                        </div>
                        <div class="ml-3">
                            <button type="submit" class="btn btn-success button-next" onclick="show3()"><span>Thêm sản phẩm mới</span></button>
                            <p class="mt-3">Ấn nút để thêm một sản phẩm mới vào kho hàng.</p>
                        </div>
                        <div id="addPro" class="add-product ml-2">
                            <form action="add-product" id="add" method="post" enctype="multipart/form-data" acceptcharset="UTF-8">
                                <div class="form-group row">
                                    <div class="col-4">
                                        <label>Tên sản phẩm:</label>
                                    </div>
                                    <div class="col-8">
                                        <input type="text" class="form-control" name="name" placeholder="Tên sản phẩm">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-4">
                                        <label>Thương hiệu:</label>
                                    </div>
                                    <div class="col-8">
                                        <select name="brand" class="form-control">
                                            <option value="-1" selected>-----------------</option>
                                            <c:forEach items="${requestScope.listBrand}" var="brands">
                                                <option value="${brands.id}">${brands.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-4">
                                        <label>Giá:</label>
                                    </div>
                                    <div class="col-8">
                                        <input type="text" class="form-control" name="price" placeholder="Giá sản phẩm">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-4">
                                        <label>Số lượng sản phẩm:</label>
                                    </div>
                                    <div class="col-8">
                                        <input type="text" class="form-control" name="quantity" placeholder="Số lượng sản phẩm">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-4">
                                        <label>Thể loại:</label>
                                    </div>
                                    <div class="col-8">
                                        <select name="style" class="form-control">
                                            <option value="-1">-----------------</option>
                                            <c:forEach items="${requestScope.listType}" var="style">
                                                <option value="${style.id}">${style.name}</option>
                                            </c:forEach>
                                            <!--<option value="other">Thêm thể loại</option>-->
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-4">
                                        <label>Màu son:</label>
                                    </div>
                                    <div class="col-8">
                                        <input type="text" class="form-control" name="color" placeholder="Màu son">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-4">
                                        <label>Ảnh sản phẩm</label>
                                    </div>
                                    <div class="col-8">
                                        <input type="file" class="form-control-file" name="file" multiple>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-4">
                                        <label>Mô tả :</label>
                                    </div>
                                    <div class="col-8">
                                        <textarea class="form-control" name="note" rows="3" placeholder="Mô tả"></textarea>
                                    </div>
                                </div>
                                <div class="center">
                                    <button type="submit" class="btn btn-success button-next"><span>Xác nhận</span></button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div>
                        <div class="title">
                            <h4>Danh sách sản phẩm</h4>
                        </div>
                        <div class="order">
                            <div class="table-responsive-xl table-data">
                                <table id="example" class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Mã SP</th>
                                            <th>Tên sản phẩm</th>
                                            <th>Số lượng</th>
                                            <th>Giá</th>
                                            <th>Trạng thái</th>
                                            <th>Chi tiết</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.listProduct}" var="pro" varStatus="loop">
                                            <tr>
                                                <td>
                                                    <p>${loop.index+1}</p>
                                                </td>
                                                <td>
                                                    <h6>
                                                        ${pro.id}
                                                    </h6>
                                                </td>
                                                <td>
                                                    <p>${pro.name}</p>
                                                </td>
                                                <td>
                                                    <p>${pro.quantity}</p>
                                                </td>
                                                <td>
                                                    <p>${pro.price}</p>
                                                </td>
                                                <td>
                                                    <p>${pro.proStatus}</p>
                                                </td>
                                                 <td>
                                                    <p>Chi tiết</p>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

</html>
