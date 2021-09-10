/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.interfaces.PedidoAlmoxarifado;
import java.util.ArrayList;

/**
 * @author Alisson Jaques
 */
public class Pedido implements PedidoAlmoxarifado{

    private Cliente cliente;
    private int codigoDoPedido;
    static private int codigo = 0;
    private ArrayList<String> pecasDoPedido;
    private int quantidadePorPeca[];
    
    public Pedido(Cliente cliente, ArrayList<String> pecasDoPedido, int[] quantidadePorPeca){
        codigo++;
        codigoDoPedido = codigo;
        this.cliente = cliente;
        this.pecasDoPedido = pecasDoPedido;
        this.quantidadePorPeca = quantidadePorPeca;
    }
    
    public Pedido(){
        codigo++;
        codigoDoPedido = codigo;
        pecasDoPedido = new ArrayList<>();
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cnpjCliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the codigoDoPedido
     */
    public int getCodigoDoPedido() {
        return codigoDoPedido;
    }

    /**
     * @param codigoDoPedido the codigoDoPedido to set
     */
    public void setCodigoDoPedido(int codigoDoPedido) {
        this.codigoDoPedido = codigoDoPedido;
    }

    /**
     * @return the codigo
     */
    public static int getCodigo() {
        return codigo;
    }

    /**
     * @return the pecasDoPedido
     */
    public ArrayList<String> getPecasDoPedido() {
        return pecasDoPedido;
    }

    /**
     * @param pecasDoPedido the pecasDoPedido to set
     */
    public void setPecasDoPedido(ArrayList<String> pecasDoPedido) {
        this.pecasDoPedido = pecasDoPedido;
    }
    
    @Override
    public Pedido clone(){
        return new Pedido(getCliente(),getPecasDoPedido(),getQuantidadePorPeca());
    }
    
    /**
     *
     * @return as peças para a rampa
     */
    @Override
    public ArrayList<String> colocarNaRampa(){
        int i = 0;
        ArrayList<String> adicionar = new ArrayList<>();
        for(int m = 0; m<quantidadePorPeca.length; m++){
            System.out.println(quantidadePorPeca[m]);
        }
        int quantidadeParaAdicionar;
        for (String peca : pecasDoPedido) {
            if (i < quantidadePorPeca.length) {
                quantidadeParaAdicionar = quantidadePorPeca[i] - 1;
                if (quantidadeParaAdicionar < 0) {
                    
                } else {
                    for (int j = 0; j < quantidadeParaAdicionar; j++) {
                        adicionar.add(peca);
                    }
                }
            }
            i++;
        }
                
        getPecasDoPedido().addAll(adicionar); 
        return getPecasDoPedido();
    }
    
    public ArrayList<String> cloneListaDePecas(ArrayList<String> listaDePecas){
        ArrayList<String> clone = new ArrayList<>();
        listaDePecas.forEach((peca) -> {
            clone.add(peca);
        });
        return clone;
    }
        
    /**
     * @return the quantidadePorPeca
     */
    public int[] getQuantidadePorPeca() {
        return quantidadePorPeca;
    }
    
    /**
     * @param aCodigo the codigo to set
     */
    public static void setCodigo(int aCodigo) {
        codigo = aCodigo;
    }
    
    /**
     * @param quantidadePorPeca the quantidadePorPeca to set
     */
    public void setQuantidadePorPeca(int[] quantidadePorPeca) {
        this.quantidadePorPeca = quantidadePorPeca;
    }
    
    @Override
    public String toString(){
        int i = 0;
        String resultado = "";
        resultado += "CNPJ do cliente: " + getCliente().getCnpj();
        resultado += "\nCódigo do Pedido: " + getCodigoDoPedido() + "\n";
        for(String peca: getPecasDoPedido()){
            if(i<getQuantidadePorPeca().length){
                resultado += "\nPeca: " + peca + " Qtde: " + getQuantidadePorPeca()[i];
            }
            i++;
        }
        return resultado;
    }
}
