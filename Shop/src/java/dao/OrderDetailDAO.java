/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Brand;
import entity.Carts;
import entity.OrderDetail;
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
public class OrderDetailDAO implements ICommonDao<OrderDetail> {

    @Override
    public List<OrderDetail> getAll() {
        String query = "select * from order_detail";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                List<OrderDetail> list = new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        OrderDetail p = OrderDetail.builder().id(rs.getInt(1))
                                .orderID(rs.getInt(2))
                                .productID(rs.getInt(3))
                                .productName(rs.getString(4))
                                .productPrice(rs.getDouble(5))
                                .quantity(rs.getInt(1))
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

    public List<OrderDetail> getAllbyOrderID(int id) {
        String query = "SELECT        order_detail.*, images.image_name\n"
                + "FROM            order_detail INNER JOIN\n"
                + "                         products ON order_detail.product_id = products.id INNER JOIN\n"
                + "                         images ON products.id = images.product_id\n"
                + "where images.cover = 1 and order_id = ? ";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                List<OrderDetail> list = new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        OrderDetail p = OrderDetail.builder().id(rs.getInt(1))
                                .orderID(rs.getInt(2))
                                .productID(rs.getInt(3))
                                .productName(rs.getString(4))
                                .productPrice(rs.getDouble(5))
                                .quantity(rs.getInt(6))
                                .avatar(rs.getString(7))
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
    public OrderDetail getOne(int id) {
        String query = "select * from order_detail where id = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                List<OrderDetail> list = new ArrayList<>();
                if (rs != null) {
                    if (rs.next()) {
                        OrderDetail p = OrderDetail.builder().id(rs.getInt(1))
                                .orderID(rs.getInt(2))
                                .productID(rs.getInt(3))
                                .productName(rs.getString(4))
                                .productPrice(rs.getDouble(5))
                                .quantity(rs.getInt(1))
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

    public boolean addListCart(List<Carts> list, int order_id) {
        String query = "INSERT INTO order_detail(order_id,product_id,product_name,product_price,quantity) VALUES(?,?,?,?,?)";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                for (Carts c : list) {
                    ps.setObject(1, order_id);
                    ps.setObject(2, c.getProductID());
                    ps.setObject(3, c.getProductName());
                    ps.setObject(4, c.getPrice());
                    ps.setObject(5, c.getQuantity());
                    ps.addBatch();
                }

                int[] isCheck = ps.executeBatch();

                return isCheck.length > 0;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public boolean add(OrderDetail obj) {
        String query = "INSERT INTO order_detail(order_id,product_id,product_name,product_price,quantity) VALUES(?,?,?,?,?)";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getOrderID());
                ps.setObject(2, obj.getProductID());
                ps.setObject(3, obj.getProductName());
                ps.setObject(4, obj.getProductPrice());
                ps.setObject(5, obj.getQuantity());
                check = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean update(int id, OrderDetail obj) {
        String query = "UPDATE [dbo].[order_detail]\n"
                + "   SET [order_id] = ?"
                + "      ,[product_id] = ?"
                + "      ,[product_name] = ?"
                + "      ,[product_price] = ?"
                + "      ,[quantity] = ?"
                + " WHERE id = ?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getOrderID());
                ps.setObject(2, obj.getProductID());
                ps.setObject(3, obj.getProductName());
                ps.setObject(4, obj.getProductPrice());
                ps.setObject(5, obj.getQuantity());
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
        String query = "delete from [dbo].[order_detail] where id=?";
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

    public int addCustomerInforReturnID(OrderDetail obj) {
        String query = "INSERT INTO order_detail(order_id,product_id,product_name,product_price,quantity) VALUES(?,?,?,?,?)";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getOrderID());
                ps.setObject(2, obj.getProductID());
                ps.setObject(3, obj.getProductName());
                ps.setObject(4, obj.getProductPrice());
                ps.setObject(5, obj.getQuantity());
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
        System.out.println(new OrderDetailDAO().getAllbyOrderID(18));
    }
}
