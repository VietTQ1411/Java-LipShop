/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Product;
import entity.Type;
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
public class TypeDAO implements ICommonDao<Type> {

    @Override
    public List<Type> getAll() {
        String query = "SELECT        *\n"
                + "FROM            types;";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                List<Type> list = new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        Type p = Type.builder().
                                id(rs.getInt(1)).
                                style(rs.getInt(2)).
                                name(rs.getString(3)).
                                description(rs.getString(4)).
                                build();
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

    public List<Type> getTypeByStyle(int style) {
        String query = "SELECT        *\n"
                + "FROM            types\n"
                + "where style = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, style);
                ResultSet rs = ps.executeQuery();
                 List<Type> list = new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        Type p = Type.builder().
                                id(rs.getInt(1)).
                                style(rs.getInt(2)).
                                name(rs.getString(3)).
                                description(rs.getString(4)).
                                build();
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
    public Type getOne(int id) {
        String query = "SELECT        *\n"
                + "FROM   types where id = ? ";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                List<Type> list = new ArrayList<>();
                if (rs != null) {
                    if (rs.next()) {
                        Type p = Type.builder().
                                id(rs.getInt(1)).
                                style(rs.getInt(2)).
                                name(rs.getString(3)).
                                description(rs.getString(4)).
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

    public static void main(String[] args) {

    }
}
