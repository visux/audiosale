/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package salaprove;

/**
 *
 * @author Ge
 */
interface IComponent {

    void add(Object toAdd);

    Object get();

    boolean Remove(Object ToRem);

    boolean set(String[] argm)throws Exception;
}
