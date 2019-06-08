public class ClientQuantFat extends QuantidadeString {
    private double fat;

    ClientQuantFat(){
        super();
        fat = 0;
    }

    ClientQuantFat(String p, int q, double f){
        super(p,q);
        this.fat = f;
    }

    ClientQuantFat(ClientQuantFat qp){
        super(qp);
        this.fat = qp.getFat();
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public ClientQuantFat clone() {
        return new ClientQuantFat(this);
    }

    public void addFat(double fat){
        this.fat += fat;
    }
}
