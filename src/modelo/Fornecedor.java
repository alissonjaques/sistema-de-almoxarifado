/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import apresentacao.CadastroFornecedor;
import bancodedados.MySqlConnection;
import enums.StatusDaEntrega;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.interfaces.FornecedorAlmoxarifado;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alisson Jaques
 */
public class Fornecedor extends Empresa implements FornecedorAlmoxarifado{
    
    private ArrayList<OrdemDeCompra> ordensDeCompras;
        
    /**
     *
     * @param cnpj
     * @param razaoSocial
     * @param endereco
     * @param numeroDeContato
     */
    public Fornecedor(String cnpj, String razaoSocial, String endereco, String numeroDeContato){
        super(cnpj,razaoSocial,endereco,numeroDeContato);
    }
    
    public Fornecedor(){
    
    }
    
    public void receberOrdemDeCompra(OrdemDeCompra ordemDeCompra){
        ordensDeCompras.add(ordemDeCompra);
    }
    
    /**
     *
     * @param codigoDaOrdem
     * @param status
     */
    @Override
    public void fazerEntrega(String codigoDaOrdem, StatusDaEntrega status){
        for(OrdemDeCompra ordem: ordensDeCompras){
            if(ordem.getCodigoDaOrdem().equals(codigoDaOrdem)){
                ordem.setStatus(status);
            }
        }
    }
    
    public void insertFornecedor() throws SQLException {
        String sql = "insert into Fornecedor(cnpj,razao_social,endereco,telefone) ";
        sql += "values (?, ?, ?, ?)";

        try {
            Connection conn = MySqlConnection.getInstance().getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, getCnpj());
            ps.setString(2, getRazaoSocial());
            ps.setString(3, getEndereco());
            ps.setString(4, getNumeroDeContato());
            ps.execute();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CadastroFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateFornecedor() {
        String sql = "update Fornecedor set razao_social = ?, endereco = ?, telefone = ? where cnpj = ?";

        try {
            Connection conn = MySqlConnection.getInstance().getConn();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, getRazaoSocial());
            ps.setString(2, getEndereco());
            ps.setString(3, getNumeroDeContato());
            ps.execute();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CadastroFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteFornecedor() {
        String sql = "delete from Fornecedor where cnpj = ?";
        try {
            Connection conn = MySqlConnection.getInstance().getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, getCnpj());
            ps.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CadastroFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @return 
     */
    @Override
    public String toString(){
        return super.toString();    
    }
}
