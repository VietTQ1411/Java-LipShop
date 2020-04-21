/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ReceiverDAO;
import entity.Receiver;
import java.util.List;

/**
 *
 * @author PC
 */
public class ReceiverService implements IsCommonService<Receiver> {

    ReceiverDAO dao = new ReceiverDAO();

    @Override
    public List<Receiver> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Receiver getOne(int id) {
        return dao.getOne(id);
    }

    @Override
    public boolean add(Receiver obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int id, Receiver obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int addReceiverReturnID(Receiver obj) {
        return dao.addReceiverReturnID(obj);
    }
}
