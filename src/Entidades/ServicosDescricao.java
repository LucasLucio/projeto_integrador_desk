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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "servicos_descricao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicosDescricao.findAll", query = "SELECT s FROM ServicosDescricao s")
    , @NamedQuery(name = "ServicosDescricao.findByContratoIdContrato", query = "SELECT s FROM ServicosDescricao s WHERE s.servicosDescricaoPK.contratoIdContrato = :contratoIdContrato")
    , @NamedQuery(name = "ServicosDescricao.findByServicosIdServicos", query = "SELECT s FROM ServicosDescricao s WHERE s.servicosDescricaoPK.servicosIdServicos = :servicosIdServicos")
    , @NamedQuery(name = "ServicosDescricao.findByValorCobrado", query = "SELECT s FROM ServicosDescricao s WHERE s.valorCobrado = :valorCobrado")
    , @NamedQuery(name = "ServicosDescricao.findByDataServico", query = "SELECT s FROM ServicosDescricao s WHERE s.dataServico = :dataServico")})
public class ServicosDescricao implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ServicosDescricaoPK servicosDescricaoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_cobrado")
    private Double valorCobrado;
    @Basic(optional = false)
    @Column(name = "data_servico")
    @Temporal(TemporalType.DATE)
    private Date dataServico;
    @JoinColumn(name = "contrato_id_contrato", referencedColumnName = "id_contrato", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Contrato contrato;
    @JoinColumn(name = "servicos_id_servicos", referencedColumnName = "id_servicos", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Servicos servicos;

    public ServicosDescricao() {
    }

    public ServicosDescricao(ServicosDescricaoPK servicosDescricaoPK) {
        this.servicosDescricaoPK = servicosDescricaoPK;
    }

    public ServicosDescricao(ServicosDescricaoPK servicosDescricaoPK, Date dataServico) {
        this.servicosDescricaoPK = servicosDescricaoPK;
        this.dataServico = dataServico;
    }

    public ServicosDescricao(int contratoIdContrato, int servicosIdServicos) {
        this.servicosDescricaoPK = new ServicosDescricaoPK(contratoIdContrato, servicosIdServicos);
    }

    public ServicosDescricaoPK getServicosDescricaoPK() {
        return servicosDescricaoPK;
    }

    public void setServicosDescricaoPK(ServicosDescricaoPK servicosDescricaoPK) {
        this.servicosDescricaoPK = servicosDescricaoPK;
    }

    public Double getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(Double valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public Date getDataServico() {
        return dataServico;
    }

    public void setDataServico(Date dataServico) {
        this.dataServico = dataServico;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Servicos getServicos() {
        return servicos;
    }

    public void setServicos(Servicos servicos) {
        this.servicos = servicos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (servicosDescricaoPK != null ? servicosDescricaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicosDescricao)) {
            return false;
        }
        ServicosDescricao other = (ServicosDescricao) object;
        if ((this.servicosDescricaoPK == null && other.servicosDescricaoPK != null) || (this.servicosDescricaoPK != null && !this.servicosDescricaoPK.equals(other.servicosDescricaoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return servicosDescricaoPK + ";" + valorCobrado + ";" + dataServico + ";" + contrato + ";" + servicos;
    }


    
}
