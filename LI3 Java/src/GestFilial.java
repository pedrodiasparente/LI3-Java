import java.io.Serializable;
import java.util.TreeMap;

public class GestFilial implements Serializable {

    private TreeMap<String, TreeMap<String,InfoProd>> clientes;

    public GestFilial () {
        this.clientes = new TreeMap<>();
    }

    public GestFilial (TreeMap<String,TreeMap<String,InfoProd>> c){
        this.clientes = c;
    }

    public GestFilial (GestFilial o) {
        this.clientes = o.getClientes();
    }

    public TreeMap<String,TreeMap<String, InfoProd>> getClientes() {
        return this.clientes;
    }

    public void setClientes(TreeMap<String,TreeMap<String, InfoProd>> c) {
        this.clientes = c;
    }

    public void addCliente (String client, TreeMap<String,InfoProd> value) {
        this.clientes.put(client, value);
    }

    public TreeMap<String,InfoProd> getProdutos(String client) {
        return this.clientes.get(client);
    }

    public void addProduto (String cliente, String produto, InfoProd value) {
        getProdutos(cliente).put(produto, value);
    }

    public GestFilial clone(){
        return new GestFilial(this);
    }
}
