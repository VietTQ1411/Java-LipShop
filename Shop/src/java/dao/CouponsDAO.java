/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Account;
import entity.Coupons;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 *
 * @author PC
 */
public class CouponsDAO implements ICommonDao<Coupons> {

    @Override
    public List<Coupons> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Coupons getOne(int id) {
        String query = "select * from coupons where id = ? and status = 1";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        Coupons p = Coupons.builder()
                                .id(rs.getInt(1)).
                                code(rs.getString(2).trim()).
                                value(rs.getDouble(3) / 100).
                                devideShip(rs.getDouble(4)).
                                status(rs.getInt(5))
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

    public Coupons getOneByCode(String code) {
        String query = "select * from coupons where code = ? and status = 1";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, code);
                ResultSet rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        Coupons p = Coupons.builder()
                                .id(rs.getInt(1)).
                                code(rs.getString(2).trim()).
                                value(rs.getDouble(3) / 100).
                                devideShip(rs.getDouble(4)).
                                status(rs.getInt(5))
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

    public boolean usingCoupons(int id) {
        String query = "UPDATE [dbo].[coupons]\n"
                + "   SET [status] = 0\n"
                + " WHERE id = ?";
        int check = -1;
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

    @Override
    public boolean add(Coupons obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int id, Coupons obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
