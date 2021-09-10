/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.interfaces.Estoque;
import java.util.ArrayList;

/**
 * @author Alisson Jaques
 */
public class Almoxarifado implements Estoque {
    
    private ArrayList<Corredor> corredores;
    
    public Almoxarifado(ArrayList<Corredor> corredores){
        this.corredores = corredores;
    }
    
    public Almoxarifado(){
        this.corredores = new ArrayList<>();
    }

    /**
     * @return the corredores
     */
    public ArrayList<Corredor> getCorredores() {
        return corredores;
    }

    /**
     * @param corredores the corredores to set
     */
    public void setCorredores(ArrayList<Corredor> corredores) {
        this.corredores = corredores;
    }
    
    /**
     *
     * @param corredor
     */
     @Override
    public void adicionarCorredor(Corredor corredor){
        corredores.add(corredor);
    }
    
    /**
     *
     * @param corredor
     */
     @Override
    public void removerCorredor(Corredor corredor){
        corredores.remove(corredor);
    }
    /**
     *
     * @return 
     */
    @Override
    public String toString(){
        String resultado = "";
        for(Corredor corredor: corredores){
            resultado += corredor.toString();
        }
        return resultado;    
    }
}
