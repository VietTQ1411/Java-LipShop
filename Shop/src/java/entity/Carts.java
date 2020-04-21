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
public class Carts implements Serializable{
    private int productID;
    private String prouductAvatar;
    private String productName;
    private double price;
    private int productQuantity;
    private int quantity;
    private double total;
}
