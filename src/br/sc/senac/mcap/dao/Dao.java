package br.sc.senac.mcap.dao;

import br.sc.senac.mcap.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Dao <Entity>{

    protected Connection obterConexao() throws SQLException{
        return ConnectionFactory.getConexao();
    }

    public abstract boolean cadastrar (Entity pojo) throws SQLException; //pojo: classes simples. só tem getter e setter
    public abstract boolean atualizar (Entity pojo) throws SQLException;
    public abstract boolean excluir (Entity pojo) throws SQLException;
    public abstract Integer getCodigo (Entity pojo) throws SQLException;

}