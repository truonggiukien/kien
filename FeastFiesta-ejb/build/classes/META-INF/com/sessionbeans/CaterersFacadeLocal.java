/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entitybeans.Caterers;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Huynh
 */
@Local
public interface CaterersFacadeLocal {

    void create(Caterers caterers);

    void edit(Caterers caterers);

    void remove(Caterers caterers);

    Caterers find(Object id);

    List<Caterers> findAll();

    List<Caterers> findRange(int[] range);

    int count();
    
}
