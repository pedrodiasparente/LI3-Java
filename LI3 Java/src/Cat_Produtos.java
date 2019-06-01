import java.util.TreeSet;

public class Cat_Produtos {

    private TreeSet<String> produtos;

    public Cat_Produtos() {
        this.produtos = new TreeSet<>();
    }

    public Cat_Produtos(TreeSet<String> tree){
        this.produtos = tree;
    }

    public Cat_Produtos(Cat_Produtos prod) {
        this.produtos = prod.getProdutos();
    }

    public TreeSet<String> getProdutos() {
        TreeSet<String> produtos = new TreeSet<>();

        for(String s : this.produtos){
            produtos.add(s);
        }
        return produtos;
    }

    public void setProdutos(TreeSet<String> produtos) {
        this.produtos = new TreeSet<>();
        produtos.forEach(s -> {this.produtos.add(s);});
    }

    public void add (String value) {
        this.produtos.add(value);
    }

    public Cat_Produtos clone() {
        return new Cat_Produtos(this);
    }

    public String toString() {
        String aux = "";
        for (String elem : this.produtos) {
            aux += elem + "\n";
        }
        return aux;
    }
}
