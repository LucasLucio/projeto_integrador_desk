package GUI;

import DAOs.DAOCidade;
import java.awt.BorderLayout;
import DAOs.DAOPessoa;
import Entidades.Cidade;
import Entidades.Pessoa;
import Ferramentas.JanelaPesquisarCidade;
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

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.text.MaskFormatter;

public class GUI_Pessoa extends JDialog {

    private Container cp;
    private JPanel pnNorte = new JPanel(new FlowLayout());
    private JPanel pnCentro = new JPanel(new GridLayout(0, 2));
    private JPanel pnSul = new JPanel(new FlowLayout());
    private JPanel pnCidade = new JPanel();

    private JLabel lbIdPessoas = new JLabel("Id Pessoa:");
    private JTextField tfIdPessoas = new JTextField(10);

    private JButton btBuscar = new JButton("Buscar");
    private JButton btInserir = new JButton("Criar");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JButton btRemover = new JButton("Remover");
    private JButton btAtualizar = new JButton("Atualizar");
    private JButton btListar = new JButton("Listar");
    private JButton btJnPesquisa = new JButton("Pesquisar Cidade");

    private JLabel lbAviso = new JLabel("Aviso");

    DAOPessoa controle = new DAOPessoa();
    List<Pessoa> dados = new ArrayList<>();
    private boolean acao = true;
    Pessoa entidade = new Pessoa();

    private JLabel lbNome = new JLabel("Nome");
    private JTextField tfNome = new JTextField();

    private JLabel lbRg = new JLabel("Rg");
    private JTextField tfRg = new JTextField();

    private JLabel lbCpf = new JLabel("Cpf");
    private JTextField tfCpf = new JTextField();

    private JLabel lbTelefone = new JLabel("Telefone");
    private JTextField tfTelefone = new JTextField();

    private JLabel lbEmail = new JLabel("Email");
    private JTextField tfEmail = new JTextField();

    private JLabel lbLogin = new JLabel("Login");
    private JTextField tfLogin = new JTextField();

    private JLabel lbSenha = new JLabel("Senha");
    private JTextField tfSenha = new JTextField();

    private SimpleDateFormat DateDataNasc = new SimpleDateFormat("dd/MM/yyyy");
    private JLabel lbDataNasc = new JLabel("Data de Nascimento");
    private JFormattedTextField tfDataNascimento = new JFormattedTextField();
    MaskFormatter maskDataNasc = new MaskFormatter("##/##/####");

    private JLabel lbSexo = new JLabel("Sexo");
    private JPanel pnSexo = new JPanel();
    private ButtonGroup bgSexo = new ButtonGroup();
    private JRadioButton rbMasculino = new JRadioButton("Masculino", true);
    private JRadioButton rbFeminino = new JRadioButton("Feminino", true);

    private SimpleDateFormat DateDataCadastro = new SimpleDateFormat("dd/MM/yyyy");
    private JLabel lbDataCadastro = new JLabel("Data de Cadastro");
    private JSpinner SpDataCadastro = new JSpinner(new SpinnerDateModel());
    private final JSpinner.DateEditor spinnerEditorCad = new JSpinner.DateEditor(SpDataCadastro, "dd/MM/yyyy");

    private JLabel lbCidadeIdCidade = new JLabel("CidadeIdCidade");
    private JTextField tfCidadeIdCidade = new JTextField("            ");

    private JDialog dialog = new JDialog();
    private JTextArea text = new JTextArea();
    private JScrollPane scroll = new JScrollPane(text);
    private Integer IdPessoas;

    DAOCidade daoCidade = new DAOCidade();
    Cidade cidadeSave = new Cidade();

    public void AtivaFields(boolean n, boolean r, boolean c, boolean t, boolean e, boolean l, boolean s, boolean dn, boolean se, boolean dc) {
        tfNome.setEnabled(n);
        tfRg.setEnabled(r);
        tfCpf.setEnabled(c);
        tfTelefone.setEnabled(t);
        tfEmail.setEnabled(e);
        tfLogin.setEnabled(l);
        tfSenha.setEnabled(s);
        tfDataNascimento.setEnabled(dn);
        rbFeminino.setEnabled(se);
        rbMasculino.setEnabled(se);
        SpDataCadastro.setEnabled(dc);
    }

    public void Ativabtn(boolean b, boolean i, boolean s, boolean c, boolean r, boolean a, boolean l, boolean p) {
        btBuscar.setVisible(b);
        btInserir.setVisible(i);
        btSalvar.setVisible(s);
        btCancelar.setVisible(c);
        btRemover.setVisible(r);
        btAtualizar.setVisible(a);
        btListar.setVisible(l);
        btJnPesquisa.setVisible(p);
    }

    public void LimpaFields() {
        Date date = new Date();
        tfNome.setText("");
        tfRg.setText("");
        tfCpf.setText("");
        tfTelefone.setText("");
        tfLogin.setText("");
        tfSenha.setText("");
        tfDataNascimento.setText(DateDataCadastro.format(date));
        rbMasculino.setSelected(true);
        SpDataCadastro.setValue(date);
    }

    public void verifica_campos() {
        if ((tfNome.getText().equals("") || tfNome.getText().equals(" ")) || (tfCpf.getText().equals("") || tfCpf.getText().equals(" "))
                || (tfLogin.getText().equals("") || tfLogin.getText().equals(" ")) || (tfRg.getText().equals("") || tfRg.getText().equals(" "))
                && (tfSenha.getText().equals("") || tfSenha.getText().equals(" ")) || (tfTelefone.getText().equals("") || tfTelefone.getText().equals(" "))) {
            int x = Integer.valueOf("Bug");
        }
        if (!tfEmail.equals("")) {
            if (!tfEmail.contains("@")) {
                
            } else {
            }
        }
    }

    public GUI_Pessoa() throws ParseException {

        setSize(800, 500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de Pessoa");

        pnNorte.setBackground(Color.lightGray);
        pnCentro.setBackground(Color.lightGray);
        pnSul.setBackground(Color.lightGray);

        dialog.add(scroll);
        text.setEditable(false);
        dialog.setSize(500, 200);

        pnNorte.add(lbIdPessoas);
        pnNorte.add(tfIdPessoas);
        pnNorte.add(btBuscar);
        pnNorte.add(btInserir);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);
        pnNorte.add(btRemover);
        pnNorte.add(btAtualizar);
        pnNorte.add(btListar);
        Ativabtn(true, false, false, false, false, false, true, false);

        pnSul.add(lbAviso);
        lbAviso.setOpaque(true);

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnCentro.add(lbNome);
        pnCentro.add(tfNome);

        pnCentro.add(lbRg);
        pnCentro.add(tfRg);

        pnCentro.add(lbCpf);
        pnCentro.add(tfCpf);

        pnCentro.add(lbTelefone);
        pnCentro.add(tfTelefone);

        pnCentro.add(lbEmail);
        pnCentro.add(tfEmail);

        pnCentro.add(lbLogin);
        pnCentro.add(tfLogin);

        pnCentro.add(lbSenha);
        pnCentro.add(tfSenha);

        maskDataNasc.install(tfDataNascimento);
        pnCentro.add(lbDataNasc);
        pnCentro.add(tfDataNascimento);

        bgSexo.add(rbMasculino);
        pnSexo.add(rbMasculino);

        bgSexo.add(rbFeminino);
        pnSexo.add(rbFeminino);

        pnCentro.add(lbSexo);
        pnCentro.add(pnSexo);

        SpDataCadastro.setEditor(spinnerEditorCad);
        pnCentro.add(lbDataCadastro);
        pnCentro.add(SpDataCadastro);

        pnCidade.add(tfCidadeIdCidade);
        pnCidade.add(btJnPesquisa);
        pnCentro.add(lbCidadeIdCidade);
        pnCentro.add(pnCidade);

        AtivaFields(false, false, false, false, false, false, false, false, false, false);
        tfCidadeIdCidade.setEnabled(false);

        btJnPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoCidade.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisarCidade(listaAuxiliar, getBounds().x + getWidth() + 5, getBounds().y).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfCidadeIdCidade.setText(aux[0]);
                        //clicarBotaoAutomaticamente(btnRetrieve, 0);
                    } else {
                        btJnPesquisa.requestFocus();
                    }
                }
            }
        });

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    entidade = new Pessoa();
                    IdPessoas = Integer.valueOf(tfIdPessoas.getText());
                    entidade.setIdPessoas(IdPessoas);
                    if (IdPessoas <= 0) {
                        int error = 3 / 0;
                    }
                    entidade = controle.obter(entidade.getIdPessoas());
                    if (entidade != null) {
                        Ativabtn(false, false, false, false, true, true, true, false);
                        tfNome.setText(entidade.getNome());
                        tfRg.setText(entidade.getRg());
                        tfCpf.setText(entidade.getCpf());
                        tfTelefone.setText(entidade.getTelefone());
                        tfEmail.setText(entidade.getEmail());
                        tfLogin.setText(entidade.getLogin());
                        tfSenha.setText(entidade.getSenha());
                        DateDataCadastro.setLenient(false);
                        tfDataNascimento.setText((DateDataNasc.format(entidade.getDataNasc())));
                        if (entidade.getSexo().contains("Masculino")) {
                            rbMasculino.setSelected(true);
                        }
                        if (entidade.getSexo().contains("Feminino")) {
                            rbFeminino.setSelected(true);
                        }
                        SpDataCadastro.setValue((Date) entidade.getDataCadastro());
                        tfCidadeIdCidade.setText(String.valueOf(entidade.getCidadeIdCidade().getIdCidade()) + " - "
                                + String.valueOf(entidade.getCidadeIdCidade().getNome()) + " - "
                                + String.valueOf(entidade.getCidadeIdCidade().getUf()));
                        lbAviso.setText("Achou na lista");
                        pnSul.setBackground(Color.green);
                    } else {
                        lbAviso.setText("Não achou na lista");
                        pnSul.setBackground(Color.red);
                        Ativabtn(false, true, false, false, false, false, true, false);
                        AtivaFields(false, false, false, false, false, false, false, false, false, false);
                    }
                } catch (Exception err) {
                    lbAviso.setText("Erro nos dados");
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
                Ativabtn(false, false, true, true, false, false, false, true);
                tfIdPessoas.setText(String.valueOf(IdPessoas));
                tfIdPessoas.setEnabled(false);
                LimpaFields();
                AtivaFields(true, true, true, true, true, true, true, true, true, false);
                tfCidadeIdCidade.setEnabled(false);
                tfCidadeIdCidade.setText(String.valueOf("            "));
                tfNome.requestFocus();
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
                        Pessoa entidade = new Pessoa();
                        entidade.setIdPessoas(IdPessoas);
                        entidade.setNome(tfNome.getText());
                        entidade.setRg(tfRg.getText());
                        entidade.setCpf(tfCpf.getText());
                        entidade.setTelefone(tfTelefone.getText());
                        entidade.setEmail(tfEmail.getText());
                        entidade.setLogin(tfLogin.getText());
                        entidade.setSenha(tfSenha.getText());
                        DateDataNasc.setLenient(false);
                        entidade.setDataNasc(DateDataNasc.parse(tfDataNascimento.getText()));
                        System.out.println("foi");
                        String rbSexo = " ";
                        if (rbMasculino.isSelected()) {
                            rbSexo = "Masculino";
                        }
                        if (rbFeminino.isSelected()) {
                            rbSexo = "Feminino";
                        }
                        entidade.setSexo(rbSexo);
                        entidade.setDataCadastro((Date) SpDataCadastro.getValue());
                        cidadeSave = daoCidade.obter(Integer.valueOf(tfCidadeIdCidade.getText().trim()));
                        entidade.setCidadeIdCidade(cidadeSave);
                        //verifica_campos();
                        controle.inserir(entidade);
                        Ativabtn(true, false, false, false, true, true, true, false);
                        AtivaFields(false, false, false, false, false, false, false, false, false, false);
                        tfIdPessoas.setEnabled(true);
                        tfIdPessoas.requestFocus();
                        lbAviso.setText("Registro inserido");
                        pnSul.setBackground(Color.green);
                    } catch (Exception btSalvar) {
                        lbAviso.setText("Erro nos Dados");
                        pnSul.setBackground(Color.red);
                        JOptionPane.showMessageDialog(null, "ERRO NOS DADOS", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    try {
                        Pessoa entidadeOriginal = entidade;
                        Pessoa entidadeModificada = new Pessoa();
                        entidadeModificada.setIdPessoas(IdPessoas);
                        entidadeModificada.setNome(tfNome.getText());
                        entidadeModificada.setRg(tfRg.getText());
                        entidadeModificada.setCpf(tfCpf.getText());
                        entidadeModificada.setTelefone(tfTelefone.getText());
                        entidadeModificada.setEmail(tfEmail.getText());
                        entidadeModificada.setLogin(tfLogin.getText());
                        entidadeModificada.setSenha(tfSenha.getText());
                        DateDataNasc.setLenient(false);
                        entidadeModificada.setDataNasc(DateDataCadastro.parse(DateDataNasc.format(tfDataNascimento.getText())));
                        String rbSexo = " ";
                        if (rbMasculino.isSelected()) {
                            rbSexo = "Masculino";
                        }
                        if (rbFeminino.isSelected()) {
                            rbSexo = "Feminino";
                        }
                        entidadeModificada.setSexo(rbSexo);
                        DateDataCadastro.setLenient(false);
                        entidadeModificada.setDataCadastro((Date) SpDataCadastro.getValue());
                        String[] Cidade = tfCidadeIdCidade.getText().split(" - ");
                        String cidade = Cidade[0].trim();
                        cidadeSave = daoCidade.obter(Integer.valueOf(cidade));
                        entidadeModificada.setCidadeIdCidade(cidadeSave);
                        verifica_campos();
                        controle.atualizar(entidadeModificada);
                        lbAviso.setText("Registro alterado");
                        tfIdPessoas.setEnabled(true);
                        tfIdPessoas.requestFocus();
                        tfIdPessoas.selectAll();
                        Ativabtn(true, false, false, false, true, true, true, false);
                        AtivaFields(false, false, false, false, false, false, false, false, false, false);
                        tfCidadeIdCidade.setEnabled(false);
                        lbAviso.setText("Registro alterado");
                        pnSul.setBackground(Color.green);
                    } catch (Exception btSalvarAtt) {
                        lbAviso.setText("Erro nos Dados");
                        pnSul.setBackground(Color.red);
                        JOptionPane.showMessageDialog(null, "ERRO NOS DADOS", "ERROR", JOptionPane.ERROR_MESSAGE);
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
                tfIdPessoas.setEnabled(true);
                tfIdPessoas.requestFocus();
                tfIdPessoas.selectAll();
                Ativabtn(true, false, false, false, false, false, true, false);
                LimpaFields();
                AtivaFields(false, false, false, false, false, false, false, false, false, false);
                tfCidadeIdCidade.setEnabled(false);
                tfCidadeIdCidade.setText(String.valueOf("            "));
                lbAviso.setText("Cancelado");
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
                tfIdPessoas.setText(String.valueOf(IdPessoas));
                tfIdPessoas.setEnabled(false);
                AtivaFields(true, true, true, true, true, true, true, true, true, false);
                tfNome.requestFocus();
                Ativabtn(false, false, true, true, false, false, false, true);
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
                        "Confirma a exclusão do registro: " + entidade.getIdPessoas() + " - " + entidade.getNome() + "?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    LimpaFields();
                    tfIdPessoas.requestFocus();
                    tfIdPessoas.setEnabled(true);
                    tfIdPessoas.setText(String.valueOf(""));
                    controle.remover(entidade);
                    lbAviso.setText("Removeu");
                    pnSul.setBackground(Color.green);
                    Ativabtn(true, false, false, false, false, false, true, false);
                } else {
                    lbAviso.setText("Cancelada a remoção");
                    pnSul.setBackground(Color.green);
                    tfIdPessoas.requestFocus();
                    tfIdPessoas.selectAll();
                    Ativabtn(false, false, false, false, true, true, true, acao);
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
                        JOptionPane.showMessageDialog(null, "Nao temos nada registrado", "OPS", JOptionPane.PLAIN_MESSAGE);
                        int erro = 3 / 0;
                    }
                    for (Pessoa linha : dados) {
                        aux = String.valueOf(linha).split(";");
                        text.append(
                                "IdPessoas: "
                                + Integer.valueOf(aux[0])
                                + "\n"
                                + "Nome: "
                                + aux[1]
                                + "\n"
                                + "Rg: "
                                + aux[2]
                                + "\n"
                                + "Cpf: "
                                + aux[3]
                                + "\n"
                                + "Telefone: "
                                + aux[4]
                                + "\n"
                                + "Email: "
                                + aux[5]
                                + "\n"
                                + "Login: "
                                + aux[6]
                                + "\n"
                                + "Senha: "
                                + aux[7]
                                + "\n"
                                + "DataNasc: "
                                + DateDataNasc.format(DateDataNasc.parse(aux[8]))
                                + "\n"
                                + "Sexo: "
                                + aux[9]
                                + "\n"
                                + "DataCadastro: "
                                + DateDataCadastro.format(DateDataCadastro.parse(aux[10]))
                                + "\n"
                                + "CidadeIdCidade: "
                                + Integer.valueOf(aux[11])
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

        tfIdPessoas.addActionListener(new ActionListener() {
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
