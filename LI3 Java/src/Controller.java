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
                    this.view.outQuery1(this.model.query1());
                    break;
                case 3:
                    int mes = this.view.inQuery2();
                    this.view.outQuery2(this.model.query2Total(mes), "Geral: ");
                    this.view.outQuery2(this.model.query2Filial(mes,1), "Filial 1: ");
                    this.view.outQuery2(this.model.query2Filial(mes,2), "Filial 2: ");
                    this.view.outQuery2(this.model.query2Filial(mes,3), "Filial 3: ");
                    break;
                case 4:
                    String cliente = this.view.inQuery3();
                    this.view.outQuery3or4(this.model.query3(cliente));
                    break;
                case 5:
                    String prod = this.view.inQuery4();
                    this.view.outQuery3or4(this.model.query4(prod));
                    break;
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
