package GUI;

import java.awt.BorderLayout;
import DAOs.DAOServicos;
import Entidades.Servicos;
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


public class GUI_Servicos extends JDialog {

    private Container cp;
    private JPanel pnNorte = new JPanel(new FlowLayout());
    private JPanel pnCentro = new JPanel(new GridLayout(0, 2));
    private JPanel pnSul = new JPanel(new FlowLayout());

    private JLabel lbIdServicos = new JLabel("IdServicos:");
    private JTextField tfIdServicos = new JTextField(10);

    private JButton btBuscar = new JButton("Buscar");
    private JButton btInserir = new JButton("Criar");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JButton btRemover = new JButton("Remover");
    private JButton btAtualizar = new JButton("Atualizar");
    private JButton btListar = new JButton("Listar");
    
    private JLabel lbAviso = new JLabel("Aviso");
    DAOServicos controle = new DAOServicos();
    List<Servicos> dados = new ArrayList<>();
    
    private boolean acao = true;
    Servicos entidade = new Servicos();

    private JLabel lbNome = new JLabel("Nome");
    private JTextField tfNome = new JTextField();

    private JLabel lbDescricao = new JLabel("Descricao");
    private JTextField tfDescricao = new JTextField();

    private JLabel lbValor = new JLabel("Valor");
    private JTextField tfValor = new JTextField();

    private JDialog dialog = new JDialog();
    private JTextArea text = new JTextArea();
    private JScrollPane scroll = new JScrollPane(text);
    private Integer IdServicos;
    public GUI_Servicos() {

        setSize(800, 400);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("");

        pnNorte.setBackground(Color.lightGray);
        pnCentro.setBackground(Color.lightGray);
        pnSul.setBackground(Color.lightGray);

        dialog.add(scroll);
        text.setEditable(false);
        dialog.setSize(500,200);

        pnNorte.add(lbIdServicos);
        pnNorte.add(tfIdServicos);
        pnNorte.add(btBuscar);
        pnNorte.add(btInserir);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);
        pnNorte.add(btRemover);
        pnNorte.add(btAtualizar);
        pnNorte.add(btListar);
        btInserir.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btRemover.setVisible(false);
        btAtualizar.setVisible(false);

        pnSul.add(lbAviso);
        lbAviso.setOpaque(true);

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);


        tfNome.setEnabled(false);
        pnCentro.add(lbNome);
        pnCentro.add(tfNome);

        tfDescricao.setEnabled(false);
        pnCentro.add(lbDescricao);
        pnCentro.add(tfDescricao);

        tfValor.setEnabled(false);
        pnCentro.add(lbValor);
        pnCentro.add(tfValor);


        btBuscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    entidade = new Servicos();

                    IdServicos = Integer.valueOf(tfIdServicos.getText());

                    entidade.setIdServicos(IdServicos);

                    if (IdServicos<= 0) {
                        int error = 3/0;
                    }
                    entidade = controle.obter(entidade.getIdServicos());
                    pnSul.setBackground(Color.green);
                    if (entidade != null) {

                        btInserir.setVisible(false);
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        lbAviso.setText("Achou na lista");

                        tfNome.setText(entidade.getNome());

                        tfDescricao.setText(entidade.getDescricao());

                        tfValor.setText(String.valueOf(entidade.getValor()));

                    } else {
                        lbAviso.setText("Não achou na lista");
                        btInserir.setVisible(true);

                        pnSul.setBackground(Color.red);
                        btAtualizar.setVisible(false);
                        btRemover.setVisible(false);


                        tfNome.setEnabled(false);

                        tfDescricao.setEnabled(false);

                        tfValor.setEnabled(false);

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
                        btBuscar.setVisible(false);
                        btListar.setVisible(false);

                        tfIdServicos.setText(String.valueOf(IdServicos));
                        tfIdServicos.setEnabled(false);

                        tfNome.setEnabled(true);
                        tfNome.setText(String.valueOf(""));

                        tfDescricao.setEnabled(true);
                        tfDescricao.setText(String.valueOf(""));

                        tfValor.setEnabled(true);
                        tfValor.setText(String.valueOf(""));

                        tfNome.requestFocus();


                        btInserir.setVisible(false);
                        btSalvar.setVisible(true);
                        btCancelar.setVisible(true);
                        btRemover.setVisible(false);
                        btAtualizar.setVisible(false);
                    }
                }
        );

        btSalvar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        if (acao) {
                            try{

                            Servicos entidade = new Servicos();

                            entidade.setIdServicos(IdServicos);

                            entidade.setNome(tfNome.getText());

                            entidade.setDescricao(tfDescricao.getText());

                            entidade.setValor(Double.valueOf(tfValor.getText()));

                            controle.inserir(entidade);

                            lbAviso.setText("Registro inserido");
                            btSalvar.setVisible(false);
                            btCancelar.setVisible(false);
                            btBuscar.setVisible(true);
                            btListar.setVisible(true);
                            tfIdServicos.setEnabled(true);
                            tfIdServicos.requestFocus();
                            tfIdServicos.selectAll();

                            tfNome.setEnabled(false);

                            tfDescricao.setEnabled(false);

                            tfValor.setEnabled(false);

                            pnSul.setBackground(Color.green);
                }catch (Exception btSalvar){
                    JOptionPane.showMessageDialog(null, "ERRO NOS DADOS", "ERROR", JOptionPane.ERROR_MESSAGE);}

                        } else {
                            try{
                            Servicos entidadeOriginal = entidade;
                            Servicos entidadeModificada = new Servicos();

                            entidadeModificada.setIdServicos(IdServicos);

                            entidadeModificada.setNome(tfNome.getText());

                            entidadeModificada.setDescricao(tfDescricao.getText());

                            entidadeModificada.setValor(Double.valueOf(tfValor.getText()));


                            controle.atualizar(entidadeModificada);

                            lbAviso.setText("Registro alterado");
                            tfIdServicos.setEnabled(true);
                            tfIdServicos.requestFocus();
                            tfIdServicos.selectAll();
                            btSalvar.setVisible(false);
                            btCancelar.setVisible(false);
                            btBuscar.setVisible(true);
                            btListar.setVisible(true);

                            tfNome.setEnabled(false);

                            tfDescricao.setEnabled(false);

                            tfValor.setEnabled(false);

                            pnSul.setBackground(Color.green);
                            }catch (Exception btSalvarAtt){
                                JOptionPane.showMessageDialog(null, "ERRO NOS DADOS", "ERROR", JOptionPane.ERROR_MESSAGE);}


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
                        tfIdServicos.setEnabled(true);

                        tfIdServicos.requestFocus();
                        tfIdServicos.selectAll();

                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                        pnSul.setBackground(Color.green);

                        tfNome.setEnabled(false);
                        tfNome.setText(String.valueOf(""));

                        tfDescricao.setEnabled(false);
                        tfDescricao.setText(String.valueOf(""));

                        tfValor.setEnabled(false);
                        tfValor.setText(String.valueOf(""));

                    }
                }
        );

        btAtualizar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        acao = false;

                        tfIdServicos.setText(String.valueOf(IdServicos));
                        tfIdServicos.setEnabled(false);

                        tfNome.setEnabled(true);

                        tfDescricao.setEnabled(true);

                        tfValor.setEnabled(true);

                        tfNome.requestFocus();

                        btSalvar.setVisible(true);
                        btCancelar.setVisible(true);
                        btBuscar.setVisible(false);
                        btListar.setVisible(false);
                        btRemover.setVisible(false);
                        btAtualizar.setVisible(false);

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
                                "Confirma a exclusão do registro <ID = " + entidade.getIdServicos() + ">?", "Confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {


                        tfNome.setText(String.valueOf(""));

                        tfDescricao.setText(String.valueOf(""));

                        tfValor.setText(String.valueOf(""));

                        tfIdServicos.requestFocus();
                        tfIdServicos.setEnabled(true);
                        tfIdServicos.setText(String.valueOf(""));
                        controle.remover(entidade);
                        lbAviso.setText("Removeu");


                        } else {
                            lbAviso.setText("Cancelada a remoção");
                            btRemover.setVisible(true);
                            btAtualizar.setVisible(true);
                        }

                    }
                }
        );

       btListar.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e
                    ) {
                try{
                String aux[];
                text.setText("");
                dados = controle.list();
                try{
                if (dados.get(0) == null){}
                }catch(Exception lista){
                    JOptionPane.showMessageDialog(null, "NOTHING TO SEE HERE! 8P", "OPS", JOptionPane.PLAIN_MESSAGE);
                    int erro = 3/0;                }
                for (Servicos linha : dados) {
                    aux = String.valueOf(linha).split(";");
                    text.append(
"IdServicos: " + 
            Integer.valueOf(aux[0])
 + "\n" +
"Nome: " + 
            aux[1]
 + "\n" +
"Descricao: " + 
            aux[2]
 + "\n" +
"Valor: " + 
            Double.valueOf(aux[3])
+ "\n-------------------------------------------------------------------------------------------\n"
);}
                dialog.setLocationRelativeTo(cp);
                dialog.setModal(true);
                dialog.setVisible(true);
                }catch(Exception Lista){                    }
        }});

       addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            System.exit(0);            }
        });
        
        tfIdServicos.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
            btBuscar.doClick();}});


        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
    }
}

