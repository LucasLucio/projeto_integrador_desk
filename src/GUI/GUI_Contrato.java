/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAOs.DAOServicos;
import Entidades.Contrato;
import Entidades.Pessoa;
import Entidades.Servicos;
import Entidades.ServicosDescricao;
import Entidades.ServicosDescricaoPK;
import java.awt.BorderLayout;
import java.awt.Container;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDialog;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author lukas
 */
public class GUI_Contrato extends JDialog{
    private Container cp;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();

    public GUI_Contrato() throws ParseException {

        setSize(800, 200);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Contrato");
        
        Servicos servicos = new Servicos();
        DAOServicos daoServicos = new DAOServicos();
        servicos = daoServicos.obter(1);
        Contrato contrato = new Contrato();
        DAOs.DAOContrato daoContrato = new DAOs.DAOContrato();
        DAOs.DAOPessoa daoPessoa = new DAOs.DAOPessoa();
        Pessoa pessoa = daoPessoa.obter(52);
        contrato.setContratoDescricao("teste");
        contrato.setDataContrato((date));
        contrato.setIdContrato(1);
        contrato.setValorContrato(10);
        contrato.setPessoaIdPessoas(pessoa);
        daoContrato.inserir(contrato);
        // aqui começa a merda
        ServicosDescricaoPK servicosDescricaoPK = new ServicosDescricaoPK();
        servicosDescricaoPK.setContratoIdContrato(contrato.getIdContrato());
        servicosDescricaoPK.setServicosIdServicos(servicos.getIdServicos());
        DAOs.DAOServicosDescricao daoServicosDescricao = new DAOs.DAOServicosDescricao();
        ServicosDescricao servicosDescricao = daoServicosDescricao.obter(servicosDescricaoPK);
        if(servicosDescricao == null){
            servicosDescricao = new ServicosDescricao();
            servicosDescricao.setContrato(contrato);
            servicosDescricao.setDataServico(date);
            servicosDescricao.setServicos(servicos);
            servicosDescricao.setServicosDescricaoPK(servicosDescricaoPK);
            servicosDescricao.setValorCobrado(10.0);
            daoServicosDescricao.inserir(servicosDescricao);
        }else{
            System.out.println("Já Cadastrado");
        }
        }
}
