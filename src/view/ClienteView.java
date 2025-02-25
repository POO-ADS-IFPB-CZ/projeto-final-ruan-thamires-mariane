package view;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;

public class ClienteView extends JDialog {
    private JPanel contentPane;
    private JButton buttonSalvar;
    private JButton buttonCancelar;
    private JTextField campoNome;
    private JFormattedTextField campFormatCPF;

    private void createUIComponents() {
        campFormatCPF = new JFormattedTextField();
        try {
            MaskFormatter formatter = new MaskFormatter("###.###.###-##");
            campFormatCPF = new JFormattedTextField(formatter);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public ClienteView() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSalvar);

        buttonSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(validarCampos()){
                    System.out.println("Cliente salvo: " + campoNome.getText());
                    onOK();
                }

            }

            private boolean validarCampos() {
                if(campFormatCPF.getText().isEmpty() || campoNome.getText().isEmpty() ){
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
        ClienteView telaCliente = new ClienteView();
        telaCliente.pack();
        telaCliente.setVisible(true);
        System.exit(0);
    }
     */
}
