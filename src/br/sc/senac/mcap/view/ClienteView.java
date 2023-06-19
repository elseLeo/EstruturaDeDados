package br.sc.senac.mcap.view;

import br.sc.senac.mcap.controller.ClienteController;
import br.sc.senac.mcap.model.Cliente;
import br.sc.senac.mcap.util.ConnectionFactory;
import br.sc.senac.mcap.util.Operacoes_crud;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClienteView extends JFrame {
    private JButton btnExcluir;
    private JButton btnSair;
    private JButton btnNovo;
    private JButton btnEditar;
    private JButton btnCancelar;
    private JButton btnSalvar;
    private JButton btnAtualizar;
    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtEndereco;
    private JTextField txtTelefone;
    private JRadioButton rbtnMasculino;
    private JRadioButton rbtnFeminino;
    private JPanel pnlClienteview;
    private JPanel pnlDados;
    private JPanel pnlAcoes;
    private JPanel pnlBotoesAcao;
    private JScrollPane pnlTabela;
    private JTable tblCliente;
    private String sexo;
    private int operacao;
    private boolean estadoF = false;
    private boolean estadoM = false;

    public ClienteView() {
        initComponents();
        listeners();
        CarregarDadosTabela();
    }

    public void initComponents() {
        setTitle("CadastrodeClientes");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(pnlClienteview);
        setVisible(true);
        pnlBotoesAcao.setVisible(false);
    }

    public void listeners() {
        btnNovo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                operacao = Operacoes_crud.NOVO.getOperacao();
                btnEditar.setEnabled(false);
                btnAtualizar.setEnabled(true);
                btnExcluir.setEnabled(false);
                btnNovo.setEnabled(false);
                pnlBotoesAcao.setVisible(true);
                rbtnMasculino.setSelected(true);
                abrirCampos();
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operacao = Operacoes_crud.EDITAR.getOperacao();
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gravarAtualizarDados();
            }
        });
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        rbtnFeminino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rbtnMasculino.setSelected(false);
                sexo = rbtnFeminino.getText();
                if (rbtnFeminino.isSelected()) {
                    estadoF = true;
                } else {
                    estadoF = false;
                }
            }
        });
        rbtnMasculino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rbtnFeminino.setSelected(false);
                sexo = rbtnMasculino.getText();
                if (rbtnMasculino.isSelected()) {
                    estadoM = true;
                } else {
                    estadoM = false;
                }

            }
        });
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (estadoM == false && estadoF == false) {
                    JOptionPane.showMessageDialog(null, "Informe um sexo.");
                } else {
                    gravarAtualizarDados();
                    limparCampos();
                    CarregarDadosTabela();
                }
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                btnNovo.setEnabled(true);
                btnAtualizar.setEnabled(true);
                btnExcluir.setEnabled(true);
                btnEditar.setEnabled(true);
                rbtnMasculino.setEnabled(false);
                rbtnFeminino.setEnabled(false);
                fecharCampos();
            }
        });
    }
    private void gravarAtualizarDados() {

        String nome = txtNome.getText();
        String cpf = txtCpf.getText();
        String fone = txtTelefone.getText();

        Cliente cliente = new Cliente();

        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setFone(fone);
        cliente.setSexo(formatarCampoSexo(sexo));

        ClienteController clienteController = new ClienteController();

        try{
            if (operacao == Operacoes_crud.NOVO.getOperacao()) {

                clienteController.cadastrar(cliente);
                JOptionPane.showMessageDialog(null,"O cliente "+cliente.getNome()+" foi criado " +
                        "com sucesso!");
                limparCampos();
            }
            else if(operacao == Operacoes_crud.EDITAR.getOperacao()){
                //aguardando atualizações
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
    private void fecharCampos() {
        txtNome.setEditable(false);
        txtCpf.setEditable(false);
        txtEndereco.setEditable(false);
        txtTelefone.setEditable(false);
    }

    private void abrirCampos() {
        txtNome.setEditable(true);
        txtNome.setEnabled(true);
        txtCpf.setEditable(true);
        txtCpf.setEnabled(true);
        txtEndereco.setEditable(true);
        txtEndereco.setEnabled(true);
        txtTelefone.setEditable(true);
        txtTelefone.setEnabled(true);
        rbtnMasculino.setEnabled(true);
        rbtnFeminino.setEnabled(true);
    }
    private void limparCampos(){
        txtNome.setText("");
        txtCpf.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
    }
    private String formatarCampoSexo(String sexo){
        if (sexo.equals("Feminino")){
            sexo = "F";
        }
        else{
            sexo = "M";
        }
        return sexo;
    }
    private void LimparCampos(){
        txtNome.setText("");
        txtCpf.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");

    }

    private void CarregarDadosTabela(){
        String sql = "SELECT cli_cod, cli_nome, cli_cpf, cli_sexo, cli_fone from cliente;";

        DefaultTableModel model = new DefaultTableModel(new Object[] []{}, new String[]
        {"CODIGO", "NOME", "CPF", "SEXO","TELEFONE"});
        tblCliente.setModel(model);

        try{
            Connection connection = ConnectionFactory.getConexao();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            tblCliente.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //ajustar manualmente o tamanho das colunas (não é obrigatório)
            tblCliente.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblCliente.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblCliente.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblCliente.getColumnModel().getColumn(4).setPreferredWidth(100);

            while(rs.next()){
                Integer rsCodigo = rs.getInt("cli_cod");
                String rsNome = rs.getString("cli_nome");
                String rsCpf = rs.getString("cli_cpf");
                String rsSexo = rs.getString("cli_sexo");
                if (rsSexo.equals("M") || rsSexo.equals("m")){
                    rsSexo ="Masculino";
                }else {
                    rsSexo = "Feminino";
                }
                String rsFone = rs.getString("cli_fone");
                model.addRow(new Object[]{rsCodigo, rsNome, rsCpf, rsSexo, rsFone});
                
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        ClienteView clienteView = new ClienteView();
    }
}