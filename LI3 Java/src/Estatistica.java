import java.util.TreeMap;
import java.util.Map;


public class Estatistica {
    private int[] compraspMes = new int[12];
    private TreeMap<Integer, TreeMap<Integer, Double>> fat;
    private double fatTotal;
    private TreeMap<Integer,TreeMap<Integer,Integer>> clienteBuy;

    public Estatistica() {
        for (int i = 0; i < 12; i++) {
            this.compraspMes[i] = 0;
        }
        this.fat = new TreeMap<>();
        this.fatTotal = 0;
        this.clienteBuy = new TreeMap<>();
    }

    public int[] getCompraspMes() {
        int[] c = new int[12];
        for (int i = 0; i < 12; i++) {
            c[i] = this.compraspMes[i];
        }
        return c;
    }

    public TreeMap<Integer, TreeMap<Integer, Double>> getFat() {
        TreeMap<Integer, TreeMap<Integer, Double>> t = new TreeMap<>();

        for (Map.Entry<Integer, TreeMap<Integer, Double>> m : this.fat.entrySet()) {
            t.put(m.getKey(), m.getValue());
        }
        return t;
    }

    public double getFatTotal() {
        return this.fatTotal;
    }

    public TreeMap<Integer, TreeMap<Integer, Integer>> getClienteBuy() {
        TreeMap<Integer, TreeMap<Integer, Integer>> t = new TreeMap<>();

        for (Map.Entry<Integer, TreeMap<Integer, Integer>> m : this.clienteBuy.entrySet()) {
            t.put(m.getKey(), m.getValue());
        }
        return t;
    }

    public void setCompraspMes(int[] compraspMes) {
        this.compraspMes = new int[12];

        for (int i = 0; i < 12; i++) {
            this.compraspMes[i] = compraspMes[i];
        }
    }

    public void setFat(TreeMap<Integer, TreeMap<Integer, Double>> t){
        this.fat = new TreeMap<>();

        for(Map.Entry<Integer, TreeMap<Integer, Double>> map : t.entrySet()) {
            this.fat.put(map.getKey(), map.getValue());
        }
    }

    public void setClienteBuy(TreeMap<Integer, TreeMap<Integer, Integer>> t){
        this.clienteBuy = new TreeMap<>();

        for(Map.Entry<Integer, TreeMap<Integer, Integer>> map : t.entrySet()) {
            this.clienteBuy.put(map.getKey(), map.getValue());
        }
    }

    public void setFatTotal(double fatTotal) {
        this.fatTotal = fatTotal;
    }
}

