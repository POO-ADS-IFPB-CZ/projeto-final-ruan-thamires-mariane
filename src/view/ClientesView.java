package view;

import Controller.ClienteController;
import dao.ClienteDAO;
import model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

public class ClientesView extends JDialog {
    private JPanel contentPane;
    private JTable tabelaClientes;
    private JButton buttonExcluir;
    private JButton editarClienteButton;
    private ClienteController clienteController;

    public ClientesView(ClienteController clienteController) {
        this.clienteController = clienteController;

        ImageIcon iconJanela = new ImageIcon("src/img/iconJanela.png");
        setIconImage(iconJanela.getImage());

        setTitle("Lista de Clientes");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Inicializa os componentes
        contentPane = new JPanel(new BorderLayout());
        tabelaClientes = new JTable();
        buttonExcluir = new JButton("Excluir");
        editarClienteButton = new JButton("Editar");

        // Painel para os botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(buttonExcluir);
        painelBotoes.add(editarClienteButton); // Adicionando o botão ao painel

        // Adiciona a tabela e os botões ao painel
        contentPane.add(new JScrollPane(tabelaClientes), BorderLayout.CENTER);
        contentPane.add(painelBotoes, BorderLayout.SOUTH);

        // Configura a janela
        setContentPane(contentPane);

        carregarClientes();
        buttonExcluir.addActionListener(e -> excluirCliente());
        editarClienteButton.addActionListener(e -> editarCliente());
    }


    private void editarCliente() {
        int linhaSelecionada = tabelaClientes.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para editar.");
            return;
        }

        int codCliente = Integer.parseInt(tabelaClientes.getValueAt(linhaSelecionada, 0).toString());
        String novoCpf = JOptionPane.showInputDialog(this, "Novo CPF do cliente:", tabelaClientes.getValueAt(linhaSelecionada, 1));
        String novoNome = JOptionPane.showInputDialog(this, "Novo nome do cliente:", tabelaClientes.getValueAt(linhaSelecionada, 2));
        String novoEndereco = JOptionPane.showInputDialog(this, "Novo endereço do cliente:", tabelaClientes.getValueAt(linhaSelecionada, 3));
        String novoTelefone = JOptionPane.showInputDialog(this, "Novo telefone do cliente:", tabelaClientes.getValueAt(linhaSelecionada, 4));

        // Obtém o cliente existente
        Cliente clienteExistente = clienteController.buscarClientePorCodigo(codCliente);
        if (clienteExistente == null) {
            JOptionPane.showMessageDialog(this, "Erro: Cliente não encontrado!");
            return;
        }

        // Atualiza os dados se o usuário inseriu novos valores
        if (novoCpf != null) clienteExistente.setCPF(novoCpf);
        if (novoNome != null) clienteExistente.setNome(novoNome);
        if (novoEndereco != null) clienteExistente.setEndereco(novoEndereco);
        if (novoTelefone != null) clienteExistente.setTelefone(novoTelefone);

        // Chama o metodo de atualização no controller
        boolean atualizado = clienteController.atualizarCliente(clienteExistente);

        if (atualizado) {
            JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
            carregarClientes(); // Atualiza a tabela na interface gráfica
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar o cliente!");
        }
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
