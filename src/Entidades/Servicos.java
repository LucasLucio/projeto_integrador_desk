/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lukas
 */
@Entity
@Table(name = "servicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicos.findAll", query = "SELECT s FROM Servicos s")
    , @NamedQuery(name = "Servicos.findByIdServicos", query = "SELECT s FROM Servicos s WHERE s.idServicos = :idServicos")
    , @NamedQuery(name = "Servicos.findByNome", query = "SELECT s FROM Servicos s WHERE s.nome = :nome")
    , @NamedQuery(name = "Servicos.findByDescricao", query = "SELECT s FROM Servicos s WHERE s.descricao = :descricao")
    , @NamedQuery(name = "Servicos.findByValor", query = "SELECT s FROM Servicos s WHERE s.valor = :valor")})
public class Servicos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_servicos")
    private Integer idServicos;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicos")
    private List<ServicosDescricao> servicosDescricaoList;

    public Servicos() {
    }

    public Servicos(Integer idServicos) {
        this.idServicos = idServicos;
    }

    public Servicos(Integer idServicos, String nome, double valor) {
        this.idServicos = idServicos;
        this.nome = nome;
        this.valor = valor;
    }

    public Integer getIdServicos() {
        return idServicos;
    }

    public void setIdServicos(Integer idServicos) {
        this.idServicos = idServicos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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
        hash += (idServicos != null ? idServicos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicos)) {
            return false;
        }
        Servicos other = (Servicos) object;
        if ((this.idServicos == null && other.idServicos != null) || (this.idServicos != null && !this.idServicos.equals(other.idServicos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idServicos + ";" + nome + ";" + descricao + ";" + valor + ";" + servicosDescricaoList;
    }

}
