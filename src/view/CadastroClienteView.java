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
    private JButton btnVizualizarClientes;
    private JTextField campoEndereco;
    private JFormattedTextField campoTelefone;
    private ClienteController clienteController;

    public CadastroClienteView(ClienteController clienteController) {
        this.clienteController = clienteController; //Inicializa o controller

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSalvar);

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
                    onOK();
                }
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

        btnVizualizarClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre a tela de listagem de clientes
                ClientesView clientesView = new ClientesView(clienteController);
                clientesView.setVisible(true);
            }
        });

        /*
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

         */
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    /*
    public static void main(String[] args) {
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


}
