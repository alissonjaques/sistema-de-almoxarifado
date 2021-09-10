/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.interfaces;

import modelo.interfaces.*;
import modelo.Peca;

/**
 * A empilhadeira guardas as peças no almoxarifado.
 * @author Alisson Jaques
 */
public interface EmpilhadeiraAlmoxarifado {
    
    /**
     *  Guarda as peças da entrega, referente à ordem de compra, no estoque.
     */
    public void guardar();
    
    /**
     *
     * @param peca uma peça para consulta
     * @return a quantidade dessa peça no estoque
     */
    public int quantidadeDaMesmaPecaNoEstoque(Peca peca);
}
