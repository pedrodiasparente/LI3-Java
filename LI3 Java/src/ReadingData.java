import java.util.ArrayList;
import java.util.HashMap;

public class ReadingData {
    private String fileName;
    private HashMap<String, String> produtosComprados;
    private HashMap<String, String> clientesComCompra;
    private int numVendas;
    private int numVendasFalhadas;
    private int numVendasZero;
    private double fatTotal;

    public ReadingData(){
        fileName = "";
        produtosComprados = new HashMap<>();
        clientesComCompra = new HashMap<>();
        numVendas = 0;
        numVendasFalhadas = 0;
        numVendasZero = 0;
        fatTotal = 0;
    }

    public double getFatTotal() {
        return fatTotal;
    }

    public HashMap<String, String> getClientesComCompra() {
        return clientesComCompra;
    }

    public HashMap<String, String> getProdutosComprados() {
        return produtosComprados;
    }

    public int getNumVendas() {
        return numVendas;
    }

    public int getNumVendasFalhadas() {
        return numVendasFalhadas;
    }

    public int getNumVendasZero() {
        return numVendasZero;
    }

    public String getFileName() {
        return fileName;
    }

    public void setClientesComCompra(HashMap<String, String> clientesComCompra) {
        this.clientesComCompra = clientesComCompra;
    }

    public void setFatTotal(double fatTotal) {
        this.fatTotal = fatTotal;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setNumVendas(int numVendas) {
        this.numVendas = numVendas;
    }

    public void setNumVendasFalhadas(int numVendasFalhadas) {
        this.numVendasFalhadas = numVendasFalhadas;
    }

    public void setNumVendasZero(int numVendasZero) {
        this.numVendasZero = numVendasZero;
    }

    public void setProdutosComprados(HashMap<String, String> produtosComprados) {
        this.produtosComprados = produtosComprados;
    }

    public void incNumVendas(){
        this.numVendas++;
    }

    public void incNumVendasFalhadas(){
        this.numVendasFalhadas++;
    }

    public void incNumVendasZero(){
        this.numVendasZero++;
    }

    public void addFatTotal(double fat){
        this.fatTotal += fat;
    }

    public void addProdutoComprado(String produto){
        this.produtosComprados.put(produto, produto);
    }

    public void addClienteComCompra(String cliente){
        this.clientesComCompra.put(cliente,cliente);
    }
}

