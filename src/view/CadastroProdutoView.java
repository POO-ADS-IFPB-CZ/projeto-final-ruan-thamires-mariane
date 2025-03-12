package view;

import dao.ProdutoDAO;
import model.Produto;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class CadastroProdutoView extends JDialog {
    private JPanel contentPane;
    private JButton buttonSalvar;
    private JButton buttonCancelar;
    private JTextField campoCodigo;
    private JTextField campoNome;
    private JTextField campoTipo;
    private JButton btnVisualizarProdutos;
    private JComboBox comboBox1;
    private ProdutoDAO produtoDAO;

    public CadastroProdutoView() {
        setContentPane(contentPane);
        setModal(true);
        ImageIcon iconJanela = new ImageIcon("src/img/iconJanela.png");
        setIconImage(iconJanela.getImage());
        getRootPane().setDefaultButton(buttonSalvar);
        produtoDAO = new ProdutoDAO();


        buttonSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(validarCampos()){
                    System.out.println("Produto salvo: " + campoNome.getText());
                    String codString = campoCodigo.getText();
                    int cod = Integer.parseInt(codString);
                    String nome = campoNome.getText();
                    String tipo = (String) comboBox1.getSelectedItem();
                    Produto produto = new Produto(cod, nome , tipo);
                    try {
                        produtoDAO.adicionarProduto(produto);
                    } catch (IOException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    //onOK();
                }
                campoCodigo.setText("");
                campoNome.setText("");
                comboBox1.setSelectedIndex(0);

            }

            private boolean validarCampos() {
                if(campoCodigo.getText().isEmpty() ||
                        campoNome.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,
                            "Preencha todos os campos");
                    return false;
                }
                return true;
            }

        });

        buttonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });


        btnVisualizarProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProdutosView telaProdutos = new ProdutosView();
                setVisible(false);
                telaProdutos.setSize(600,600);
                telaProdutos.setLocationRelativeTo(null);
                telaProdutos.setVisible(true);
                setVisible(true);
            }
        });
    }

    private void onOK() {
        dispose();
    }
    private void onCancel() {
        dispose();
    }

    /*
    public static void main(String[] args) {
        CadastroProdutoView dialog = new CadastroProdutoView();
        //dialog.pack();
        dialog.setSize(500,500);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }
    */

}
