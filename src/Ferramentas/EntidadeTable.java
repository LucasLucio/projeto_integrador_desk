package Ferramentas;

import java.io.Serializable;
import java.util.Date;

public class EntidadeTable implements Serializable {

    private int id_servico;
    private String Nome_servico;
    private String data_servico;
    private double Valor;

    public EntidadeTable(int id, String Nome, String data, double Valor) {
        this.id_servico = id;
        this.Nome_servico = Nome;
        this.data_servico = data;
        this.Valor = Valor;
    }

    @Override
    public String toString() {
        return id_servico + ";" + Nome_servico + ";" + data_servico + ";" + Valor;
    }

    public int getId_servico() {
        return id_servico;
    }

    public void setId_servico(int id_servico) {
        this.id_servico = id_servico;
    }

    public String getNome_servico() {
        return Nome_servico;
    }

    public void setNome_servico(String Nome_servico) {
        this.Nome_servico = Nome_servico;
    }

    public String getData_servico() {
        return data_servico;
    }

    public void setData_servico(String data_servico) {
        this.data_servico = data_servico;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double Valor) {
        this.Valor = Valor;
    }
}
