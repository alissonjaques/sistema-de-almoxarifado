/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import enums.StatusDaEntrega;
import static enums.StatusDaEntrega.NAOENTREGUE;
import java.util.ArrayList;

/**
 * @author Alisson Jaques
 */
public class OrdemDeCompra {

    private StatusDaEntrega status;
    private String codigoDaOrdem;
    private Fornecedor fornecedor;
    private ArrayList<Peca> pecasOrdemDeCompra;
    private int quantidadePorPeca[];

    public OrdemDeCompra(String codigoDaOrdem, Fornecedor fornecedor, ArrayList<Peca> pecasOrdemDeCompra, int[] quantidadePorPeca) {
        this.codigoDaOrdem = codigoDaOrdem;
        this.fornecedor = fornecedor;
        this.pecasOrdemDeCompra = pecasOrdemDeCompra;
        this.quantidadePorPeca = quantidadePorPeca;
        status = NAOENTREGUE;
    }

    public OrdemDeCompra() {
        status = NAOENTREGUE;
    }

    /**
     * @return the fornecedor
     */
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor
     */
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * @return the quantidadePorPeca
     */
    public int[] getQuantidadePorPeca() {
        return quantidadePorPeca;
    }

    /**
     * @param quantidadePorPeca the quantidadePorPeca to set
     */
    public void setQuantidadePorPeca(int[] quantidadePorPeca) {
        this.quantidadePorPeca = quantidadePorPeca;
    }

    /**
     * @return the pecasOrdemDeCompra
     */
    public ArrayList<Peca> getPecasOrdemDeCompra() {
        return pecasOrdemDeCompra;
    }

    /**
     * @param pecasOrdemDeCompra the pecasOrdemDeCompra to set
     */
    public void setPecasOrdemDeCompra(ArrayList<Peca> pecasOrdemDeCompra) {
        this.pecasOrdemDeCompra = pecasOrdemDeCompra;
    }

    /**
     * @return the status
     */
    public StatusDaEntrega getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusDaEntrega status) {
        this.status = status;
    }

    /**
     * @return the codigoDaOrdem
     */
    public String getCodigoDaOrdem() {
        return codigoDaOrdem;
    }

    /**
     * @param codigoDaOrdem the codigoDaOrdem to set
     */
    public void setCodigoDaOrdem(String codigoDaOrdem) {
        this.codigoDaOrdem = codigoDaOrdem;
    }

    @Override
    public OrdemDeCompra clone() {
        return new OrdemDeCompra(getCodigoDaOrdem(), getFornecedor(), getPecasOrdemDeCompra(), getQuantidadePorPeca());
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String resultado = "";
        int i = 0;
        resultado += "Código da Ordem: " + getCodigoDaOrdem();
        resultado += "\nFornecedor: \n" + fornecedor.toString();
        resultado += "\nStatus da Ordem: " + getStatus();
        for (Peca peca : pecasOrdemDeCompra) {
            if (i < quantidadePorPeca.length) {
                resultado += "\nPeca: " + peca.getDescricao() + " Qtde: " + quantidadePorPeca[i];
            }
            i++;
        }
        resultado += "\n";
        return resultado;
    }
}
