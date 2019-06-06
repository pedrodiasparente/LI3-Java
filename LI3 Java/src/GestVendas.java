import java.io.Serializable;

public class GestVendas implements Serializable {
    public static void main(String[] args) {
        Model model = new Model();
        model.loadData();
        MainMenuView view = new MainMenuView();
        Controller control = new Controller();

        control.setModel(model);
        control.setView(view);
        control.start();
        System.exit(0);
    }
}