package view;

import javax.swing.*;
import java.awt.*;

public class MenuApp extends JFrame{
    private JPanel JPanelPrincipal;
    private JButton btnCadastrarProd;
    private JButton btnCadastrarClient;
    private JPanel JPanelButton;
    private JButton btnFinalizar;
    private JLabel labelImg;

    public MenuApp(){
        setContentPane(JPanelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().setDefaultButton(btnFinalizar);


        ImageIcon iconJanela = new ImageIcon("img/iconJanela.png");
        setIconImage(iconJanela.getImage());
        setTitle("Sistema do Consultor");
        setSize(600,600);

        btnFinalizar.setPreferredSize(new Dimension(150, 40));
        btnCadastrarProd.setPreferredSize(new Dimension(200, 40));
        btnCadastrarClient.setPreferredSize(new Dimension(200, 40));
        ImageIcon original = new ImageIcon("img/icon.png");
        Image imagem = original.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        labelImg.setIcon(new ImageIcon(imagem));

        setResizable(false);
        setLocationRelativeTo(null);

        btnCadastrarProd.addActionListener(e -> {
            ProdutoCadastroView telaProduto = new ProdutoCadastroView();
            telaProduto.setSize(300,300);
            telaProduto.setLocationRelativeTo(null);
            setVisible(false);
            telaProduto.setVisible(true);
            //dispose(); //fecha o menu

            setVisible(true);
        });

        btnCadastrarClient.addActionListener(e -> {

            ClienteView telaCliente = new ClienteView();
            setVisible(false);
            telaCliente.setSize(300,300);
            telaCliente.setLocationRelativeTo(null);
            telaCliente.setVisible(true);
            setVisible(true);

        });

        btnFinalizar.addActionListener(e -> {
            dispose();
        });

    }

    public static void main(String[] args) {
        MenuApp menu = new MenuApp();
        menu.setSize(600,600);
        menu.setVisible(true);
    }


}
