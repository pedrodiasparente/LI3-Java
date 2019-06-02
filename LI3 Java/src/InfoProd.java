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
        return this.quantP;
     }

     public List<Integer> getQuantN() {
        return this.quantN;
     }

    public List<Float> getPrecoP() {
        return this.precoP;
    }

    public List<Float> getPrecoN() {
        return this.precoN;
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

    /*no trabalho de C o getQuant tá definido de maneira diferente, como fazer?
    private void somaProdInfo(InfoProd produtoData, InfoProd newProduto){
        int i;

        for(i = 0; i < 12; i++)
            setQuant(produtoData, i, getQuant(newProduto, i, 'N'), 'N');
        for(i = 0; i < 12; i++)
            setQuant(produtoData, i, getQuant(newProduto, i, 'P'), 'P');
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
