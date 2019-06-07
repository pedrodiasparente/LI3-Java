import java.util.*;

public class MainMenuView implements InterfGereVendasView{

    private int op;
    private List<String> opcoes;

    public MainMenuView(){
        String[] options = {"Ler dos Logs","query1","query2"};
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
}
