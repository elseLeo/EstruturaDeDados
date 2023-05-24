package br.sc.senac.mcap.view;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Tela_Cadastro_Cliente {
    private JButton btnExcluir;
    private JButton btnSair;
    private JButton btnNovo;
    private JButton btnEditar;
    private JButton btnCancelar;
    private JButton btnGravar;
    private JButton btnAtualizar;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField4;
    private JTextField textField5;
    private JTable table1;
    private JRadioButton RbtnMasculino;
    private JRadioButton rbtnFeminino;
    private JPanel pnlCadastroCliente;

    public Tela_Cadastro_Cliente() {
        table1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
