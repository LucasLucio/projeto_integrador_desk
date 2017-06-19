package GUI;

import java.awt.BorderLayout;
import DAOs.DAOCidade;
import Entidades.Cidade;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.text.SimpleDateFormat;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class GUI_Cidade extends JDialog {

    private Container cp;
    private JPanel pnNorte = new JPanel(new FlowLayout());
    private JPanel pnCentro = new JPanel(new GridLayout(0, 2));
    private JPanel pnSul = new JPanel(new FlowLayout());
    private JPanel pnAjusteSpinner = new JPanel();

    private JLabel lbIdCidade = new JLabel("IdCidade:");
    private JTextField tfIdCidade = new JTextField(10);

    private JButton btBuscar = new JButton("Buscar");
    private JButton btInserir = new JButton("Criar");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JButton btRemover = new JButton("Remover");
    private JButton btAtualizar = new JButton("Atualizar");
    private JButton btListar = new JButton("Listar");

    private JLabel lbAviso = new JLabel("Aviso");
    DAOCidade controle = new DAOCidade();
    List<Cidade> dados = new ArrayList<>();

    private boolean acao = true;
    Cidade entidade = new Cidade();

    private JLabel lbNome = new JLabel("Nome");
    private JTextField tfNome = new JTextField();
    private JLabel lbUf = new JLabel("Uf");
    private JTextField tfUf = new JTextField();
    private SimpleDateFormat DateDataCadastro = new SimpleDateFormat("dd/MM/yyyy");
    private JLabel lbDataCadastro = new JLabel("DataCadastro");
    private JSpinner SpDataCadastro = new JSpinner(new SpinnerDateModel());
    private final JSpinner.DateEditor spinnerEditor = new JSpinner.DateEditor(SpDataCadastro, "dd/MM/yyyy");

    private JDialog dialog = new JDialog();
    private JTextArea text = new JTextArea();
    private JScrollPane scroll = new JScrollPane(text);
    private Integer IdCidade;

    public void AtivaFields(boolean nome, boolean uf, boolean data) {
        tfNome.setEnabled(nome);
        tfUf.setEnabled(uf);
        SpDataCadastro.setEnabled(data);
    }

    public void Ativabtn(boolean b, boolean i, boolean s, boolean c, boolean r, boolean a, boolean l) {
        btBuscar.setVisible(b);
        btInserir.setVisible(i);
        btSalvar.setVisible(s);
        btCancelar.setVisible(c);
        btRemover.setVisible(r);
        btAtualizar.setVisible(a);
        btListar.setVisible(l);
    }

    public void LimpaFields() {
        tfNome.setText("");
        tfUf.setText("");
        Date date = new Date();
        SpDataCadastro.setValue(date);
    }

    public GUI_Cidade() throws ParseException {

        setSize(800, 200);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("");

        pnNorte.setBackground(Color.lightGray);
        pnCentro.setBackground(Color.lightGray);
        pnSul.setBackground(Color.lightGray);
        pnAjusteSpinner.setBackground(Color.lightGray);

        dialog.add(scroll);
        text.setEditable(false);
        dialog.setSize(500, 180);

        pnNorte.add(lbIdCidade);
        pnNorte.add(tfIdCidade);
        pnNorte.add(btBuscar);
        pnNorte.add(btInserir);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);
        pnNorte.add(btRemover);
        pnNorte.add(btAtualizar);
        pnNorte.add(btListar);

        pnSul.add(lbAviso);
        lbAviso.setOpaque(true);

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnCentro.add(lbNome);
        pnCentro.add(tfNome);

        pnCentro.add(lbUf);
        pnCentro.add(tfUf);

        SpDataCadastro.setEditor(spinnerEditor);
        
        pnCentro.add(lbDataCadastro);
        pnAjusteSpinner.add(SpDataCadastro);
        pnCentro.add(pnAjusteSpinner);
        
        Ativabtn(true, false, false, false, false, false, true);
        LimpaFields();
        AtivaFields(false, false, false);

        btBuscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    entidade = new Cidade();
                    IdCidade = Integer.valueOf(tfIdCidade.getText());
                    entidade.setIdCidade(IdCidade);
                    if (IdCidade <= 0) {
                        int error = 3 / 0;
                    }
                    entidade = controle.obter(entidade.getIdCidade());
                    pnSul.setBackground(Color.green);
                    if (entidade != null) {
                        Ativabtn(true, false, false, false, true, true, true);
                        AtivaFields(false, false, false);
                        lbAviso.setText("Achou na lista");
                        pnSul.setBackground(Color.green);
                        tfNome.setText(entidade.getNome());
                        tfUf.setText(entidade.getUf());
                        SpDataCadastro.setValue((Date)entidade.getDataCadastro());
                    } else {
                        lbAviso.setText("Não achou na lista");
                        Ativabtn(true, true, false, false, false, false, true);
                        pnSul.setBackground(Color.red);
                        btAtualizar.setVisible(false);
                        btRemover.setVisible(false);
                        AtivaFields(false, false, false);
                    }
                } catch (Exception err) {
                    lbAviso.setText("Erro nos Dados");
                    pnSul.setBackground(Color.red);
                }
            }
        }
        );
        btInserir.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                acao = true;
                Ativabtn(false, false, true, true, false, false, false);
                AtivaFields(true, true, false);
                tfIdCidade.setText(String.valueOf(IdCidade));
                tfIdCidade.setEnabled(false);
                tfNome.requestFocus();
                LimpaFields();

            }
        }
        );

        btSalvar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (acao) {
                    try {
                        Cidade entidade = new Cidade();
                        entidade.setIdCidade(IdCidade);
                        if (!tfNome.getText().equals("") && !tfUf.getText().equals("")) {
                            entidade.setNome(tfNome.getText());
                            entidade.setUf(tfUf.getText());
                            DateDataCadastro.setLenient(false);
                            System.out.println("Foi1");
                            entidade.setDataCadastro((Date) SpDataCadastro.getValue());
                            System.out.println("foi");
                            controle.inserir(entidade);
                            Ativabtn(true, false, false, false, true, true, true);
                            AtivaFields(false, false, false);
                            tfIdCidade.setEnabled(true);
                            tfIdCidade.requestFocus();
                            tfIdCidade.selectAll();
                            lbAviso.setText("Registro inserido");
                            pnSul.setBackground(Color.green);
                        } else {
                            int x = Integer.valueOf("bug");
                        }
                    } catch (Exception btSalvar) {
                        JOptionPane.showMessageDialog(null, "ERRO NOS DADOS", "ERROR", JOptionPane.ERROR_MESSAGE);
                        pnSul.setBackground(Color.red);
                        lbAviso.setText("Erro nos Dados");
                    }

                } else {
                    try {
                        Cidade entidadeOriginal = entidade;
                        Cidade entidadeModificada = new Cidade();
                        entidadeModificada.setIdCidade(IdCidade);
                        if (!tfNome.getText().equals("") && !tfUf.getText().equals("")) {
                            entidadeModificada.setNome(tfNome.getText());
                            entidadeModificada.setUf(tfUf.getText());
                            DateDataCadastro.setLenient(false);
                            entidadeModificada.setDataCadastro((Date) SpDataCadastro.getValue());
                            controle.atualizar(entidadeModificada);
                            tfIdCidade.setEnabled(true);
                            tfIdCidade.requestFocus();
                            tfIdCidade.selectAll();
                            Ativabtn(true, false, false, false, true, true, true);
                            AtivaFields(false, false, false);
                            lbAviso.setText("Modificado com Sucesso");
                            pnSul.setBackground(Color.green);
                        } else {
                            int x = Integer.valueOf("bug");
                        }
                    } catch (Exception btSalvarAtt) {
                        JOptionPane.showMessageDialog(null, "ERRO NOS DADOS", "ERROR", JOptionPane.ERROR_MESSAGE);
                        lbAviso.setText("Erro nos Dados");
                        pnSul.setBackground(Color.red);
                    }
                }
            }
        }
        );
        btCancelar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                lbAviso.setText("Cancelado");
                tfIdCidade.setEnabled(true);
                tfIdCidade.requestFocus();
                tfIdCidade.selectAll();
                Ativabtn(true, false, false, false, false, false, true);
                AtivaFields(false, false, false);
                LimpaFields();
                lbAviso.setText("Cancelado com Sucesso");
                pnSul.setBackground(Color.green);
            }
        }
        );
        btAtualizar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                acao = false;
                tfIdCidade.setText(String.valueOf(IdCidade));
                tfIdCidade.setEnabled(false);
                AtivaFields(true, true, false);
                Ativabtn(false, false, true, true, false, false, false);
                SpDataCadastro.setValue(entidade.getDataCadastro());
            }
        }
        );

        btRemover.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                btRemover.setVisible(false);
                btAtualizar.setVisible(false);
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro: " + entidade.getIdCidade() + " - " + entidade.getNome() + "?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    LimpaFields();
                    tfIdCidade.requestFocus();
                    tfIdCidade.setEnabled(true);
                    tfIdCidade.setText(String.valueOf(""));
                    controle.remover(entidade);
                    lbAviso.setText("Removeu");
                    pnSul.setBackground(Color.green);
                } else {
                    lbAviso.setText("Cancelada a remoção");
                    Ativabtn(true, false, false, false, true, true, true);
                    btAtualizar.setVisible(true);
                }

            }
        }
        );
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                try {
                    String aux[];
                    text.setText("");
                    dados = controle.list();
                    try {
                        if (dados.get(0) == null) {
                        }
                    } catch (Exception lista) {
                        JOptionPane.showMessageDialog(null, "Não possui nenhuma cidade ainda", "OPS", JOptionPane.PLAIN_MESSAGE);
                        int erro = 3 / 0;
                    }
                    for (Cidade linha : dados) {
                        aux = String.valueOf(linha).split(";");
                        text.append(
                                "IdCidade: "
                                + Integer.valueOf(aux[0])
                                + "\n"
                                + "Nome: "
                                + aux[1]
                                + "\n"
                                + "Uf: "
                                + aux[2]
                                + "\n"
                                + "DataCadastro: "
                                + DateDataCadastro.format(DateDataCadastro.parse(aux[3]))
                                + "\n-------------------------------------------------------------------------------------------\n"
                        );
                    }
                    dialog.setLocationRelativeTo(cp);
                    dialog.setModal(true);
                    dialog.setVisible(true);
                } catch (Exception Lista) {
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        tfIdCidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.doClick();
            }
        });

        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
    }
}
