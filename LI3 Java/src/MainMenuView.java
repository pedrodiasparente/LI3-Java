import java.util.*;

public class MainMenuView implements InterfGereVendasView{

    private int op;
    private List<String> opcoes;

    public MainMenuView(){
        String[] options = {"query1","query2", "query3", "query4", "query5", "query6", "query7", "query8", "query9", "query10", "Ler dos Logs", "Save Model", "Load Model", "Query Estatistica"};
        this.opcoes = Arrays.asList(options);
        this.op = 0;
    }

    public void runMenu(){
        do {
            showMenu();
            this.op = readOP();
        } while (this.op == -1);
    }

    public void showMenu() {
        System.out.println("\nMenu:\n");
        for (int i=0; i<this.opcoes.size(); i++) {
            System.out.print(i+1);
            System.out.print(" - ");
            System.out.println(this.opcoes.get(i));
        }
        System.out.println("0 - Terminar");
    }

    public int readOP(){
        int op;
        Scanner is = new Scanner(System.in);

        System.out.print("Opção: ");
        try {
            op = is.nextInt();
        }
        catch (InputMismatchException e) { // Não foi inscrito um int
            op = -1;
        }
        if (op<0 || op>this.opcoes.size()) {
            System.out.println("Opção Inválida!!!");
            op = -1;
        }
        return op;
    }

    public int getOP() {
        return this.op;
    }

    public void outQuery1(Cat_Produtos p){
        for (String s : p.getProdutos()) {
            System.out.println(s);
        }
        System.out.println(p.getProdutos().size());
    }

    public String lerNomeFich(){
        System.out.println("Insira nome do ficheiro");
        return Input.lerString();
    }

    public int inQueryMes(){
        System.out.println("Insira mês");
        return Input.lerInt();
    }

    public int inQueryNumClientes(){
        System.out.println("Insira o número de Clientes");
        return Input.lerInt();
    }

    public int inQueryNumProdutos(){
        System.out.println("Insira o número de Produtos");
        return Input.lerInt();
    }

    public void outQuery2(AbstractMap.SimpleEntry<Integer,Integer> query2, String s){
        int query2Vendas = query2.getKey();
        int query2Clientes = query2.getValue();
        System.out.println(s + query2Vendas + " " + query2Clientes);
    }

    public String inQueryCliente(){
        System.out.println("Insira Cliente");
        return Input.lerString();
    }

    public String inQueryProduto(){
        System.out.println("Insira Produto");
        return Input.lerString();
    }

    public void outQuery3or4(TripleInt query4){
        for(int i = 1; i < 13; i++){
            System.out.println("-------- Início mês " + i + " ----------");
            System.out.println(query4.getInt1()[i-1]);
            System.out.println(query4.getInt2()[i-1]);
            System.out.println(query4.getInt3()[i-1]);
        }
    }

    public void outQuery5(TreeSet<QuantidadeString> t){
        for (QuantidadeString q: t) {
            System.out.println("Produto: " + q.getString() + " Quantidade: " + q.getQuantidade());
        }
    }

    public void outQuery6(ArrayList<AbstractMap.SimpleEntry<String, Integer>> query6){
        for(AbstractMap.SimpleEntry<String, Integer> s : query6){
            System.out.println("Produto: " + s.getKey() + " Numero de Clientes distintos: " + s.getValue());
        }
    }

    public void outQuery7(List<AbstractMap.SimpleEntry<String, Double>> l, int filial){
        System.out.println("Filial " + filial);
        for(AbstractMap.SimpleEntry<String, Double> s: l){
            System.out.println("Cliente " + s.getKey() + " Faturacao: " + s.getValue());
        }
    }

    public void outQuery8(ArrayList<String> array){
        for(String s : array){
            System.out.println(s);
        }
    }

    public void outQuery9(TreeSet<ClientQuantFat> t){
        for(ClientQuantFat s : t){
            System.out.println("Cliente: " + s.getString() + " Faturacao: " + s.getFat() + " Quantidade: " + s.getQuantidade());
        }
    }

    public void outQuery10(ArrayList<FaturacaoMesFilial> a){
        int j = 1;
        System.out.println("Mês  ||  Geral    ||   F1   ||   F2   ||   F3" );
        for(FaturacaoMesFilial f : a) {
            System.out.println("Mês " + j + " || "+  f.getFatTotal() + " || " + f.getFatFilial().get(0) + " || " + f.getFatFilial().get(1) + " || " + f.getFatFilial().get(2));
            j++;
        }
    }

    public void outLoadData(ReadingData data, int totalClientes, int totalProds){
        int prodComprados, prodNComprados, ClientCompr, ClientNCompr;
        prodComprados = data.getProdutosComprados().size();
        prodNComprados = totalProds - prodComprados;
        ClientCompr = data.getClientesComCompra().size();
        ClientNCompr = totalClientes - ClientCompr;
        System.out.println("Nome do Ficheiro: " + data.getFileName());
        System.out.println("Total de Vendas: " + data.getNumVendas());
        System.out.println("Vendas falhadas: " + data.getNumVendasFalhadas());
        System.out.println("Total de produtos: " + totalProds);
        System.out.println("Total de produtos comprados: " + prodComprados);
        System.out.println("Total de produtos não comprados: " + prodNComprados);
        System.out.println("Total de clientes: " + totalClientes);
        System.out.println("Total de clientes que compraram: " + ClientCompr);
        System.out.println("Total de clientes que não compraram: " + ClientNCompr);
        System.out.println("Vendas gratuitas: " + data.getNumVendasZero());
        System.out.println("Faturacao Total: " + data.getFatTotal());
    }

    public void outQueryEstatistica(Estatistica e) {
        System.out.println("numero de compras por mes");
        for(int i = 0; i < 12; i++){
            System.out.println("Mes " + i + "Compras: " + e.getCompraspMes()[i]);
        }
        System.out.println("Faturacao Total: " + e.getFatTotal());
        System.out.println("Faturacao por mes e filial");
        for (int i = 0; i < 12; i++){
            System.out.println("Mes " + i);
            for (int j = 0; j < 3; j++){
                System.out.println("Filial");
                System.out.println(e.getFat().get(i).get(j));
            }
        }
        for (int i = 0; i < 3; i++){
            System.out.println("Filial " + i);
            for (int j = 0; j < 12; j++){
                System.out.println(e.getClienteBuy().get(i).get(j));
            }
        }
    }
}
