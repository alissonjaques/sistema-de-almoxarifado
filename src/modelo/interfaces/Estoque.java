/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.interfaces;

import modelo.Corredor;

/**
 * @author Alisson Jaques
 */
public interface Estoque {    
    public void adicionarCorredor(Corredor corredor);
    public void removerCorredor(Corredor corredor);
}
