import java.util.HashMap;
import java.util.Map;

public class FaturacaoMesFilial {
    private HashMap<String, Double> fatTotal;
    private HashMap<Integer, HashMap<String, Double>> fatFilial;

    FaturacaoMesFilial(){
        this.fatTotal = new HashMap<>();
        this.fatFilial = new HashMap<>();
    }

    public HashMap<Integer, HashMap<String, Double>> getFatFilial() {
        return fatFilial;
    }

    public HashMap<String, Double> getFatTotal() {
        return fatTotal;
    }

    public void addFatFilial(String prod, double fat, int filial){
        double oldFat;
        if(fatTotal.containsKey(prod)) {
            oldFat = fatTotal.get(prod);
            fatTotal.put(prod, oldFat + fat);
        } else{
            fatTotal.put(prod, fat);
        }
        if(fatFilial.get(filial).containsKey(prod)) {
            oldFat = fatFilial.get(filial).get(prod);
            fatFilial.get(filial).put(prod, oldFat + fat);
        } else{
            fatFilial.get(filial).put(prod, fat);
        }
    }
}
