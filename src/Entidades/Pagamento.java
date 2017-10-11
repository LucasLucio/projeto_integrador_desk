/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lukas
 */
@Entity
@Table(name = "pagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagamento.findAll", query = "SELECT p FROM Pagamento p")
    , @NamedQuery(name = "Pagamento.findByIdPagamento", query = "SELECT p FROM Pagamento p WHERE p.idPagamento = :idPagamento")
    , @NamedQuery(name = "Pagamento.findByDescricao", query = "SELECT p FROM Pagamento p WHERE p.descricao = :descricao")
    , @NamedQuery(name = "Pagamento.findByDataPagamento", query = "SELECT p FROM Pagamento p WHERE p.dataPagamento = :dataPagamento")
    , @NamedQuery(name = "Pagamento.findByTipo", query = "SELECT p FROM Pagamento p WHERE p.tipo = :tipo")})
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_pagamento")
    private Integer idPagamento;
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "data_pagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "contrato_id_contrato", referencedColumnName = "id_contrato")
    @ManyToOne(optional = false)
    private Contrato contratoIdContrato;

    public Pagamento() {
    }

    public Pagamento(Integer idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Pagamento(Integer idPagamento, Date dataPagamento, String tipo) {
        this.idPagamento = idPagamento;
        this.dataPagamento = dataPagamento;
        this.tipo = tipo;
    }

    public Integer getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Integer idPagamento) {
        this.idPagamento = idPagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Contrato getContratoIdContrato() {
        return contratoIdContrato;
    }

    public void setContratoIdContrato(Contrato contratoIdContrato) {
        this.contratoIdContrato = contratoIdContrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPagamento != null ? idPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagamento)) {
            return false;
        }
        Pagamento other = (Pagamento) object;
        if ((this.idPagamento == null && other.idPagamento != null) || (this.idPagamento != null && !this.idPagamento.equals(other.idPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Pagamento[ idPagamento=" + idPagamento + " ]";
    }

}
