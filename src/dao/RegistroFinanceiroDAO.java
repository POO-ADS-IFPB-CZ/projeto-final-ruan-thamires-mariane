package dao;

import model.RegistroFinanceiro;

import java.io.*;
import java.util.*;

public class RegistroFinanceiroDAO {
    private static final String FILE_NAME = "registro_financeiro.dat";

    //  Cria um registro financeiro no arquivo
    public void salvarRegistros(RegistroFinanceiro registro) {
        List<RegistroFinanceiro> registros = listarTodos(); // Carrega os registros existentes
        registros.add(registro); // Adiciona o novo registro

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(registros); // Sobrescreve o arquivo com a lista atualizada
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //  Lê os registros armazenados no arquivo
    public List<RegistroFinanceiro> listarTodos() {
        List<RegistroFinanceiro> registros = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return registros; // Retorna lista vazia se o arquivo não existir ainda
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            registros = (List<RegistroFinanceiro>) ois.readObject();
        } catch (EOFException e) {
            System.out.println("Arquivo vazio.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return registros;
    }

    public void editar(int codigo, RegistroFinanceiro novoRegistro) {
        List<RegistroFinanceiro> registros = listarTodos();
        boolean encontrado = false;

        for (int i = 0; i < registros.size(); i++) {
            if (registros.get(i).getCodigo() == codigo) { // Comparação direta
                registros.set(i, novoRegistro);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            salvarRegistros((RegistroFinanceiro) registros);
            System.out.println("Registro financeiro atualizado com sucesso!");
        } else {
            System.out.println("Registro não encontrado!");
        }
    }


//    Para evitar duplicação de código criei um métôdo que reescreve todos os registros no arquivo.
    private void salvarLista(List<RegistroFinanceiro> registros) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(registros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void excluir(int codigo) {
        List<RegistroFinanceiro> registros = listarTodos();
        boolean removido = registros.removeIf(r -> r.getCodigo() == codigo);

        if (removido) {
            salvarRegistros((RegistroFinanceiro) registros);
            System.out.println("Registro financeiro removido com sucesso!");
        } else {
            System.out.println("Registro não encontrado!");
        }
    }

    public double calcularTotalVendasPorMes(int mes, int ano) {
        List<RegistroFinanceiro> registros = listarTodos();
        double total = 0;

        for (RegistroFinanceiro r : registros) {
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
