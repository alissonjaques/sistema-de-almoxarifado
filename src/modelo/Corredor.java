/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.interfaces.CorredorAlmoxarifado;
import java.util.ArrayList;

/**
 * @author Alisson Jaques
 */
public class Corredor implements CorredorAlmoxarifado{
      
    static private int numero = 0;
    private int numeroCorredor;
    private ArrayList<Receptaculo> receptaculos;
    
    public Corredor(ArrayList<Receptaculo> receptaculos){
        numero++;
        numeroCorredor = numero;
        this.receptaculos = receptaculos;
    }
    
    public Corredor(){
        numero++;
        numeroCorredor = numero;
        this.receptaculos = new ArrayList<>();
    }

    /**
     * @return the numero
     */
    public static int getNumero() {
        return numero;
    }

    /**
     * @param aNumero the numero to set
     */
    public static void setNumero(int aNumero) {
        numero = aNumero;
    }

    /**
     * @return the numeroCorredor
     */
    public int getNumeroCorredor() {
        return numeroCorredor;
    }

    /**
     * @param numeroCorredor the numeroCorredor to set
     */
    public void setNumeroCorredor(int numeroCorredor) {
        this.numeroCorredor = numeroCorredor;
    }

    /**
     * @return the receptaculos
     */
    public ArrayList<Receptaculo> getReceptaculos() {
        return receptaculos;
    }

    /**
     * @param receptaculos the receptaculos to set
     */
    public void setReceptaculos(ArrayList<Receptaculo> receptaculos) {
        this.receptaculos = receptaculos;
    }
    
    /**
     * @param receptaculo
     */
    @Override
    public void adicionarReceptaculo(Receptaculo receptaculo){
        receptaculos.add(receptaculo);
    }
    
    /**
     *
     * @param receptaculo
     */
    @Override
    public void removerReceptaculo(Receptaculo receptaculo){
        receptaculos.remove(receptaculo);
    }
    
    /**
     *
     */
    @Override
    public String toString(){
        String resultado = "";
        resultado += "Numero do Corredor: " + getNumeroCorredor();
        resultado += "\nReceptáculos do corredor:\n";
        for(Receptaculo receptaculo: receptaculos){
            resultado += getNumeroCorredor() + "-" + receptaculo.getOrdemNoCorredor()+"\n";
        }
        return resultado;
    }
}
