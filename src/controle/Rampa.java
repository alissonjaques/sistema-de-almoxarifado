/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import modelo.*;
import controle.interfaces.RampaAlmoxarifado;
import java.util.ArrayList;

/**
 * @author Alisson Jaques
 */
public class Rampa implements RampaAlmoxarifado {

    private String listaDeBusca;
    private ArrayList<String> pecasDoPedido;
    private Almoxarifado estoque;
    private ArrayList<String> corredor;
    private ArrayList<String> receptaculo;
    private ArrayList<String> codigoProduto;
    private ArrayList<String> codigoUPC;

    public Rampa(ArrayList<String> pecasDoPedido, Almoxarifado estoque) {
        this.pecasDoPedido = pecasDoPedido;
        this.estoque = estoque;
        corredor = new ArrayList<>();
        receptaculo = new ArrayList<>();
        codigoProduto = new ArrayList<>();
        codigoUPC = new ArrayList<>();
        listaDeBusca = "";
    }

    public Rampa() {
        corredor = new ArrayList<>();
        receptaculo = new ArrayList<>();
        codigoProduto = new ArrayList<>();
        codigoUPC = new ArrayList<>();
        listaDeBusca = "";
    }

    /**
     *
     */
    @Override
    public void removePecasDoEstoque() {
        setListaDeBusca("======= Lista de Busca do Pedido ======= \n");
        for (String peca : getPecasDoPedido()) {
            boolean botao = true;
            for (Corredor corredor : getEstoque().getCorredores()) {
                for (Receptaculo receptaculos : corredor.getReceptaculos()) {
                    ArrayList<Peca> clone = cloneListaDePecas(receptaculos.getPecas());
                    for (Peca umaPeca : clone) {
                        if (umaPeca.getCodigoDeBarrasUpc().equals(peca)) {
                            receptaculos.removerPeca(umaPeca);

                            adicionarCorredor("" + corredor.getNumeroCorredor());
                            adicionarReceptaculo(receptaculos.toString());
                            adicionarCodigoProduto(umaPeca.getCodigoDoProduto() + "");
                            adicionarCodigoUPC(umaPeca.getCodigoDeBarrasUpc());
                            setListaDeBusca(getListaDeBusca() + "Corredor: " + corredor.getNumeroCorredor() + " " + receptaculos.toString() + " Ordem da peça: " + umaPeca.getCodigoDoProduto() + " Codigo de Barras:" + umaPeca.getCodigoDeBarrasUpc() + "\n");
                            botao = false;
                            break;
                        }
                    }
                    if (!botao) {
                        break;
                    }
                }
                if (!botao) {
                    break;
                }
            }
        }
    }

    private void adicionarCorredor(String corredor) {
        this.corredor.add(corredor);
    }

    private void adicionarReceptaculo(String receptaculo) {
        this.receptaculo.add(receptaculo);
    }

    private void adicionarCodigoProduto(String codigoProduto) {
        this.codigoProduto.add(codigoProduto);
    }

    private void adicionarCodigoUPC(String codigoUPC) {
        this.codigoUPC.add(codigoUPC);
    }

    public ArrayList<Peca> cloneListaDePecas(ArrayList<Peca> listaDePecas) {
        ArrayList<Peca> clone = new ArrayList<>();
        listaDePecas.forEach((peca) -> {
            clone.add(peca);
        });
        return clone;
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

    /**
     * @return the listaDeBusca
     */
    public String getListaDeBusca() {
        return listaDeBusca;
    }

    /**
     * @return the estoque
     */
    public Almoxarifado getEstoque() {
        return estoque;
    }

    /**
     * @param estoque the estoque to set
     */
    public void setEstoque(Almoxarifado estoque) {
        this.estoque = estoque;
    }

    /**
     * @param listaDeBusca the listaDeBusca to set
     */
    public void setListaDeBusca(String listaDeBusca) {
        this.listaDeBusca = listaDeBusca;
    }

    /**
     * @return the corredor
     */
    public ArrayList<String> getCorredor() {
        return corredor;
    }

    /**
     * @param corredor the corredor to set
     */
    public void setCorredor(ArrayList<String> corredor) {
        this.corredor = corredor;
    }

    /**
     * @return the receptaculo
     */
    public ArrayList<String> getReceptaculo() {
        return receptaculo;
    }

    /**
     * @param receptaculo the receptaculo to set
     */
    public void setReceptaculo(ArrayList<String> receptaculo) {
        this.receptaculo = receptaculo;
    }

    /**
     * @return the codigoProduto
     */
    public ArrayList<String> getCodigoProduto() {
        return codigoProduto;
    }

    /**
     * @param codigoProduto the codigoProduto to set
     */
    public void setCodigoProduto(ArrayList<String> codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    /**
     * @return the codigoUPC
     */
    public ArrayList<String> getCodigoUPC() {
        return codigoUPC;
    }

    /**
     * @param codigoUPC the codigoUPC to set
     */
    public void setCodigoUPC(ArrayList<String> codigoUPC) {
        this.codigoUPC = codigoUPC;
    }
}
