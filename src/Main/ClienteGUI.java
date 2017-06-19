package Cliente;

import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;
import java.awt.Image;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class ClienteGUI extends JFrame {

    Container cp;
    private JPanel pnNorte = new JPanel(new FlowLayout());
    private JPanel pnCentro = new JPanel(new GridLayout(6, 2));
    private JPanel pnSul = new JPanel(new FlowLayout());
    private JLabel lbAviso2 = new JLabel(">>> Bem vindo <<<");

    private ImageIcon iconeBuscar = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    private JButton btBuscar = new JButton(iconeBuscar);
    private ImageIcon iconeInserir = new ImageIcon(getClass().getResource("/icones/create.png"));
    private JButton btInserir = new JButton(iconeInserir);
    private ImageIcon iconeSalvar = new ImageIcon(getClass().getResource("/icones/save-as.png"));
    private JButton btSalvar = new JButton(iconeSalvar);
    private ImageIcon iconeAtualizar = new ImageIcon(getClass().getResource("/icones/update.png"));
    private JButton btAtualizar = new JButton(iconeAtualizar);
    private ImageIcon iconeRemover = new ImageIcon(getClass().getResource("/icones/delete.png"));
    private JButton btRemover = new JButton(iconeRemover);
    private ImageIcon iconeCancelar = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    private JButton btCancelar = new JButton(iconeCancelar);
    private JButton btListagem = new JButton("Listagem");
    private JLabel lbId = new JLabel(">>>Id: ");
    private JTextField tfId = new JTextField(30);

    private JLabel lbNome = new JLabel(">>>Nome: ");
    private JTextField tfNome = new JTextField(30);

    private JLabel labelFoto = new JLabel();

    private JPanel pnLeste = new JPanel();
    String origem;
    String destino = "src/fotos/";
    Image imagemAux;
    String verify = "nao pode";
    private JPanel pnDate = new JPanel(new FlowLayout());
    private JLabel lbPagamento = new JLabel(">>>Pagamento: ");
    private JTextField tfPagamento = new JTextField(30);

    private JLabel lbValortotal = new JLabel(">>>Valortotal: ");
    private JTextField tfValortotal = new JTextField(30);

    private JLabel lbStatus = new JLabel(">>>Status: ");

    private JLabel lbNascimento = new JLabel(">>>Nascimento: ");
    private JSpinner spNascimento = new JSpinner(new SpinnerDateModel());
    private final JSpinner.DateEditor spinnerEditor = new JSpinner.DateEditor(spNascimento, "dd/MM/yyyy");

    private JPanel pnStatusajuste = new JPanel();
    private ButtonGroup grupoStatus = new ButtonGroup();
    private JRadioButton rbAtivo = new JRadioButton("Ativo", true);
    private JRadioButton rbInativo = new JRadioButton("Inativo");

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private boolean action = true;
    ControleCliente controle = new ControleCliente();

    Cliente cliente = new Cliente();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    List<String> dados = new ArrayList<>();

    public void Deleta_imagem() {
        String aux = String.valueOf(cliente.getId()).trim();
        origem = "src/fotos/" + aux + ".png";
        System.out.println(origem);
        File f = new File(origem);
        if (f.exists()) {
            f.delete();
        }

    }

    public void Salva_imagem() {
        destino = destino + tfId.getText() + ".png";
        CopiaImagem.copiar(origem, destino);
        destino = "src/fotos/";
    }

    public void Abre_lbImagem(boolean acao) {
        if (acao == true) {
            labelFoto.setEnabled(true);
        }
        if (acao == false) {
            labelFoto.setEnabled(false);
        }
    }

    public void Acha_imagem() {
        String aux = String.valueOf(cliente.getId()).trim();
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

    public ClienteGUI() {
        dados = manipulaArquivo.abrirArquivo("src//Cliente.txt");
        String aux[];
        for (String linha : dados) {
            Cliente cliente = new Cliente();
            aux = linha.split(";");
            cliente.setId(Long.valueOf(aux[0]));
            cliente.setNome(aux[1]);
            cliente.setNascimento(aux[2]);
            cliente.setPagamento(Integer.valueOf(aux[3]));
            cliente.setValortotal(Double.valueOf(aux[4]));
            cliente.setStatus(aux[5]);
            controle.inserir(cliente
            );
        }
        setSize(860, 350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD Gerado");
        pnNorte.setBackground(Color.lightGray);
        pnSul.setBackground(Color.LIGHT_GRAY);

        btAtualizar.setToolTipText("Atualzar");
        btBuscar.setToolTipText("Buscar");
        btCancelar.setToolTipText("Cancelar");
        btSalvar.setToolTipText("Salvar");
        btRemover.setToolTipText("Remover");
        btInserir.setToolTipText("Inserir");
        pnNorte.add(lbId);
        pnNorte.add(tfId);
        pnNorte.add(btBuscar);
        pnNorte.add(btInserir);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);
        pnNorte.add(btRemover);
        pnNorte.add(btAtualizar);
        pnNorte.add(btListagem);
        btInserir.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btRemover.setVisible(false);
        btAtualizar.setVisible(false);
        tfNome.setEnabled(false);
        spNascimento.setEnabled(false);
        spNascimento.setEditor(spinnerEditor);
        tfPagamento.setEnabled(false);
        tfValortotal.setEnabled(false);
        pnCentro.add(lbNome);
        pnCentro.add(tfNome);
        pnDate.add(spNascimento);
        pnCentro.add(lbNascimento);
        pnCentro.add(pnDate);
        pnCentro.add(lbPagamento);
        pnCentro.add(tfPagamento);
        pnCentro.add(lbValortotal);
        pnCentro.add(tfValortotal);
        grupoStatus.add(rbAtivo);
        grupoStatus.add(rbInativo);
        grupoStatus.add(rbAtivo);

        pnStatusajuste.add(rbAtivo);
        grupoStatus.add(rbInativo);

        pnStatusajuste.add(rbInativo);
        pnCentro.add(lbStatus);

        pnCentro.add(pnStatusajuste);

        rbAtivo.setSelected(true);
        rbAtivo.setEnabled(false);
        rbInativo.setEnabled(false);
        pnLeste.setLayout(new GridLayout(1, 1));
        pnLeste.add(labelFoto);
        pnLeste.setBackground(Color.lightGray);
        pnSul.add(lbAviso2);
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);
        cp.add(pnLeste, BorderLayout.EAST);
        Nao_achou();
        btListagem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dados.clear();
                List<Cliente> listaCliente = controle.getLista();
                for (Cliente a : listaCliente) {
                    dados.add(a.toString());
                }
                ClienteGUIListagem guiListagem = new ClienteGUIListagem(dados, getBounds().x, getBounds().y);
            }
        });
        btBuscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                verify = "nao pode";
                try {
                    cliente = new Cliente();
                    long ra;
                    ra = Long.valueOf(tfId.getText());
                    if (ra <= 0) {
                        ra = Integer.valueOf("sete");
                    }
                    cliente.setId(Long.valueOf(tfId.getText()));
                    cliente = controle.buscar(cliente);
                    if (cliente != null) {
                        pnSul.setBackground(Color.green);
                        lbAviso2.setText(">>> Busca concluida!!! <<<");
                        tfNome.setText(cliente.getNome());
                        spNascimento.setValue(sdf.parse(cliente.getNascimento()));
                        tfPagamento.setText(String.valueOf((cliente.getPagamento())));
                        tfValortotal.setText(String.valueOf((cliente.getValortotal())));
                        String Status = cliente.getStatus();

                        if (Status.equals("rbAtivo")) {
                            rbAtivo.setSelected(true);
                        }
                        if (Status.equals("rbInativo")) {
                            rbInativo.setSelected(true);
                        }
                        rbAtivo.setEnabled(false);
                        rbInativo.setEnabled(false);
                        tfId.setEnabled(false);
                        tfId.requestFocus();
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);;
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(true);
                        btInserir.setVisible(false);
                        btBuscar.setVisible(true);
                        Acha_imagem();
                    } else {
                        pnSul.setBackground(Color.red);
                        lbAviso2.setText(">>> Nada foi encontrada na lista... <<<");
                        Nao_achou();
                        tfNome.setText("");
                        try {
                            spNascimento.setValue(sdf.parse(sdf.format(new Date())));
                        } catch (ParseException ex) {
                        }
                        tfPagamento.setText("");
                        tfValortotal.setText("");
                        rbAtivo.setSelected(true);
                        rbAtivo.setEnabled(false);
                        rbInativo.setEnabled(false);
                        btInserir.setVisible(true);
                        btRemover.setVisible(false);
                        btAtualizar.setVisible(false);
                        btBuscar.setVisible(false);
                        btCancelar.setVisible(true);
                        tfId.setEnabled(false);
                        tfNome.setEnabled(false);
                        spNascimento.setEnabled(false);
                        tfPagamento.setEnabled(false);
                        tfValortotal.setEnabled(false);
                    }
                } catch (Exception err) {
                    Nao_achou();
                    tfNome.setText("");
                    try {
                        spNascimento.setValue(sdf.parse(sdf.format(new Date())));
                    } catch (ParseException ex) {
                    }
                    tfPagamento.setText("");
                    tfValortotal.setText("");
                    btInserir.setVisible(false);
                    btRemover.setVisible(false);
                    btAtualizar.setVisible(false);
                    btBuscar.setVisible(true);
                    btCancelar.setVisible(true);
                    tfId.requestFocus();
                    tfId.setEnabled(true);
                    tfNome.setEnabled(false);
                    spNascimento.setEnabled(false);
                    tfPagamento.setEnabled(false);
                    tfValortotal.setEnabled(false);
                    rbAtivo.setSelected(true);
                    rbAtivo.setEnabled(false);
                    rbInativo.setEnabled(false);
                    lbAviso2.setText("Erro nos dados");
                    pnSul.setBackground(Color.red);
                }

            }
        });
        btInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnSul.setBackground(Color.yellow);
                lbAviso2.setText(">>> Inserindo... <<<");
                verify = "pode";
                action = true;
                btInserir.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btAtualizar.setVisible(false);
                btRemover.setVisible(false);
                btBuscar.setVisible(false);
                tfNome.setText("");
                try {
                    spNascimento.setValue(sdf.parse(sdf.format(new Date())));
                } catch (ParseException ex) {
                }
                tfPagamento.setText("");
                tfValortotal.setText("");
                rbAtivo.setSelected(true);
                rbAtivo.setEnabled(true);
                rbInativo.setEnabled(true);
                tfId.setEnabled(false);
                tfNome.setEnabled(true);
                tfNome.requestFocus();
                spNascimento.setEnabled(true);
                tfPagamento.setEnabled(true);
                tfValortotal.setEnabled(true);
            }
        });
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (action == true) {
                    sdf.setLenient(false);
                    try {
                        if ("".equals(tfNome.getText())) {
                            int x = Integer.valueOf("sete");
                        }
                        if ("".equals(tfPagamento.getText())) {
                            int x = Integer.valueOf("sete");
                        }
                        if ("".equals(tfValortotal.getText())) {
                            int x = Integer.valueOf("sete");
                        }
                        Cliente cliente = new Cliente();
                        cliente.setId(Long.valueOf(tfId.getText()));
                        cliente.setNome(tfNome.getText());
                        cliente.setNascimento(sdf.format(spNascimento.getValue()));
                        cliente.setPagamento(Integer.parseInt(tfPagamento.getText()));
                        cliente.setValortotal(Double.parseDouble(tfValortotal.getText()));
                        if (rbAtivo.isSelected() == true) {
                            cliente.setStatus("rbAtivo");
                        }
                        if (rbInativo.isSelected() == true) {
                            cliente.setStatus("rbInativo");
                        }
                        controle.inserir(cliente);
                        Salva_imagem();
                        verify = "nao pode";
                        Nao_achou();
                        tfNome.setText("");
                        try {
                            spNascimento.setValue(sdf.parse(sdf.format(new Date())));
                        } catch (ParseException ex) {
                        }
                        tfPagamento.setText("");
                        tfValortotal.setText("");
                        tfId.requestFocus();
                        tfId.setEnabled(true);
                        tfNome.setEnabled(false);
                        spNascimento.setEnabled(false);
                        tfPagamento.setEnabled(false);
                        rbAtivo.setSelected(true);
                        rbAtivo.setEnabled(false);
                        rbInativo.setEnabled(false);
                        btSalvar.setVisible(false);
                        btAtualizar.setVisible(false);
                        btBuscar.setVisible(true);
                        btInserir.setVisible(false);
                        btRemover.setVisible(false);
                        btCancelar.setVisible(false);
                        pnSul.setBackground(Color.green);
                        tfValortotal.setEnabled(false);
                        lbAviso2.setText(">>> Inserido com sucesso <<<");
                    } catch (Exception ex) {
                        pnSul.setBackground(Color.red);
                        lbAviso2.setText("Tenha certeza que está preenchedo todos os campos corretamente");
                        btSalvar.setVisible(true);
                        btCancelar.setVisible(true);
                    }
                } else {
                    Cliente clienteAntiga = cliente;
                    try {
                        if ("".equals(tfNome.getText())) {
                            int x = Integer.valueOf("sete");
                        }
                        if ("".equals(tfPagamento.getText())) {
                            int x = Integer.valueOf("sete");
                        }
                        if ("".equals(tfValortotal.getText())) {
                            int x = Integer.valueOf("sete");
                        }
                        Cliente clientenova = new Cliente();
                        clientenova.setId(Long.valueOf(tfId.getText()));
                        clientenova.setNome(tfNome.getText());
                        clientenova.setNascimento(sdf.format(spNascimento.getValue()));
                        clientenova.setPagamento(Integer.parseInt(tfPagamento.getText()));
                        clientenova.setValortotal(Double.parseDouble(tfValortotal.getText()));
                        if (rbAtivo.isSelected() == true) {
                            clientenova.setStatus("rbAtivo");
                        }
                        if (rbInativo.isSelected() == true) {
                            clientenova.setStatus("rbInativo");
                        }
                        controle.alterar(clienteAntiga, clientenova);
                        Salva_imagem();
                        verify = "nao pode";
                        Nao_achou();
                        tfNome.setText("");
                        try {
                            spNascimento.setValue(sdf.parse(sdf.format(new Date())));
                        } catch (ParseException ex) {
                        }
                        tfPagamento.setText("");
                        tfValortotal.setText("");
                        tfId.requestFocus();
                        tfId.setEnabled(true);
                        tfNome.setEnabled(false);
                        spNascimento.setEnabled(false);
                        tfPagamento.setEnabled(false);
                        tfValortotal.setEnabled(false);
                        rbAtivo.setSelected(true);
                        rbAtivo.setEnabled(false);
                        rbInativo.setEnabled(false);
                        btSalvar.setVisible(false);
                        btAtualizar.setVisible(false);
                        btBuscar.setVisible(true);
                        btInserir.setVisible(false);
                        btRemover.setVisible(false);
                        btCancelar.setVisible(false);
                        pnSul.setBackground(Color.green);
                        lbAviso2.setText(">>> Atualização feita com sucesso <<<");
                    } catch (Exception ex) {
                        pnSul.setBackground(Color.red);
                        lbAviso2.setText("Tenha certeza que está preenchedo todos os campos corretamente");
                        btSalvar.setVisible(true);
                        btCancelar.setVisible(true);

                    }
                }
            }
        ;
        });
btCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                verify = "nao pode";
                btSalvar.setVisible(false);
                btInserir.setVisible(false);
                btAtualizar.setVisible(false);
                btRemover.setVisible(false);
                btBuscar.setVisible(true);
                tfId.requestFocus();
                tfId.setEnabled(true);
                tfNome.setEnabled(false);
                spNascimento.setEnabled(false);
                tfPagamento.setEnabled(false);
                tfValortotal.setEnabled(false);
                tfId.setText("");
                tfNome.setText("");
                try {
                    spNascimento.setValue(sdf.parse(sdf.format(new Date())));
                } catch (ParseException ex) {
                }
                tfPagamento.setText("");
                tfValortotal.setText("");
                rbAtivo.setSelected(true);
                rbAtivo.setEnabled(false);
                rbInativo.setEnabled(false);
                Nao_achou();
                lbAviso2.setText(">>> Operação cancelada com sucesso <<<");
                pnSul.setBackground(Color.green);
            }
        });
        btRemover.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                verify = "nao pode";
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão deste registro ?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    Deleta_imagem();
                    controle.remover(cliente);
                    pnSul.setBackground(Color.green);
                    lbAviso2.setText("Removeu");
                    tfId.setText("");
                    tfNome.setText("");
                    try {
                        spNascimento.setValue(sdf.parse(sdf.format(new Date())));
                    } catch (ParseException ex) {
                    }
                    tfPagamento.setText("");
                    tfValortotal.setText("");
                    tfId.requestFocus();
                    rbAtivo.setSelected(true);
                    rbAtivo.setEnabled(false);
                    rbInativo.setEnabled(false);
                    Nao_achou();
                    btAtualizar.setVisible(false);
                    btBuscar.setVisible(true);
                    btInserir.setVisible(false);
                    btRemover.setVisible(false);
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(true);
                } else {
                    pnSul.setBackground(Color.green);
                    lbAviso2.setText("Cancelada a remoção");
                    tfId.setEnabled(false);
                    tfId.requestFocus();
                    btAtualizar.setVisible(false);
                    btBuscar.setVisible(true);
                    btInserir.setVisible(false);
                    btRemover.setVisible(true);
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(true);
                }
            }

        });
        btAtualizar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                action = false;

                verify = "pode";
                tfNome.requestFocus();
                tfNome.setEnabled(true);
                spNascimento.setEnabled(true);
                tfPagamento.setEnabled(true);
                tfValortotal.setEnabled(true);
                rbAtivo.setEnabled(true);
                rbInativo.setEnabled(true);
                tfId.setEnabled(false);
                btAtualizar.setVisible(false);
                btBuscar.setVisible(false);
                btInserir.setVisible(false);
                btRemover.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                pnSul.setBackground(Color.yellow);
                lbAviso2.setText(">>> Atualizando... <<<");
            }
        });
        tfPagamento.addActionListener(new ActionListener() { ///Adicionar depois

            @Override
            public void actionPerformed(ActionEvent e) {
                ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
                List<String> listaAuxiliar = manipulaArquivo.abrirArquivo("src//Pagamento.txt");

                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5, getBounds().y).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split(";");
                        tfPagamento.setText(aux[0]);

                    } else {
                        tfPagamento.requestFocus();
                        tfPagamento.selectAll();
                    }
                }
            }
        });
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
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dados.clear();
                List<Cliente> listaCliente = controle.getLista();
                for (Cliente a : listaCliente) {
                    dados.add(a.toString());
                }
                manipulaArquivo.salvarArquivo("src//Cliente.txt", dados);
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
