/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vinnyroundfoot.hypo;

/**
 *
 * @author Vinnyroundfoot
 * 
 */
public class Exemples {
    
    
    public static void main(String [] args) {
        float r = Hypo.convTx(0.01f, 1, 12, 6);
        System.out.println(r);
        
        r = Hypo.convTx(0.00830f, 12, 1, 6);
        System.out.println(r);        

        r = Hypo.convTx(0.003274f, 12, 1, 6);
        System.out.println(r);         

        r = Hypo.vpm(100000.0f, 240, .003274f, 2);
        System.out.println(r);           

        r = Hypo.princPer1(100000.0f, 240, .003274f, 2);
        System.out.println(r);           

        r = Hypo.princPer(100000.0f, 240, .003274f, 1, 2);
        System.out.println(r);          
        
    }
    
}