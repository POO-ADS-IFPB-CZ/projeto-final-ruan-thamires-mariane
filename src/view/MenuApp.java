package view;

import Controller.ClienteController;
import view.CadastroClienteView;
import view.ClientesView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuApp extends JFrame{
    private JPanel JPanelPrincipal;
    private JButton btnCadastrarProd;
    private JButton btnCadastrarClient;
    private JPanel JPanelButton;
    private JButton btnFinalizar;
    private JButton btnVisualizarProdutos;
    private JButton btnVisualizarClientes;

    private JLabel labelImg;
    private ClienteController clienteController;

    public MenuApp(){
        setContentPane(JPanelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().setDefaultButton(btnFinalizar);

        ImageIcon iconJanela = new ImageIcon("img/iconJanela.png");
        setIconImage(iconJanela.getImage());

        setTitle("Sistema do Consultor");
        iconJanela = new ImageIcon("src/img/iconJanela.png");
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
            telaProduto.setVisible(true);
            //dispose(); //fecha o menu
        });

        clienteController = new ClienteController();

        btnCadastrarClient.addActionListener(e -> {
            CadastroClienteView telaCliente = new CadastroClienteView(clienteController);
            telaCliente.setSize(400,400);
            telaCliente.setLocationRelativeTo(null);
            telaCliente.setVisible(true);
        });

        btnVisualizarClientes.addActionListener(e -> {
                // Abre a tela de listagem de clientes
                ClientesView dialog = new ClientesView(clienteController);
                dialog.setSize(500, 300);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
        });

        btnVisualizarProdutos.addActionListener(e -> {
            ProdutosView telaProdutos = new ProdutosView();
            telaProdutos.setSize(400,400);
            telaProdutos.setLocationRelativeTo(null);
            telaProdutos.setVisible(true);
        });

        btnFinalizar.addActionListener(e -> dispose());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuApp menu = new MenuApp();
            menu.setVisible(true);
        });
    }
}
