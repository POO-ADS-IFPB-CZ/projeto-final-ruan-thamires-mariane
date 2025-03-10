package view;

import dao.VendaDAO;
import model.Venda;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.*;
import java.time.LocalDate;

public class RegistrarVenda extends JDialog {
    private JPanel contentPane;
    private JButton btnRegistarVenda;
    private JButton btnCancelar;
    private JTextField campoCodigo;
    private JTextField campoCliente;
    private JFormattedTextField campoFormatData;
    private JTextField campoValor;
    private JTextField campoDescricao;
    private VendaDAO vendaDao;

    public RegistrarVenda() {
        setContentPane(contentPane);
        setModal(true);
        ImageIcon iconJanela = new ImageIcon("src/img/iconJanela.png");
        setIconImage(iconJanela.getImage());
        getRootPane().setDefaultButton(btnRegistarVenda);

        btnRegistarVenda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(validarCampos()){
                    String codString = campoCodigo.getText();
                    int cod = Integer.parseInt(codString);
                    String cliente = campoCliente.getText();
                    String valorString = campoValor.getText();
                    Double valor = Double.parseDouble(valorString);
                    String descricao = campoDescricao.getText();
                    LocalDate data = LocalDate.now();
                    Venda venda = new Venda(cod, cliente , valor, descricao, data);

                    //vendaDao.salvarRegistros(venda);

                    //onOK();
                }
                campoCodigo.setText("");
                campoCliente.setText("");
                campoValor.setText("");
                campoFormatData.setText("");
            }
            private boolean validarCampos() {
                if(campoCodigo.getText().isEmpty() || campoCliente.getText().isEmpty() ||
                        campoValor.getText().isEmpty() ){
                    JOptionPane.showMessageDialog(null,
                            "Preencha todos os campos");
                    return false;
                }
                return true;
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        RegistrarVenda dialog = new RegistrarVenda();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
