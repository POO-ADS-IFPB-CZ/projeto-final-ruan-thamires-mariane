package dao;

import model.Venda;

import javax.swing.*;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class VendaDAO {
    private File arquivo;
    public VendaDAO() {
        arquivo = new File("Vendas");
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Set<Venda> getVendas() throws IOException, ClassNotFoundException {
        if (arquivo.length() == 0) {
            return new HashSet<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (Set<Venda>) in.readObject();
        }
    }

    private void registrarVenda(Set<Venda> vendas) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(vendas);
        }
    }

    public void adicionarProduto(Venda venda) throws IOException, ClassNotFoundException {
        Set<Venda> vendas = getVendas();
        vendas.add(venda);
        registrarVenda(vendas);
        JOptionPane.showMessageDialog(null, "Venda registrada com sucesso!");
    }

    public void atualizarVenda(Venda vendaAtualizada) throws IOException, ClassNotFoundException {
        Set<Venda> vendas = getVendas();
        vendas.removeIf(v -> v.getCodVenda() == vendaAtualizada.getCodVenda());
        vendas.add(vendaAtualizada);
        registrarVenda(vendas);
    }

    public void removerVenda(int codVenda) throws IOException, ClassNotFoundException {
        Set<Venda> vendas = getVendas();
        vendas.removeIf(v -> v.getCodVenda() == codVenda);
        registrarVenda(vendas);
    }
}
