package view;

import javax.swing.*;
import java.awt.event.*;

public class ProdutoView extends JDialog {
    private JPanel contentPane;
    private JButton buttonSalvar;
    private JButton buttonCancelar;
    private JTextField campoCodigo;
    private JTextField campoNome;
    private JTextField campoTipo;

    public ProdutoView() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSalvar);


        buttonSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(validarCampos()){
                    System.out.println("Produto salvo: " + campoNome.getText());
                    onOK();
                }

            }

            private boolean validarCampos() {
                if(campoCodigo.getText().isEmpty() ||
                        campoNome.getText().isEmpty() ||
                        campoTipo.getText().isEmpty() ){
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
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    /*
    public static void main(String[] args) {
        ProdutoView dialog = new ProdutoView();
        //dialog.pack();
        dialog.setSize(300,300);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }
     */
}
