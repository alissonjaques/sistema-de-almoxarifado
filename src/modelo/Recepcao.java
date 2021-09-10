/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import static enums.StatusDaEntrega.ENTREGUE;
import static enums.StatusDaEntrega.PARCIALMENTE;
import modelo.interfaces.RecepcaoAlmoxarifado;
import java.util.ArrayList;

/**
 * @author Alisson Jaques
 */
public class Recepcao implements RecepcaoAlmoxarifado {

    private ArrayList<OrdemDeCompra> ordensDeCompra;

    /**
     *
     * @param ordensDeCompra
     */
    public Recepcao(ArrayList<OrdemDeCompra> ordensDeCompra) {
        this.ordensDeCompra = ordensDeCompra;
    }

    /**
     *
     */
    public Recepcao() {

    }

    /**
     *
     */
    private void receberOrdemDeCompra(OrdemDeCompra ordem) {
        getOrdensDeCompra().add(ordem);
    }

    /**
     *
     * @param ordem
     * @param pecasEntrega
     * @param quantidadePorPeca
     * @return as peças para o estrado
     */
    @Override
    public ArrayList<Peca> registrarEntrega(OrdemDeCompra ordem, int[] quantidadePorPeca) {
        int i = 0;
        ArrayList<Peca> listaDePecas = ordem.clone().getPecasOrdemDeCompra();
        ArrayList<Peca> remover = new ArrayList<>();
        ArrayList<Peca> adicionar = new ArrayList<>();
        System.out.println("Aqui\n"+ listaDePecas);
        System.out.println("Novamente\n");
        for(int m = 0; m<quantidadePorPeca.length; m++){
            System.out.println(quantidadePorPeca[m]);
        }
        int quantidadeParaAdicionar;
        for (Peca peca : listaDePecas) {
            if (i < quantidadePorPeca.length) {
                quantidadeParaAdicionar = quantidadePorPeca[i] - 1;
                if (quantidadeParaAdicionar < 0) {
                    remover.add(peca);
                } else {
                    for (int j = 0; j < quantidadeParaAdicionar; j++) {
                        Peca clone = new Peca(peca.getCodigoDeBarrasUpc(),peca.getDescricao());
                        adicionar.add(clone);
                    }
                }
            }
            i++;
        }
                
        listaDePecas.removeAll(remover);
        listaDePecas.addAll(adicionar);
        
        int vetor[] = ordem.getQuantidadePorPeca();
        boolean botao = true;
        
        for(int k=0; k<quantidadePorPeca.length; k++){
            if(quantidadePorPeca[k]!=vetor[k]){
                botao = false;
            }
        }

        if (botao) {
            ordem.setStatus(ENTREGUE);
        } else {
            ordem.setStatus(PARCIALMENTE);
        }

        return listaDePecas;
    }

    /**
     * @return the ordensDeCompra
     */
    public ArrayList<OrdemDeCompra> getOrdensDeCompra() {
        return ordensDeCompra;
    }

    /**
     * @param ordensDeCompra the ordensDeCompra to set
     */
    public void setOrdensDeCompra(ArrayList<OrdemDeCompra> ordensDeCompra) {
        this.ordensDeCompra = ordensDeCompra;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String resultado = "";
        for (OrdemDeCompra ordem : getOrdensDeCompra()) {
            resultado += ordem.toString() + "\n";
        }
        return resultado;
    }
}
