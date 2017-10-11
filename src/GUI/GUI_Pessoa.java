package GUI;

import DAOs.DAOCidade;
import java.awt.BorderLayout;
import DAOs.DAOPessoa;
import Entidades.Cidade;
import Entidades.Pessoa;
import Ferramentas.CopiaImagem;
import Ferramentas.JanelaPesquisarCidade;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.text.MaskFormatter;

public class GUI_Pessoa extends JDialog {

    private Container cp;
    private JPanel pnNorte = new JPanel(new FlowLayout());
    private JPanel pnCentro = new JPanel(new GridLayout(0, 2));
    private JPanel pnSul = new JPanel(new FlowLayout());
    private JPanel pnOeste = new JPanel(new GridLayout(0, 1));
    private JPanel pnCidade = new JPanel();
    private JPanel pnData = new JPanel();
    private JPanel pnData2 = new JPanel();

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
    //Verifica Cidades
    List<String> listaUM = daoCidade.listInOrderNomeStrings("id");

    //Funçoes da Imagem
    JLabel labelFoto = new JLabel();

    String origem;
    String destino = "src/fotos/";
    Image imagemAux;
    String verify = "nao pode";

    public void Deleta_imagem() {
        String aux = String.valueOf(entidade.getIdPessoas()).trim();
        origem = "src/fotos/" + aux + ".png";
        System.out.println(origem);
        File f = new File(origem);
        if (f.exists()) {
            f.delete();
        }

    }

    public void Salva_imagem() {
        destino = destino + tfIdPessoas.getText() + ".png";
        CopiaImagem.copiar(origem, destino);
        destino = "src/fotos/";
    }

    public void Acha_imagem() {
        String aux = String.valueOf(entidade.getIdPessoas()).trim();
        origem = "/fotos/" + aux + ".png";
        ImageIcon icone = new ImageIcon(getClass().getResource(origem));
        imagemAux = icone.getImage();
        icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
        labelFoto.setIcon(icone);

    }

    public void Nao_achou() {
        origem = "/fotos/0.png";
        ImageIcon icone = new ImageIcon(getClass().getResource(origem));
        imagemAux = icone.getImage();
        icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
        labelFoto.setIcon(icone);
    }

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
        tfEmail.setText("");
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
        if (!tfEmail.getText().equals("")) {
            if (!tfEmail.getText().contains("@")) {
                int x = Integer.valueOf("Bug");
            }
            if (tfEmail.getText().trim().equals("")) {
                int x = Integer.valueOf("Bug");
            }
        }
        if (tfSenha.getText().trim().equals("")) {
            int x = Integer.valueOf("Bug");
        }
    }

    public GUI_Pessoa() throws ParseException {

        setSize(1200, 500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de Pessoa");

        pnNorte.setBackground(Color.lightGray);
        pnCentro.setBackground(Color.lightGray);
        pnSul.setBackground(Color.lightGray);
        pnOeste.setBackground(Color.lightGray);
        pnData.setBackground(Color.lightGray);
        pnData2.setBackground(Color.lightGray);
        pnSexo.setBackground(Color.lightGray);
        pnCidade.setBackground(Color.lightGray);

        dialog.add(scroll);
        text.setEditable(false);
        dialog.setSize(500, 200);

        if (listaUM.size() == 0) {
            JOptionPane.showMessageDialog(cp, "Não é possível cadastrar alimentos sem \ncadastrar primeiro as Cidades \n"
                    + "Escolha -> menu -> Cadastros -> Cidades");
            return;

        }
        
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
        pnData.add(tfDataNascimento);
        pnCentro.add(pnData);

        bgSexo.add(rbMasculino);
        pnSexo.add(rbMasculino);

        bgSexo.add(rbFeminino);
        pnSexo.add(rbFeminino);

        pnCentro.add(lbSexo);
        pnCentro.add(pnSexo);

        SpDataCadastro.setEditor(spinnerEditorCad);
        pnCentro.add(lbDataCadastro);
        pnData2.add(SpDataCadastro);
        pnCentro.add(pnData2);

        pnCidade.add(tfCidadeIdCidade);
        pnCidade.add(btJnPesquisa);
        pnCentro.add(lbCidadeIdCidade);
        pnCentro.add(pnCidade);

        pnOeste.add(labelFoto);
        Nao_achou();

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);
        cp.add(pnOeste, BorderLayout.EAST);

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
                        tfCidadeIdCidade.setText(aux[0] + " - " + aux[1] + " - " + aux[2]);
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
                    verify = "nao pode";
                    entidade = new Pessoa();
                    IdPessoas = Integer.valueOf(tfIdPessoas.getText());
                    entidade.setIdPessoas(IdPessoas);
                    if (IdPessoas <= 0) {
                        int error = 3 / 0;
                    }
                    entidade = controle.obter(entidade.getIdPessoas());
                    if (entidade != null) {
                        Ativabtn(true, false, false, false, true, true, true, false);
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
                        try {
                            Acha_imagem();
                        } catch (Exception err) {
                            Nao_achou();
                        }
                        lbAviso.setText("Achou na lista");
                        pnSul.setBackground(Color.green);
                    } else {
                        lbAviso.setText("Não achou na lista");
                        pnSul.setBackground(Color.red);
                        Nao_achou();
                        Ativabtn(true, true, false, false, false, false, true, false);
                        AtivaFields(false, false, false, false, false, false, false, false, false, false);
                        LimpaFields();
                    }
                } catch (Exception err) {
                    LimpaFields();
                    lbAviso.setText("Erro nos dados");
                    pnSul.setBackground(Color.red);
                    Nao_achou();
                }

            }
        }
        );

        btInserir.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                verify = "pode";
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

                        String[] Cidade = tfCidadeIdCidade.getText().split(" - ");
                        String cidade = Cidade[0].trim();
                        cidadeSave = daoCidade.obter(Integer.valueOf(cidade));

                        entidade.setCidadeIdCidade(cidadeSave);
                        verifica_campos();
                        controle.inserir(entidade);
                        Ativabtn(true, false, false, false, true, true, true, false);
                        AtivaFields(false, false, false, false, false, false, false, false, false, false);
                        tfIdPessoas.setEnabled(true);
                        tfIdPessoas.requestFocus();
                        Salva_imagem();
                        verify = "nao pode";
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
                        entidadeModificada.setDataNasc(DateDataCadastro.parse(tfDataNascimento.getText()));
                        String rbSexo = " ";
                        System.out.println("Chegou");
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
                        Salva_imagem();
                        verify = "nao pode";
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
                Nao_achou();
                verify = "nao pode";
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
                verify = "pode";
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
                    verify = "nao pode";
                    Nao_achou();
                } else {
                    verify = "nao pode";
                    lbAviso.setText("Cancelada a remoção");
                    pnSul.setBackground(Color.green);
                    tfIdPessoas.requestFocus();
                    tfIdPessoas.selectAll();
                    Ativabtn(false, false, false, false, true, true, true, acao);
                }

            }
        }
        );

        labelFoto.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (verify.equals("pode")) {
                    JFileChooser fc = new JFileChooser();
                    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    if (fc.showOpenDialog(cp) == JFileChooser.APPROVE_OPTION) {
                        File img = fc.getSelectedFile();
                        origem = fc.getSelectedFile().getAbsolutePath();
                        try {
                            ImageIcon icone = new javax.swing.ImageIcon(img.getAbsolutePath());
                            Image imagemAux;
                            imagemAux = icone.getImage();
                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                            labelFoto.setIcon(icone);

                        } catch (Exception ex) {
                            System.out.println("Erro: ");
                        }
                    }

                }

            }
        });

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
                        entidade = controle.obter(Integer.valueOf(aux[0]));
//                        text.append(
//                                "IdPessoas: "
//                                + Integer.valueOf(aux[0])
//                                + "\n"
//                                + "Nome: "
//                                + aux[1]
//                                + "\n"
//                                + "Rg: "
//                                + aux[2]
//                                + "\n"
//                                + "Cpf: "
//                                + aux[3]
//                                + "\n"
//                                + "Telefone: "
//                                + aux[4]
//                                + "\n"
//                                + "Email: "
//                                + aux[5]
//                                + "\n"
//                                + "Login: "
//                                + aux[6]
//                                + "\n"
//                                + "Senha: "
//                                + aux[7]
//                                + "\n"
//                                + "DataNasc: "
//                                + DateDataNasc.format(entidade.getDataNasc())
//                                + "\n"
//                                + "Sexo: "
//                                + aux[9]
//                                + "\n"
//                                + "DataCadastro: "
//                                + DateDataCadastro.format(entidade.getDataCadastro())
//                                + "\n"
//                                + "Cidade: "
//                                + (Integer.valueOf(aux[11]) + " - " + aux[12] + " - " + aux[13])
//                                + "\n-------------------------------------------------------------------------------------------\n"
//                        );

                    }
                    dialog.setLocationRelativeTo(cp);
                    dialog.setModal(true);
                    dialog.setVisible(true);
                    System.out.println("foi");
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
