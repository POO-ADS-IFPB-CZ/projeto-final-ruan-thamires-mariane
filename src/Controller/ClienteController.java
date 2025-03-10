package Controller;

import dao.ClienteDAO;
import model.Cliente;

import java.util.List;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }
    // Método para salvar um cliente
    public void salvarCliente(int codCliente, String CPF, String nome, String endereco, String telefone) {
        Cliente cliente = new Cliente(codCliente, CPF, nome, endereco, telefone);
        clienteDAO.salvar(cliente);
        clienteDAO.carregarClientes();
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listarTodos(); // Usa a instância já inicializada de clienteDAO
    }

    public boolean excluirCliente(int codCliente) {
        boolean sucesso = clienteDAO.excluir(codCliente); // Exclui o cliente diretamente
        return sucesso;
    }

    // Método para buscar um cliente pelo código
    public Cliente buscarClientePorCodigo(int codCliente) {
        return clienteDAO.buscarPorCodigo(codCliente);
    }
}
