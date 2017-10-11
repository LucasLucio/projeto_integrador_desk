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

public class GUI_ContratoBuscar extends JDialog {

    //Valores e outros campos;
    int id_contrato;

    private final Container cp;
    Point p;
    
    private JPanel painelAviso = new JPanel();
    private JPanel painelAjuste = new JPanel(new FlowLayout());
    private JPanel painelInf = new JPanel();
    private JPanel painelNorte = new JPanel(new GridLayout(2, 1));

    private JButton btnBuscar = new JButton("Buscar Contrato");
    private JButton btnSalvar = new JButton("Remover Contrato");

    private JTable table = new JTable();
    private TableModel tableModel;

    JLabel lbId = new JLabel("");
    JLabel lbDataContrato = new JLabel("");
    JLabel lbAvisos = new JLabel("Bem Vindo");
    JLabel lbValorContrato = new JLabel("Valor Total:  R$ 00.00");
    JLabel lbSolicitante = new JLabel("Solicitante: ");
    
    JTextField tfId_contrato = new JTextField(30);
    
    private SimpleDateFormat DateDataContrato = new SimpleDateFormat("dd/MM/yyyy");
    private JFormattedTextField tfData = new JFormattedTextField();
    MaskFormatter maskDataNasc;

    public GUI_ContratoBuscar() throws ParseException {
        this.maskDataNasc = new MaskFormatter("##/##/####");

        setTitle("Buscar Contrato");
        setLayout(new FlowLayout());
        setSize(1000, 500);

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        painelAjuste.add(tfId_contrato);
        painelAjuste.add(btnBuscar);
        painelNorte.add(painelAjuste);
        painelNorte.add(painelInf);

        painelAviso.setBackground(Color.green);
        painelNorte.setBackground(Color.lightGray);
        painelAjuste.setBackground(Color.lightGray);
        
        

        cp.add(BorderLayout.SOUTH, painelAviso);
        cp.add(BorderLayout.NORTH, painelNorte);

        // Set Id e Data
        DAOContrato daoContrato = new DAOContrato();
       // aqui tem que mudar lbDataContrato.setText("Data do Contrato: " + DateDataContrato.format(new Date()));


        painelAviso.add(lbAvisos);

        painelInf.add(lbSolicitante);
        painelInf.add(lbValorContrato);
        painelInf.add(btnSalvar);
        btnSalvar.setEnabled(false);
        painelInf.setBackground(Color.LIGHT_GRAY);

        //Coisas dos painel que nao mudam
        lbSolicitante.setForeground(Color.blue);
        lbDataContrato.setForeground(Color.blue);
        lbId.setForeground(Color.blue);
        lbValorContrato.setForeground(Color.blue);

        //Itens para a Table
        List<EntidadeTable> lista = new ArrayList<>();
        tableModel = new TableModel(lista);
        table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        cp.add(scrollPane);

        btnBuscar.addActionListener(btnBuscarListener);
        btnSalvar.addActionListener(btnSalvarListener);

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
                    int response = JOptionPane.showConfirmDialog(null, "Deseja sair sem Fechar o Contrato??", "Tem Certeza?",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                       dispose();
                    }
                }
            }
        });


        p = new CentroDoMonitorMaior().getCentroMonitorMaior(this);

        setLocation(p);

        setModal(
                true);
        setVisible(
                true);

    }

    private ActionListener btnBuscarListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            id_contrato = Integer.valueOf(tfId_contrato.getText());
            Contrato contrato = new Contrato();
            DAOContrato daoContrato = new DAOContrato();
            contrato = daoContrato.obter(id_contrato);
            //lbSolicitante.setText("Solicitante: "+contrato.);
        }
    };

    private ActionListener btnRemListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (table.getSelectedRow() != -1 && table.getSelectedRow() < tableModel.getRowCount()) {
                int[] aux = table.getSelectedRows();
                for (int i : aux) {
                }
                tableModel.onRemove(table.getSelectedRows());
            }
        }
    };

    private ActionListener btnSalvarListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
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
