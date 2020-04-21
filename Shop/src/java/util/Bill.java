/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.CustomerDetail;
import entity.Order;
import entity.OrderDetail;
import entity.Receiver;
import java.util.List;
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
public class Bill {
    private Order order;
    private CustomerDetail customer;
    private Receiver recei;
    private List<OrderDetail> list;
}
