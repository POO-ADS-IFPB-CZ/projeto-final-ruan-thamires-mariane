package dao;

import model.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO {
    private List<Cliente> clientes = new ArrayList<>();
    private int proximoCodigo = 1;

    // Nome do arquivo para salvar os clientes
    private static final String FILE_NAME = "clientes.dat";

    public ClienteDAO() {
        carregarClientes(); // Carrega os clientes do arquivo ao iniciar
    }

    public void salvar(Cliente cliente) {
        if (cliente.getCodCliente() == 0) {
            cliente.setCodCliente(proximoCodigo++);
            clientes.add(cliente);
        } else {
            for (int i = 0; i < clientes.size(); i++) {
                if (clientes.get(i).getCodCliente() == cliente.getCodCliente()) {
                    clientes.set(i, cliente);
                    break;
                }
            }
        }
        // Salva os clientes no arquivo sempre que modificar
        salvarClientesNoArquivo();
    }

    public List<Cliente> listarTodos() {
        carregarClientes(); // Garantir que a lista esteja sempre atualizada
        return new ArrayList<>(clientes);
    }

    public Cliente buscarPorCodigo(int codCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getCodCliente() == codCliente) {
                return cliente;
            }
        }
        return null;
    }

    public boolean excluir(int codCliente) {
        carregarClientes(); //garante que a lista fique atualizada
        boolean removido = clientes.removeIf(cliente -> cliente.getCodCliente() == codCliente);
        if (removido) {
            salvarClientesNoArquivo();
        }
        return removido;
    }

    private void salvarClientesNoArquivo() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(clientes);
            out.writeInt(proximoCodigo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    public void carregarClientes() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            clientes = (List<Cliente>) in.readObject();
            proximoCodigo = in.readInt();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar clientes: " + e.getMessage());
        }
    }
}