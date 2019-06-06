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
                    System.out.println("coco");
                    break;
            }
        } while (view.getOP()!=0);
        System.out.println("See ya next time, partner...");
    }
}
