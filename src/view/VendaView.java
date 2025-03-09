package view;

import javax.swing.*;

public class VendaView extends JDialog {
    private JPanel contentPane;
    private JTable tabelaVendas;

    public VendaView() {
        setContentPane(contentPane);
        setModal(true);
    }

    public static void main(String[] args) {
        VendaView dialog = new VendaView();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
