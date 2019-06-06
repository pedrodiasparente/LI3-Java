import java.util.ArrayList;

public class Model implements InterfGereVendasModel{
    private Cat_Produtos produtos;
    private Cat_Clientes clientes;
    private ArrayList<GestFilial> gestFilial;
    private FatGlobal fatGlobal;

    public Model() {
        this.produtos = new Cat_Produtos();
        this.clientes = new Cat_Clientes();
        this.gestFilial = new ArrayList<>();
        this.fatGlobal = new FatGlobal();
    }

    public Model(Cat_Produtos prod, Cat_Clientes cli, ArrayList<GestFilial> gfs, FatGlobal fg) {
        this.produtos = prod;
        this.clientes = cli;
        this.gestFilial = new ArrayList<>();
        for(GestFilial f : gfs) {
            this.gestFilial.add(f.clone());
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

    public ArrayList<GestFilial> getGestFilial() {
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

    public void setGestFilial(ArrayList<GestFilial> gestFilial) {
        this.gestFilial = new ArrayList<>();
        for(GestFilial f : gestFilial){
            this.gestFilial.add(f);
        }
    }

    public void setFatGlobal(FatGlobal fatGlobal) {
        this.fatGlobal = fatGlobal;
    }

    public Model clone() {
        return new Model(this);
    }

    public void loadData() {

    }
}

