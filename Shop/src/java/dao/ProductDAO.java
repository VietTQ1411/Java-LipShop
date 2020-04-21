/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Carts;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.SQLServerConnection;
import util.Conditions;
import util.Helper;

/**
 *
 * @author PC
 */
public class ProductDAO implements ICommonDao<Product> {

    @Override
    public List<Product> getAll() {
        String query = "SELECT        products.id, products.brand_id, products.name, products.price, "
                + "products.sale, products.quantity, products.type, products.color, products.sold,"
                + " products.rate, images.image_name, products.description\n"
                + "FROM            images INNER JOIN\n"
                + "                         products ON images.product_id = products.id\n"
                + "where images.cover = 1 and products.status != -1;";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                List<Product> list = new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        Product p = Product.builder().
                                id(rs.getInt(1)).
                                brand(rs.getInt(2)).
                                name(rs.getString(3)).
                                price(rs.getDouble(4)).
                                sale(rs.getDouble(5) / 100).
                                quantity(rs.getInt(6)).
                                type(rs.getInt(7)).
                                color(rs.getString(8)).
                                sold(rs.getInt(9)).
                                rate(rs.getDouble(10)).
                                avatar(rs.getString(11)).
                                description(rs.getString(12)).
                                build();
                        list.add(p);
                    }
                    if (list.size() > 0) {
                        return list;
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

    public List<Product> getHotSold(int number, String by) {
        String sql = "SELECT   top(?)     products.id, products.brand_id, products.name, "
                + "products.price, products.sale, products.quantity, products.type,products.color, products.sold,"
                + " products.rate, images.image_name, products.description\n"
                + "FROM            products INNER JOIN\n"
                + "                         images ON products.id = images.product_id\n"
                + "where cover = 1 \n"
                + "order by " + by + " desc;";

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con == null) ? null : con.prepareStatement(sql);) //excure query trong data base
        {
            ps.setObject(1, number);
            if (ps != null) {
                ps.setObject(1, number);
                ResultSet rs = ps.executeQuery();
                List<Product> list = new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        Product p = Product.builder().
                                id(rs.getInt(1)).
                                brand(rs.getInt(2)).
                                name(rs.getString(3)).
                                price(rs.getDouble(4)).
                                sale(rs.getDouble(5) / 100).
                                quantity(rs.getInt(6)).
                                type(rs.getInt(7)).
                                color(rs.getString(8)).
                                sold(rs.getInt(9)).
                                rate(rs.getDouble(10)).
                                avatar(rs.getString(11)).
                                description(rs.getString(12)).
                                build();
                        list.add(p);
                    }
                    if (list.size() > 0) {
                        return list;
                    }
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean buyingProduct(List<Carts> list) {
        String query = " UPDATE [dbo].[products]\n"
                + "   SET [quantity] = ((SELECT        quantity\n"
                + "			FROM            products\n"
                + "			where id = ?)-?)\n"
                + "      ,[sold] = ((SELECT        sold\n"
                + "		    FROM            products\n"
                + "		    where id = ?)+?)\n"
                + " WHERE id = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                for (Carts c : list) {
                    ps.setObject(1, c.getProductID());
                    ps.setObject(2, c.getQuantity());
                    ps.setObject(3, c.getProductID());
                    ps.setObject(4, c.getProductQuantity());
                    ps.setObject(5, c.getProductID());
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
    public Product getOne(int id) {
        String query = "SELECT  products.id, products.brand_id, products.name, products.price, "
                + "products.sale, products.quantity, products.type,products.color, products.sold, products.rate,"
                + " images.image_name, products.description\n"
                + "FROM      products INNER JOIN\n"
                + "   images ON products.id = images.product_id\n"
                + "where cover = 1 and products.id = ? ;";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        Product p = Product.builder().
                                id(rs.getInt(1)).
                                brand(rs.getInt(2)).
                                name(rs.getString(3)).
                                price(rs.getDouble(4)).
                                sale(rs.getDouble(5) / 100).
                                quantity(rs.getInt(6)).
                                type(rs.getInt(7)).
                                color(rs.getString(8)).
                                sold(rs.getInt(9)).
                                rate(rs.getDouble(10)).
                                avatar(rs.getString(11)).
                                description(rs.getString(12)).
                                build();
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
    public boolean add(Product obj) {
        String sql = "INSERT INTO [dbo].[products]\n"
                + "           ([brand_id]\n"
                + "           ,[name]\n"
                + "           ,[price]\n"
                + "           ,[quantity]\n"
                + "           ,[type]\n"
                + "           ,[description]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n)";
        int rs = -1;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con == null) ? null : con.prepareStatement(sql);) //excure query trong data base
        {
            if (ps != null) {
                ps.setObject(1, obj.getBrand());
                ps.setObject(2, obj.getName());
                ps.setObject(3, obj.getPrice());
                ps.setObject(4, obj.getQuantity());
                ps.setObject(5, obj.getType());
                ps.setObject(6, obj.getDescription());
                ps.setObject(7, obj.getStatus());
                 rs = ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return rs > 0;
    }

    public int addReturnID(Product obj){
        String sql = "INSERT INTO [dbo].[products]\n"
                + "           ([brand_id]\n"
                + "           ,[name]\n"
                + "           ,[price]\n"
                + "           ,[color]\n"
                + "           ,[quantity]\n"
                + "           ,[type]\n"
                + "           ,[description]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n)";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getBrand());
                ps.setObject(2, obj.getName());
                ps.setObject(3, obj.getPrice());
                ps.setObject(4, obj.getColor());
                ps.setObject(5, obj.getQuantity());
                ps.setObject(6, obj.getType());
                ps.setObject(7, obj.getDescription());
                ps.setObject(8, obj.getStatus());
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
    @Override
    public boolean update(int id, Product obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Product> Filter(String where, String order, int pageindex, int pagesize) {
        String sql = "SELECT       *\n"
                + "FROM            (SELECT        products.id, products.brand_id, products.name, products.price, products.sale, "
                + "products.quantity, products.type, products.color, products.sold, products.rate, images.image_name,"
                + " products.description,ROW_NUMBER() OVER (" + order + ") as row_num\n"
                + "FROM            products INNER JOIN\n"
                + "                         images ON products.id = images.product_id INNER JOIN\n"
                + "                         types ON products.type = types.id\n"
                + where + ") tlb\n"
                + "WHERE row_num >= (? - 1)*? +1 AND row_num<= ? * ?";

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con == null) ? null : con.prepareStatement(sql);) //excure query trong data base
        {
            if (ps != null) {
                ps.setInt(1, pageindex);
                ps.setInt(2, pagesize);
                ps.setInt(3, pageindex);
                ps.setInt(4, pagesize);
                ResultSet rs = ps.executeQuery();
                List<Product> list = new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        Product p = Product.builder().
                                id(rs.getInt(1)).
                                brand(rs.getInt(2)).
                                name(rs.getString(3)).
                                price(rs.getDouble(4)).
                                sale(rs.getDouble(5) / 100).
                                quantity(rs.getInt(6)).
                                type(rs.getInt(7)).
                                color(rs.getString(8)).
                                sold(rs.getInt(9)).
                                rate(rs.getDouble(10)).
                                avatar(rs.getString(11)).
                                description(rs.getString(12)).
                                build();
                        list.add(p);
                    }
                    if (list.size() > 0) {
                        return list;
                    }
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public int count(String where) {
        String sql = "SELECT COUNT(*) as rownum FROM            products INNER JOIN\n"
                + "                         images ON products.id = images.product_id INNER JOIN\n"
                + "                         types ON products.type = types.id " + where;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con == null) ? null : con.prepareStatement(sql);) //excure query trong data base
        {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return -1;
    }

    public int checkQuantity(int productID) {
        String sql = "SELECT        quantity\n"
                + "FROM            products\n"
                + "where id = ?;";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con == null) ? null : con.prepareStatement(sql);) //excure query trong data base
        {
            if (ps != null) {
                ps.setObject(1, productID);
                ResultSet rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
