package view;

import dao.ProdutoDAO;
import dao.VendaDAO;
import model.Cliente;
import model.Produto;
import model.Venda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
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
        setSize(500, 400);
        setLocationRelativeTo(null);

        vendaDao = new VendaDAO();
        //carregarTabela();

        btnExcluirVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //excluirVenda();
            }
        });

        btnEditarVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarVenda();
            }
        });
    }

    private void editarVenda() {
        int linhaSelecionada = tabelaVendas.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para editar.");
            return;
        }
        int codigo = Integer.parseInt(tabelaVendas.getValueAt(linhaSelecionada, 0).toString());

        String nomeCliente = (String) tabelaVendas.getValueAt(linhaSelecionada, 1);
        String stringNovoValor = JOptionPane.showInputDialog(this, "Novo valor da venda:", tabelaVendas.getValueAt(linhaSelecionada, 2));
        double novoValor = Double.parseDouble(stringNovoValor);
        String novaDescricao = JOptionPane.showInputDialog(this, "Nova descrição para a venda:", tabelaVendas.getValueAt(linhaSelecionada, 3));
        LocalDate data = (LocalDate) tabelaVendas.getValueAt(linhaSelecionada, 4);
        Venda vendaAtualizada = new Venda(codigo, nomeCliente, novoValor, novaDescricao, data);

        System.out.println(vendaAtualizada.toString());
        /*
        if (novoValor != 0 && novaDescricao != null) {
            try {
                List<Venda> listVendas = vendaDao.listarTodos();
                for (Venda v : listVendas) {
                    if (v.getCodVenda() == codigo) {
                        v.setValor(novoValor);
                        v.setDescricao(novaDescricao);
                        vendaDao.editar(v);
                        break;
                    }
                }
                //carregarTabela();
                JOptionPane.showMessageDialog(this, "Registro de venda atualizado com sucesso.");
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar registro de venda.");
            }

        }
         */
    }
    /*
    private void excluirVenda(){
        int linhaSelecionada = tabelaVendas.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para excluir.");
            return;
        }

        int codigo = Integer.parseInt(tabelaVendas.getValueAt(linhaSelecionada, 0).toString());

        try {
            vendaDao.excluir(codigo);
            JOptionPane.showMessageDialog(this, "Produto excluído com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir produto.");
        }
    }
    */
    public static void main(String[] args) {
        VendaView dialog = new VendaView();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
