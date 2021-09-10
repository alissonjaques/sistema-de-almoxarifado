/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 * @author Alisson Jaques
 */
public enum StatusDaEntrega {
     ENTREGUE("Entregue"), PARCIALMENTE("Entregue Parcialmente"), NAOENTREGUE("Não Entregue");

    private String descricao;
    
    /**
     *
     */
    private StatusDaEntrega(String descricao) {
        this.descricao = descricao;
    } 
    
    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
