package view;

import Controller.ClienteController;
import model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClientesView extends JDialog {
    private JPanel contentPane;
    private JTable tabelaClientes;
    private JButton buttonExcluir;
    private JButton btnFechar;
    private ClienteController clienteController;

    public ClientesView(ClienteController clienteController) {
        this.clienteController = clienteController;

        setTitle("Lista de Clientes");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Inicializa os componentes
        contentPane = new JPanel(new BorderLayout());
        tabelaClientes = new JTable();
        btnFechar = new JButton("Fechar");
        buttonExcluir = new JButton("Excluir"); // Adicionado botão de exclusão

        // Painel para os botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(buttonExcluir);
        painelBotoes.add(btnFechar);

        // Adiciona a tabela e os botões ao painel
        contentPane.add(new JScrollPane(tabelaClientes), BorderLayout.CENTER);
        contentPane.add(painelBotoes, BorderLayout.SOUTH);

        // Configura a janela
        setContentPane(contentPane);

        carregarClientes();
        btnFechar.addActionListener(e -> dispose());
        buttonExcluir.addActionListener(e -> excluirCliente());
    }

    private void carregarClientes() {
        List<Cliente> clientes = clienteController.listarClientes();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Código");
        model.addColumn("CPF");
        model.addColumn("Nome");
        model.addColumn("Endereço");
        model.addColumn("Telefone");

        for (Cliente cliente : clientes) {
            model.addRow(new Object[]{
                    cliente.getCodCliente(),
                    cliente.getCPF(),
                    cliente.getNome(),
                    cliente.getEndereco(),
                    cliente.getTelefone()
            });
        }
        //Atualiza a tabela
        tabelaClientes.setModel(model);
    }

    private void excluirCliente() {
        int linhaSelecionada = tabelaClientes.getSelectedRow(); // Pega a linha selecionada

        if (linhaSelecionada == -1) { // Se nenhuma linha estiver selecionada
            JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir!");
            return;
        }

        int codigo = (int) tabelaClientes.getValueAt(linhaSelecionada, 0); // Obtém o código do cliente

        int confirmacao = JOptionPane.showConfirmDialog(
                this, "Tem certeza que deseja excluir este cliente?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            boolean sucesso = clienteController.excluirCliente(codigo); // Chama o Controller

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
                ((DefaultTableModel) tabelaClientes.getModel()).removeRow(linhaSelecionada); // Remove da tabela
                carregarClientes(); // Atualiza a tabela
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir o cliente!");
            }
        }
    }

}
