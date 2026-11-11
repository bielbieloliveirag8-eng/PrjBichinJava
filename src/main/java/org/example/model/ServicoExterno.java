package org.example.model;

public class ServicoExterno extends Servico{
    private double valorTerceirizado;

    public ServicoExterno() {
        super();
    }

    public ServicoExterno(int id, String descricao, double valor, double valorTerceirizado) {
        super(id, descricao, valor);
        this.valorTerceirizado = valorTerceirizado;
    }

    @Override
    public double valorFinalDoServico() {
        return getValor() + valorTerceirizado;
    }

    public double getValorTerceirizado() {
        return valorTerceirizado;
    }

    public void setValorTerceirizado(double valorTerceirizado) {
        this.valorTerceirizado = valorTerceirizado;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Valor Terceirizado: R$" + valorTerceirizado + "\n" +
                "-----------------------------------------";
    }
}
