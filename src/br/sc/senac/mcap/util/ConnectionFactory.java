package br.sc.senac.mcap.util;



import javax.swing.*;
import java.sql.*;




public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/mca";
    private static final String USER = "root";
    private static final String PASSWORD = "root";



    public static Connection getConexao() {
        Connection conexao = null;



        try {
            conexao = DriverManager.getConnection(URL, USER, PASSWORD);



            if (conexao != null) {
                System.out.println("Conexão estabelecida!");
            }



        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro na conexão: " + e.getMessage(),
                    "Erro 46", JOptionPane.ERROR_MESSAGE);
        }
        return conexao;
    }
    public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs)
            throws StandardException{
        close(conn, ps, rs);
    }
    public static void closeConnection(Connection conn, PreparedStatement ps)
            throws StandardException{
        close(conn, ps, null);
    }
    public static void closeConnection(Connection conn)
            throws StandardException{
        close(conn, null, null);
    }
    private static void close(Connection conn, PreparedStatement ps, ResultSet rs)
            throws  StandardException{
        try{
            if (conn != null){
                conn.close();
            }
            if (ps != null){
                ps.close();
            }
            if (rs != null){
                rs.close();
            }
        } catch (Exception e){
            throw new StandardException(e.getMessage());
        }
    }



    public static void main(String[] args) {
        //ConnectionFactory connectionFactory = new ConnectionFactory();
        getConexao();
    }
}
