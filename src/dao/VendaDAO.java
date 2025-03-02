package dao;

import java.io.*;
import java.util.*;

public class VendaDAO {
    private static final String FILE_NAME = "registro_financeiro.dat";

    // Cria um registro de uma venda no arquivo
    public void salvarRegistros(model.Venda registro) {
        List<model.Venda> registros = listarTodos(); // Carrega os registros existentes
        registros.add(registro); // Adiciona o novo registro

        salvarLista(registros);
    }

    // Lê os registros armazenados no arquivo
    public List<model.Venda> listarTodos() {
        List<model.Venda> registros = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return registros; // Retorna uma lista vazia se o arquivo não existir
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                registros = (List<model.Venda>) obj;
            }
        } catch (EOFException e) {
            System.out.println("Arquivo vazio.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return registros;
    }

    // Edita um registro da lista
    public void editar(int codigo, model.Venda novoRegistro) {
        List<model.Venda> registros = listarTodos();
        boolean encontrado = false;

        for (int i = 0; i < registros.size(); i++) {
            if (registros.get(i).getCodVenda() == codigo) { // Comparação direta
                registros.set(i, novoRegistro);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            salvarLista(registros); // métôdo para salvar
            System.out.println("Venda atualizada com sucesso!");
        } else {
            System.out.println("Venda não encontrada!");
        }
    }

    // Exclui um registro da lista
    public void excluir(int codigo) {
        List<model.Venda> registros = listarTodos();
        boolean removido = registros.removeIf(r -> r.getCodVenda() == codigo);

        if (removido) {
            salvarLista(registros); // Usa o métôdo correto para salvar
            System.out.println("Registro financeiro removido com sucesso!");
        } else {
            System.out.println("Registro não encontrado!");
        }
    }

    // Métôdo para evitar duplicação de código e reescrever todos os registros no arquivo.
    private void salvarLista(List<model.Venda> registros) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(registros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Calcula o total de vendas do consultor por mês
    public double calcularTotalVendasPorMes(int mes, int ano) {
        List<model.Venda> registros = listarTodos();
        double total = 0;

        for (model.Venda r : registros) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(r.getDataRegistro());

            int mesRegistro = cal.get(Calendar.MONTH) + 1;
            int anoRegistro = cal.get(Calendar.YEAR);

            if (mesRegistro == mes && anoRegistro == ano) {
                total += r.getValor();
            }
        }
        return total;
    }
}