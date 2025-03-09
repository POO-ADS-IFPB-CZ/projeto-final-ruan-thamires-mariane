package view;

import javax.swing.*;

public class ClientesView extends JDialog {
    private JPanel contentPane;
    private JTable tabelaClientes;

    public ClientesView(){
        ImageIcon iconJanela = new ImageIcon("src/img/iconJanela.png");
        setIconImage(iconJanela.getImage());
    }
}
