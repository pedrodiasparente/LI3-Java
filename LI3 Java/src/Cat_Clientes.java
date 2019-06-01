import java.io.Serializable;
import java.util.TreeSet;

public class Cat_Clientes implements Serializable {

    private TreeSet<String> clientes;

    public Cat_Clientes() {
        this.clientes = new TreeSet<>();
    }

    public Cat_Clientes(TreeSet<String> tree){
        this.clientes = tree;
    }

    public Cat_Clientes(Cat_Clientes cat) {
        this.clientes = cat.getClientes();
    }

    public TreeSet<String> getClientes() {
        TreeSet<String> clientes = new TreeSet<>();

        for(String s : this.clientes){
            clientes.add(s);
        }
        return clientes;
    }

    public void setClientes(TreeSet<String> clientes) {
        this.clientes = new TreeSet<>();
        clientes.forEach(s -> {this.clientes.add(s);});
    }

    public void add (String value) {
        this.clientes.add(value);
    }

    public Cat_Clientes clone() {
        return new Cat_Clientes(this);
    }

    public String toString() {
        String aux = "";
        for (String elem : this.clientes) {
            aux += elem + "\n";
        }
        return aux;
    }
}
