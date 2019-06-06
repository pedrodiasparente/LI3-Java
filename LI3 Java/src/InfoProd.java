import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InfoProd implements Serializable {

    private List<Integer> quantP;
    private List<Integer> quantN;
    private List<Float> precoP;
    private List<Float> precoN;

    public InfoProd () {
        this.quantP = new ArrayList<>();
        this.quantN = new ArrayList<>();
        this.precoP = new ArrayList<>();
        this.precoN = new ArrayList<>();
    }

    public InfoProd (List<Integer> quantP, List<Integer> quantN, List<Float> precoP, List<Float> precoN) {
        this.quantP = quantP;
        this.quantN = quantN;
        this.precoP = precoP;
        this.precoN = precoN;
    }
     public InfoProd (InfoProd o) {
        this.quantP = o.getQuantP();
        this.quantN = o.getQuantN();
        this.precoP = o.getPrecoP();
        this.precoN = o.getPrecoN();
     }

     public List<Integer> getQuantP() {
        ArrayList<Integer> newArray = new ArrayList<>(this.quantP);
        return newArray;
     }

     public int getQuantPMes(int i) { return this.quantP.get(i); }

     public List<Integer> getQuantN() {
        ArrayList<Integer> newArray = new ArrayList<>(this.quantN);
        return newArray;
     }

    public int getQuantNMes(int i) {
        return this.quantN.get(i);
    }

    public List<Float> getPrecoP() {
        ArrayList<Float> newArray = new ArrayList<>(this.precoP);
        return newArray;
    }

    public float getPrecoPMes(int i) {
        return this.precoP.get(i);
    }

    public List<Float> getPrecoN() {
        ArrayList<Float> newArray = new ArrayList<>(this.precoN);
        return newArray;
    }

    public float getPrecoNMes(int i) {
        return this.precoN.get(i);
    }

    public void setQuantP(List<Integer> q) {
        this.quantP = q;
    }

    public void setQuantN(List<Integer> q) {
        this.quantN = q;
    }

    public void setPrecoP(List<Float> q) {
        this.precoP = q;
    }

    public void setPrecoN(List<Float> q) {
        this.precoN = q;
    }

/*
    private void somaProdInfo(InfoProd produtoData, InfoProd newProduto){
        int i;

        for(i = 0; i < 12; i++)
            produtoData.setQuantN();
            setQuant(produtoData, i, getQuantN(newProduto, i, 'N'), 'N');
        for(i = 0; i < 12; i++)
            setQuant(produtoData, i, getQuantP(newProduto, i, 'P'), 'P');
        for(i = 0; i < 12; i++)
            setPrecoGF(produtoData, i, getPrecoGF(newProduto, i, 'N'), 'N');
        for(i = 0; i < 12; i++)
            setPrecoGF(produtoData, i, getPrecoGF(newProduto, i, 'P'), 'P');
    }
*/
    public InfoProd clone() {
        return new InfoProd(this);
    }

}
