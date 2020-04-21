/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.CustomerDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 *
 * @author PC
 */
public class CustomerDetailDAO implements ICommonDao<CustomerDetail> {
    
    @Override
    public List<CustomerDetail> getAll() {
        String query = "select * from customer_info";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                List<CustomerDetail> list = new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        CustomerDetail p = CustomerDetail.builder().
                                id(rs.getInt(1))
                                .accID(rs.getInt(2))
                                .name(rs.getString(3))
                                .mobile(rs.getString(4))
                                .gender(rs.getInt(6))
                                .email(rs.getString(5))
                                .address(rs.getString(7))
                                .avatar(rs.getString(8))
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
    public CustomerDetail getOne(int id) {
        String query = "select * from customer_info where id = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                         CustomerDetail p = CustomerDetail.builder().
                                id(rs.getInt(1))
                                .accID(rs.getInt(2))
                                .name(rs.getString(3))
                                .mobile(rs.getString(4))
                                .gender(rs.getInt(6))
                                .email(rs.getString(5))
                                .address(rs.getString(7))
                                .avatar(rs.getString(8))
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
    
    public CustomerDetail getOneByAccID(int accID) {
        String query = "select * from customer_info where acc_id = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, accID);
                ResultSet rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        CustomerDetail p = CustomerDetail.builder().
                                id(rs.getInt(1))
                                .accID(rs.getInt(2))
                                .name(rs.getString(3))
                                .mobile(rs.getString(4))
                                .gender(rs.getInt(6))
                                .email(rs.getString(5))
                                .address(rs.getString(7))
                                .avatar(rs.getString(8))
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
    public boolean add(CustomerDetail obj) {
        String query = "INSERT INTO [dbo].[customer_info]\n"
                + "           ([acc_id]\n"
                + "           ,[name]\n"
                + "           ,[mobile]\n"
                + "           ,[email]\n"
                + "           ,[gender]\n"
                + "           ,[address]\n"
                + "           ,[avatar])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getAccID());
                ps.setObject(2, obj.getName());
                ps.setObject(3, obj.getMobile());
                ps.setObject(4, obj.getEmail());
                ps.setObject(5, obj.getGender());
                ps.setObject(6, obj.getAddress());
                ps.setObject(7, obj.getAvatar());
                check = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
    @Override
    public boolean update(int id, CustomerDetail obj) {
        String query = "UPDATE [dbo].[customer_info]\n"
                + "   SET [name] = ?"
                + "      ,[mobile] = ?"
                + "      ,[gender] = ?"
                + "      ,[address] = ?"
                + " WHERE id = ?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getValiName(obj.getName()));
                ps.setObject(2, obj.getMobile());
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
    
    public int addCustomerInforReturnID(CustomerDetail obj) {
        String query = "INSERT INTO customer_info(name,mobile,email,address) VALUES(?,?,?,?)";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getName());
                ps.setObject(2, obj.getMobile());
                ps.setObject(3, obj.getEmail());
                ps.setObject(4, obj.getAddress());
                
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
}
