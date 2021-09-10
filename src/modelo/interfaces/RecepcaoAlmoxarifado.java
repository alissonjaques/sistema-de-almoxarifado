/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.interfaces;

import modelo.OrdemDeCompra;
import modelo.Peca;
import java.util.ArrayList;

/**
 * @author Alisson Jaques
 */
public interface RecepcaoAlmoxarifado {
    
    /**
     *
     * @param ordem a ordem de compra
     * @param pecasEntrega a peças entregues pelo fornecedor
     * @param quantidadePorPeca a quantidade por peças entregues
     * @return 
     */
    public ArrayList<Peca> registrarEntrega(OrdemDeCompra ordem, int[] quantidadePorPeca);
}
