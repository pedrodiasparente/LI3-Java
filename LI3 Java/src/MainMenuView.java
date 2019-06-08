import java.util.*;

public class MainMenuView implements InterfGereVendasView{

    private int op;
    private List<String> opcoes;

    public MainMenuView(){
        String[] options = {"Ler dos Logs","query1","query2", "query3", "query4", "query5"};
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

    public int inQuery2(){
        System.out.println("Insira mês");
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
            System.out.println(query4.getInt1()[i-1]);
            System.out.println(query4.getInt2()[i-1]);
            System.out.println(query4.getInt3()[i-1]);
            System.out.println("-------- Fim mês " + i + " ----------");
        }
    }

    public void outQuery5(TreeSet<QuantidadeProduto> t){
        for (QuantidadeProduto q: t) {
            System.out.println("Produto: " + q.getProduto() + " Quantidade: " + q.getQuantidade());
        }
    }
}
