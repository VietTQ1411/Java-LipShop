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
public class OrderDetail implements Serializable{
    private int id;
    private int orderID;
    private int productID;
    private String productName;
    private double productPrice;
    private int quantity;
    private String avatar;
}
