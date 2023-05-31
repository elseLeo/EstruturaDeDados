package br.sc.senac.mcap.controller;

import br.sc.senac.mcap.dao.LoginDao;
import br.sc.senac.mcap.model.Login;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoginController {

    public ArrayList<Login>buscarLoginSenha()
            throws SQLException, ClassNotFoundException{
        return LoginDao.buscarLogin();
    }
}