package view;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;

public class CadastroClienteView extends JDialog {
    private JPanel contentPane;
    private JButton buttonSalvar;
    private JButton buttonCancelar;
    private JTextField campoNome;
    private JFormattedTextField campFormatCPF;
    private JButton btnVisualizarClientes;

    private void createUIComponents() {
        campFormatCPF = new JFormattedTextField();
        try {
            MaskFormatter formatter = new MaskFormatter("###.###.###-##");
            campFormatCPF = new JFormattedTextField(formatter);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public CadastroClienteView() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSalvar);
        ImageIcon iconJanela = new ImageIcon("src/img/iconJanela.png");
        setIconImage(iconJanela.getImage());

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

    }

    private void onOK() { dispose(); }

    private void onCancel() { dispose(); }

    /*
    public static void main(String[] args) {
        CadastroClienteView telaCliente = new CadastroClienteView();
        telaCliente.setSize(500,500);
        telaCliente.setLocationRelativeTo(null);
        telaCliente.setVisible(true);
        System.exit(0);
    }
    */

}
