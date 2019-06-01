import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class FatGlobal implements Serializable {

    private TreeMap<String, Faturacao> fatGlobal;

    public FatGlobal() {
        this.fatGlobal = new TreeMap<>();
    }

    public FatGlobal(TreeMap<String, Faturacao> tree){
        this.fatGlobal = tree;
    }

    public FatGlobal(FatGlobal fatGlobal) {
        this.fatGlobal = fatGlobal.getFatGlobal();
    }

    public TreeMap<String, Faturacao> getFatGlobal() {
        TreeMap<String, Faturacao> fatGlobal = new TreeMap<>();

        for(Map.Entry<String, Faturacao> entry : this.fatGlobal.entrySet()){
            String key = entry.getKey();
            Faturacao fat = new Faturacao(entry.getValue());

            fatGlobal.put(key, fat);
        }
        return fatGlobal;
    }

    public void setFatGlobal(TreeMap<String, Faturacao> fatGlobal) {
        this.fatGlobal = new TreeMap<>();
        for(Map.Entry<String, Faturacao> entry : fatGlobal.entrySet()){
            String key = entry.getKey();
            Faturacao fat = new Faturacao(entry.getValue());

            this.fatGlobal.put(key, fat);
        }
    }

    public Faturacao getFaturacao(String key) {
        Faturacao fat = this.fatGlobal.get(key);
        return fat;
    }

    public void add (String key, Faturacao value) {
        this.fatGlobal.put(key,value);
    }

    public FatGlobal clone() {
        return new FatGlobal(this);
    }
}

