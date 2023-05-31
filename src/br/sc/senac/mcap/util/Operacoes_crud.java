package br.sc.senac.mcap.util;

public enum Operacoes_crud {

    NOVO(1), EDITAR(2), EXCLUIR(3);

    private final Integer operacao;

    private Operacoes_crud(Integer operacao){
        this.operacao = operacao;
    }

    public Integer getOperacao(){
        return operacao;
    }

}
