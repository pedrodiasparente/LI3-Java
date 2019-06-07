import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class Controller implements InterfGereVendasControlador{

    private Model model;
    private MainMenuView view;

    public Controller () {
        this.model = new Model();
        this.view = new MainMenuView();
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void start(){
        do{
            this.view.runMenu();
            switch (view.getOP()){
                case 1:
                    System.out.println("heyo");
                    break;
                case 2:
                    //nuno nao sei como é para fazer sobre onde imprimir as coisas
                    Cat_Produtos p = new Cat_Produtos(this.model.query1());
                    Iterator<String> it = p.getProdutos().iterator();

                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }
                    System.out.println(p.getProdutos().size());
                    break;
                case 3:
                    System.out.println("Insira mês");
                    Scanner is = new Scanner(System.in);
                    int mes = is.nextInt();
                    int query2Vendas1 = this.model.query2Filial(mes,1).getKey();
                    int query2Clientes1 = this.model.query2Filial(mes,1).getValue();
                    int query2Vendas2 = this.model.query2Filial(mes,2).getKey();
                    int query2Clientes2 = this.model.query2Filial(mes,2).getValue();
                    int query2Vendas3 = this.model.query2Filial(mes,3).getKey();
                    int query2Clientes3 = this.model.query2Filial(mes,3).getValue();
                    int query2TotalVendas = this.model.query2Total(mes).getKey();
                    int query2TotalClientes = this.model.query2Total(mes).getValue();
                    System.out.println("Geral: " + query2TotalVendas + " " + query2TotalClientes);
                    System.out.println("Filial 1: " + query2Vendas1 + " " + query2Clientes1);
                    System.out.println("Filial 2: " + query2Vendas2 + " " + query2Clientes2);
                    System.out.println("Filial 3: " + query2Vendas3 + " " + query2Clientes3);
            }
        } while (view.getOP()!=0);
        System.out.println("See ya next time, partner...");
    }

    public void saveController(String fich){
        try{
            ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(fich));
            oout.writeObject(this);
            oout.flush();
            oout.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Controller readEstado(String fich){
        Controller s = new Controller();
        try {
            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(fich));
            s = (Controller) oin.readObject();
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
}
