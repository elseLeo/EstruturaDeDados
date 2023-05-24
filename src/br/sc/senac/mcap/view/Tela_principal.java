package br.sc.senac.mcap.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Tela_principal {
    private JPanel pnlLogin;
    private JButton btnLimpar;
    private JButton btnLogin;
    private JTextField txtSenha;
    private JTextField txtLogin;
    private JButton btnTestar;

    public Tela_principal() {
    btnLogin.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
        pnlLogin.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }
}
