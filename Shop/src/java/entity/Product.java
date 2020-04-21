/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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
public class Product implements Serializable {

    private int id;
    private int brand;
    private String name;
    private double price;
    private double sale;
    private int quantity;
    private int type;
    private int sold;
    private double rate;
    private String avatar;
    private String color;
    private String description;
    private int status;

    public double getRealPrice() {
        return (price - (price * sale));
    }

    public String getProStatus() {
        if (quantity == 0) {
            return "Hết hàng";
        } else if (sold != 0) {
            return "Đang giảm giá";
        } else if (status == 2) {
            return "Ngừng bán";
        } else {
            return "Đang bán";
        }
    }
}
