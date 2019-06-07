import java.io.*;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;
import java.util.TreeSet;

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

    public Cat_Produtos query1() {
        int val;
        Cat_Produtos p = new Cat_Produtos();
        TreeSet<String> t = new TreeSet<>();
        for(Map.Entry<String,Faturacao> m : this.fatGlobal.getFatGlobal().entrySet()) {
            val = 0;
            //nuno isto devia funcionar mas nao está
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 12; j++) {
                    if (m.getValue().getnVendasP()[i][j] != 0) val = 1;
                    if (m.getValue().getnVendasN()[i][j] != 0) val = 1;
                }
            }
            if (val == 0) {
                t.add(m.getKey());

            }
            else ;//System.out.println(m.getKey());
        }
        p.setProdutos(t);
        return p;
    }

    public AbstractMap.SimpleEntry<Integer,Integer> query2Filial(int mes, int filial){
        int quantVendas = 0;
        int quantClientes = 0;
        int trigger = 0;
        for(Map.Entry<String,Faturacao> m : this.fatGlobal.getFatGlobal().entrySet()){
            quantVendas += m.getValue().getnVendasP()[filial-1][mes-1];
            quantVendas += m.getValue().getnVendasN()[filial-1][mes-1];
        }
        for(Map.Entry<String,TreeMap<String,InfoProd>> m : this.gestFilial.get(filial-1).getClientes().entrySet()){
            for(Map.Entry<String,InfoProd> t : m.getValue().entrySet()) {
                if(t.getValue().getQuantPMes(mes) != 0 && trigger == 0) {
                    quantClientes ++;
                    trigger = 1;
                    break;
                }
                if(t.getValue().getQuantNMes(mes) != 0 && trigger == 0) {
                    quantClientes ++;
                    trigger = 1;
                    break;
                }
                if(trigger == 1) break;
            }
            trigger = 0;
        }
        AbstractMap.SimpleEntry<Integer,Integer> par = new AbstractMap.SimpleEntry<>(quantVendas,quantClientes);
        return par;
    }

    public AbstractMap.SimpleEntry<Integer,Integer> query2Total(int mes){
        int quantVendas = 0;
        int quantClientes;
        Cat_Clientes c = new Cat_Clientes();
        int trigger = 0;
        for(Map.Entry<String,Faturacao> m : this.fatGlobal.getFatGlobal().entrySet()){
            for(int i = 0; i < 3; i++) {
                quantVendas += m.getValue().getnVendasP()[i][mes - 1];
                quantVendas += m.getValue().getnVendasN()[i][mes - 1];
            }
        }
        for (int i = 0; i < 3; i++) {
            for (Map.Entry<String, TreeMap<String, InfoProd>> m : this.gestFilial.get(i).getClientes().entrySet()) {
                String key = m.getKey();
                for (Map.Entry<String, InfoProd> t : m.getValue().entrySet()) {
                    if (t.getValue().getQuantPMes(mes) != 0 && trigger == 0) {
                        c.add(key);
                        trigger = 1;
                        break;
                    }
                    if (t.getValue().getQuantNMes(mes) != 0 && trigger == 0) {
                        c.add(key);
                        trigger = 1;
                        break;
                    }
                    if (trigger == 1) break;
                }
                trigger = 0;
            }
        }
        quantClientes = c.getClientes().size();
        return new AbstractMap.SimpleEntry<>(quantVendas,quantClientes);
    }
}

