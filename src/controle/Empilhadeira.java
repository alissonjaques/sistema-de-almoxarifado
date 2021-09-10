/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import modelo.*;
import controle.interfaces.EmpilhadeiraAlmoxarifado;
import java.util.ArrayList;

/**
 * @author Alisson Jaques
 */
public class Empilhadeira implements EmpilhadeiraAlmoxarifado {

    private String listaDeDistribuicao;
    private int capacidadeReceptaculo = 10;
    private int capacidadeCorredor = 10;
    private ArrayList<Peca> estrado;
    private Almoxarifado estoque;
    private ArrayList<String> corredor;
    private ArrayList<String> receptaculo;
    private ArrayList<String> codigoProduto;
    private ArrayList<String> codigoUPC;

    /**
     * @param estrado o array de peça com suas respectivas quantidades
     * @param estoque o estoque do almoxarifado
     */
    public Empilhadeira(ArrayList<Peca> estrado, Almoxarifado estoque) {
        this.estrado = estrado;
        this.estoque = estoque;
        corredor = new ArrayList<>();
        receptaculo = new ArrayList<>();
        codigoProduto = new ArrayList<>();
        codigoUPC = new ArrayList<>();
        listaDeDistribuicao = "";
    }

    /**
     *
     */
    public Empilhadeira() {
        corredor = new ArrayList<>();
        receptaculo = new ArrayList<>();
        codigoProduto = new ArrayList<>();
        codigoUPC = new ArrayList<>();
        listaDeDistribuicao = "";    
    }

    @Override
    public void guardar() {
        setListaDeDistribuicao("======= Lista de Distribuição do Pedido ======= \n");
        for (Peca peca : getEstrado()) {
            if (getEstoque().getCorredores().isEmpty()) {
                ArrayList<Peca> listaPeca = new ArrayList<>();
                ArrayList<Receptaculo> listaReceptaculo = new ArrayList<>();
                ArrayList<Corredor> listaCorredores = new ArrayList<>();
                Corredor corredor = new Corredor();
                listaPeca.add(peca);
                Receptaculo receptaculo = new Receptaculo(corredor.getNumeroCorredor(), listaPeca);
                listaReceptaculo.add(receptaculo);
                corredor.setReceptaculos(listaReceptaculo);
                listaCorredores.add(corredor);
                getEstoque().setCorredores(listaCorredores);

                adicionarCorredor("" + corredor.getNumeroCorredor());
                adicionarReceptaculo(receptaculo.toString());
                adicionarCodigoProduto(peca.getCodigoDoProduto() + "");
                adicionarCodigoUPC(peca.getCodigoDeBarrasUpc());
                setListaDeDistribuicao(getListaDeDistribuicao() + "Corredor: " + corredor.getNumeroCorredor() + " Receptáculo: " + receptaculo.toString() + " Código da Peça: " + peca.getCodigoDoProduto() + " Codigo de Barras da Peça: " + peca.getCodigoDeBarrasUpc() + "\n");
            } else {
                ArrayList<Corredor> cloneCorredor = clonaListaDeCorredores(getEstoque().getCorredores());
                boolean controlador = true;
                for (Corredor corredor1 : cloneCorredor) {
                    if (corredor1.getReceptaculos().size() < getCapacidadeCorredor()) {
                        boolean botao = true;
                        for (Receptaculo receptaculo : corredor1.getReceptaculos()) {
                            ArrayList<Peca> clone = clonaListaDePecas(receptaculo.getPecas());
                            for (Peca peca1 : clone) {
                                if (peca1.getCodigoDeBarrasUpc().equals(peca.getCodigoDeBarrasUpc()) && receptaculo.getPecas().size() < getCapacidadeReceptaculo()) {
                                    if (botao) {
                                        receptaculo.adicionarPeca(peca);

                                        adicionarCorredor("" + corredor1.getNumeroCorredor());
                                        adicionarReceptaculo(receptaculo.toString());
                                        adicionarCodigoProduto(peca.getCodigoDoProduto() + "");
                                        adicionarCodigoUPC(peca.getCodigoDeBarrasUpc());
                                        setListaDeDistribuicao(getListaDeDistribuicao() + "Corredor: " + corredor1.getNumeroCorredor() + " Receptáculo: " + receptaculo.toString() + " Código da Peça: " + peca.getCodigoDoProduto() + " Codigo de Barras da Peça: " + peca.getCodigoDeBarrasUpc() + "\n");
                                        botao = false;
                                    }
                                }
                            }

                            if (!botao) {
                                break;
                            }
                        }

                        for (Receptaculo receptaculo : corredor1.getReceptaculos()) {
                            if (botao) {
                                ArrayList<Peca> novaListaPeca = new ArrayList<>();
                                novaListaPeca.add(peca);
                                Receptaculo receptaculoNovo = new Receptaculo(corredor1.getNumeroCorredor(), novaListaPeca);
                                corredor1.adicionarReceptaculo(receptaculoNovo);

                                adicionarCorredor("" + corredor1.getNumeroCorredor());
                                adicionarReceptaculo(receptaculoNovo.toString());
                                adicionarCodigoProduto(peca.getCodigoDoProduto() + "");
                                adicionarCodigoUPC(peca.getCodigoDeBarrasUpc());
                                setListaDeDistribuicao(getListaDeDistribuicao() + "Corredor: " + corredor1.getNumeroCorredor() + " Receptáculo: " + receptaculoNovo.toString() + " Código da Peça: " + peca.getCodigoDoProduto() + " Codigo de Barras da Peça: " + peca.getCodigoDeBarrasUpc() + "\n");
                                break;
                            }
                        }
                        controlador = false;
                    }
                    if (!controlador) {
                        break;
                    }
                }

                for (Corredor corredor : cloneCorredor) {
                    if (controlador) {
                        ArrayList<Peca> listaPeca = new ArrayList<>();
                        ArrayList<Receptaculo> listaReceptaculo = new ArrayList<>();
                        Corredor corredor3 = new Corredor();
                        listaPeca.add(peca);
                        Receptaculo receptaculo = new Receptaculo(corredor3.getNumeroCorredor(), listaPeca);
                        listaReceptaculo.add(receptaculo);
                        corredor3.setReceptaculos(listaReceptaculo);
                        getEstoque().adicionarCorredor(corredor3);

                        adicionarCorredor("" + corredor3.getNumeroCorredor());
                        adicionarReceptaculo(receptaculo.toString());
                        adicionarCodigoProduto(peca.getCodigoDoProduto() + "");
                        adicionarCodigoUPC(peca.getCodigoDeBarrasUpc());
                        setListaDeDistribuicao(getListaDeDistribuicao() + "Corredor: " + corredor3.getNumeroCorredor() + " Receptáculo: " + receptaculo.toString() + " Código da Peça: " + peca.getCodigoDoProduto() + " Codigo de Barras da Peça: " + peca.getCodigoDeBarrasUpc() + "\n");
                        break;
                    }
                }
            }
        }

    }

    private void adicionarCorredor(String corredor){
        this.corredor.add(corredor);    
    }
    
    private void adicionarReceptaculo(String receptaculo){
        this.receptaculo.add(receptaculo);
    }
    
    private void adicionarCodigoProduto(String codigoProduto){
        this.codigoProduto.add(codigoProduto);
    }
    
    private void adicionarCodigoUPC(String codigoUPC){
        this.codigoUPC.add(codigoUPC);
    }
    
    public ArrayList<Peca> clonaListaDePecas(ArrayList<Peca> lista) {
        ArrayList<Peca> clone = new ArrayList<>(lista.size());

        lista.forEach((peca) -> {
            clone.add(peca);
        });

        return clone;
    }

    public ArrayList<Corredor> clonaListaDeCorredores(ArrayList<Corredor> lista) {
        ArrayList<Corredor> clone = new ArrayList<>(lista.size());

        lista.forEach((corredor) -> {
            clone.add(corredor);
        });

        return clone;
    }

    /**
     * @param peca uma peça para consulta
     */
    @Override
    public int quantidadeDaMesmaPecaNoEstoque(Peca peca) {
        int quantidade = 0;
        for (Corredor corredor : getEstoque().getCorredores()) {
            for (Receptaculo receptaculos : corredor.getReceptaculos()) {
                for (Peca umaPeca : receptaculos.getPecas()) {
                    if (umaPeca.equals(peca)) {
                        quantidade++;
                    }
                }
            }
        }
        return quantidade;
    }

    /**
     * @return the capacidadeCorredor
     */
    public int getCapacidadeCorredor() {
        return capacidadeCorredor;
    }

    /**
     * @return the capacidadeReceptaculo
     */
    public int getCapacidadeReceptaculo() {
        return capacidadeReceptaculo;
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
     * @return the estrado
     */
    public ArrayList<Peca> getEstrado() {
        return estrado;
    }

    /**
     * @param estrado the estrado to set
     */
    public void setEstrado(ArrayList<Peca> estrado) {
        this.estrado = estrado;
    }

    /**
     * @return the listaDeDistribuicao
     */
    public String getListaDeDistribuicao() {
        return listaDeDistribuicao;
    }

    /**
     * @param listaDeDistribuicao the listaDeDistribuicao to set
     */
    public void setListaDeDistribuicao(String listaDeDistribuicao) {
        this.listaDeDistribuicao = listaDeDistribuicao;
    }

    /**
     * @param capacidadeReceptaculo the capacidadeReceptaculo to set
     */
    public void setCapacidadeReceptaculo(int capacidadeReceptaculo) {
        this.capacidadeReceptaculo = capacidadeReceptaculo;
    }

    /**
     * @param capacidadeCorredor the capacidadeCorredor to set
     */
    public void setCapacidadeCorredor(int capacidadeCorredor) {
        this.capacidadeCorredor = capacidadeCorredor;
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
