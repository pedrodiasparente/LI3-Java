import java.io.Serializable;
import java.util.Map;
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
        TreeMap<String,TreeMap<String,InfoProd>> res = new TreeMap<>();
        for(Map.Entry<String,TreeMap<String,InfoProd>> s: this.clientes.entrySet()){
            String key = s.getKey();
            TreeMap<String,InfoProd> aux = new TreeMap<>();
            for(Map.Entry<String,InfoProd> t: this.clientes.get(key).entrySet()) {
                String key2 = t.getKey();
                InfoProd value = t.getValue().clone();
                aux.put(key2,value);
            }
            res.put(key,aux);
        }
        return res;
    }

    public void setClientes(TreeMap<String,TreeMap<String, InfoProd>> c) {
        this.clientes = c;
    }

    public void addCliente (String client, TreeMap<String,InfoProd> value) {
        this.clientes.put(client, value);
    }

    public TreeMap<String,InfoProd> getProdutos(String client) {
        TreeMap<String,InfoProd> res = new TreeMap<>();
        for(Map.Entry<String,InfoProd> s: this.clientes.get(client).entrySet()){
            String key = s.getKey();
            InfoProd value = s.getValue().clone();
            res.put(key,value);
        }
        return res;
    }

    public void addProduto (String cliente, String produto, InfoProd value) {
        getProdutos(cliente).put(produto, value.clone());
    }

    public GestFilial clone(){
        return new GestFilial(this);
    }
}
