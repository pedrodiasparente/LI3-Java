import java.io.*;
import java.util.*;

public class Model implements Serializable, InterfGereVendasModel{
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

    private ReadingData loadVendas(String fich){
        String vendaString;
        String[] venda;
        int filial;
        ReadingData readData = new ReadingData();
        readData.setFileName(fich);
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
                    readData.incNumVendas();
                    if(Double.parseDouble(venda[1]) == 0) readData.incNumVendasZero();
                    else readData.addFatTotal(Double.parseDouble(venda[1]));
                    readData.addClienteComCompra(venda[4]);
                    readData.addProdutoComprado(venda[0]);
                } else{
                    readData.incNumVendasFalhadas();
                }
            }
            br.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        return readData;
    }

    public ReadingData loadData(){
        String fileClientes, fileProdutos, fileVendas;
        int numFilial;
        ReadingData readData = new ReadingData();

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
            readData = this.loadVendas(fileVendas);

        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        return readData;
    }

    public void saveModel(String fich){
        if (fich.equals("")) fich = "gestVendas.dat";
        try{
            ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(fich));
            oout.writeObject(this);
            oout.flush();
            oout.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Model loadModel(String fich){
        Model s = new Model();
        if (fich.equals("")) fich = "gestVendas.dat";
        try {
            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(fich));
            s = (Model) oin.readObject();
            oin.close();
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch(IOException e){
            System.out.println(e.getMessage());
        } catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return s;
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

    public TripleInt query3(String client){
        TripleInt res = new TripleInt();
        int[] triggers = new int[12];
        for (int i = 0; i < 3; i++) {
            for (Map.Entry<String, InfoProd> m : this.gestFilial.get(i).getClientes().get(client).entrySet()) {
                Arrays.fill(triggers,1);
                res.setInt1(res.addArraysInt(res.getInt1(),m.getValue().getNVendasP()));
                res.setInt1(res.addArraysInt(res.getInt1(),m.getValue().getNVendasN()));
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

    public TreeSet<QuantidadeString> query5(String cliente){
        TreeSet<QuantidadeString> ret = new TreeSet<>(new QuantidadeComparator());
        TreeMap<String, QuantidadeString> prods = new TreeMap<>();
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
                    prods.put(entry.getKey(), new QuantidadeString(entry.getKey(), quantTotal));
                }
            }
        }
        for (QuantidadeString qp : prods.values()){
            ret.add(qp.clone());
        }

        return ret;
    }

    public ArrayList<AbstractMap.SimpleEntry<String,Integer>> query6(int x) {
        Set<QuantidadeString> lista = new TreeSet<>(new QuantidadeComparator());
        ArrayList<AbstractMap.SimpleEntry<String,Integer>> ret = new ArrayList<>();
        int quant;
        for (Map.Entry<String, Faturacao> e : this.fatGlobal.getFatGlobal().entrySet()) {
            quant = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 12; j++) {
                    quant += e.getValue().getQuantP()[i][j] + e.getValue().getQuantN()[i][j];
                }
            }
            lista.add(new QuantidadeString(e.getKey(),quant));
        }

        int totClientes;
        int k = 0;
        for(QuantidadeString n : lista){
            totClientes = 0;
            for(int i = 0; i < 3; i++){
                for (Map.Entry<String, TreeMap<String, InfoProd>> m : this.gestFilial.get(i).getClientes().entrySet()) {
                    if(m.getValue().containsKey(n.getString())){
                        totClientes++;
                    }
                }
            }
            if (k == x) break;
            else {
                ret.add(new AbstractMap.SimpleEntry<>(n.getString(),totClientes));
                k++;
            }
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
        TreeMap<String, HashMap<String, String>> clientesProdutos = new TreeMap<>();
        TreeMap<QuantidadeString, String> clientesPorProduto = new TreeMap<>(new QuantidadeComparator());
        int i, total = 0;

        for(String c : this.clientes.getClientes()){
            clientesProdutos.put(c, new HashMap<>());
        }

        for(GestFilial g: this.gestFilial.values()){
            for(Map.Entry<String, TreeMap<String, InfoProd>> entry : g.getClientes().entrySet()){
                for(String prod : entry.getValue().keySet()) {
                    clientesProdutos.get(entry.getKey()).put(prod,prod);
                }
            }
        }

        for(Map.Entry<String, HashMap<String, String>> entry : clientesProdutos.entrySet()){
            clientesPorProduto.put(new QuantidadeString(entry.getKey(), entry.getValue().size()), entry.getKey());
        }

        i = x;
        for(String cliente : clientesPorProduto.values()){
            if(i == 0) break;
            ret.add(cliente);
            i = i-1;
        }

        return ret;
    }

    public TreeSet<ClientQuantFat> query9(String produto){
        TreeSet<ClientQuantFat> ret = new TreeSet<>(new QuantidadeComparator());
        TreeMap<String, ClientQuantFat> prods = new TreeMap<>();
        int quantTotal;
        double fatTotal;

        for(GestFilial g : this.gestFilial.values()){
            for(Map.Entry<String,TreeMap<String,InfoProd>> m : g.getClientes().entrySet()) {
                if(m.getValue().containsKey(produto)){
                    quantTotal = 0;
                    fatTotal = 0;
                    for (double i : m.getValue().get(produto).getQuantP()) {
                        quantTotal += i;
                    }
                    for (double i : m.getValue().get(produto).getQuantN()) {
                        quantTotal += i;
                    }
                    for (double i : m.getValue().get(produto).getPrecoP()) {
                        fatTotal += i;
                    }
                    for (double i : m.getValue().get(produto).getPrecoN()) {
                        fatTotal += i;
                    }
                    if (prods.containsKey(m.getKey())) {
                        prods.get(m.getKey()).addQuantidade(quantTotal);
                        prods.get(m.getKey()).addFat(fatTotal);
                    } else {
                        prods.put(m.getKey(), new ClientQuantFat(m.getKey(), quantTotal, fatTotal));
                    }
                }
            }
        }
        for (ClientQuantFat cqf : prods.values()){
            ret.add(cqf);
        }

        return ret;
    }

    public ArrayList<FaturacaoMesFilial> query10(String prod){
        ArrayList<FaturacaoMesFilial> ret = new ArrayList<>();
        int mes;
        double fat;

        for (mes = 0; mes < 12; mes++){
            ret.add(new FaturacaoMesFilial());
        }

        for(Map.Entry<Integer, GestFilial> filial : this.gestFilial.entrySet()){
            for(mes = 0; mes < 12; mes++){
                ret.get(mes).getFatFilial().put(filial.getKey(), 0.0);
            }
            for(TreeMap<String, InfoProd> treeProds :  filial.getValue().getClientes().values()){
                if(treeProds.containsKey(prod)){
                    for (mes = 0; mes < 12; mes++) {
                        fat = treeProds.get(prod).getPrecoPMes(mes) + treeProds.get(prod).getPrecoNMes(mes);
                        ret.get(mes).addFatFilial(fat, filial.getKey());
                    }
                }
            }
        }

        return ret;
    }
}

