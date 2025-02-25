import view.ClienteView;
import view.ProdutoView;

public class App {
    public static void main(String[] args) {

        ProdutoView telaProduto = new ProdutoView();
        telaProduto.setSize(300,300);
        telaProduto.setLocationRelativeTo(null);
        telaProduto.setVisible(true);

        ClienteView telaCliente = new ClienteView();
        telaProduto.setVisible(false);
        telaCliente.setSize(300,300);
        telaCliente.setLocationRelativeTo(null);
        telaCliente.setVisible(true);
        System.exit(0);

    }
}