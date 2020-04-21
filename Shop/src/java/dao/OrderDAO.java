/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Order;
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
public class OrderDAO implements ICommonDao<Order> {

    @Override
    public List<Order> getAll() {
        String query = "select * from [dbo].[order]";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                List<Order> list = new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        Order p = Order.builder()
                                .id(rs.getInt(1))
                                .customerID(rs.getInt(2))
                                .receiverID(rs.getInt(3))
                                .total(rs.getDouble(4))
                                .couponsID(rs.getInt(5))
                                .note(rs.getString(6))
                                .createDate(rs.getDate(7))
                                .status(rs.getInt(8))
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
    public Order getOne(int id) {
        String query = "select * from [dbo].[order] where id = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs != null) {
                    if (rs.next()) {
                        Order p = Order.builder().id(rs.getInt(1))
                                .customerID(rs.getInt(2))
                                .total(rs.getDouble(3))
                                .note(rs.getString(4))
                                .status(rs.getInt(6))
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

    public List<Order> getAllByCus(int cusID, boolean done) {
        String and = " and status > 1 and status < 5";
        String query = "SELECT [id]\n"
                + "      ,[customer_info_id]\n"
                + "      ,[receiver_id]\n"
                + "      ,[total_price]\n"
                + "      ,[coupons_id]\n"
                + "      ,[note]\n"
                + "      ,[create_date]\n"
                + "      ,[status]\n"
                + "  FROM [dbo].[order] where customer_info_id = ? " + (done ? "" : and);
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, cusID);
                ResultSet rs = ps.executeQuery();
                List<Order> list = new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        Order p = Order.builder()
                                .id(rs.getInt(1))
                                .customerID(rs.getInt(2))
                                .receiverID(rs.getInt(3))
                                .total(rs.getDouble(4))
                                .couponsID(rs.getInt(5))
                                .note(rs.getString(6))
                                .createDate(rs.getDate(7))
                                .status(rs.getInt(8))
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

    public boolean changeOrder(String orderID, int status) {
        String query = "UPDATE [dbo].[order]\n"
                + "   SET [status] = ?"
                + " WHERE id = ?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, status);
                ps.setObject(2, orderID);
                check = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean add(Order obj) {
        String query = "INSERT INTO [dbo].[order]\n"
                + "           ([customer_info_id]\n"
                + "           ,[total_price]\n"
                + "           ,[note]\n"
                + "           ,[create_date]\n"
                + "           ,[status]\n"
                + "           ,[account_id])\n"
                + "     VALUES\n"
                + "           (?"
                + "           ,?"
                + "           ,?"
                + "           ,?"
                + "           ,?"
                + "           ,?)";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getCustomerID());
                ps.setObject(2, obj.getTotal());
                ps.setObject(3, obj.getNote());
                ps.setObject(4, obj.getStatus());

                check = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean update(int id, Order obj) {
        String query = "UPDATE [dbo].[order]\n"
                + "   SET [customer_info_id] = ?"
                + "      ,[total_price] = ?"
                + "      ,[note] = ?"
                + "      ,[create_date] = ?"
                + "      ,[status] = ?"
                + "      ,[account_id] = ?"
                + " WHERE id = ?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getCustomerID());
                ps.setObject(2, obj.getTotal());
                ps.setObject(3, obj.getNote());
                ps.setObject(4, obj.getStatus());

                ps.setObject(6, id);
                check = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean delete(int id) {
        String query = "delete from [dbo].[order] where id=?";
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

    public int addOrderReturnID(Order obj) {
        String query = "INSERT INTO [dbo].[order]\n"
                + "           ([customer_info_id]\n"
                + "           ,[receiver_id]\n"
                + "           ,[total_price]\n"
                + "           ,[coupons_id]\n"
                + "           ,[note]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getCustomerID());
                ps.setObject(2, obj.getReceiverID());
                ps.setObject(3, obj.getTotal());
                ps.setObject(4, obj.getCouponsID());
                ps.setObject(5, obj.getNote());
                ps.setObject(6, obj.getStatus());
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

    public static void main(String[] args) {
        System.out.println(new OrderDAO().changeOrder("20", 3));
    }
}
