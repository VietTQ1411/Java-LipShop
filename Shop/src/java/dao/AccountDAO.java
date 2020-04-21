/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Account;
import entity.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 *
 * @author PC
 */
public class AccountDAO implements ICommonDao<Account> {

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

    public int addAccountReturnID(Account obj) {
        String query = "INSERT INTO [dbo].[account]\n"
                + "           ([user_name]\n"
                + "           ,[password]\n"
                + "           ,[name]\n"
                + "           ,[acc_type]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getUser());
                ps.setObject(2, obj.getPass());
                ps.setObject(3, obj.getName());
                ps.setObject(4, obj.getType());
                ps.setObject(5, obj.getStatus());
                int isCheck = ps.executeUpdate();

                if (isCheck > 0) {
                    ResultSet rs = ps.getGeneratedKeys();
                    rs.next();
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return -1;
    }

    public boolean isDuplicate(String email) {
        String query = "select * from account where user_name = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, email);
                ResultSet rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        return true;
                    }
                }
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return true;
    }

    public boolean ChangePass(int id, String pass) {
        String query = "UPDATE [dbo].[account]\n"
                + "   SET [password] = ?\n"
                + " WHERE id = ?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, pass);
                ps.setObject(2, id);
                check = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public Account login(String user, String pass) {
        String query = "select * from account where user_name = ? and password = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, user);
                ps.setObject(2, pass);
                ResultSet rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        Account p = Account.builder()
                                .id(rs.getInt(1)).
                                user(rs.getString(2)).
                                pass(rs.getString(3)).
                                name(rs.getString(4)).
                                date(rs.getString(5)).
                                type(rs.getInt(6)).
                                status(rs.getInt(7))
                                .build();
                        return p;
                    }
                } else {
                    return null;
                }

            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new AccountDAO().addAccountReturnID(Account.builder().user("viettqhe130524@fpt.edu.vn").pass("123")
                .name("Trần Việt").build()));
    }
}
