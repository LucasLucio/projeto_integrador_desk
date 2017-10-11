/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    , @NamedQuery(name = "Contrato.findByContratoDescricao", query = "SELECT c FROM Contrato c WHERE c.contratoDescricao = :contratoDescricao")
    , @NamedQuery(name = "Contrato.findByDataContrato", query = "SELECT c FROM Contrato c WHERE c.dataContrato = :dataContrato")
    , @NamedQuery(name = "Contrato.findByValorContrato", query = "SELECT c FROM Contrato c WHERE c.valorContrato = :valorContrato")})
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_contrato")
    private Integer idContrato;
    @Basic(optional = false)
    @Column(name = "contrato_descricao")
    private String contratoDescricao;
    @Basic(optional = false)
    @Column(name = "data_contrato")
    @Temporal(TemporalType.DATE)
    private Date dataContrato;
    @Basic(optional = false)
    @Column(name = "valor_contrato")
    private double valorContrato;
    @JoinColumn(name = "pessoa_id_pessoas", referencedColumnName = "id_pessoas")
    @ManyToOne(optional = false)
    private Pessoa pessoaIdPessoas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratoIdContrato")
    private List<Pagamento> pagamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contrato")
    private List<ServicosDescricao> servicosDescricaoList;

    public Contrato() {
    }

    public Contrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Contrato(Integer idContrato, String contratoDescricao, Date dataContrato, double valorContrato) {
        this.idContrato = idContrato;
        this.contratoDescricao = contratoDescricao;
        this.dataContrato = dataContrato;
        this.valorContrato = valorContrato;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getContratoDescricao() {
        return contratoDescricao;
    }

    public void setContratoDescricao(String contratoDescricao) {
        this.contratoDescricao = contratoDescricao;
    }

    public Date getDataContrato() {
        return dataContrato;
    }

    public void setDataContrato(Date dataContrato) {
        this.dataContrato = dataContrato;
    }

    public double getValorContrato() {
        return valorContrato;
    }

    public void setValorContrato(double valorContrato) {
        this.valorContrato = valorContrato;
    }

    public Pessoa getPessoaIdPessoas() {
        return pessoaIdPessoas;
    }

    public void setPessoaIdPessoas(Pessoa pessoaIdPessoas) {
        this.pessoaIdPessoas = pessoaIdPessoas;
    }

    @XmlTransient
    public List<Pagamento> getPagamentoList() {
        return pagamentoList;
    }

    public void setPagamentoList(List<Pagamento> pagamentoList) {
        this.pagamentoList = pagamentoList;
    }

    @XmlTransient
    public List<ServicosDescricao> getServicosDescricaoList() {
        return servicosDescricaoList;
    }

    public void setServicosDescricaoList(List<ServicosDescricao> servicosDescricaoList) {
        this.servicosDescricaoList = servicosDescricaoList;
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
        SimpleDateFormat dateDataContrato = new SimpleDateFormat("");
        return idContrato + ";" + contratoDescricao + ";" + dateDataContrato.format(dataContrato) + ";" + valorContrato + ";" + pessoaIdPessoas + ";" + pagamentoList + ";" + servicosDescricaoList;
    }

}
