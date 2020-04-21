<%-- 
    Document   : page1
    Created on : Nov 7, 2019, 6:08:57 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <section id="user">

            <div class="row">
                <div class="col-lg-3 col-md-4">
                    <div class="title">
                        <h4>Danh mục quản lý</h4>
                    </div>
                    <div class="slider-bar">
                        <ul>
                            <a class="current">
                                Thông Tin Chung
                            </a>
                            <a href="user?page=2">
                                Đơn hàng
                            </a>
                            <a href="user?page=3">
                                Lịch sử mua hàng
                            </a>
                            <a href="user?page=4">
                                Sản phẩm yêu thích
                            </a>
                        </ul>
                    </div>
                </div>
                <div class="offset-lg-1 col-lg-8 col-md-8">
                    <div>
                        <div class="title">
                            <h4>Thông tin tài khoản</h4>
                        </div>
                        <div class="row user-account">
                            <div class="col-4 col-md-3 user-img">
                                <img src="image/acc/${requestScope.customer.avatar}" alt="">
                                <div class="account-name">
                                    <p>${sessionScope.account.name}</p>
                                </div>

                            </div>
                            <div class="col-8 col-md-9 account-infor">
                                <div class="row infor-items">
                                    <div class="col-5 col-sm-4 col-md-5 col-lg-4">
                                        <span>Email:</span>
                                    </div>
                                    <div class="col-7 col-sm-8 col-md-7 col-lg-8">
                                        <span>${sessionScope.account.user}</span>
                                    </div>
                                </div>
                                <div class="row  infor-items">
                                    <div class="col-5 col-sm-4 col-md-5 col-lg-4">
                                        <span>Mật Khẩu:</span>
                                    </div>
                                    <div class="col-7 col-sm-8 col-md-7 col-lg-8">
                                        <span class="change-pass" onclick="show()">Đổi mật khẩu</span>
                                    </div>
                                    <form action="change-pass" id="form-change" class="form-change-pass" method="post">
                                        <div class="form-inline">
                                            <input type="password" class="form-control" name="olderPass" placeholder="Mật khẩu cũ" required="true">
                                        </div>
                                        <div class="form-inline">
                                            <input type="password" class="form-control" name="newPass" placeholder="Mật khẩu mới" required="true">
                                        </div>
                                        <div class="form-inline">
                                            <input type="password" class="form-control" name="reNewPass" placeholder="Nhập lại mật khẩu mới" required="true">
                                        </div>
                                        <input class="btn btn-primary button-next" value="Đồng ý" readonly onclick="checkPass()">
                                    </form>
                                    
                                </div>
                                <div class="row  infor-items">
                                    <div class="col-5 col-sm-4 col-md-5 col-lg-4">
                                        <span>Loại tài khoản:</span>
                                    </div>
                                    <div class="col-7 col-sm-8 col-md-7 col-lg-8">
                                        <span>${sessionScope.account.type==0?"Quản lý":(sessionScope.account.type==1?"Nhân Viên":"Khách hàng")}</span>
                                    </div>
                                </div>
                                <div class="row  infor-items">
                                    <div class="col-5 col-sm-4 col-md-5 col-lg-4">
                                        <span>Trạng thái:</span>
                                    </div>
                                    <div class="col-7 col-sm-8 col-md-7 col-lg-8">
                                        <span>${sessionScope.account.status==1?"Đang hoạt động":"Tạm dừng"}</span>
                                    </div>
                                </div>
                                <div class="row  infor-items">
                                    <div class="col-5 col-sm-4 col-md-5 col-lg-4">
                                        <span>Ngày Khởi Tạo:</span>
                                    </div>
                                    <div class="col-7 col-sm-8 col-md-7 col-lg-8">
                                        <span>${sessionScope.account.date} </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="user-contact">
                        <div class="title">
                            <h4>Thông tin cá nhân</h4>
                        </div>

                        <div class="user-infor">
                            <div class="row infor-items">
                                <div class="col-5 col-sm-3"><span>Họ Tên:</span></div>
                                <div class="col-7 col-sm-9"><span>${requestScope.customer.name}</span></div>
                            </div>
                            <div class="row infor-items">
                                <div class="col-5 col-sm-3"><span>Số Điện Thoại:</span></div>
                                <div class="col-7 col-sm-9"><span>${requestScope.customer.mobile}</span></div>
                            </div>
                            <div class="row infor-items">
                                <div class="col-5 col-sm-3"><span>Giới tính:</span></div>
                                <div class="col-7 col-sm-9"><span>${requestScope.customer.gender==1?"Nam":"Nữ"}</span></div>
                            </div>
                            <div class="row infor-items">
                                <div class="col-5 col-sm-3"><span>Địa Chỉ:</span></div>
                                <div class="col-7 col-sm-9"><span>${requestScope.customer.address}</span></div>
                            </div>
                            <button type="submit" class="btn btn-primary button-next" onclick="show2()"><span>Thay Đổi Thông Tin</span></button>
                        </div>
                        <div>
                            <form action="changeInfor" id="form-address" class="form-change-address" method="post">
                                <div class="form-group">
                                    <input type="text" class="form-control" name="name" placeholder="Họ và tên" required="true">
                                </div>
                                <div class="form-group">
                                    <input type="tel" class="form-control" name="phone" placeholder="Số điện thoại" required="true">
                                </div>
                                <div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" class="custom-control-input" id="male" name="gender" value="male" ${requestScope.customer.gender==1?"checked":""}>
                                    <label class="custom-control-label" for="male" >Nam</label>
                                </div>
                                <div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" class="custom-control-input" id="female" name="gender" value="female" ${requestScope.customer.gender==1?"":"checked"}>
                                    <label class="custom-control-label" for="female">Nữ</label>
                                </div>
                                <div class="form-group mt-2">
                                    <textarea class="form-control" name="address" rows="3" placeholder="Địa chỉ" required="true"></textarea>
                                </div>
                                <button type="submit" class="btn btn-success button-next"><span>Xác nhận</span></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
