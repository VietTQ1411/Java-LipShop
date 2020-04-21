/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CustomerDetailDAO;
import entity.CustomerDetail;
import java.util.List;

/**
 *
 * @author PC
 */
public class CustomerDetailService implements IsCommonService<CustomerDetail> {

    CustomerDetailDAO dao = new CustomerDetailDAO();

    @Override
    public List<CustomerDetail> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CustomerDetail getOne(int id) {
        return dao.getOne(id);
    }

    @Override
    public boolean add(CustomerDetail obj) {
        return dao.add(obj);
    }

    @Override
    public boolean update(int id, CustomerDetail obj) {
       return dao.update(id, obj);
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public CustomerDetail getOneByAccID(int accID) {
      return dao.getOneByAccID(accID);
    }
}
