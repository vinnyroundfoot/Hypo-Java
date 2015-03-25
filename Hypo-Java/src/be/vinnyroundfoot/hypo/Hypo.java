/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vinnyroundfoot.hypo;

/**
 *
 * @author VPI
 */
public class Hypo {

    

    public static float arrondi(float m, int dec) {
        float arr = new Double(Math.pow(10.0, dec+0.0)).floatValue();

        m = (m * arr);
        long m2 = new Double(m).longValue();
        double m3 = (m2 +0.0)/ new Float(arr).longValue();
        return new Double(m3).floatValue();
        
    }

    
    /**
     * Conversion d'un taux périodique vers une autre période
     * @param  taux  (float) taux à convertir
     * @param  pOri (int) période de départ (1:annuel, 2:semestriel, 4:trimestriel, 12:mensuel)
     * @param  pDest (int) période de "destination" (1:annuel, 2:semestriel, 4:trimestriel, 12:mensuel)
     * @param  dec (int) nombre de décimales à appliquer au résultat;
     * @return {float} nombre arronditaux converti
     */    
    public static float convTx (float taux, int pOri, int pDest, int dec) {
        
        if (taux == 0) {
            return 0;
        }
        
        if (pOri == pDest) {
            return taux;
        }else{
            float tx = new Double(Math.pow ( 1+taux+0.0, (pOri + 0.0) / (pDest + 0.0) ) - 1).floatValue();
            return arrondi(tx, dec);
        }
    } ;    

    
    /**
     * Calcul de la mensualité d'un emprunt K0 souscrit pour n périodes
     * à un taux t
     * n pérodes à un taux t
     *
     *             K0 * t
     *   m = ------------------
     *          1 - (1 + t)^-n
     *
     * @param K0 (float)    capital emprunté
     * @param n (int)       nombre de périodes
     * @param t (float)     taux d'intérêt pour la période
     * @param dec (float)   nombre de décimales dans le résultat (optionnel)
     * @return {float}      amortissement de la 1ere période
     */
    public static float vpm (float K0, int n, float t, int dec ) {
      
        if (t == 0) {
            return arrondi(K0 / n,2);
        }
        
        float num = K0 * t;
        float den =  new Double(1 - Math.pow(new Double(1+t), new Double(-n))).floatValue();
        
        return arrondi(num / den,dec);
        
    };
    
    /**
     * Calcul de l'amortissement (A) de 1ere période
     * d'un emprunt K0 souscrit pour n périodes à un taux t
     *
     *           K0 * t
     *   A = -------------
     *        (1 +t)^n -1
     *
     * @param K0 (float)    capital emprunté
     * @param n (int)       nombre de périodes
     * @param t (float)     taux d'intérêt pour la période
     * @param dec (float)   nombre de décimales dans le résultat (optionnel)
     * @return {float}      amortissement de la 1ere période
    */
    public static float princPer1 (float K0, int n, float t, int dec) {

        if (t == 0) {
            return arrondi(K0 / n, dec);
        }
        
        float num = K0 * t;
        float den =  new Double(Math.pow(new Double(1+t), new Double(n))-1).floatValue();
        
        return arrondi(num / den,dec);
        
    };
    
    /**
     * Calcul de l'amortissement (Apn) de la période p
     * d'un emprunt K0 souscrit pour n périodes à un taux t
     *
     *   A = amortissementP1
     *
     *  Apn =  (1 + t)^(p-1) x A
     *
     * @param K0 (float)    capital emprunté
     * @param n (int)       nombre de périodes
     * @param t (float)     taux d'intérêt pour la période
     * @param p (int)       période de calcul de l'amortissement
     * @param dec (float)   nombre de décimales dans le résultat (optionnel)
     * @return {float}      amortissement de la 1ere période
     */
    public static float princPer(float K0, int n, float t, int p, int dec) {
        float A = princPer1(K0, n, t, dec);
        
        if (t==0) {
            return A;
        }
        
        float Apn =  new Double(Math.pow(new Double(1+t), new Double(p - 1)) * A).floatValue();
        
        return arrondi(Apn,dec);
    }
    
    /**
     * Calcul de l'amortissement (Apn2) de la période p
     * d'un emprunt souscrit pour n périodes à un taux t
     * en fonction de l'amortissement d'un période donné (apn1)
     *
     *  Apn2 =  Apn1 * (1 + t)^(k-p)
     *
     * @param n (int)  nombre de périodes
     * @param t (float) taux d'intérêt pour la période
     * @param apn1  (float) montant de l'amortissement à la période p1
     * @param p1 (int) période de l'amortissement donné
     * @param p2 (int) période de calcul de l'amortissement
     * @param dec (int) nombre de décimales dans le résultat (optionnel)
     * @return {float} amortissement de la période p
     */
    public static float princPerP (int n, float t, float apn1, int p1, int p2, int dec) {
        
        if (t == 0) {
            return apn1;
        } 
        
        float apn2 = apn1 * new Double(Math.pow(new Double(1 + t), new Double(p2 - p1))).floatValue();
        return arrondi(apn2,dec);
    };
    
}
