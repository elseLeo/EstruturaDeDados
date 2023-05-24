package br.sc.senac.mcap.util;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql//localhost:3386/mcap";
    private static final String USER = "root";
    private static final String PASSWORD = "root99";

    public static Connection getConexao(){
        Connection conexao = null;

        try{
            conexao = DriverManager.getConnection(URL, USER, PASSWORD);

            if (conexao != null) {
                System.out.println("conexão estabelecida");
            }
        }catch (SQLException e){
        JOptionPane.showMessageDialog(null,
        "Erro na conexão: "+e.getMessage(), "erro",
            JOptionPane.ERROR_MESSAGE);
    }
    return conexao;

        }

    public static void main(String[] args) {
        //ConnectionFactory connectionFactory = new ConnectionFactory();
        getConexao();

    }
}
