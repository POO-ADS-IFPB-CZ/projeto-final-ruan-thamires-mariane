package dao;

import model.Produto;

import javax.swing.*;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ProdutoDAO {
    private File arquivo;

    //construtor, inicializa o arquivo
    public ProdutoDAO() {
        arquivo = new File("Produtos");
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Set<Produto> getProdutos() throws IOException, ClassNotFoundException {
        if (arquivo.length() == 0) {
            return new HashSet<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (Set<Produto>) in.readObject();
        }
    }

    private void salvarProdutos(Set<Produto> produtos) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(produtos);
        }
    }

    public void adicionarProduto(Produto produto) throws IOException, ClassNotFoundException {
        Set<Produto> produtos = getProdutos();
        produtos.add(produto);
        salvarProdutos(produtos);
        JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!");
    }

    public void atualizarProduto(Produto produtoAtualizado) throws IOException, ClassNotFoundException {
        Set<Produto> produtos = getProdutos();
        produtos.removeIf(p -> p.getCodProduto() == produtoAtualizado.getCodProduto());
        produtos.add(produtoAtualizado);
        salvarProdutos(produtos);
    }

    public void removerProduto(int codProduto) throws IOException, ClassNotFoundException {
        Set<Produto> produtos = getProdutos();
        produtos.removeIf(p -> p.getCodProduto() == codProduto);
        salvarProdutos(produtos);
    }
}
