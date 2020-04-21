/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.AccountDAO;
import entity.Account;
import java.util.List;

/**
 *
 * @author PC
 */
public class AccountService implements IsCommonService<Account> {

    AccountDAO dao = new AccountDAO();

    @Override
    public List<Account> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Account getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Account obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int id, Account obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Account login(String user,String pass){
        return dao.login(user, pass);
    }
    public boolean isDuplicate(String email) {
        return dao.isDuplicate(email);
    }
    public boolean ChangePass(int id, String pass) {
        return dao.ChangePass(id, pass);
    }
    public int addAccountReturnID(Account obj) {
        return dao.addAccountReturnID(obj);
    }
}
