import java.io.*;
import java.util.*;

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
        String[] venda;

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
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 12; j++) {
                    if (m.getValue().getnVendasP()[i][j] != 0) val = 1;
                    if (m.getValue().getnVendasN()[i][j] != 0) val = 1;
                }
            }
            if (val == 0) {
                t.add(m.getKey());
            }
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
                if(t.getValue().getQuantPMes(mes-1) != 0 && trigger == 0) {
                    quantClientes ++;
                    trigger = 1;
                    break;
                }
                if(t.getValue().getQuantNMes(mes-1) != 0 && trigger == 0) {
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
        int trigger;
        for(Map.Entry<String,Faturacao> m : this.fatGlobal.getFatGlobal().entrySet()){
            for(int i = 0; i < 3; i++) {
                quantVendas += m.getValue().getnVendasP()[i][mes - 1];
                quantVendas += m.getValue().getnVendasN()[i][mes - 1];
            }
        }
        for (int i = 0; i < 3; i++) {
            for (Map.Entry<String, TreeMap<String, InfoProd>> m : this.gestFilial.get(i).getClientes().entrySet()) {
                trigger = 1;
                String key = m.getKey();
                for (Map.Entry<String, InfoProd> t : m.getValue().entrySet()) {
                    if (t.getValue().getQuantPMes(mes-1) != 0 && trigger == 1) {
                        c.add(key);
                        trigger = 0;
                        break;
                    }
                    if (t.getValue().getQuantNMes(mes-1) != 0 && trigger == 1) {
                        c.add(key);
                        trigger = 0;
                        break;
                    }
                    if (trigger == 0) break;
                }
            }
        }
        quantClientes = c.getClientes().size();
        return new AbstractMap.SimpleEntry<>(quantVendas,quantClientes);
    }

    public TripleInt query3(String client){ // Há um pequeno problema nesta query no número de vendas, se comprar duas vezes o mesmo produto no mesmo mês só vai contar com uma comprar, quando devia contar duas
        //Isto é porque ele só percorre o array das quantidades e soma um por cada != 0, mas deveria era haver mais um array na gestFilial que dê o numero de vendas
        TripleInt res = new TripleInt();
        int[] triggers = new int[12];
        for (int i = 0; i < 3; i++) {
            for (Map.Entry<String, InfoProd> m : this.gestFilial.get(i).getClientes().get(client).entrySet()) {
                Arrays.fill(triggers,1);
                for (int k = 0; k < 12; k++){
                    if (m.getValue().getQuantNMes(k) != 0) {
                        res.addInt1Mes(k);
                    }
                    if (m.getValue().getQuantPMes(k) != 0) {
                        res.addInt1Mes(k);
                    }
                }
                res.setInt3(res.addArraysDouble(res.getInt3(),m.getValue().getPrecoP()));
                res.setInt3(res.addArraysDouble(res.getInt3(),m.getValue().getPrecoN()));
                for (int k = 0; k < 12; k++){
                    if (m.getValue().getQuantNMes(k) != 0 && triggers[k] == 1) {
                        res.addInt2Mes(k);
                        triggers[k] = 0;
                    }
                    if (m.getValue().getQuantPMes(k) != 0 && triggers[k] == 1) {
                        res.addInt2Mes(k);
                        triggers[k] = 0;
                    }
                }
            }
        }

        return res;
    }

    public TripleInt query4(String produto) {
        TripleInt res = new TripleInt();
        Faturacao f = this.fatGlobal.getFatGlobal().get(produto).clone();

        res.setInt1(res.addArraysMatInt(res.getInt1(), f.getnVendasP(), f.getnVendasN()));

        res.setInt3(res.addArraysMatInt(res.getInt3(), f.getPrecoTotalP(), f.getPrecoTotalN()));
        for(int k = 0; k < 3;k++) {
            for (Map.Entry<String, TreeMap<String, InfoProd>> m : this.gestFilial.get(k).getClientes().entrySet()) {
                for (int i = 0; i < 12; i++) {
                    try {
                        if (m.getValue().get(produto).getQuantN()[i] != 0) {
                            res.addInt2Mes(i);
                        }

                        if (m.getValue().get(produto).getQuantP()[i] != 0) {
                            res.addInt2Mes(i);
                        }
                    }
                    catch (NullPointerException e) {
                    }
                }
            }
        }

        return res;
    }

    public TreeSet<QuantidadeProduto> query5(String cliente){
        TreeSet<QuantidadeProduto> ret = new TreeSet<>(new QuantidadeComparator());
        TreeMap<String, QuantidadeProduto> prods = new TreeMap<>();
        int quantTotal;


        for(GestFilial g : this.gestFilial.values()){
            for(Map.Entry<String, InfoProd> entry  : g.getClientes().get(cliente).entrySet()){
                quantTotal = 0;
                for(int i : entry.getValue().getQuantP()){
                    quantTotal += i;
                }
                for(int i : entry.getValue().getQuantN()){
                    quantTotal += i;
                }
                if(prods.containsKey(entry.getKey())){
                    prods.get(entry.getKey()).addQuantidade(quantTotal);
                } else{
                    prods.put(entry.getKey(), new QuantidadeProduto(entry.getKey(), quantTotal));
                }
            }
        }
        for (QuantidadeProduto qp : prods.values()){
            ret.add(qp.clone());
        }

        return ret;
    }

    public List<AbstractMap.SimpleEntry<String, Double>> query7(int filial) {
        double fat = 0;
        List<AbstractMap.SimpleEntry<String, Double>> l = new ArrayList<>();
        Map<Double, String> lista = new TreeMap<>(new CompareDouble());
        for (Map.Entry<String, TreeMap<String, InfoProd>> m : this.gestFilial.get(filial).getClientes().entrySet()) {
            for (Map.Entry<String, InfoProd> t : m.getValue().entrySet()) {
                for (int i = 0; i < 12; i++) {
                    fat += t.getValue().getPrecoP()[i] + t.getValue().getPrecoN()[i];
                }
            }
            lista.put(fat, m.getKey());
            fat = 0;
        }

        int j = 0;
        for (Map.Entry<Double,String> aux: lista.entrySet()) {
            if (j==3) break;
            AbstractMap.SimpleEntry<String,Double> par = new AbstractMap.SimpleEntry<>(aux.getValue(),aux.getKey());
            l.add(par);
            j++;
        }

        return l;

    }

    public ArrayList<String> query8(int x){
        ArrayList<String> ret = new ArrayList<>();
        HashMap<String,String> prodsComprados = new HashMap<>();
        TreeMap<Integer, String> clientesPorProduto = new TreeMap<>(Collections.reverseOrder());

        for(String cliente : this.clientes.getClientes()) {
            for (GestFilial g : this.gestFilial.values()) {
                for(String produto: g.getClientes().get(cliente).keySet()){
                    prodsComprados.put(produto, produto);
                }
            }
            clientesPorProduto.put(prodsComprados.size(), cliente);
        }

        for(String cliente : clientesPorProduto.values()){
            if(x == 0) break;
            ret.add(cliente);
            x--;
        }

        return ret;
    }
}

