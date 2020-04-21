/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.TypeDAO;
import entity.Type;
import java.util.List;

/**
 *
 * @author PC
 */
public class TypeService implements IsCommonService<Type> {

    TypeDAO dao = new TypeDAO();

    @Override
    public List<Type> getAll() {
        return dao.getAll();
    }

    public List<Type> getTypeByStyle(int style) {
        return dao.getTypeByStyle(style);
    }

    @Override
    public Type getOne(int id) {
        return dao.getOne(id);
    }

    @Override
    public boolean add(Type obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int id, Type obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
