/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Type;
import entity.TypeStyle;
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
public class TypeStyleDAO implements ICommonDao<TypeStyle> {

    @Override
    public List<TypeStyle> getAll() {
        String query = "SELECT        type_style.*\n"
                + "FROM            type_style";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                List<TypeStyle> list = new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        TypeStyle p = TypeStyle.builder().
                                id(rs.getInt(1)).
                                name(rs.getString(2)).
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
    public TypeStyle getOne(int id) {
        String query = "SELECT        type_style.* \n"
                + "FROM            type_style "
                + "where type_style.id = ? ";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                List<TypeStyle> list = new ArrayList<>();
                if (rs != null) {
                    if (rs.next()) {
                        TypeStyle p = TypeStyle.builder().
                                id(rs.getInt(1)).
                                name(rs.getString(2)).
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
