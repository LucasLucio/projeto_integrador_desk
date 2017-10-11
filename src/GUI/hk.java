//package GUI;
//
//import java.awt.BorderLayout;
//import DAOs.DAOContrato;
//import Entidades.Contrato;
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.FlowLayout;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.util.ArrayList;
//import java.util.List;
//import javax.swing.JDialog;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//
//import java.text.SimpleDateFormat;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
//import java.text.ParseException;
//
//
//public class hk extends JDialog {
//
//    private Container cp;
//    private JPanel pnNorte = new JPanel(new FlowLayout());
//    private JPanel pnCentro = new JPanel(new GridLayout(0, 2));
//    private JPanel pnSul = new JPanel(new FlowLayout());
//
//    private JLabel lbIdContrato = new JLabel("IdContrato:");
//    private JTextField tfIdContrato = new JTextField(10);
//
//    private JButton btBuscar = new JButton("Buscar");
//    private JButton btInserir = new JButton("Criar");
//    private JButton btSalvar = new JButton("Salvar");
//    private JButton btCancelar = new JButton("Cancelar");
//    private JButton btRemover = new JButton("Remover");
//    private JButton btAtualizar = new JButton("Atualizar");
//    private JButton btListar = new JButton("Listar");
//    
//    private JLabel lbAviso = new JLabel("Aviso");
//    DAOContrato controle = new DAOContrato();
//    List<Contrato> dados = new ArrayList<>();
//    
//    private boolean acao = true;
//    Contrato entidade = new Contrato();
//
//    private JLabel lbContratoDescricao = new JLabel("ContratoDescricao");
//    private JTextField tfContratoDescricao = new JTextField();
//
//    private SimpleDateFormat DateDataContrato = new SimpleDateFormat("");
//    private JLabel lbDataContrato = new JLabel("DataContrato");
//    private JTextField tfDataContrato = new JTextField(20);
//
//    private JLabel lbValorContrato = new JLabel("ValorContrato");
//    private JTextField tfValorContrato = new JTextField();
//
//    private JLabel lbPessoaIdPessoas = new JLabel("PessoaIdPessoas");
//    private JTextField tfPessoaIdPessoas = new JTextField();
//
//    private JLabel lbPagamentoList = new JLabel("PagamentoList");
//    private JTextField tfPagamentoList = new JTextField();
//
//    private JLabel lbServicosDescricaoList = new JLabel("ServicosDescricaoList");
//    private JTextField tfServicosDescricaoList = new JTextField();
//
//    private JDialog dialog = new JDialog();
//    private JTextArea text = new JTextArea();
//    private JScrollPane scroll = new JScrollPane(text);
//    private Integer IdContrato;
//    public hk() throws ParseException{
//
//        setSize(800, 700);
//        setResizable(false);
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        cp = getContentPane();
//        cp.setLayout(new BorderLayout());
//        setTitle("");
//
//        pnNorte.setBackground(Color.yellow);
//        pnCentro.setBackground(Color.cyan);
//        pnSul.setBackground(Color.green);
//
//        dialog.add(scroll);
//        text.setEditable(false);
//        dialog.setSize(500,200);
//
//        pnNorte.add(lbIdContrato);
//        pnNorte.add(tfIdContrato);
//        pnNorte.add(btBuscar);
//        pnNorte.add(btInserir);
//        pnNorte.add(btSalvar);
//        pnNorte.add(btCancelar);
//        pnNorte.add(btRemover);
//        pnNorte.add(btAtualizar);
//        pnNorte.add(btListar);
//        btInserir.setVisible(false);
//        btSalvar.setVisible(false);
//        btCancelar.setVisible(false);
//        btRemover.setVisible(false);
//        btAtualizar.setVisible(false);
//
//        pnSul.add(lbAviso);
//        lbAviso.setOpaque(true);
//
//        cp.add(pnNorte, BorderLayout.NORTH);
//        cp.add(pnCentro, BorderLayout.CENTER);
//        cp.add(pnSul, BorderLayout.SOUTH);
//
//
//        tfContratoDescricao.setEnabled(false);
//        pnCentro.add(lbContratoDescricao);
//        pnCentro.add(tfContratoDescricao);
//
//        tfDataContrato.setEnabled(false);
//        pnCentro.add(lbDataContrato);
//        pnCentro.add(tfDataContrato);
//
//        tfValorContrato.setEnabled(false);
//        pnCentro.add(lbValorContrato);
//        pnCentro.add(tfValorContrato);
//
//        tfPessoaIdPessoas.setEnabled(false);
//        pnCentro.add(lbPessoaIdPessoas);
//        pnCentro.add(tfPessoaIdPessoas);
//
//        tfPagamentoList.setEnabled(false);
//        pnCentro.add(lbPagamentoList);
//        pnCentro.add(tfPagamentoList);
//
//        tfServicosDescricaoList.setEnabled(false);
//        pnCentro.add(lbServicosDescricaoList);
//        pnCentro.add(tfServicosDescricaoList);
//
//
//        btBuscar.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    entidade = new Contrato();
//
//                    IdContrato = Integer.valueOf(tfIdContrato.getText());
//
//                    entidade.setIdContrato(IdContrato);
//
//                    if (IdContrato<= 0) {
//                        int error = 3/0;
//                    }
//                    entidade = controle.obter(entidade.getIdContrato());
//                    lbAviso.setBackground(Color.green);
//                    if (entidade != null) {
//
//                        btInserir.setVisible(false);
//                        btAtualizar.setVisible(true);
//                        btRemover.setVisible(true);
//                        lbAviso.setText("Achou na lista");
//
//                        tfContratoDescricao.setText(entidade.getContratoDescricao());
//
//                        tfDataContrato.setText(DateDataContrato.format(entidade.getDataContrato()));
//
//                        tfValorContrato.setText(String.valueOf(entidade.getValorContrato()));
//
//                        tfPessoaIdPessoas.setText(String.valueOf(entidade.getPessoaIdPessoas()));
//
//                        tfPagamentoList.setText(entidade.getPagamentoList());
//
//                        tfServicosDescricaoList.setText(entidade.getServicosDescricaoList());
//
//                    } else {
//                        lbAviso.setText("Não achou na lista");
//                        btInserir.setVisible(true);
//
//                        lbAviso.setBackground(Color.red);
//                        btAtualizar.setVisible(false);
//                        btRemover.setVisible(false);
//
//
//                        tfContratoDescricao.setEnabled(false);
//
//                        tfDataContrato.setEnabled(false);
//
//                        tfValorContrato.setEnabled(false);
//
//                        tfPessoaIdPessoas.setEnabled(false);
//
//                        tfPagamentoList.setEnabled(false);
//
//                        tfServicosDescricaoList.setEnabled(false);
//
//                    }
//
//                } catch (Exception err) {
//                    lbAviso.setText("Erro nos dados");
//                    lbAviso.setBackground(Color.red);
//                }
//
//            }
//        }
//        );
//
//        btInserir.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e
//                    ) {
//                        acao = true;
//                        btBuscar.setVisible(false);
//                        btListar.setVisible(false);
//
//                        tfIdContrato.setText(String.valueOf(IdContrato));
//                        tfIdContrato.setEnabled(false);
//
//                        tfContratoDescricao.setEnabled(true);
//                        tfContratoDescricao.setText(String.valueOf(""));
//
//                        tfDataContrato.setEnabled(true);
//                        tfDataContrato.setText(String.valueOf(""));
//
//                        tfDataContrato.addFocusListener(new FocusListener() {
//                        public void focusGained(FocusEvent e) {
//                            if (tfDataContrato.getText().equals("")){
//                                tfDataContrato.setText("");}}
//                        public void focusLost(FocusEvent e) {
//                            if (tfDataContrato.getText().equals("")){
//                                tfDataContrato.setText("");}}});
//                        tfDataContrato.requestFocus();
//
//
//                        tfValorContrato.setEnabled(true);
//                        tfValorContrato.setText(String.valueOf(""));
//
//                        tfPessoaIdPessoas.setEnabled(true);
//                        tfPessoaIdPessoas.setText(String.valueOf(""));
//
//                        tfPagamentoList.setEnabled(true);
//                        tfPagamentoList.setText(String.valueOf(""));
//
//                        tfServicosDescricaoList.setEnabled(true);
//                        tfServicosDescricaoList.setText(String.valueOf(""));
//
//                        tfContratoDescricao.requestFocus();
//
//
//                        btInserir.setVisible(false);
//                        btSalvar.setVisible(true);
//                        btCancelar.setVisible(true);
//                        btRemover.setVisible(false);
//                        btAtualizar.setVisible(false);
//                    }
//                }
//        );
//
//        btSalvar.addActionListener(
//                new ActionListener() {
//
//                    @Override
//                    public void actionPerformed(ActionEvent e
//                    ) {
//                        if (acao) {
//                            try{
//
//                            Contrato entidade = new Contrato();
//
//                            entidade.setIdContrato(IdContrato);
//
//                            entidade.setContratoDescricao(tfContratoDescricao.getText());
//
//                            DateDataContrato.setLenient(false);
//                            entidade.setDataContrato(DateDataContrato.parse(tfDataContrato.getText()));
//
//                            entidade.setValorContrato(Double.valueOf(tfValorContrato.getText()));
//
//                            entidade.setPessoaIdPessoas(Integer.valueOf(tfPessoaIdPessoas.getText()));
//
//                            entidade.setPagamentoList(tfPagamentoList.getText());
//
//                            entidade.setServicosDescricaoList(tfServicosDescricaoList.getText());
//
//                            controle.inserir(entidade);
//
//                            lbAviso.setText("Registro inserido");
//                            btSalvar.setVisible(false);
//                            btCancelar.setVisible(false);
//                            btBuscar.setVisible(true);
//                            btListar.setVisible(true);
//                            tfIdContrato.setEnabled(true);
//                            tfIdContrato.requestFocus();
//                            tfIdContrato.selectAll();
//
//                            tfContratoDescricao.setEnabled(false);
//
//                            tfDataContrato.setEnabled(false);
//
//                            tfValorContrato.setEnabled(false);
//
//                            tfPessoaIdPessoas.setEnabled(false);
//
//                            tfPagamentoList.setEnabled(false);
//
//                            tfServicosDescricaoList.setEnabled(false);
//
//                            lbAviso.setBackground(Color.green);
//                }catch (Exception btSalvar){
//                    JOptionPane.showMessageDialog(null, "ERRO NOS DADOS", "ERROR", JOptionPane.ERROR_MESSAGE);}
//
//                        } else {
//                            try{
//                            Contrato entidadeOriginal = entidade;
//                            Contrato entidadeModificada = new Contrato();
//
//                            entidadeModificada.setIdContrato(IdContrato);
//
//                            entidadeModificada.setContratoDescricao(tfContratoDescricao.getText());
//
//                            DateDataContrato.setLenient(false);
//                            entidadeModificada.setDataContrato(DateDataContrato.parse(tfDataContrato.getText()));
//
//                            entidadeModificada.setValorContrato(Double.valueOf(tfValorContrato.getText()));
//
//                            entidadeModificada.setPessoaIdPessoas(Integer.valueOf(tfPessoaIdPessoas.getText()));
//
//                            entidadeModificada.setPagamentoList(tfPagamentoList.getText());
//
//                            entidadeModificada.setServicosDescricaoList(tfServicosDescricaoList.getText());
//
//
//                            controle.atualizar(entidadeModificada);
//
//                            lbAviso.setText("Registro alterado");
//                            tfIdContrato.setEnabled(true);
//                            tfIdContrato.requestFocus();
//                            tfIdContrato.selectAll();
//                            btSalvar.setVisible(false);
//                            btCancelar.setVisible(false);
//                            btBuscar.setVisible(true);
//                            btListar.setVisible(true);
//
//                            tfContratoDescricao.setEnabled(false);
//
//                            tfDataContrato.setEnabled(false);
//
//                            tfValorContrato.setEnabled(false);
//
//                            tfPessoaIdPessoas.setEnabled(false);
//
//                            tfPagamentoList.setEnabled(false);
//
//                            tfServicosDescricaoList.setEnabled(false);
//
//                            lbAviso.setBackground(Color.green);
//                            }catch (Exception btSalvarAtt){
//                                JOptionPane.showMessageDialog(null, "ERRO NOS DADOS", "ERROR", JOptionPane.ERROR_MESSAGE);}
//
//
//                        }
//                    }
//                }
//        );
//
//        btCancelar.addActionListener(
//                new ActionListener() {
//
//                    @Override
//                    public void actionPerformed(ActionEvent e
//                    ) {
//                        lbAviso.setText("Cancelado");
//                        tfIdContrato.setEnabled(true);
//
//                        tfIdContrato.requestFocus();
//                        tfIdContrato.selectAll();
//
//                        btSalvar.setVisible(false);
//                        btCancelar.setVisible(false);
//                        btBuscar.setVisible(true);
//                        btListar.setVisible(true);
//                        lbAviso.setBackground(Color.green);
//
//                        tfContratoDescricao.setEnabled(false);
//                        tfContratoDescricao.setText(String.valueOf(""));
//
//                        tfDataContrato.setEnabled(false);
//                        tfDataContrato.setText(String.valueOf(""));
//
//                        tfValorContrato.setEnabled(false);
//                        tfValorContrato.setText(String.valueOf(""));
//
//                        tfPessoaIdPessoas.setEnabled(false);
//                        tfPessoaIdPessoas.setText(String.valueOf(""));
//
//                        tfPagamentoList.setEnabled(false);
//                        tfPagamentoList.setText(String.valueOf(""));
//
//                        tfServicosDescricaoList.setEnabled(false);
//                        tfServicosDescricaoList.setText(String.valueOf(""));
//
//                    }
//                }
//        );
//
//        btAtualizar.addActionListener(
//                new ActionListener() {
//
//                    @Override
//                    public void actionPerformed(ActionEvent e
//                    ) {
//                        acao = false;
//
//                        tfIdContrato.setText(String.valueOf(IdContrato));
//                        tfIdContrato.setEnabled(false);
//
//                        tfContratoDescricao.setEnabled(true);
//
//                        tfDataContrato.setEnabled(true);
//
//                        tfValorContrato.setEnabled(true);
//
//                        tfPessoaIdPessoas.setEnabled(true);
//
//                        tfPagamentoList.setEnabled(true);
//
//                        tfServicosDescricaoList.setEnabled(true);
//
//                        tfContratoDescricao.requestFocus();
//
//                        btSalvar.setVisible(true);
//                        btCancelar.setVisible(true);
//                        btBuscar.setVisible(false);
//                        btListar.setVisible(false);
//                        btRemover.setVisible(false);
//                        btAtualizar.setVisible(false);
//
//                    }
//                }
//        );
//
//        btRemover.addActionListener(
//                new ActionListener() {
//
//                    @Override
//                    public void actionPerformed(ActionEvent e
//                    ) {
//                        btRemover.setVisible(false);
//                        btAtualizar.setVisible(false);
//                        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
//                                "Confirma a exclusão do registro <ID = " + entidade.getIdContrato() + ">?", "Confirm",
//                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
//
//
//                        tfContratoDescricao.setText(String.valueOf(""));
//
//                        tfDataContrato.setText(String.valueOf(""));
//
//                        tfValorContrato.setText(String.valueOf(""));
//
//                        tfPessoaIdPessoas.setText(String.valueOf(""));
//
//                        tfPagamentoList.setText(String.valueOf(""));
//
//                        tfServicosDescricaoList.setText(String.valueOf(""));
//
//                        tfIdContrato.requestFocus();
//                        tfIdContrato.setEnabled(true);
//                        tfIdContrato.setText(String.valueOf(""));
//                        controle.remover(entidade);
//                        lbAviso.setText("Removeu");
//
//
//                        } else {
//                            lbAviso.setText("Cancelada a remoção");
//                            btRemover.setVisible(true);
//                            btAtualizar.setVisible(true);
//                        }
//
//                    }
//                }
//        );
//
//       btListar.addActionListener( new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent e
//                    ) {
//                try{
//                String aux[];
//                text.setText("");
//                dados = controle.list();
//                try{
//                if (dados.get(0) == null){}
//                }catch(Exception lista){
//                    JOptionPane.showMessageDialog(null, "NOTHING TO SEE HERE! 8P", "OPS", JOptionPane.PLAIN_MESSAGE);
//                    int erro = 3/0;                }
//                for (Contrato linha : dados) {
//                    aux = String.valueOf(linha).split(";");
//                    text.append(
//"IdContrato: " + 
//            Integer.valueOf(aux[0])
// + "\n" +
//"ContratoDescricao: " + 
//            aux[1]
// + "\n" +
//"DataContrato: " + 
//            DateDataContrato.format(DateDataContrato.parse(aux[2]))
// + "\n" +
//"ValorContrato: " + 
//            Double.valueOf(aux[3])
// + "\n" +
//"PessoaIdPessoas: " + 
//            Integer.valueOf(aux[4])
// + "\n" +
//"PagamentoList: " + 
//            aux[5]
// + "\n" +
//"ServicosDescricaoList: " + 
//            aux[6]
//+ "\n-------------------------------------------------------------------------------------------\n"
//);}
//                dialog.setLocationRelativeTo(cp);
//                dialog.setModal(true);
//                dialog.setVisible(true);
//                }catch(Exception Lista){                    }
//        }});
//
//       addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//            System.exit(0);            }
//        });
//        
//        tfIdContrato.addActionListener(new ActionListener() {
//        @Override
//            public void actionPerformed(ActionEvent e) {
//            btBuscar.doClick();}});
//
//
//        setLocationRelativeTo(null);
//        setModal(true);
//        setVisible(true);
//    }
//}
//
