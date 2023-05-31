package br.sc.senac.mcap.view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;



public class ClienteView extends JFrame {
    private JButton btnExcluir;
    private JButton btnSair;
    private JButton btnNovo;
    private JButton btnEditar;
    private JButton btnCancelar;
    private JButton btnSalvar;
    private JButton btnAtualizar;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField4;
    private JTextField textField5;
    private JTable table1;
    private JRadioButton RbtnMasculino;
    private JRadioButton rbtnFeminino;
    private JPanel pnlClienteview;
    private JPanel pnlDados;
    private JPanel pnlAcoes;
    private JPanel pnlBotoesAcao;
    private JPanel pnlTabela;

    public ClienteView() {
        initComponents();
        listeners();
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
        btnNovo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent) {
                operacao = OperacoesCrud.NOVO.getOperacao();
            btnEditar.setEnabled(false);
            btnAtualizar.setEnabled(true);
            btnExcluir.setEnabled(false);
            pnlBotoesAcao.setVisible(true);
            abrirCampos();
            }
        });
        btnEditar.addActionListener(newActionListener(){
            @Override
            public void actionPerformed (ActionEvente) {
                    operacao = OperacoesCrud.EDITAR.getOperacao();
            }
        });
        btnSalvar.addActionListener(newActionListener() {
            @Override
            public void actionPerformed (ActionEvente) {
                    gravarAtualizarDados();
            }
        });
        btnAtualizar.addActionListener(newActionListener() {
            @Override
            public void actionPerformed (ActionEvente) {
                    gravarAtualizarDados();
            }
        });
    }

    private void gravar

    AtualizarDados() {
        if (operacao == OperacoesCrud.NOVO.getOperacao()) {

        }
    }
    elseif(operacao==OperacoesCrud.EDITAR.getOperacao())

    {

    }
    private void abrirCampos() {
        txtNome.setEditable(true);
        txtCpf.setEditable(true);
        txtEndereco.setEditable(true);
        txtTelefone.setEditable(true);
    }
}