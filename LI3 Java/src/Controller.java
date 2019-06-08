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
                    Crono.start();
                    System.out.println("heyo");
                    Crono.stop();
                    System.out.println(Crono.print());
                    break;
                case 2:
                    Crono.start();
                    this.view.outQuery1(this.model.query1());
                    Crono.stop();
                    System.out.println(Crono.print());
                    break;
                case 3:
                    int mes = this.view.inQuery2();
                    Crono.start();
                    this.view.outQuery2(this.model.query2Total(mes), "Geral: ");
                    this.view.outQuery2(this.model.query2Filial(mes,1), "Filial 1: ");
                    this.view.outQuery2(this.model.query2Filial(mes,2), "Filial 2: ");
                    this.view.outQuery2(this.model.query2Filial(mes,3), "Filial 3: ");
                    Crono.stop();
                    System.out.println(Crono.print());
                    break;
                case 4:
                    String cliente = this.view.inQueryCliente();
                    Crono.start();
                    this.view.outQuery3or4(this.model.query3(cliente));
                    Crono.stop();
                    System.out.println(Crono.print());
                    break;
                case 5:
                    String prod = this.view.inQueryProduto();
                    Crono.start();
                    this.view.outQuery3or4(this.model.query4(prod));
                    Crono.stop();
                    System.out.println(Crono.print());
                    break;
                case 6:
                    cliente = this.view.inQueryCliente();
                    Crono.start();
                    this.view.outQuery5(this.model.query5(cliente));
                    Crono.stop();
                    System.out.println(Crono.print());
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
