package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuApp extends JFrame{
    private JPanel JPanelPrincipal;
    private JButton btnCadastrarProd;
    private JButton btnCadastrarClient;
    private JPanel JPanelButton;
    private JButton btnFinalizar;
    private JButton btnVizualizarProdutos;
    private JButton btnVizualizarClientes;

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
        btnVizualizarClientes.setPreferredSize(new Dimension(200, 40));
        btnVizualizarProdutos.setPreferredSize(new Dimension(200, 40));
        //ImageIcon original = new ImageIcon("img/icon.png");
        //Image imagem = original.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        //labelImg.setIcon(new ImageIcon(imagem));

        setResizable(false);
        setLocationRelativeTo(null);


        btnCadastrarProd.addActionListener(e -> {
            CadastroProdutoView telaProduto = new CadastroProdutoView();
            telaProduto.setSize(400,400);
            telaProduto.setLocationRelativeTo(null);
            setVisible(false);
            telaProduto.setVisible(true);
            //dispose(); //fecha o menu

            setVisible(true);
        });

        btnCadastrarClient.addActionListener(e -> {

            CadastroClienteView telaCliente = new CadastroClienteView();
            setVisible(false);
            telaCliente.setSize(400,400);
            telaCliente.setLocationRelativeTo(null);
            telaCliente.setVisible(true);
            setVisible(true);

        });

        btnVizualizarProdutos.addActionListener(e -> {
            ProdutosView telaProdutos = new ProdutosView();
            setVisible(false);
            telaProdutos.setSize(400,400);
            telaProdutos.setLocationRelativeTo(null);
            telaProdutos.setVisible(true);
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
