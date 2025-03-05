package view;

import dao.ProdutoDAO;
import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ProdutosView extends JDialog{
    private JPanel contentPane;
    private JTable tabelaProdutos;
    private JButton btnEditarProduto;
    private JButton btnExcluirProduto;

    public ProdutosView() {
        setContentPane(contentPane);
        setModal(true);
        //getRootPane().setDefaultButton();
    }

    private void createUIComponents() {
        ProdutoDAO dao = new ProdutoDAO();
        Set<Produto> produtos = null;
        try {
            produtos = dao.getProdutos();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (produtos != null) {
            tabelaProdutos = new JTable();
            String colunas[] = {"Código", "Descrição", "Tipo"};
            String conteudo[][] = new String[produtos.size()][3];

            List<Produto> produtoList = new ArrayList<>(produtos);
            Collections.sort(produtoList, new Comparator<Produto>() {
                @Override
                public int compare(Produto p1, Produto p2) {
                    return Integer.compare(p1.getCodProduto(), p2.getCodProduto());
                }
            });


            for (int i = 0; i < produtoList.size(); i++) {
                conteudo[i][0] = "" + produtoList.get(i).getCodProduto();
                conteudo[i][1] = produtoList.get(i).getNome();
                conteudo[i][2] = "" + produtoList.get(i).getTipo();

            }
            tabelaProdutos.setModel(new DefaultTableModel(conteudo, colunas));

            JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
            add(scrollPane, BorderLayout.CENTER);
        }
    }
}