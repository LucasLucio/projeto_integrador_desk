package GUI;

import DAOs.DAOContrato;
import DAOs.DAOPessoa;
import DAOs.DAOServicos;
import DAOs.DAOServicosDescricao;
import Entidades.Contrato;
import Entidades.Pessoa;
import Entidades.Servicos;
import Entidades.ServicosDescricao;
import Entidades.ServicosDescricaoPK;
import Ferramentas.CentroDoMonitorMaior;
import Ferramentas.EntidadeTable;
import Ferramentas.JanelaPesquisarPessoa;
import Ferramentas.JanelaPesquisarServico;
import Ferramentas.TableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.text.MaskFormatter;

public class GUI_ContratoNovo extends JDialog {

    //Valores e outros campos;
    double valor_contrato;
    Date data_contrato;
    String pessoa = "";
    int id_cont;
    String Fechou = "";

    private final Container cp;
    Point p;
    private JPanel painelBotoes = new JPanel(new FlowLayout());
    private JPanel painelBotoes2 = new JPanel(new FlowLayout());
    private JPanel painelDescricao = new JPanel(new FlowLayout());
    private JPanel painelInicio = new JPanel(new FlowLayout());
    private JPanel painelAjuste = new JPanel(new GridLayout(4, 1));
    private JPanel painelAjusteSul = new JPanel(new GridLayout(2, 1));
    private JPanel painelAviso = new JPanel();
    private JPanel painelInf = new JPanel();

    private JButton btnAdd = new JButton("Buscar Serviço");
    private JButton btnSalvar = new JButton("Fechar Contrato");
    private JButton btnCancelar = new JButton("Cancelar");

    JButton btnAdicionar = new JButton("Adicionar no Contrato");
    JButton btnRem = new JButton("Remover do Contrato");

    JButton btnPessoa = new JButton("Adicionar Solicitante");

    private JTable table = new JTable();
    private TableModel tableModel;

    JLabel lbId = new JLabel("");
    JLabel lbIdServico = new JLabel("Id do Serviço:");
    JLabel lbData = new JLabel("Data do Serviço:");
    JLabel lbValor = new JLabel("Valor do Serviço: ");
    JLabel lbNome = new JLabel("Descrição:");
    JLabel lbDataContrato = new JLabel("");
    JLabel lbAvisos = new JLabel("Bem Vindo");
    JLabel lbValorContrato = new JLabel("Valor Total:  R$ 00.00");
    JLabel lbSolicitante = new JLabel("Solicitante: ");

    JTextField tfIdServico = new JTextField(10);
    JTextField tfValor = new JTextField(10);
    JTextField tfNome = new JTextField(20);

    private SimpleDateFormat DateDataContrato = new SimpleDateFormat("dd/MM/yyyy");
    private JFormattedTextField tfData = new JFormattedTextField();
    MaskFormatter maskDataNasc;

    public GUI_ContratoNovo() throws ParseException {
        this.maskDataNasc = new MaskFormatter("##/##/####");

        setTitle("Contrato Novo");
        setLayout(new FlowLayout());
        setSize(1000, 500);

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        painelAjuste.add(painelInicio);
        painelAjuste.add(painelBotoes);
        painelAjuste.add(painelDescricao);
        painelAjuste.add(painelBotoes2);
        painelAjusteSul.add(painelInf);
        painelAjusteSul.add(painelAviso);

        painelBotoes2.setBackground(Color.LIGHT_GRAY);
        painelAviso.setBackground(Color.green);

        cp.add(BorderLayout.NORTH, painelAjuste);
        cp.add(BorderLayout.SOUTH, painelAjusteSul);

        painelInicio.add(lbId);
        // Set Id e Data
        DAOContrato daoContrato = new DAOContrato();
        id_cont = daoContrato.autoIdContrato();
        lbId.setText("Id do Contrato: " + id_cont + "                                                                                                             ");
        data_contrato = new Date();
        lbDataContrato.setText("Data do Contrato: " + DateDataContrato.format(data_contrato));

        painelInicio.add(lbDataContrato);
        painelInicio.setBackground(Color.LIGHT_GRAY);

        painelBotoes.add(btnAdd);
        painelBotoes.add(btnCancelar);
        painelBotoes.setBackground(Color.LIGHT_GRAY);

        painelDescricao.add(lbIdServico);
        painelDescricao.add(tfIdServico);
        painelDescricao.add(lbNome);
        painelDescricao.add(tfNome);
        painelDescricao.add(lbData);
        maskDataNasc.install(tfData);
        tfData.setText("00/00/0000");
        painelDescricao.add(tfData);
        painelDescricao.add(lbValor);
        painelDescricao.add(tfValor);
        painelDescricao.setBackground(Color.LIGHT_GRAY);

        painelBotoes2.add(btnAdicionar);
        painelBotoes2.add(btnRem);

        painelAviso.add(lbAvisos);

        painelInf.add(lbSolicitante);
        painelInf.add(btnPessoa);
        painelInf.add(lbValorContrato);
        painelInf.add(btnSalvar);
        painelInf.setBackground(Color.LIGHT_GRAY);

        //Coisas dos painel que nao mudam
        tfNome.setEnabled(false);
        tfIdServico.setEnabled(false);
        lbSolicitante.setForeground(Color.red);
        lbDataContrato.setForeground(Color.blue);
        lbId.setForeground(Color.blue);
        lbValorContrato.setForeground(Color.blue);

        //Itens para a Table
        List<EntidadeTable> lista = new ArrayList<>();
        tableModel = new TableModel(lista);
        table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        cp.add(scrollPane);

        btnAdd.addActionListener(btnAddListener);
        btnRem.addActionListener(btnRemListener);
        btnSalvar.addActionListener(btnSalvarListener);
        btnCancelar.addActionListener(btnCancelarListener);

        table.setDefaultEditor(Date.class, new DateEditor());
        table.setDefaultRenderer(Date.class, new DateRenderer());

        // Esse código abre a outra janela quando pressionar INSERT
        // É necessário clicar antes na tabela para o código funcionar
        InputMap im = table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap am = table.getActionMap();
        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0);
        im.put(enterKey, "Action.insert");

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                if (tableModel.mudou) {
                    if (!Fechou.equals("")) {
                        dispose();
                    }
                    if (Fechou.equals("")) {

                        int response = JOptionPane.showConfirmDialog(null, "Deseja sair sem Fechar o Contrato??", "Tem Certeza?",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            dispose();
                        }
                    }
                }
            }
        });

        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Verificaçoes
                    String erro = String.valueOf(tfValor.getText());

                    //Verificação de datas
                    DateDataContrato.setLenient(false);
                    String Cad = DateDataContrato.format(DateDataContrato.parse(tfData.getText()));
                    String Atual = DateDataContrato.format(new Date());
                    String aux[] = Cad.split("/");
                    String aux2[] = Atual.split("/");
                    int anoA = Integer.valueOf(aux2[2]);
                    int anoC = Integer.valueOf(aux[2]);
                    int mesA = Integer.valueOf(aux2[1]);
                    int mesC = Integer.valueOf(aux[1]);
                    int diaA = Integer.valueOf(aux2[0]);
                    int diaC = Integer.valueOf(aux[0]);
                    if (anoC < anoA) {
                        int errou = Integer.valueOf("erro");
                    }
                    if (anoC == anoA) {
                        if (mesC < mesA) {
                            int errou = Integer.valueOf("erro");
                        }
                        if (mesC == mesA) {
                            if (diaC < diaA) {
                                int errou = Integer.valueOf("erro");
                            }
                            if (diaC == diaA) {
                                int errou = Integer.valueOf("erro");
                            }
                        }
                    }
                    //Ver se data está diponivel
                    DAOServicosDescricao daoServicosDescricao = new DAOServicosDescricao();
                    List<ServicosDescricao> dados = daoServicosDescricao.list();
                    for (ServicosDescricao linha : dados) {
                        aux = String.valueOf(linha).split(";");
                        ServicosDescricaoPK Sdp = new ServicosDescricaoPK();
                        Sdp.setContratoIdContrato(Integer.valueOf(aux[0]));
                        Sdp.setServicosIdServicos(Integer.valueOf(aux[1]));
                        ServicosDescricao Sd = new ServicosDescricao();
                        Sd = daoServicosDescricao.obter(Sdp);
                        String data = DateDataContrato.format(Sd.getDataServico());
                        if (tfIdServico.getText().equals(aux[1])) {

                            if (tfData.getText().equals(data)) {
                                JOptionPane.showMessageDialog(null, "Este serviço não está disponivel para esta data", "OPS", JOptionPane.PLAIN_MESSAGE);
                                int erro3 = 1 / 0;
                            }
                        }
                    }
                    // verifica se já nao está no contrato
                    List<EntidadeTable> dados2 = tableModel.getDados();
                    for (EntidadeTable linha2 : dados2) {
                        aux = String.valueOf(linha2).split(";");

                        if (tfIdServico.getText().equals(aux[0])) {
                            JOptionPane.showMessageDialog(null, "O Solicitante já tem este serviço", "OPS", JOptionPane.PLAIN_MESSAGE);
                            int erro3 = 1 / 0;
                        }
                    }
                    //Adicionar na Table
                    tableModel.onAdd(new EntidadeTable(Integer.valueOf(tfIdServico.getText()), tfNome.getText(), tfData.getText(),
                            Double.valueOf(tfValor.getText())));
                    valor_contrato += Double.valueOf(tfValor.getText());
                    lbValorContrato.setText("Valor Total: R$" + valor_contrato);
                } catch (Exception err) {
                    painelAviso.setBackground(Color.red);
                    lbAvisos.setText("Erro nos Dados");
                }
            }
        }
        );

        btnPessoa.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                DAOPessoa daoPessoa = new DAOPessoa();
                List<String> listaAuxiliar = daoPessoa.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisarPessoa(listaAuxiliar, getBounds().x + getWidth() + 5, getBounds().y).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        int id = Integer.valueOf(aux[0].trim());
                        Pessoa individuo = daoPessoa.obter(id);
                        pessoa = individuo.getIdPessoas() + " - " + individuo.getNome();
                        lbSolicitante.setText("Solicitante: " + pessoa);
                        lbSolicitante.setForeground(Color.blue);
                    }

                }
            }
        }
        );
        p = new CentroDoMonitorMaior().getCentroMonitorMaior(this);

        setLocation(p);

        setModal(
                true);
        setVisible(
                true);

    }

    private ActionListener btnAddListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            DAOServicos daoServico = new DAOServicos();
            List<String> listaAuxiliar = daoServico.listInOrderNomeStrings("id");
            if (listaAuxiliar.size() > 0) {
                String selectedItem = new JanelaPesquisarServico(listaAuxiliar, getBounds().x + getWidth() + 5, getBounds().y).getValorRetornado();
                if (!selectedItem.equals("")) {
                    String[] aux = selectedItem.split("-");
                    int id = Integer.valueOf(aux[0].trim());
                    Servicos servicos = new Servicos();
                    servicos = daoServico.obter(id);
                    tfIdServico.setText(String.valueOf(servicos.getIdServicos()));
                    tfNome.setText(servicos.getNome());
                    tfValor.setText(String.valueOf(servicos.getValor()));
                } else {
                    btnAdd.requestFocus();
                }
            }
        }
    };

    private ActionListener btnRemListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (table.getSelectedRow() != -1 && table.getSelectedRow() < tableModel.getRowCount()) {
                int[] aux = table.getSelectedRows();
                for (int i : aux) {
                    valor_contrato = valor_contrato - Double.valueOf(String.valueOf(table.getValueAt(i, 3)));
                }
                tableModel.onRemove(table.getSelectedRows());
                lbValorContrato.setText("Valor Total: R$" + valor_contrato);
            }
        }
    };

    private ActionListener btnSalvarListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                if (pessoa == null || pessoa.equals("")) {
                    JOptionPane.showMessageDialog(null, "Verifique se o Contrato tem um Solicitante", "OPS", JOptionPane.PLAIN_MESSAGE);
                    int erro = 1 / 0;
                }
                int linhas = table.getRowCount();
                if (linhas == 0) {
                    JOptionPane.showMessageDialog(null, "Não é possivel criar um Contrato Vazio", "OPS", JOptionPane.PLAIN_MESSAGE);
                    int erro = 1 / 0;
                }
                //Criando o Contrato
                //System.out.println("5");
                DAOs.DAOPessoa daoPessoa = new DAOs.DAOPessoa();
                String[] p1 = pessoa.split(" - ");
                String p2 = pessoa;
                Pessoa pessoap = daoPessoa.obter(Integer.valueOf(p1[0]));
                Contrato contrato = new Contrato();
                DAOs.DAOContrato daoContrato = new DAOs.DAOContrato();
                contrato.setContratoDescricao("Contrato do Cliente " + p2);
                //System.out.println("1");
                contrato.setDataContrato(data_contrato);
                contrato.setIdContrato(id_cont);
                contrato.setValorContrato(valor_contrato);
                contrato.setPessoaIdPessoas(pessoap);
                daoContrato.inserir(contrato);
                //System.out.println("1");
                //Inserindo itens do contrato no BD

                List<EntidadeTable> dados2 = tableModel.getDados();
                for (EntidadeTable linha2 : dados2) {
                    ServicosDescricaoPK servicosDescricaoPK = new ServicosDescricaoPK();
                    servicosDescricaoPK.setContratoIdContrato(contrato.getIdContrato());
                    String[] auxiliar = String.valueOf(linha2).split(";");
                    System.out.println(linha2);
                    DAOServicos daoServicos = new DAOServicos();
                    Servicos servicos = new Servicos();
                    int id = Integer.valueOf(auxiliar[0]);
                    Date data = null;
                    Double value = Double.valueOf(auxiliar[3]);
                    try {
                        data = DateDataContrato.parse(auxiliar[2]);
                    } catch (ParseException ex) {
                        Logger.getLogger(GUI_ContratoNovo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    servicos = daoServicos.obter(id);
                    servicosDescricaoPK.setServicosIdServicos(servicos.getIdServicos());
                    DAOs.DAOServicosDescricao daoServicosDescricao = new DAOs.DAOServicosDescricao();
                    ServicosDescricao servicosDescricao = daoServicosDescricao.obter(servicosDescricaoPK);
                    //System.out.println("2");
                    //if (servicosDescricao == null) {
                    servicosDescricao = new ServicosDescricao();
                    servicosDescricao.setContrato(contrato);
                    servicosDescricao.setDataServico(data);
                    servicosDescricao.setServicos(servicos);
                    servicosDescricao.setServicosDescricaoPK(servicosDescricaoPK);
                    //System.out.println("merda");
                    servicosDescricao.setValorCobrado(value);
                    daoServicosDescricao.inserir(servicosDescricao);
                    //System.out.println("2");
                    painelAviso.setBackground(Color.blue);
                    lbAvisos.setText("Cadastro fechado com Sucesso");
                    Fechou = "sim";
                    lbAvisos.setForeground(Color.white);
                    btnSalvar.setEnabled(false);
                    btnAdd.setEnabled(false);
                    btnAdicionar.setEnabled(false);
                    btnCancelar.setEnabled(false);
                    btnPessoa.setEnabled(false);
                    btnRem.setEnabled(false);

                    tfData.setText("");
                    tfData.setEnabled(false);
                    tfIdServico.setText("");
                    tfIdServico.setEnabled(false);
                    tfNome.setText("");
                    tfNome.setEnabled(false);
                    tfValor.setText("");
                    tfValor.setEnabled(false);
                    // } else {
                    // JOptionPane.showMessageDialog(null, "Já Cadastrado", "OPS", JOptionPane.PLAIN_MESSAGE);
                    // }
                }
            } catch (Exception err) {
                painelAviso.setBackground(Color.red);
                lbAvisos.setForeground(Color.black);
                lbAvisos.setText("Erro nos Dados");
            }

        }
    };

    private ActionListener btnCancelarListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            tfData.setText("00/00/0000");
            tfData.setEnabled(true);
            tfIdServico.setText("");
            tfIdServico.setEnabled(false);
            tfNome.setText("");
            tfNome.setEnabled(false);
            tfValor.setText("");
            tfValor.setEnabled(true);
        }
    };

    private static class DateRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!(value instanceof Date)) {
                return this;
            }
            DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
            setText(DATE_FORMAT.format((Date) value));
            return this;
        }
    }

    private static class DateEditor extends AbstractCellEditor implements TableCellEditor {

        private static final long serialVersionUID = 1L;
        private JSpinner theSpinner;
        private Object value;

        DateEditor() {
            theSpinner = new JSpinner(new SpinnerDateModel());
            theSpinner.setOpaque(true);
            theSpinner.setEditor(new JSpinner.DateEditor(theSpinner, "dd/MM/yyyy"));
        }

        @Override
        public Object getCellEditorValue() {
            return theSpinner.getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            theSpinner.setValue(value);
            if (isSelected) {
                theSpinner.setBackground(table.getSelectionBackground());
            } else {
                theSpinner.setBackground(table.getBackground());
            }
            return theSpinner;
        }
    }
}
