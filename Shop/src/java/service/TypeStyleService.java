/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.TypeStyleDAO;
import entity.TypeStyle;
import java.util.List;

/**
 *
 * @author PC
 */
public class TypeStyleService implements IsCommonService<TypeStyle>{

    TypeStyleDAO dao = new TypeStyleDAO();
    @Override
    public List<TypeStyle> getAll() {
        return dao.getAll();
    }

    @Override
    public TypeStyle getOne(int id) {
        return dao.getOne(id);
    }

    @Override
    public boolean add(TypeStyle obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int id, TypeStyle obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
