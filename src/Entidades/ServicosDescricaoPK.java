/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author lukas
 */
@Embeddable
public class ServicosDescricaoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "contrato_id_contrato")
    private int contratoIdContrato;
    @Basic(optional = false)
    @Column(name = "servicos_id_servicos")
    private int servicosIdServicos;

    public ServicosDescricaoPK() {
    }

    public ServicosDescricaoPK(int contratoIdContrato, int servicosIdServicos) {
        this.contratoIdContrato = contratoIdContrato;
        this.servicosIdServicos = servicosIdServicos;
    }

    public int getContratoIdContrato() {
        return contratoIdContrato;
    }

    public void setContratoIdContrato(int contratoIdContrato) {
        this.contratoIdContrato = contratoIdContrato;
    }

    public int getServicosIdServicos() {
        return servicosIdServicos;
    }

    public void setServicosIdServicos(int servicosIdServicos) {
        this.servicosIdServicos = servicosIdServicos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) contratoIdContrato;
        hash += (int) servicosIdServicos;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicosDescricaoPK)) {
            return false;
        }
        ServicosDescricaoPK other = (ServicosDescricaoPK) object;
        if (this.contratoIdContrato != other.contratoIdContrato) {
            return false;
        }
        if (this.servicosIdServicos != other.servicosIdServicos) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return contratoIdContrato + ";" + servicosIdServicos;
    }

    
    
}
