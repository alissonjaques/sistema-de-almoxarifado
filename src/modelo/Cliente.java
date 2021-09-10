/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import apresentacao.CadastroCliente;
import bancodedados.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.interfaces.ClienteAlmoxarifado;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alisson Jaques
 */
public class Cliente extends Empresa implements ClienteAlmoxarifado {

    private ArrayList<Pedido> pedidos;

    /**
     *
     * @param cnpj
     * @param razaoSocial
     * @param endereco
     * @param numeroDeContato
     */
    public Cliente(String cnpj, String razaoSocial, String endereco, String numeroDeContato) {
        super(cnpj, razaoSocial, endereco, numeroDeContato);
    }

    /**
     *
     */
    public Cliente() {

    }

    /**
     *
     * @param pecasDoPedido
     * @param quantidadePorPeca
     */
    @Override
    public void fazerPedido(ArrayList<String> pecasDoPedido, int[] quantidadePorPeca) {
        Pedido pedido = new Pedido(this, pecasDoPedido, quantidadePorPeca);
        pedidos.add(pedido);
    }

    public void insertCliente() throws SQLException {
        String sql = "insert into Cliente(cnpj,razao_social,endereco,telefone) ";
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
            Logger.getLogger(CadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCliente() {
        String sql = "update Cliente set razao_social = ?, endereco = ?, telefone = ? where cnpj = ?";

        try {
            Connection conn = MySqlConnection.getInstance().getConn();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, getRazaoSocial());
            ps.setString(2, getEndereco());
            ps.setString(3, getNumeroDeContato());
            ps.execute();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteCliente() {
        String sql = "delete from Cliente where cnpj = ?";
        try {
            Connection conn = MySqlConnection.getInstance().getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, getCnpj());
            ps.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
