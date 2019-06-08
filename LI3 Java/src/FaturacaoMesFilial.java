import java.util.HashMap;
import java.util.Map;

public class FaturacaoMesFilial {
    private double fatTotal;
    private HashMap<Integer, Double> fatFilial;

    FaturacaoMesFilial(){
        this.fatTotal = 0;
        this.fatFilial = new HashMap<>();
    }

    public HashMap<Integer, Double> getFatFilial() {
        return fatFilial;
    }

    public double getFatTotal() {
        return fatTotal;
    }

    public void addFatFilial(double fat, int filial){
        double oldFat;
        this.fatTotal += fat;
        oldFat = fatFilial.get(filial);
        fatFilial.put(filial,oldFat + fat);
    }
}
