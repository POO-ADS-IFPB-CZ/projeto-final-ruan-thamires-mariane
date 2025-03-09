package view;

import dao.ProdutoDAO;
import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ProdutosView extends JDialog {
    private JPanel contentPane;
    private JTable tabelaProdutos;
    private JButton btnEditarProduto;
    private JButton btnExcluirProduto;
    private ProdutoDAO dao;

    public ProdutosView() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Gerenciamento de Produtos");
        setSize(500, 400);
        setLocationRelativeTo(null);

        dao = new ProdutoDAO();
        carregarTabela();

        btnExcluirProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirProduto();
            }
        });

        btnEditarProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarProduto();
            }
        });
    }

    private void carregarTabela() {
        Set<Produto> produtos;
        try {
            produtos = dao.getProdutos();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (produtos != null) {
            String colunas[] = {"Código", "Descrição", "Tipo"};
            String conteudo[][] = new String[produtos.size()][3];

            List<Produto> produtoList = new ArrayList<>(produtos);
            produtoList.sort(Comparator.comparingInt(Produto::getCodProduto));

            for (int i = 0; i < produtoList.size(); i++) {
                conteudo[i][0] = String.valueOf(produtoList.get(i).getCodProduto());
                conteudo[i][1] = produtoList.get(i).getNome();
                conteudo[i][2] = produtoList.get(i).getTipo();
            }

            tabelaProdutos.setModel(new DefaultTableModel(conteudo, colunas));
        }
    }

    private void excluirProduto() {
        int linhaSelecionada = tabelaProdutos.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para excluir.");
            return;
        }

        int codigo = Integer.parseInt(tabelaProdutos.getValueAt(linhaSelecionada, 0).toString());

        try {
            dao.removerProduto(codigo);
            carregarTabela();
            JOptionPane.showMessageDialog(this, "Produto excluído com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir produto.");
        }
    }

    private void editarProduto() {
        int linhaSelecionada = tabelaProdutos.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para editar.");
            return;
        }

        int codigo = Integer.parseInt(tabelaProdutos.getValueAt(linhaSelecionada, 0).toString());
        String novoNome = JOptionPane.showInputDialog(this, "Novo nome do produto:", tabelaProdutos.getValueAt(linhaSelecionada, 1));
        String novoTipo = JOptionPane.showInputDialog(this, "Novo tipo do produto:", tabelaProdutos.getValueAt(linhaSelecionada, 2));

        if (novoNome != null && novoTipo != null) {
            try {
                Set<Produto> produtos = dao.getProdutos();
                for (Produto p : produtos) {
                    if (p.getCodProduto() == codigo) {
                        p.setNome(novoNome);
                        p.setTipo(novoTipo);
                        dao.atualizarProduto(p);
                        break;
                    }
                }
                carregarTabela();
                JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso.");
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar produto.");
            }
        }
    }
}
