package br.sc.senac.mcap.view;

import br.sc.senac.mcap.util.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tela_Pesquisa extends JFrame{

    private JButton btnPesquisar;
    private JButton btnLimpar;
    private JTextField txtPesquisaNome;
    private JTable tblCliente;
    private JLabel lblNome;
    private JPanel Panel;

    private  String nomeCliente;

    DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new String []{
            "Código", "Nome", "CPF", "Sexo", "Fone"});


    public Tela_Pesquisa() {
        initComponents();
        Listners();
        CarregarTabela();
    }
    public void initComponents() {
        setTitle("CadastrodeClientes");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(Panel);
        setVisible(true);
    }
    private void CarregarTabela(){
        String sql = "SELECT cli_cod, cli_nome, cli_cpf, cli_sexo, cli_fone FROM cliente ORDER BY cli_cod;";

        tblCliente.setModel(model);

        try{
            Connection connection = ConnectionFactory.getConexao();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            tblCliente.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            tblCliente.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblCliente.getColumnModel().getColumn(1).setPreferredWidth(180);
            tblCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblCliente.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblCliente.getColumnModel().getColumn(4).setPreferredWidth(100);

            while(rs.next()){
                Integer rsCodigo = rs.getInt("cli_cod");
                String rsNome = rs.getString("cli_nome");
                String rsCpf = rs.getString("cli_cpf");
                String rsSexo = rs.getString("cli_sexo");
                if (rsSexo.equals("M")|| rsSexo.equals("m")){
                    rsSexo = "Masculino";
                }else{
                    rsSexo = "Feminino";
                }

                String rsFone = rs.getString("cli_fone");
                model.addRow(new Object[]{rsCodigo, rsNome, rsCpf, rsSexo, rsFone});
            }

        }catch (SQLException e){
           throw new RuntimeException(e);
        }
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    private void Listners(){
        tblCliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                ListSelectionModel tableSelectionModel = tblCliente.getSelectionModel();
                tblCliente.setSelectionModel(tableSelectionModel);
                setNomeCliente(tblCliente.getValueAt(tblCliente.getSelectedRow(),1).toString());
                txtPesquisaNome.setText(getNomeCliente());
            }
        });

        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model = (DefaultTableModel) tblCliente.getModel();

            }
        });
        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        Tela_Pesquisa telaPesquisa = new Tela_Pesquisa();
    }
}
