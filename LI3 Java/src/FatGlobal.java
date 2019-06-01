import java.util.TreeMap;

public class FatGlobal {

    private TreeMap<String, Faturacao> fatGlobal;

    public FatGlobal() {
        this.fatGlobal = new TreeMap<>();
    }

    public FatGlobal(TreeMap<String, Faturacao> tree){
        this.fatGlobal = tree;
    }

/*precisa do get
    public FatGlobal(FatGlobal fatGlobal) {
        this.fatGlobal = fatGlobal.getFatGlobal();
    }

Map cannot be resolved
    public TreeMap<String, Faturacao> getFatGlobal() {
        TreeMap<String, Faturacao> fatGlobal = new TreeMap<>();

        for(Map.Entry<String, Faturacao> entry : fatGlobal.entrySet()){
            String key = entry.getKey();
            Faturacao fat = new Faturacao(entry.getValue());

            fatGlobal.add(key, fat);
        }
        return fatGlobal;
    }

//idk if work
    public void setFatGlobal(TreeMap<String, Faturacao> fatGlobal) {
        this.fatGlobal = new TreeMap<>();
        fatGlobal.forEach(s -> {this.fatGlobal.add(s);});
    }
*/
    public Faturacao getFaturacao(String key) {
        Faturacao fat = this.fatGlobal.get(key);
        return fat;
    }

    public void add (String key, Faturacao value) {
        this.fatGlobal.put(key,value);
    }

/*precisa do construtor
    public FatGlobal clone() {
        return new FatGlobal(this);
    }
*/
}

