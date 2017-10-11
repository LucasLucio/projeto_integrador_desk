/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Ferramentas.CentroDoMonitorMaior;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author lukas
 */
public class GUI_MenuPrincipal extends JFrame {

    Container cp;
    JPanel pnCentro = new JPanel();

    JLabel labelComImagemDeTamanhoDiferente = new JLabel();

    JMenuBar menuBar = new JMenuBar();
    JMenu menuCadastros = new JMenu("Cadastros");
    JMenu menuContrato = new JMenu("Contrato");
    JMenuItem cadPessoa = new JMenuItem("Pessoa");
    JMenuItem cadCidade = new JMenuItem("Cidade");
    JMenuItem cadContrato = new JMenuItem("Novo Contrato");
    JMenuItem cadBusca = new JMenuItem("Buscar Contrato");
    Point p;

    @Override
    public void setResizable(boolean resizable) {
        super.setResizable(resizable); //To change body of generated methods, choose Tools | Templates.
    }
    
    public GUI_MenuPrincipal(Dimension dimensao) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(dimensao);
        setResizable(false);
        setTitle("Gerenciador Help Entertainment");

        cp = getContentPane();
        cp.setLayout(new BorderLayout());

        //para ajustar o tamanho de uma imagem
        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("/Imagens/FundoHelp.jpg"));
            Image imagemAux;
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(500, 280, Image.SCALE_FAST));

            labelComImagemDeTamanhoDiferente = new JLabel();
            labelComImagemDeTamanhoDiferente.setIcon(icone);
        } catch (Exception e) {
            System.out.println("erro ao carregar a imagem");
        }

        pnCentro.add(labelComImagemDeTamanhoDiferente);
        pnCentro.setBackground(Color.BLACK);

        cp.add(pnCentro, BorderLayout.CENTER);

        setJMenuBar(menuBar);
        menuBar.add(menuCadastros);
        menuBar.add(menuContrato);

        menuCadastros.add(cadPessoa);
        menuCadastros.add(cadCidade);
        
        menuContrato.add(cadContrato);
        menuContrato.add(cadBusca);

        cadPessoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GUI_Pessoa pessoa = new GUI_Pessoa();
                } catch (ParseException ex) {
                    Logger.getLogger(GUI_MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        cadBusca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GUI_ContratoBuscar gui_ContratoBuscar = new GUI_ContratoBuscar();
                } catch (ParseException ex) {
                    Logger.getLogger(GUI_MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        cadCidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GUI_Cidade gui_Cidade = new GUI_Cidade();
                } catch (ParseException ex) {
                    Logger.getLogger(GUI_MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        cadContrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GUI_ContratoNovo gui = new GUI_ContratoNovo();
                    // GUI_Contrato gui_Contrato = new GUI_Contrato();
                } catch (ParseException ex) {
                    Logger.getLogger(GUI_MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Sai do sistema  
                System.exit(0);
            }
        });

        pack();
        p = new CentroDoMonitorMaior().getCentroMonitorMaior(this);
        setLocation(p);
        setVisible(true);
    }
}
