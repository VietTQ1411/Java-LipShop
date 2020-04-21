/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Images;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 *
 * @author CUONG
 */
public class ImageDAO implements ICommonDao<Images> {

    @Override
    public List<Images> getAll() {
        String query = "select * from images";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                List<Images> list = new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        Images p = Images.builder().id(rs.getInt(1))
                                .productId(rs.getInt(2))
                                .name(rs.getString(3))
                                .cover(rs.getBoolean(4))
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
    public List<Images> getAllByProductID(int proID) {
        String query = "select * from images where product_id = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, proID);
                ResultSet rs = ps.executeQuery();
                List<Images> list = new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        Images p = Images.builder().id(rs.getInt(1))
                                .productId(rs.getInt(2))
                                .name(rs.getString(3))
                                .cover(rs.getBoolean(4))
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
    public Images getOne(int id) {
        String query = "select * from images where id = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        Images p = Images.builder().id(rs.getInt(1))
                                .productId(rs.getInt(2))
                                .name(rs.getString(3))
                                .cover(rs.getBoolean(4))
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
    public Images getOneByProductID(int productID) {
        String query = "select * from images where product_id = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, productID);
                ResultSet rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        Images p = Images.builder().id(rs.getInt(1))
                                .productId(rs.getInt(2))
                                .name(rs.getString(3))
                                .cover(rs.getBoolean(4))
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
    public boolean add(Images obj) {
        String query = "insert into images(product_id,image_name,cover)"
                + "values(?,?,?)";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getProductId());
                ps.setObject(2, obj.getName());
                ps.setObject(3, obj.isCover());

                check = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean update(int id, Images obj) {
        String query = "update images set product_id=?,image_name=?,cover=?"
                + " where id = ?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setInt(1, obj.getProductId());
                ps.setString(2, obj.getName());
                ps.setBoolean(3, obj.isCover());
                ps.setInt(4, id);
                check = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean delete(int id) {
        String query = "delete from images where id=?";
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
    public static void main(String[] args) {
        System.out.println(new ImageDAO().getOne(2));
        
        System.out.println(new ImageDAO().update(2, Images.builder().id(2).name("son-kem-li-nhe-moi-maybelline-02.jpg").productId(2).cover(false).build()));
    }
}
