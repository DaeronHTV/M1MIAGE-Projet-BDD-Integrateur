/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asmr.dataAccess;

public interface DAO<T> {

    boolean create(T obj);

    T read(String id);

    boolean update(T obj);

    boolean delete(T obj);
}
