/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.interfaces;

import java.util.ArrayList;

/**
 * @author Alisson Jaques
 */
public interface ClienteAlmoxarifado {
    
    public void fazerPedido(ArrayList<String> pecasDoPedido, int[] quantidadePorPeca);
}
