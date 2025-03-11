package view;

import dao.VendaDAO;
import model.Venda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class VendaView extends JDialog {
    private JPanel contentPane;
    private JTable tabelaVendas;
    private JButton btnEditarVenda;
    private JButton btnExcluirVenda;
    private JPanel painelBotoes;
    private VendaDAO vendaDao;

    public VendaView() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Gerenciamento de Vendas");
        ImageIcon iconJanela = new ImageIcon("src/img/iconJanela.png");
        setIconImage(iconJanela.getImage());
        setSize(600, 600);
        setLocationRelativeTo(null);

        vendaDao = new VendaDAO();
        carregarTabelaVendas();

        btnExcluirVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirVenda();
            }
        });

        btnEditarVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarVenda();
            }
        });
    }

    private void carregarTabelaVendas() {
        Set<Venda> vendas;
        try {
            vendas = vendaDao.getVendas();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (vendas != null) {
            String colunas[] = {"Código", "Cliente", "Valor", "Descrição", "Data"};
            String conteudo[][] = new String[vendas.size()][5];

            List<Venda> listVendas = new ArrayList<>(vendas);
            listVendas.sort(Comparator.comparingInt(Venda::getCodVenda));

            for (int i = 0; i < listVendas.size(); i++) {
                conteudo[i][0] = String.valueOf(listVendas.get(i).getCodVenda());
                conteudo[i][1] = listVendas.get(i).getCliente();
                conteudo[i][2] = String.valueOf(listVendas.get(i).getValor());
                conteudo[i][3] = listVendas.get(i).getDescricao();
                conteudo[i][4] = String.valueOf(listVendas.get(i).getDataRegistro());
            }

            tabelaVendas.setModel(new DefaultTableModel(conteudo, colunas));

        }
    }

    private void editarVenda() {
        int linhaSelecionada = tabelaVendas.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um registro de venda para editar.");
            return;
        }
        int codigo = Integer.parseInt(tabelaVendas.getValueAt(linhaSelecionada, 0).toString());

        String nomeCliente = JOptionPane.showInputDialog(this, "Novo cliente da venda:", tabelaVendas.getValueAt(linhaSelecionada, 1));
        String stringNovoValor = JOptionPane.showInputDialog(this, "Novo valor da venda:", tabelaVendas.getValueAt(linhaSelecionada, 2));
        double novoValor = Double.parseDouble(stringNovoValor);
        String novaDescricao = JOptionPane.showInputDialog(this, "Nova descrição para a venda:", tabelaVendas.getValueAt(linhaSelecionada, 3));
        String dataString = JOptionPane.showInputDialog(this, "Nova data da venda (AAAA-MM-DD):", tabelaVendas.getValueAt(linhaSelecionada, 4));
        LocalDate novaData = LocalDate.parse(dataString);


        Venda vendaAtualizada = new Venda(codigo, nomeCliente, novoValor, novaDescricao, novaData);

        System.out.println(vendaAtualizada.toString());

        if (novoValor != 0 && nomeCliente != "") {
            try {
                Set<Venda> listVendas = vendaDao.getVendas();
                for (Venda v : listVendas) {
                    if (v.getCodVenda() == codigo) {
                        v.setCliente(nomeCliente);
                        v.setValor(novoValor);
                        v.setDescricao(novaDescricao);
                        v.setDataRegistro(novaData);
                        vendaDao.atualizarVenda(v);
                        break;
                    }
                }
                carregarTabelaVendas();
                JOptionPane.showMessageDialog(this, "Registro de venda atualizado com sucesso.");
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar registro de venda.");
            }
        }
    }

    private void excluirVenda(){
        int linhaSelecionada = tabelaVendas.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para excluir.");
            return;
        }

        int codigo = Integer.parseInt(tabelaVendas.getValueAt(linhaSelecionada, 0).toString());

        try {
            vendaDao.removerVenda(codigo);
            carregarTabelaVendas();
            JOptionPane.showMessageDialog(this, "Venda excluída com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir venda.");
        }
    }
    /*
    public static void main(String[] args) {
        VendaView dialog = new VendaView();
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }
    */
}
