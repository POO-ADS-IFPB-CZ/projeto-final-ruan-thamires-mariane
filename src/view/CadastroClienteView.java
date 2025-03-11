package view;

import Controller.ClienteController;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import javax.swing.JFormattedTextField;

public class CadastroClienteView extends JDialog {
    private JPanel contentPane;
    private JButton buttonSalvar;
    private JButton buttonCancelar;
    private JTextField campoNome;
    private JFormattedTextField campoCPF;
    private JTextField campoEndereco;
    private JFormattedTextField campoTelefone;
    private ClienteController clienteController;
    private JButton btnVisualizarClientes;

    public CadastroClienteView(ClienteController clienteController) {
        this.clienteController = clienteController; //Inicializa o controller

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSalvar);
        ImageIcon iconJanela = new ImageIcon("src/img/iconJanela.png");
        setIconImage(iconJanela.getImage());

        configurarMascaras(); // Chama o método para formatar CPF e Telefone

        buttonSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    String CPF = campoCPF.getText();
                    String telefone = campoTelefone.getText();
                    String nome = campoNome.getText();
                    String endereco = campoEndereco.getText();

                    // Salva o cliente (código 0 indica um novo cliente)
                    clienteController.salvarCliente(0, CPF, nome, endereco, telefone);
                    JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!");
                    //onOK();
                }
                campoCPF.setText("");
                campoTelefone.setText("");
                campoNome.setText("");
                campoEndereco.setText("");

            }

            private boolean validarCampos() {
                if (campoCPF.getText().contains("_")|| campoNome.getText().isEmpty() || campoEndereco.getText().isEmpty() || campoTelefone.getText().contains("_")) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente!");
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

        btnVisualizarClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientesView dialog = new ClientesView(clienteController);
                dialog.setSize(600, 600);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    }

    private void onOK() { dispose(); }

    private void onCancel() { dispose(); }

    /*
    public static void main(String[] args) {
<<<<<<< HEAD
        ClienteController clienteController = new ClienteController(); // Cria o controller
        CadastroClienteView dialog = new CadastroClienteView(clienteController); // Passa o controller para a view
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
     */

    private void configurarMascaras() {
        try {
            // Criando máscara para CPF: "###.###.###-##"
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.setPlaceholderCharacter('_');
            campoCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(cpfMask));

            // Criando máscara para Telefone: "(##) #####-####"
            MaskFormatter telefoneMask = new MaskFormatter("(##) #####-####");
            telefoneMask.setPlaceholderCharacter('_');
            campoTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(telefoneMask));

        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao aplicar máscara!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
        /*CadastroClienteView telaCliente = new CadastroClienteView();
        telaCliente.setSize(500,500);
        telaCliente.setLocationRelativeTo(null);
        telaCliente.setVisible(true);
        System.exit(0);
    */
}
