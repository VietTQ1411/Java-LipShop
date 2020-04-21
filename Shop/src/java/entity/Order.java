/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author PC
 */
@Builder
@Getter
@Setter
@ToString
public class Order implements Serializable {

    private int id;
    private int customerID;//người gửi
    private int receiverID;//người nhận
    private double total;
    private int couponsID;//mã giảm giá dc sử dụng
    private String note;
    private Date createDate;//ngày tạo
    private int status;// 5-đã hủy 4-đang sử lý 3-chờ xác nhận 2-đang giao hàng 1-thành công
    

    public String getStatusValue() {
        switch (status) {
            case 1:
                return "Thành công";
            case 2:
                return "Đang giao hàng";
            case 3:
                return "Chờ xác nhận";
            case 4:
                return "Đang xử lý";
            case 5:
                return "Đã hủy đơn";
            default:
                return "Error";
        }
    }
    
    public String getStatusView() {
        switch (status) {
            case 1:
                return "dot-suc";
            case 2:
                return "dot-wait";
            case 3:
                return "dot-wait";
            case 4:
                return "dot-pro";
            case 5:
                return "dot-can";
            default:
                return "Error";
        }
    }
}
