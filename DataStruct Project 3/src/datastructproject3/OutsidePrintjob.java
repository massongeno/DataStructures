/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructproject3;

/**
 *
 * @author Mason
 */
public class OutsidePrintjob extends Printjob {
    double cost = numpages * .10;
    
    public OutsidePrintjob (String n, int np, int up, String fl){
    super(n,np,up,fl);
    }
    
    public double getCost(){
        return cost;
    }
}
