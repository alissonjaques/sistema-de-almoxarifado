/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao;

import bancodedados.MySqlConnection;
import modelo.Almoxarifado;
import modelo.Cliente;
import modelo.Corredor;
import controle.Empilhadeira;
import modelo.Fornecedor;
import modelo.OrdemDeCompra;
import modelo.Pedido;
import controle.Rampa;
import static enums.StatusDaEntrega.ENTREGUE;
import static enums.StatusDaEntrega.NAOENTREGUE;
import static enums.StatusDaEntrega.PARCIALMENTE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Recepcao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Alisson Jaques
 */
public class TelaInicial extends javax.swing.JFrame {
 
    private List<OrdemDeCompra> listaDeOrdensDeCompra;    
    private List<Pedido> listaDePedidos;
    private List<Fornecedor> listaDeFornecedores;
    private List<Cliente> listaDeClientes;
    private CadastroCliente telaCliente;
    private CadastroFornecedor telaFornecedor;
    private EfetuarPedido telaPedido;
    private TelaDeLogin telaLogin;
    private OrdemCompra telaOrdemCompra;
    private ReceberCompra telaReceberCompra;
    private Almoxarifado estoque;
    private ArrayList<Corredor> listaDeCorredores;
    private Recepcao recepcao;
    private Empilhadeira empilhadeira;
    private Rampa rampa;

    /**
     * Creates new form TelaInicial
     */
    public TelaInicial() throws SQLException {
        this.listaDeClientes = new ArrayList<>();
        preencherClienteDB();
        this.listaDeFornecedores = new ArrayList<>();
        preencherFornecedoresDB();
        this.listaDeOrdensDeCompra = new ArrayList<>();
        preencherListaDeOrdensDeCompraDB();
        this.listaDePedidos = new ArrayList<>();
        preencherListaDePedidosDB();
        recepcao = new Recepcao();
        recepcao.setOrdensDeCompra((ArrayList) listaDeOrdensDeCompra);
        listaDeCorredores = new ArrayList<>();
        empilhadeira = new Empilhadeira();
        rampa = new Rampa();
        estoque = new Almoxarifado(getListaDeCorredores());
        initComponents();
    }

    private void centralizarJInternalFrame(JInternalFrame frame) {

        int lDesk = getJdpPrincipal().getWidth();
        int aDesk = getJdpPrincipal().getHeight();

        int lFrame = frame.getWidth();
        int aFrame = frame.getHeight();

        frame.setLocation(lDesk / 2 - lFrame / 2, aDesk / 2 - aFrame / 2);

    }

    private void preencherClienteDB() throws SQLException{
        
        try {
            Connection conn = MySqlConnection.getInstance().getConn();
            String sql = "select * from Cliente";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String cnpj = rs.getString("cnpj");
                String razaoSocial = rs.getString("razao_social");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
               
                Cliente cliente = new Cliente(cnpj,razaoSocial,endereco,telefone);
                listaDeClientes.add(cliente);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(getTelaCliente(), "Erro ao obter os dados do banco de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        } 
    }
    
    private void preencherFornecedoresDB() throws SQLException{
        
        try {
            Connection conn = MySqlConnection.getInstance().getConn();
            String sql = "select * from Fornecedor";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String cnpj = rs.getString("cnpj");
                String razaoSocial = rs.getString("razao_social");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
               
                Fornecedor fornecedor = new Fornecedor(cnpj,razaoSocial,endereco,telefone);
                listaDeFornecedores.add(fornecedor);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(getTelaFornecedor(), "Erro ao obter os dados do banco de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        } 
    }
    
    private void preencherListaDeOrdensDeCompraDB() throws SQLException{
        
        try {
            Connection conn = MySqlConnection.getInstance().getConn();
            String sql = "select * from Ordem_de_compra";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String codigoDaOrdem = rs.getString("id_compra");
                String cnpjFornecedor = rs.getString("id_fornecedor");
                String status = rs.getString("status");
                
                Fornecedor fornecedor = new Fornecedor();
                for(Fornecedor fornecedor1: getListaDeFornecedores()){
                    if(fornecedor1.getCnpj().equals(cnpjFornecedor)){
                        fornecedor = fornecedor1;
                    }
                }
                
                OrdemDeCompra ordemDeCompra = new OrdemDeCompra(codigoDaOrdem,fornecedor,null,null);
                if(status.equals("Entregue")){
                   ordemDeCompra.setStatus(ENTREGUE);
                }
                else if(status.equals("Entregue Parcialmente")){
                    ordemDeCompra.setStatus(PARCIALMENTE);
                }
                else{
                    ordemDeCompra.setStatus(NAOENTREGUE);
                }
                
                listaDeOrdensDeCompra.add(ordemDeCompra);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(getTelaOrdemCompra(), "Erro ao obter os dados do banco de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        } 
    }
    
     private void preencherListaDePedidosDB() throws SQLException{
        
        try {
            Connection conn = MySqlConnection.getInstance().getConn();
            String sql = "select * from Pedido";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String codigoDoPedido = rs.getString("id_pedido");
                String cnpjCliente = rs.getString("id_cliente");
                
                int idPedido;

                try {
                    idPedido = Integer.parseInt(codigoDoPedido);
                } catch (NumberFormatException ex) {
                    idPedido = 1;
                }
                
                Cliente cliente = new Cliente();
                for(Cliente cliente1: getListaDeClientes()){
                    if(cliente1.getCnpj().equals(cnpjCliente)){
                        cliente = cliente1;
                    }
                }
                
                Pedido pedido = new Pedido(cliente,null,null);
                pedido.setCodigoDoPedido(idPedido);                              
                listaDePedidos.add(pedido);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(getTelaOrdemCompra(), "Erro ao obter os dados do banco de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        } 
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jdpPrincipal = new javax.swing.JDesktopPane();
        jmbMenus = new javax.swing.JMenuBar();
        jmLogin = new javax.swing.JMenu();
        jmiLogin = new javax.swing.JMenuItem();
        jmiSair = new javax.swing.JMenuItem();
        jmCadastro = new javax.swing.JMenu();
        jmiCliente = new javax.swing.JMenuItem();
        jmiFornecedor = new javax.swing.JMenuItem();
        jmOrdens = new javax.swing.JMenu();
        jmiSolicitarCompra = new javax.swing.JMenuItem();
        jmiReceberCompra = new javax.swing.JMenuItem();
        jmPedido = new javax.swing.JMenu();
        jmiItemPedido = new javax.swing.JMenuItem();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Almoxarifado");

        jdpPrincipal.setBackground(new java.awt.Color(238, 238, 238));
        jdpPrincipal.setPreferredSize(new java.awt.Dimension(856, 751));

        javax.swing.GroupLayout jdpPrincipalLayout = new javax.swing.GroupLayout(jdpPrincipal);
        jdpPrincipal.setLayout(jdpPrincipalLayout);
        jdpPrincipalLayout.setHorizontalGroup(
            jdpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 856, Short.MAX_VALUE)
        );
        jdpPrincipalLayout.setVerticalGroup(
            jdpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jmLogin.setText("Login");

        jmiLogin.setText("Login");
        jmiLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiLoginActionPerformed(evt);
            }
        });
        jmLogin.add(jmiLogin);

        jmiSair.setText("Sair");
        jmiSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSairActionPerformed(evt);
            }
        });
        jmLogin.add(jmiSair);

        jmbMenus.add(jmLogin);

        jmCadastro.setText("Cadastro");
        jmCadastro.setEnabled(false);
        jmCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCadastroActionPerformed(evt);
            }
        });

        jmiCliente.setText("Cliente");
        jmiCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiClienteActionPerformed(evt);
            }
        });
        jmCadastro.add(jmiCliente);

        jmiFornecedor.setText("Fornecedor");
        jmiFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFornecedorActionPerformed(evt);
            }
        });
        jmCadastro.add(jmiFornecedor);

        jmbMenus.add(jmCadastro);

        jmOrdens.setText("Ordens");
        jmOrdens.setEnabled(false);

        jmiSolicitarCompra.setText("Solicitar Compra");
        jmiSolicitarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSolicitarCompraActionPerformed(evt);
            }
        });
        jmOrdens.add(jmiSolicitarCompra);

        jmiReceberCompra.setText("Receber Compra");
        jmiReceberCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiReceberCompraActionPerformed(evt);
            }
        });
        jmOrdens.add(jmiReceberCompra);

        jmbMenus.add(jmOrdens);

        jmPedido.setText("Pedido");
        jmPedido.setEnabled(false);

        jmiItemPedido.setText("Efetuar Pedido");
        jmiItemPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiItemPedidoActionPerformed(evt);
            }
        });
        jmPedido.add(jmiItemPedido);

        jmbMenus.add(jmPedido);

        setJMenuBar(jmbMenus);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jmiFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFornecedorActionPerformed
        if (getTelaFornecedor() == null) {
            setTelaFornecedor(new CadastroFornecedor(getListaDeFornecedores()));
            getJdpPrincipal().add(getTelaFornecedor());
            getTelaFornecedor().setVisible(true);
            centralizarJInternalFrame(getTelaFornecedor());
        } else if (getTelaFornecedor() != null) {
            if (getTelaFornecedor().isClosed()) {
                setTelaFornecedor(new CadastroFornecedor(getListaDeFornecedores()));
                getJdpPrincipal().add(getTelaFornecedor());
                getTelaFornecedor().setVisible(true);
                centralizarJInternalFrame(getTelaFornecedor());
            }
        }
    }//GEN-LAST:event_jmiFornecedorActionPerformed

    private void jmiReceberCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReceberCompraActionPerformed
        if (getTelaReceberCompra() == null) {
            setTelaReceberCompra(new ReceberCompra(getListaDeOrdensDeCompra(), getEstoque(), getListaDeCorredores(), getRecepcao(), getEmpilhadeira()));
            getJdpPrincipal().add(getTelaReceberCompra());
            getTelaReceberCompra().setVisible(true);
            centralizarJInternalFrame(getTelaReceberCompra());
        } else if (getTelaReceberCompra() != null) {
            if (getTelaReceberCompra().isClosed()) {
                setTelaReceberCompra(new ReceberCompra(getListaDeOrdensDeCompra(), getEstoque(), getListaDeCorredores(), getRecepcao(), getEmpilhadeira()));
                getJdpPrincipal().add(getTelaReceberCompra());
                getTelaReceberCompra().setVisible(true);
                centralizarJInternalFrame(getTelaReceberCompra());
            }
        }
    }//GEN-LAST:event_jmiReceberCompraActionPerformed

    private void jmiClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiClienteActionPerformed
        if (getTelaCliente() == null) {
            setTelaCliente(new CadastroCliente(getListaDeClientes()));
            getJdpPrincipal().add(getTelaCliente());
            getTelaCliente().setVisible(true);
            centralizarJInternalFrame(getTelaCliente());
        } else if (getTelaCliente() != null) {
            if (getTelaCliente().isClosed()) {
                setTelaCliente(new CadastroCliente(getListaDeClientes()));
                getJdpPrincipal().add(getTelaCliente());
                getTelaCliente().setVisible(true);
                centralizarJInternalFrame(getTelaCliente());
            }
        }
    }//GEN-LAST:event_jmiClienteActionPerformed

    private void jmiItemPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiItemPedidoActionPerformed
        if (getTelaPedido() == null) {
            setTelaPedido(new EfetuarPedido(getListaDePedidos(),getListaDeClientes(),getRampa(),getEstoque()));
            getJdpPrincipal().add(getTelaPedido());
            getTelaPedido().setVisible(true);
            centralizarJInternalFrame(getTelaPedido());
        } else if (getTelaPedido() != null) {
            if (getTelaPedido().isClosed()) {
                setTelaPedido(new EfetuarPedido(getListaDePedidos(),getListaDeClientes(),getRampa(),getEstoque()));
                getJdpPrincipal().add(getTelaPedido());
                getTelaPedido().setVisible(true);
                centralizarJInternalFrame(getTelaPedido());
            }
        }
    }//GEN-LAST:event_jmiItemPedidoActionPerformed

    private void jmiLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiLoginActionPerformed
        TelaDeLogin tl = new TelaDeLogin(this, true);
        tl.setVisible(true);
    }//GEN-LAST:event_jmiLoginActionPerformed

    private void jmiSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSairActionPerformed
        int op = JOptionPane.showConfirmDialog(this, "Deseja mesmo sair do sistema?", "Sair", JOptionPane.OK_CANCEL_OPTION);

        if (op == 0) {
            dispose();
        }
    }//GEN-LAST:event_jmiSairActionPerformed

    private void jmiSolicitarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSolicitarCompraActionPerformed
        if (getTelaOrdemCompra() == null) {
            setTelaOrdemCompra(new OrdemCompra(getListaDeOrdensDeCompra(), getListaDeFornecedores()));
            getJdpPrincipal().add(getTelaOrdemCompra());
            getTelaOrdemCompra().setVisible(true);
            centralizarJInternalFrame(getTelaOrdemCompra());
        } else if (getTelaOrdemCompra() != null) {
            if (getTelaOrdemCompra().isClosed()) {
                setTelaOrdemCompra(new OrdemCompra(getListaDeOrdensDeCompra(), getListaDeFornecedores()));
                getJdpPrincipal().add(getTelaOrdemCompra());
                getTelaOrdemCompra().setVisible(true);
                centralizarJInternalFrame(getTelaOrdemCompra());
            }
        }
    }//GEN-LAST:event_jmiSolicitarCompraActionPerformed

    private void jmCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmCadastroActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    public javax.swing.JDesktopPane jdpPrincipal;
    private javax.swing.JMenu jmCadastro;
    private javax.swing.JMenu jmLogin;
    private javax.swing.JMenu jmOrdens;
    private javax.swing.JMenu jmPedido;
    private javax.swing.JMenuBar jmbMenus;
    private javax.swing.JMenuItem jmiCliente;
    private javax.swing.JMenuItem jmiFornecedor;
    private javax.swing.JMenuItem jmiItemPedido;
    private javax.swing.JMenuItem jmiLogin;
    private javax.swing.JMenuItem jmiReceberCompra;
    private javax.swing.JMenuItem jmiSair;
    private javax.swing.JMenuItem jmiSolicitarCompra;
    // End of variables declaration//GEN-END:variables
    
    /**
     * @return the rampa
     */
    public Rampa getRampa() {
        return rampa;
    }

    /**
     * @param rampa the rampa to set
     */
    public void setRampa(Rampa rampa) {
        this.rampa = rampa;
    }
    
    /**
     * @return the listaDeFornecedores
     */
    public List<Fornecedor> getListaDeFornecedores() {
        return listaDeFornecedores;
    }

    /**
     * @param listaDeFornecedores the listaDeFornecedores to set
     */
    public void setListaDeFornecedores(List<Fornecedor> listaDeFornecedores) {
        this.listaDeFornecedores = listaDeFornecedores;
    }

    /**
     * @return the listaDeClientes
     */
    public List<Cliente> getListaDeClientes() {
        return listaDeClientes;
    }

    /**
     * @param listaDeClientes the listaDeClientes to set
     */
    public void setListaDeClientes(List<Cliente> listaDeClientes) {
        this.listaDeClientes = listaDeClientes;
    }

    /**
     * @return the telaCliente
     */
    public CadastroCliente getTelaCliente() {
        return telaCliente;
    }

    /**
     * @param telaCliente the telaCliente to set
     */
    public void setTelaCliente(CadastroCliente telaCliente) {
        this.telaCliente = telaCliente;
    }

    /**
     * @return the telaPedido
     */
    public EfetuarPedido getTelaPedido() {
        return telaPedido;
    }

    /**
     * @param telaPedido the telaPedido to set
     */
    public void setTelaPedido(EfetuarPedido telaPedido) {
        this.telaPedido = telaPedido;
    }

    /**
     * @return the telaLogin
     */
    public TelaDeLogin getTelaDeLogin() {
        return getTelaLogin();
    }

    /**
     * @param telaLogin the telaLogin to set
     */
    public void setTelaDeLogin(TelaDeLogin telaLogin) {
        this.setTelaLogin(telaLogin);
    }

    /**
     * @return the telaOrdemCompra
     */
    public OrdemCompra getTelaOrdemCompra() {
        return telaOrdemCompra;
    }

    /**
     * @param telaOrdemCompra the telaOrdemCompra to set
     */
    public void setTelaOrdemCompra(OrdemCompra telaOrdemCompra) {
        this.telaOrdemCompra = telaOrdemCompra;
    }

    /**
     * @return the telaReceberCompra
     */
    public ReceberCompra getTelaReceberCompra() {
        return telaReceberCompra;
    }

    /**
     * @param telaReceberCompra the telaReceberCompra to set
     */
    public void setTelaReceberCompra(ReceberCompra telaReceberCompra) {
        this.telaReceberCompra = telaReceberCompra;
    }

    /**
     * @return the jCheckBoxMenuItem1
     */
    public javax.swing.JCheckBoxMenuItem getjCheckBoxMenuItem1() {
        return jCheckBoxMenuItem1;
    }

    /**
     * @param jCheckBoxMenuItem1 the jCheckBoxMenuItem1 to set
     */
    public void setjCheckBoxMenuItem1(javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1) {
        this.jCheckBoxMenuItem1 = jCheckBoxMenuItem1;
    }

    /**
     * @return the jdpPrincipal
     */
    public javax.swing.JDesktopPane getJdpPrincipal() {
        return jdpPrincipal;
    }

    /**
     * @param jdpPrincipal the jdpPrincipal to set
     */
    public void setJdpPrincipal(javax.swing.JDesktopPane jdpPrincipal) {
        this.jdpPrincipal = jdpPrincipal;
    }

    /**
     * @return the jmbMenus
     */
    public javax.swing.JMenuBar getJmbMenus() {
        return jmbMenus;
    }

    /**
     * @param jmbMenus the jmbMenus to set
     */
    public void setJmbMenus(javax.swing.JMenuBar jmbMenus) {
        this.jmbMenus = jmbMenus;
    }

    /**
     * @return the jmiCliente
     */
    public javax.swing.JMenuItem getJmiCliente() {
        return jmiCliente;
    }

    /**
     * @param jmiCliente the jmiCliente to set
     */
    public void setJmiCliente(javax.swing.JMenuItem jmiCliente) {
        this.jmiCliente = jmiCliente;
    }

    /**
     * @return the telaLogin
     */
    public TelaDeLogin getTelaLogin() {
        return telaLogin;
    }

    /**
     * @param telaLogin the telaLogin to set
     */
    public void setTelaLogin(TelaDeLogin telaLogin) {
        this.telaLogin = telaLogin;
    }

    /**
     * @return the jmCadastro
     */
    public javax.swing.JMenu getJmCadastro() {
        return jmCadastro;
    }

    /**
     * @param jmCadastro the jmCadastro to set
     */
    public void setJmCadastro(javax.swing.JMenu jmCadastro) {
        this.jmCadastro = jmCadastro;
    }

    /**
     * @return the jmLogin
     */
    public javax.swing.JMenu getJmLogin() {
        return jmLogin;
    }

    /**
     * @param jmLogin the jmLogin to set
     */
    public void setJmLogin(javax.swing.JMenu jmLogin) {
        this.jmLogin = jmLogin;
    }

    /**
     * @return the jmOrdens
     */
    public javax.swing.JMenu getJmOrdens() {
        return jmOrdens;
    }

    /**
     * @param jmOrdens the jmOrdens to set
     */
    public void setJmOrdens(javax.swing.JMenu jmOrdens) {
        this.jmOrdens = jmOrdens;
    }

    /**
     * @return the jmPedido
     */
    public javax.swing.JMenu getJmPedido() {
        return jmPedido;
    }

    /**
     * @param jmPedido the jmPedido to set
     */
    public void setJmPedido(javax.swing.JMenu jmPedido) {
        this.jmPedido = jmPedido;
    }

    /**
     * @return the jmiFornecedor
     */
    public javax.swing.JMenuItem getJmiFornecedor() {
        return jmiFornecedor;
    }

    /**
     * @param jmiFornecedor the jmiFornecedor to set
     */
    public void setJmiFornecedor(javax.swing.JMenuItem jmiFornecedor) {
        this.jmiFornecedor = jmiFornecedor;
    }

    /**
     * @return the jmiItemPedido
     */
    public javax.swing.JMenuItem getJmiItemPedido() {
        return jmiItemPedido;
    }

    /**
     * @param jmiItemPedido the jmiItemPedido to set
     */
    public void setJmiItemPedido(javax.swing.JMenuItem jmiItemPedido) {
        this.jmiItemPedido = jmiItemPedido;
    }

    /**
     * @return the jmiLogin
     */
    public javax.swing.JMenuItem getJmiLogin() {
        return jmiLogin;
    }

    /**
     * @param jmiLogin the jmiLogin to set
     */
    public void setJmiLogin(javax.swing.JMenuItem jmiLogin) {
        this.jmiLogin = jmiLogin;
    }

    /**
     * @return the jmiReceberCompra
     */
    public javax.swing.JMenuItem getJmiReceberCompra() {
        return jmiReceberCompra;
    }

    /**
     * @param jmiReceberCompra the jmiReceberCompra to set
     */
    public void setJmiReceberCompra(javax.swing.JMenuItem jmiReceberCompra) {
        this.jmiReceberCompra = jmiReceberCompra;
    }

    /**
     * @return the jmiSair
     */
    public javax.swing.JMenuItem getJmiSair() {
        return jmiSair;
    }

    /**
     * @param jmiSair the jmiSair to set
     */
    public void setJmiSair(javax.swing.JMenuItem jmiSair) {
        this.jmiSair = jmiSair;
    }

    /**
     * @return the jmiSolicitarCompra
     */
    public javax.swing.JMenuItem getJmiSolicitarCompra() {
        return jmiSolicitarCompra;
    }

    /**
     * @param jmiSolicitarCompra the jmiSolicitarCompra to set
     */
    public void setJmiSolicitarCompra(javax.swing.JMenuItem jmiSolicitarCompra) {
        this.jmiSolicitarCompra = jmiSolicitarCompra;
    }

    /**
     * @return the telaFornecedor
     */
    public CadastroFornecedor getTelaFornecedor() {
        return telaFornecedor;
    }

    /**
     * @param telaFornecedor the telaFornecedor to set
     */
    public void setTelaFornecedor(CadastroFornecedor telaFornecedor) {
        this.telaFornecedor = telaFornecedor;
    }

    /**
     * @return the listaDeOrdensDeCompra
     */
    public List<OrdemDeCompra> getListaDeOrdensDeCompra() {
        return listaDeOrdensDeCompra;
    }

    /**
     * @param listaDeOrdensDeCompra the listaDeOrdensDeCompra to set
     */
    public void setListaDeOrdensDeCompra(List<OrdemDeCompra> listaDeOrdensDeCompra) {
        this.listaDeOrdensDeCompra = listaDeOrdensDeCompra;
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
     * @return the listaDeCorredores
     */
    public ArrayList<Corredor> getListaDeCorredores() {
        return listaDeCorredores;
    }

    /**
     * @param listaDeCorredores the listaDeCorredores to set
     */
    public void setListaDeCorredores(ArrayList<Corredor> listaDeCorredores) {
        this.listaDeCorredores = listaDeCorredores;
    }

    /**
     * @return the recepcao
     */
    public Recepcao getRecepcao() {
        return recepcao;
    }

    /**
     * @param recepcao the recepcao to set
     */
    public void setRecepcao(Recepcao recepcao) {
        this.recepcao = recepcao;
    }

    /**
     * @return the empilhadeira
     */
    public Empilhadeira getEmpilhadeira() {
        return empilhadeira;
    }

    /**
     * @param empilhadeira the empilhadeira to set
     */
    public void setEmpilhadeira(Empilhadeira empilhadeira) {
        this.empilhadeira = empilhadeira;
    }

    /**
     * @return the listaDePedidos
     */
    public List<Pedido> getListaDePedidos() {
        return listaDePedidos;
    }

    /**
     * @param listaDePedidos the listaDePedidos to set
     */
    public void setListaDePedidos(List<Pedido> listaDePedidos) {
        this.listaDePedidos = listaDePedidos;
    }

}
