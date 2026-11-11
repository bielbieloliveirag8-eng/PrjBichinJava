package org.example.model;

public class ServicoInterno extends Servico{
    private String crmv;

    public ServicoInterno() {
        super();
    }

    public ServicoInterno(int id, String descricao, double valor, String crmv) {
        super(id, descricao, valor);
        this.crmv = crmv;
    }

    @Override
    public double valorFinalDoServico() {
        return getValor();
    }

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    @Override
    public String toString() {
        return super.toString() +
                "CRMV: " + crmv + "\n" +
                "-----------------------------------------";
    }
}
