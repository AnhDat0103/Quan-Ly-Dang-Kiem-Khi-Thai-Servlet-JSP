/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;

/**
 *
 * @author DAT
 * @param <T>
 */
public interface Dao<T> {
     
    public int save(T t);
    
    public List<T> findAll();
    
    public int update(T t);
    
    public int delete(int t);
}
