/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lukas
 */
@Entity
@Table(name = "contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c")
    , @NamedQuery(name = "Contrato.findByIdContrato", query = "SELECT c FROM Contrato c WHERE c.idContrato = :idContrato")
    , @NamedQuery(name = "Contrato.findByServicosDescricao", query = "SELECT c FROM Contrato c WHERE c.servicosDescricao = :servicosDescricao")})
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_contrato")
    private Integer idContrato;
    @Basic(optional = false)
    @Column(name = "servicos_descricao")
    private String servicosDescricao;
    @JoinColumn(name = "pagamento_id_pagamento", referencedColumnName = "id_pagamento")
    @ManyToOne(optional = false)
    private Pagamento pagamentoIdPagamento;
    @JoinColumn(name = "pessoa_id_pessoas", referencedColumnName = "id_pessoas")
    @ManyToOne(optional = false)
    private Pessoa pessoaIdPessoas;
    @JoinColumn(name = "servicos_id_servicos", referencedColumnName = "id_servicos")
    @ManyToOne(optional = false)
    private Servicos servicosIdServicos;

    public Contrato() {
    }

    public Contrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Contrato(Integer idContrato, String servicosDescricao) {
        this.idContrato = idContrato;
        this.servicosDescricao = servicosDescricao;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getServicosDescricao() {
        return servicosDescricao;
    }

    public void setServicosDescricao(String servicosDescricao) {
        this.servicosDescricao = servicosDescricao;
    }

    public Pagamento getPagamentoIdPagamento() {
        return pagamentoIdPagamento;
    }

    public void setPagamentoIdPagamento(Pagamento pagamentoIdPagamento) {
        this.pagamentoIdPagamento = pagamentoIdPagamento;
    }

    public Pessoa getPessoaIdPessoas() {
        return pessoaIdPessoas;
    }

    public void setPessoaIdPessoas(Pessoa pessoaIdPessoas) {
        this.pessoaIdPessoas = pessoaIdPessoas;
    }

    public Servicos getServicosIdServicos() {
        return servicosIdServicos;
    }

    public void setServicosIdServicos(Servicos servicosIdServicos) {
        this.servicosIdServicos = servicosIdServicos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContrato != null ? idContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.idContrato == null && other.idContrato != null) || (this.idContrato != null && !this.idContrato.equals(other.idContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Main.Contrato[ idContrato=" + idContrato + " ]";
    }
    
}
