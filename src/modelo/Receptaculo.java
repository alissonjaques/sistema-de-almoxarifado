/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.interfaces.ReceptaculoAlmoxarifado;
import java.util.ArrayList;

/**
 * @author Alisson Jaques
 */
public class Receptaculo implements ReceptaculoAlmoxarifado{
    
    private int codigoCorredor;
    static private int ordem = 0;
    private int ordemNoCorredor;
    private ArrayList<Peca> pecas;
    
    
    public Receptaculo(int codigoCorredor, ArrayList<Peca> pecas){
        ordem++;
        ordemNoCorredor = ordem;
        this.codigoCorredor = codigoCorredor;
        this.pecas = pecas;
    }
    
    public Receptaculo(){
        ordem++;
        ordemNoCorredor = ordem;
        this.pecas = new ArrayList<>();
    }

    /**
     * @return the codigoCorredor
     */
    public int getCodigoCorredor() {
        return codigoCorredor;
    }

    /**
     * @param codigoCorredor the codigoCorredor to set
     */
    public void setCodigoCorredor(int codigoCorredor) {
        this.codigoCorredor = codigoCorredor;
    }

    /**
     * @return the ordem
     */
    public static int getOrdem() {
        return ordem;
    }

    /**
     * @param aOrdem the ordem to set
     */
    public static void setOrdem(int aOrdem) {
        ordem = aOrdem;
    }

    /**
     * @return the ordemNoCorredor
     */
    public int getOrdemNoCorredor() {
        return ordemNoCorredor;
    }

    /**
     * @param ordemNoCorredor the ordemNoCorredor to set
     */
    public void setOrdemNoCorredor(int ordemNoCorredor) {
        this.ordemNoCorredor = ordemNoCorredor;
    }

    /**
     * @return the pecas
     */
    public ArrayList<Peca> getPecas() {
        return pecas;
    }

    /**
     * @param pecas the pecas to set
     */
    public void setPecas(ArrayList<Peca> pecas) {
        this.pecas = pecas;
    }
    
    /**
     *
     * @param peca
     */
    @Override
    public void adicionarPeca(Peca peca){
       pecas.add(peca);
    }
    
    
    
    /**
     *
     * @param peca
     * @return 
     */
    @Override
    public void removerPeca(Peca peca){
        ArrayList<Peca> clone = clone(getPecas());
        clone.stream().filter((peca1) -> (peca1.equals(peca))).forEachOrdered((_item) -> {
            pecas.remove(peca);
        });
    }
    
    public ArrayList<Peca> clone(ArrayList<Peca> pecas){
        ArrayList<Peca> clone = new ArrayList<>();
        getPecas().forEach((peca) -> {
            clone.add(peca);
        });
        return clone;
    }
    
    /**
     *
     * @return 
     */
    @Override
    public String toString(){
        String resultado = "";
        resultado += getCodigoCorredor() + "-" + getOrdemNoCorredor();
        return resultado;
    }

}
