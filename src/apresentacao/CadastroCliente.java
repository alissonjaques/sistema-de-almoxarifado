/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao;

import java.sql.SQLException;
import modelo.Cliente;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alisson Jaques
 */
public class CadastroCliente extends javax.swing.JInternalFrame {
  
    private List<Cliente> listaDeClientes;
    private boolean alterar;
    
    /**
     * Creates new form CadastroCliente
     * @param listaDeClientes
     */
    public CadastroCliente(List<Cliente> listaDeClientes) {
        this.listaDeClientes = listaDeClientes;
        alterar = false;
        initComponents();
        preencherTabelaCliente();
    }
    
    private void limparCampos(){
        getJftCnpj().setText("");
        getJtRazaoSocial().setText("");
        getJtEndereco().setText("");
        getJftTel().setText("");
        getJftCnpj().setEnabled(true);
        getJbEditar().setEnabled(false);
        getJbExcluir().setEnabled(false);
        getTbCliente().setEnabled(true);
        getTbCliente().getSelectionModel().removeSelectionInterval(0, getTbCliente().getRowCount());
        alterar = false;
    }
    
    private boolean validarDados() {

        boolean ret = true;

        if (getJftCnpj().getText().equals("  .   .   /    -  ")) {
            ret = false;
        } else if (getJtRazaoSocial().getText().isEmpty()) {
            ret = false;
        } else if (getJtEndereco().getText().isEmpty()) {
            ret = false;
        } else if (getJftTel().getText().equals("(  )     -    ")){
            ret = false;
        }

        if (!ret) {
            JOptionPane.showMessageDialog(this, "Favor preencher todos os campos referentes a cliente.", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
        }

        return ret;

    }
    
    private void preencherTabelaCliente(){
        if(!getListaDeClientes().isEmpty()){
            
            DefaultTableModel model = (DefaultTableModel) getTbCliente().getModel();
            
            for (Cliente cliente : getListaDeClientes()) {
                model.addRow(new Object[]{cliente.getCnpj(), cliente.getRazaoSocial(), cliente.getEndereco(), cliente.getNumeroDeContato()});
            }
            
        }   
    }
        
    private void preencherCampos(Cliente cliente){
        getJftCnpj().setText(cliente.getCnpj());
        getJtRazaoSocial().setText(cliente.getRazaoSocial());
        getJtEndereco().setText(cliente.getEndereco());
        getJftTel().setText(cliente.getNumeroDeContato());
        getJftCnpj().setEnabled(false);
    }
    
    /**
     * @return the listaDeClintes
     */
    public List<Cliente> getListaDeClientes() {
        return listaDeClientes;
    }

    /**
     * @param listaDeClintes the listaDeClintes to set
     */
    public void setListaDeClientes(List<Cliente> listaDeClintes) {
        this.listaDeClientes = listaDeClintes;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpInformacoesCliente = new javax.swing.JPanel();
        jlRazaoSocial = new javax.swing.JLabel();
        jlCnpj = new javax.swing.JLabel();
        jlEndereco = new javax.swing.JLabel();
        jlTel = new javax.swing.JLabel();
        jtRazaoSocial = new javax.swing.JTextField();
        jtEndereco = new javax.swing.JTextField();
        jftCnpj = new javax.swing.JFormattedTextField();
        jftTel = new javax.swing.JFormattedTextField();
        jbCadastrar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jpTabelaClientes = new javax.swing.JPanel();
        spTabelaCliente = new javax.swing.JScrollPane();
        tbCliente = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("CADASTRO CLIENTE");

        jpInformacoesCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INFORMA????ES DO CLIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jlRazaoSocial.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlRazaoSocial.setText("Raz??o Social:");

        jlCnpj.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlCnpj.setText("CNPJ:");

        jlEndereco.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlEndereco.setText("Endere??o:");

        jlTel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlTel.setText("TEL:");

        try {
            jftCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jftCnpj.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jftCnpjFocusLost(evt);
            }
        });

        try {
            jftTel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jftTel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jftTelFocusLost(evt);
            }
        });
        jftTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jftTelActionPerformed(evt);
            }
        });

        jbCadastrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbCadastrar.setText("Incluir");
        jbCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCadastrarActionPerformed(evt);
            }
        });

        jbEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbEditar.setText("Editar");
        jbEditar.setEnabled(false);
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        jbExcluir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbExcluir.setText("Excluir");
        jbExcluir.setEnabled(false);
        jbExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirActionPerformed(evt);
            }
        });

        jbCancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbCancelar.setText("Cancelar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpInformacoesClienteLayout = new javax.swing.GroupLayout(jpInformacoesCliente);
        jpInformacoesCliente.setLayout(jpInformacoesClienteLayout);
        jpInformacoesClienteLayout.setHorizontalGroup(
            jpInformacoesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInformacoesClienteLayout.createSequentialGroup()
                .addGroup(jpInformacoesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpInformacoesClienteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jbCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                        .addComponent(jbCancelar))
                    .addGroup(jpInformacoesClienteLayout.createSequentialGroup()
                        .addGroup(jpInformacoesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpInformacoesClienteLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jpInformacoesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jlEndereco)
                                    .addComponent(jlCnpj)))
                            .addGroup(jpInformacoesClienteLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jlRazaoSocial)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpInformacoesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtRazaoSocial)
                            .addGroup(jpInformacoesClienteLayout.createSequentialGroup()
                                .addComponent(jftCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlTel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jftTel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtEndereco))))
                .addContainerGap())
        );
        jpInformacoesClienteLayout.setVerticalGroup(
            jpInformacoesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInformacoesClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpInformacoesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlRazaoSocial))
                .addGap(9, 9, 9)
                .addGroup(jpInformacoesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlEndereco)
                    .addComponent(jtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpInformacoesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlTel)
                    .addComponent(jftCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlCnpj)
                    .addComponent(jftTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpInformacoesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpInformacoesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbEditar)
                        .addComponent(jbExcluir)
                        .addComponent(jbCancelar)))
                .addContainerGap())
        );

        jpTabelaClientes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CLIENTES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        tbCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CNPJ", "RAZAO SOCIAL", "ENDERE??O", "TELEFONE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tbClienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbClienteFocusLost(evt);
            }
        });
        tbCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbClienteMouseClicked(evt);
            }
        });
        spTabelaCliente.setViewportView(tbCliente);

        javax.swing.GroupLayout jpTabelaClientesLayout = new javax.swing.GroupLayout(jpTabelaClientes);
        jpTabelaClientes.setLayout(jpTabelaClientesLayout);
        jpTabelaClientesLayout.setHorizontalGroup(
            jpTabelaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spTabelaCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
        );
        jpTabelaClientesLayout.setVerticalGroup(
            jpTabelaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTabelaClientesLayout.createSequentialGroup()
                .addComponent(spTabelaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpInformacoesCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpTabelaClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpInformacoesCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpTabelaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
       if(getTbCliente().getSelectedRow() > -1){
            alterarCliente();
        }
    }//GEN-LAST:event_jbEditarActionPerformed
    
    private void alterarCliente(){
        DefaultTableModel model = (DefaultTableModel) getTbCliente().getModel();
        String cnpj = (String) model.getValueAt(getTbCliente().getSelectedRow(), 0);
        
        Cliente cliente = new Cliente();
        cliente.setCnpj(cnpj);
      
        int i = 0;
        int index = -1;
        for(Cliente cliente1: getListaDeClientes()){
            
            if(cliente1.getCnpj().equals(cnpj)){
                index = i;
            }
            i++;
        }
        
        if(index > -1){
            cliente = getListaDeClientes().get(index);            
            preencherCampos(cliente);
            cliente.updateCliente();
            alterar = true;
            getJbEditar().setEnabled(false);
            getJbExcluir().setEnabled(false);
            getTbCliente().setEnabled(false);
        }
    }
    
    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        int op = JOptionPane.showConfirmDialog(this, "Deseja mesmo sair do Cadastro de Clientes?", "Sair", JOptionPane.OK_CANCEL_OPTION);

        if (op == 0) {
            dispose();
        }        
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void tbClienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbClienteFocusGained
        if(getTbCliente().getSelectedRow() > -1){
            getJbEditar().setEnabled(true);
            getJbExcluir().setEnabled(true);
        }
    }//GEN-LAST:event_tbClienteFocusGained

    private void tbClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbClienteFocusLost
        if(getTbCliente().getSelectedRow() < 0){
            getJbEditar().setEnabled(false);
            getJbExcluir().setEnabled(false);
        }
    }//GEN-LAST:event_tbClienteFocusLost

    private void tbClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClienteMouseClicked
        if(evt.getClickCount() == 2){
            if(getTbCliente().getSelectedRow() >- 1){
                alterarCliente();
            }
        }
    }//GEN-LAST:event_tbClienteMouseClicked

    private void jbCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCadastrarActionPerformed
        if (validarDados()) {
            Cliente cliente = new Cliente();
            cliente.setCnpj(getJftCnpj().getText());

            int i = 0;
            int index = -1;
            for(Cliente cliente1: getListaDeClientes()){
                if(cliente1.getCnpj().equals(cliente.getCnpj())){
                    index = i;
                }
                i++;
            }
            
            if(index > -1){
                if(!alterar){
                    JOptionPane.showMessageDialog(this, "J?? existe um cliente com esse CNPJ!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else{
                    cliente = getListaDeClientes().get(index);
                }   
            }
            
            cliente.setRazaoSocial(getJtRazaoSocial().getText());
            cliente.setEndereco(getJtEndereco().getText());
            cliente.setNumeroDeContato(getJftTel().getText());
            
            DefaultTableModel model = (DefaultTableModel) getTbCliente().getModel();
            
            String msg = "";
            if(!alterar){
                msg = "Cliente cadastrado com sucesso!";
                getListaDeClientes().add(cliente);
                try {
                    cliente.insertCliente();
                } catch (SQLException ex) {
                    Logger.getLogger(CadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                model.addRow(new Object[]{cliente.getCnpj(), cliente.getRazaoSocial(), cliente.getEndereco(), cliente.getNumeroDeContato()});
            }
            else{
                msg = "Altera????o cadastral do cliente realizada com sucesso!";
                model.setValueAt(cliente.getRazaoSocial(), index, 1);
                model.setValueAt(cliente.getEndereco(), index, 2);
                model.setValueAt(cliente.getNumeroDeContato(), index, 3);
            }
            
            JOptionPane.showMessageDialog(this, msg, "Confirma????o", JOptionPane.INFORMATION_MESSAGE);
            
            limparCampos();
            System.out.println(getListaDeClientes());
        }
    }//GEN-LAST:event_jbCadastrarActionPerformed

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed
        int op = JOptionPane.showConfirmDialog(this, "Deseja mesmo excluir o cliente selecionado?", "Sair", JOptionPane.OK_CANCEL_OPTION);

        if (op == 0) {
            excluirCliente();
        }
    }//GEN-LAST:event_jbExcluirActionPerformed

    private void jftTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jftTelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jftTelActionPerformed

    private void jftCnpjFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jftCnpjFocusLost
        if ("  .   .   /    -  ".equals(jftCnpj.getText())) {
            jftCnpj.setValue("");
        }
    }//GEN-LAST:event_jftCnpjFocusLost

    private void jftTelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jftTelFocusLost
        if ("(  )     -    ".equals(jftTel.getText())) {
            jftTel.setValue("");
        }
    }//GEN-LAST:event_jftTelFocusLost

    private void excluirCliente(){
        DefaultTableModel model = (DefaultTableModel) getTbCliente().getModel();
        String cnpj = (String) model.getValueAt(getTbCliente().getSelectedRow(), 0);
        
        int i = 0;
        int index = -1;
        for(Cliente cliente: getListaDeClientes()){
            
            if(cliente.getCnpj().equals(cnpj)){
                index = i;
            }
            i++;
        }
        
        if(index > -1){
            Cliente cliente = getListaDeClientes().get(index);
            getListaDeClientes().remove(index);
            cliente.deleteCliente();
            model.removeRow(getTbCliente().getSelectedRow());
            JOptionPane.showMessageDialog(this, "Cliente exclu??do com sucesso!", "Confirma????o de exclus??o", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbCadastrar;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JFormattedTextField jftCnpj;
    private javax.swing.JFormattedTextField jftTel;
    private javax.swing.JLabel jlCnpj;
    private javax.swing.JLabel jlEndereco;
    private javax.swing.JLabel jlRazaoSocial;
    private javax.swing.JLabel jlTel;
    private javax.swing.JPanel jpInformacoesCliente;
    private javax.swing.JPanel jpTabelaClientes;
    private javax.swing.JTextField jtEndereco;
    private javax.swing.JTextField jtRazaoSocial;
    private javax.swing.JScrollPane spTabelaCliente;
    private javax.swing.JTable tbCliente;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the alterar
     */
    public boolean isAlterar() {
        return alterar;
    }

    /**
     * @param alterar the alterar to set
     */
    public void setAlterar(boolean alterar) {
        this.alterar = alterar;
    }

    /**
     * @return the jbCadastrar
     */
    public javax.swing.JButton getJbCadastrar() {
        return jbCadastrar;
    }

    /**
     * @param jbCadastrar the jbCadastrar to set
     */
    public void setJbCadastrar(javax.swing.JButton jbCadastrar) {
        this.jbCadastrar = jbCadastrar;
    }

    /**
     * @return the jbCancelar
     */
    public javax.swing.JButton getJbCancelar() {
        return jbCancelar;
    }

    /**
     * @param jbCancelar the jbCancelar to set
     */
    public void setJbCancelar(javax.swing.JButton jbCancelar) {
        this.jbCancelar = jbCancelar;
    }

    /**
     * @return the jbEditar
     */
    public javax.swing.JButton getJbEditar() {
        return jbEditar;
    }

    /**
     * @param jbEditar the jbEditar to set
     */
    public void setJbEditar(javax.swing.JButton jbEditar) {
        this.jbEditar = jbEditar;
    }

    /**
     * @return the jbExcluir
     */
    public javax.swing.JButton getJbExcluir() {
        return jbExcluir;
    }

    /**
     * @param jbExcluir the jbExcluir to set
     */
    public void setJbExcluir(javax.swing.JButton jbExcluir) {
        this.jbExcluir = jbExcluir;
    }

    /**
     * @return the jftCnpj
     */
    public javax.swing.JFormattedTextField getJftCnpj() {
        return jftCnpj;
    }

    /**
     * @param jftCnpj the jftCnpj to set
     */
    public void setJftCnpj(javax.swing.JFormattedTextField jftCnpj) {
        this.jftCnpj = jftCnpj;
    }

    /**
     * @return the jftTel
     */
    public javax.swing.JFormattedTextField getJftTel() {
        return jftTel;
    }

    /**
     * @param jftTel the jftTel to set
     */
    public void setJftTel(javax.swing.JFormattedTextField jftTel) {
        this.jftTel = jftTel;
    }

    /**
     * @return the jlCnpj
     */
    public javax.swing.JLabel getJlCnpj() {
        return jlCnpj;
    }

    /**
     * @param jlCnpj the jlCnpj to set
     */
    public void setJlCnpj(javax.swing.JLabel jlCnpj) {
        this.jlCnpj = jlCnpj;
    }

    /**
     * @return the jlEndereco
     */
    public javax.swing.JLabel getJlEndereco() {
        return jlEndereco;
    }

    /**
     * @param jlEndereco the jlEndereco to set
     */
    public void setJlEndereco(javax.swing.JLabel jlEndereco) {
        this.jlEndereco = jlEndereco;
    }

    /**
     * @return the jlRazaoSocial
     */
    public javax.swing.JLabel getJlRazaoSocial() {
        return jlRazaoSocial;
    }

    /**
     * @param jlRazaoSocial the jlRazaoSocial to set
     */
    public void setJlRazaoSocial(javax.swing.JLabel jlRazaoSocial) {
        this.jlRazaoSocial = jlRazaoSocial;
    }

    /**
     * @return the jlTel
     */
    public javax.swing.JLabel getJlTel() {
        return jlTel;
    }

    /**
     * @param jlTel the jlTel to set
     */
    public void setJlTel(javax.swing.JLabel jlTel) {
        this.jlTel = jlTel;
    }

    /**
     * @return the jpInformacoesCliente
     */
    public javax.swing.JPanel getJpInformacoesCliente() {
        return jpInformacoesCliente;
    }

    /**
     * @param jpInformacoesCliente the jpInformacoesCliente to set
     */
    public void setJpInformacoesCliente(javax.swing.JPanel jpInformacoesCliente) {
        this.jpInformacoesCliente = jpInformacoesCliente;
    }

    /**
     * @return the jpTabelaClientes
     */
    public javax.swing.JPanel getJpTabelaClientes() {
        return jpTabelaClientes;
    }

    /**
     * @param jpTabelaClientes the jpTabelaClientes to set
     */
    public void setJpTabelaClientes(javax.swing.JPanel jpTabelaClientes) {
        this.jpTabelaClientes = jpTabelaClientes;
    }

    /**
     * @return the jtEndereco
     */
    public javax.swing.JTextField getJtEndereco() {
        return jtEndereco;
    }

    /**
     * @param jtEndereco the jtEndereco to set
     */
    public void setJtEndereco(javax.swing.JTextField jtEndereco) {
        this.jtEndereco = jtEndereco;
    }

    /**
     * @return the jtRazaoSocial
     */
    public javax.swing.JTextField getJtRazaoSocial() {
        return jtRazaoSocial;
    }

    /**
     * @param jtRazaoSocial the jtRazaoSocial to set
     */
    public void setJtRazaoSocial(javax.swing.JTextField jtRazaoSocial) {
        this.jtRazaoSocial = jtRazaoSocial;
    }

    /**
     * @return the spTabelaCliente
     */
    public javax.swing.JScrollPane getSpTabelaCliente() {
        return spTabelaCliente;
    }

    /**
     * @param spTabelaCliente the spTabelaCliente to set
     */
    public void setSpTabelaCliente(javax.swing.JScrollPane spTabelaCliente) {
        this.spTabelaCliente = spTabelaCliente;
    }

    /**
     * @return the tbCliente
     */
    public javax.swing.JTable getTbCliente() {
        return tbCliente;
    }

    /**
     * @param tbCliente the tbCliente to set
     */
    public void setTbCliente(javax.swing.JTable tbCliente) {
        this.tbCliente = tbCliente;
    }
}
