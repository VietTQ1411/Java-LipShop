/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author PC
 * @param <T>
 */
public interface IsCommonService<T> {
     /**
     *  get all element from database
     * @return
     */
    List<T> getAll();
    
    /**
     *  get one element from database where have id
     * @param id
     * @return
     */
    T getOne(int id);
    
    /**
     *  add in to database object t
     * @param obj
     * @return
     */
    boolean add(T obj);
    
    /**
     *  update element from data where id = id
     * @param id
     * @param obj
     * @return
     */
    boolean update(int id, T obj);
    
    /**
     *  delete element from data where id - id
     * @param id
     * @return
     */
    boolean delete(int id);
    
}
