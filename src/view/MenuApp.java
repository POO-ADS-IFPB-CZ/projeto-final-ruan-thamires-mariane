package view;

import javax.swing.*;
import java.awt.*;

public class MenuApp extends JFrame{
    private JPanel JPanelPrincipal;
    private JButton btnCadastrarProd;
    private JButton btnCadastrarClient;
    private JPanel JPanelButton;
    private JButton btnFinalizar;
    private JButton btnVisualizarProdutos;
    private JButton btnVisualizarClientes;

    private JLabel labelImg;

    public MenuApp(){
        setContentPane(JPanelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().setDefaultButton(btnFinalizar);

        setTitle("Sistema do Consultor");
        ImageIcon iconJanela = new ImageIcon("src/img/iconJanela.png");
        setIconImage(iconJanela.getImage());
        setSize(600,600);

        btnFinalizar.setPreferredSize(new Dimension(150, 40));
        btnCadastrarProd.setPreferredSize(new Dimension(200, 40));
        btnCadastrarClient.setPreferredSize(new Dimension(200, 40));
        btnVisualizarClientes.setPreferredSize(new Dimension(200, 40));
        btnVisualizarProdutos.setPreferredSize(new Dimension(200, 40));

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

        btnVisualizarProdutos.addActionListener(e -> {
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
