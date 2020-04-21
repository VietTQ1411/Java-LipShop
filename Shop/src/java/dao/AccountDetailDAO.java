/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Account;
import entity.AccountDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 *
 * @author PC
 */
public class AccountDetailDAO implements ICommonDao<AccountDetail> {

    @Override
    public List<AccountDetail> getAll() {
        String query = "select * from account_detail";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                List<AccountDetail> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        AccountDetail p = AccountDetail.builder().id(rs.getInt(1))
                                .name(rs.getString(2))
                                .phone(rs.getString(3))
                                .gender(rs.getInt(4))
                                .address(rs.getString(5))
                                .build();
                        list.add(p);
                    }
                    return list;
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public AccountDetail getOne(int id) {
        String query = "select * from account_detail where id = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs != null) {
                    if (rs.next()) {
                        AccountDetail p = AccountDetail.builder().id(rs.getInt(1))
                                .name(rs.getString(2))
                                .phone(rs.getString(3))
                                .gender(rs.getInt(4))
                                .address(rs.getString(5))
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

    @Override
    public boolean add(AccountDetail obj) {
        String query = "INSERT INTO [dbo].[account_detail]\n"
                + "           ([name]\n"
                + "           ,[mobile]\n"
                + "           ,[gender]\n"
                + "           ,[address])\n"
                + "     VALUES\n"
                + "           (?"
                + "           ,?"
                + "           ,?"
                + "           ,?)";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getName());
                ps.setObject(2, obj.getPhone());
                ps.setObject(3, obj.getGender());
                ps.setObject(4, obj.getAddress());
                check = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean update(int id, AccountDetail obj) {
        String query = "UPDATE [dbo].[account_detail]\n"
                + "   SET [name] = ?"
                + "      ,[mobile] = ?"
                + "      ,[gender] = ?"
                + "      ,[address] =?"
                + " WHERE id= ?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getName());
                ps.setObject(2, obj.getPhone());
                ps.setObject(3, obj.getGender());
                ps.setObject(4, obj.getAddress());
                ps.setObject(5, id);
                check = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean delete(int id) {
        String query = "delete from customer_info where id=?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                check = ps.executeUpdate();

            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

}
