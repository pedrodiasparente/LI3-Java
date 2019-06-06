import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Model implements InterfGereVendasModel{
    private Cat_Produtos produtos;
    private Cat_Clientes clientes;
    private Map<Integer, GestFilial> gestFilial;
    private FatGlobal fatGlobal;

    public Model() {
        this.produtos = new Cat_Produtos();
        this.clientes = new Cat_Clientes();
        this.gestFilial = new HashMap<>();
        this.fatGlobal = new FatGlobal();
    }

    public Model(Cat_Produtos prod, Cat_Clientes cli, HashMap<Integer, GestFilial> gfs, FatGlobal fg) {
        this.produtos = prod;
        this.clientes = cli;
        this.gestFilial = new HashMap<>();
        for(Map.Entry<Integer, GestFilial> entry : gfs.entrySet()){
            int key = entry.getKey();
            GestFilial gest = new GestFilial(entry.getValue().clone());
            this.gestFilial.put(key, gest);
        }
        this.fatGlobal = fg;
    }

    public Model(Model o) {
        this.produtos = o.getProdutos();
        this.clientes = o.getClientes();
        this.gestFilial = o.getGestFilial();
        this.fatGlobal = o.getFatGlobal();
    }

    public Cat_Produtos getProdutos() {
        return produtos;
    }

    public Cat_Clientes getClientes() {
        return clientes;
    }

    public Map<Integer,GestFilial> getGestFilial() {
        return gestFilial;
    }

    public FatGlobal getFatGlobal() {
        return fatGlobal;
    }

    public void setProdutos(Cat_Produtos produtos) {
        this.produtos = produtos;
    }

    public void setClientes(Cat_Clientes clientes) {
        this.clientes = clientes;
    }

    public void setGestFilial(HashMap<Integer, GestFilial> gfs) {
        this.gestFilial = new HashMap<>();
        for(Map.Entry<Integer, GestFilial> entry : gfs.entrySet()){
            int key = entry.getKey();
            GestFilial gest = new GestFilial(entry.getValue().clone());
            this.gestFilial.put(key, gest);
        }
    }

    public void setFatGlobal(FatGlobal fatGlobal) {
        this.fatGlobal = fatGlobal;
    }

    public Model clone() {
        return new Model(this);
    }

    private void loadClientes(String fich){
        String cliente;
        try{
            BufferedReader br = new BufferedReader(new FileReader(fich));
            while((cliente = br.readLine()) != null){
                this.clientes.add(cliente);
                for(GestFilial g : this.gestFilial.values()){
                    g.addCliente(cliente);
                }
            }
            br.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadProdutos(String fich){
        String produto;
        Faturacao fat = new Faturacao();
        try{
            BufferedReader br = new BufferedReader(new FileReader(fich));
            while((produto = br.readLine()) != null){
                this.produtos.add(produto);
                this.fatGlobal.add(produto, fat);
            }
            br.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean valVenda(String vendaString){
        boolean val;
        String venda[];

        venda = vendaString.split(" ");

        val = true;

        if(!this.produtos.lookup(venda[0])) val = false;

        if (Double.parseDouble(venda[1]) < 0 || Double.parseDouble(venda[1]) >= 1000.0) val = false;

        if (Integer.parseInt(venda[2]) < 1 || Integer.parseInt(venda[2]) > 200) val = false;

        if (!venda[3].equals("N") && !venda[3].equals("P")) val = false;

        if(!this.clientes.lookup(venda[4])) val = false;

        if (Integer.parseInt(venda[5]) < 1 || Integer.parseInt(venda[5]) > 12) val = false;

        if (Integer.parseInt(venda[6]) < 1 || Integer.parseInt(venda[6]) > 3) val = false;

        return val;
    }

    private void loadVendas(String fich){
        String vendaString;
        String[] venda;
        int filial;
        try{

            BufferedReader br = new BufferedReader(new FileReader(fich));
            while((vendaString = br.readLine()) != null){
                if(this.valVenda(vendaString)){
                    venda = vendaString.split(" ");
                    filial = Integer.parseInt(venda[6]) - 1;
                    this.fatGlobal.addVenda(vendaString);
                    if(!this.gestFilial.containsKey(filial))
                        this.gestFilial.put(filial, new GestFilial());
                    this.gestFilial.get(filial).addVenda(vendaString);
                }
            }
            br.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadData(){
        String fileClientes, fileProdutos, fileVendas;
        int numFilial;

        try{
            BufferedReader br = new BufferedReader(new FileReader("loadConfig.txt"));
            fileClientes = br.readLine();
            fileProdutos = br.readLine();
            fileVendas = br.readLine();
            numFilial = Integer.parseInt(br.readLine());
            br.close();
            for(int i = 0; i < numFilial; i++)
                    this.gestFilial.put(i, new GestFilial());
            this.loadClientes(fileClientes);
            this.loadProdutos(fileProdutos);
            this.loadVendas(fileVendas);

            System.out.println(this.fatGlobal.getFatGlobal().size());
            System.out.println(this.produtos.getProdutos().size());
            System.out.println(this.clientes.getClientes().size());
            System.out.println(this.gestFilial.get(1).getClientes().size());


        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

