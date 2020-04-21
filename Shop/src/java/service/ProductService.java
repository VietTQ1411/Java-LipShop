/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ProductDAO;
import entity.Carts;
import entity.Product;
import java.util.List;

/**
 *
 * @author PC
 */
public class ProductService implements IsCommonService<Product> {

    ProductDAO dao = new ProductDAO();

    @Override
    public List<Product> getAll() {
        return dao.getAll();
    }
    public int addReturnID(Product obj){
        return dao.addReturnID(obj);
    }
    @Override
    public Product getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Product obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int id, Product obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean buyingProduct(List<Carts> list) {
        return dao.buyingProduct(list);
    }
    
        public int checkQuantity(int productID) {
            return dao.checkQuantity(productID);
        }
}
