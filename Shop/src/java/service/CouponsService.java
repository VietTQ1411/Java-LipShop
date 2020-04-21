/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CouponsDAO;
import entity.Coupons;
import java.util.List;

/**
 *
 * @author PC
 */
public class CouponsService implements IsCommonService<Coupons> {

    CouponsDAO cdao = new CouponsDAO();

    @Override
    public List<Coupons> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Coupons getOne(int id) {
        return cdao.getOne(id);
    }

    @Override
    public boolean add(Coupons obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int id, Coupons obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Coupons getOneByCode(String code) {
        return cdao.getOneByCode(code);
    }
       
    public boolean usingCoupons(int id) {
        return cdao.usingCoupons(id);
    }
}
