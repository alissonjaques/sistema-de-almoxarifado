/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import apresentacao.TelaInicial;
import java.sql.SQLException;

/**
 *
 * @author Alisson Jaques
 */
public class Main {
    public static void main(String[] argumentos) throws SQLException{
        TelaInicial tela = new TelaInicial();
        tela.setVisible(true);
    }
}
