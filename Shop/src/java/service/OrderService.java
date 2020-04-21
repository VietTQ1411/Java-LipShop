/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.OrderDAO;
import entity.Order;
import java.util.List;

/**
 *
 * @author PC
 */
public class OrderService implements IsCommonService<Order> {

    OrderDAO dao = new OrderDAO();

    @Override
    public List<Order> getAll() {
       return dao.getAll();
    }

    @Override
    public Order getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Order obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int id, Order obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Order> getAllByCus(int cusID,boolean done) {
        return dao.getAllByCus(cusID,done);
    }
    public boolean changeOrder(String orderID,int status){
        return dao.changeOrder(orderID,status);
    }
    public int addOrderReturnID(Order obj) {
        return dao.addOrderReturnID(obj);
    }
}
