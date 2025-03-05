package dao;

import model.Produto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Set;

public class ProdutoDAO {
    private File arquivo;

    public ProdutoDAO() {
        arquivo = new File("Produtos");
        if(!arquivo.exists()){
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Set<Produto> getProdutos() throws IOException,
            ClassNotFoundException {
        if(arquivo.length()==0){
            return new HashSet<>();
        }
        try(ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(arquivo))){
            return (Set<Produto>) in.readObject();
        }
    }
}
