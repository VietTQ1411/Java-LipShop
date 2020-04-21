/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Brand;
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
public class BrandDAO implements ICommonDao<Brand> {

    @Override
    public List<Brand> getAll() {
        String query = "select * from brands";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                List<Brand> list = new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        Brand p = Brand.builder().id(rs.getInt(1))
                                .name(rs.getString(2))
                                .status(rs.getBoolean(3))
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
    public Brand getOne(int id) {
        String query = "select * from brands where id = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            ps.setObject(1, id);
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        Brand p = Brand.builder().id(rs.getInt(1))
                                .name(rs.getString(2))
                                .status(rs.getBoolean(3))
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

    public List<Brand> getList(int style) {
        String query = "SELECT        brands.*\n"
                + "FROM            brands INNER JOIN\n"
                + "                         brand_type ON brands.id = brand_type.brand_id INNER JOIN\n"
                + "                         types ON brand_type.type_id = types.id\n"
                + "group by brands.id,brands.name,brands.status,types.style\n"
                + "having types.style = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            ps.setObject(1, style);
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                List<Brand> list = new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        Brand p = Brand.builder().id(rs.getInt(1))
                                .name(rs.getString(2))
                                .status(rs.getBoolean(3))
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
    public boolean add(Brand obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int id, Brand obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        System.out.println(new BrandDAO().getOne(-1));
    }
}
