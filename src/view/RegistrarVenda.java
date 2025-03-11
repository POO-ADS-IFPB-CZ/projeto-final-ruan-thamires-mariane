package view;

import dao.VendaDAO;
import model.Venda;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
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
    private JButton btnVisualizarVendas;
    private VendaDAO vendaDao;

    public RegistrarVenda() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Registro de vendas");
        ImageIcon iconJanela = new ImageIcon("src/img/iconJanela.png");
        setIconImage(iconJanela.getImage());
        getRootPane().setDefaultButton(btnRegistarVenda);
        vendaDao = new VendaDAO();

        btnRegistarVenda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(validarCampos()){
                    String codString = campoCodigo.getText();
                    int cod = Integer.parseInt(codString);
                    String nomeCliente = campoCliente.getText();
                    String valorString = campoValor.getText();
                    Double valor = Double.parseDouble(valorString);
                    String descricao = campoDescricao.getText();
                    //LocalDate data = LocalDate.now();
                    String dataString = campoFormatData.getText();
                    LocalDate data = LocalDate.parse(dataString);
                    Venda novaVenda = new Venda(cod, nomeCliente, valor, descricao, data);
                    try {
                        vendaDao.adicionarProduto(novaVenda);
                    } catch (IOException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }

                    //onOK();
                }
                campoCodigo.setText("");
                campoCliente.setText("");
                campoValor.setText("");
                campoDescricao.setText("");
                campoFormatData.setText("");
            }
            private boolean validarCampos() {
                if(campoCodigo.getText().isEmpty() || campoCliente.getText().isEmpty() ||
                        campoValor.getText().isEmpty() || campoDescricao.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos");
                    return false;
                }
                if (Integer.parseInt(campoValor.getText()) < 0){
                    JOptionPane.showMessageDialog(null, "Erro: valor negativo");
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

        btnVisualizarVendas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VendaView telaVendas = new VendaView();
                telaVendas.setSize(600,600);
                telaVendas.setLocationRelativeTo(null);
                telaVendas.setVisible(true);
            }
        });

    }

    private void onOK() { dispose(); }

    private void onCancel() { dispose(); }

    /*
    public static void main(String[] args) {
        RegistrarVenda dialog = new RegistrarVenda();
        dialog.setSize(500,500);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }
     */
}
