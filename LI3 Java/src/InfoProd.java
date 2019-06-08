import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoProd implements Serializable {

    private int[] quantP;
    private int[] quantN;
    private double[] precoP;
    private double[] precoN;

    public InfoProd () {
        this.quantP = new int[12];
        this.quantN = new int[12];
        this.precoP = new double[12];
        this.precoN = new double[12];
    }

    public InfoProd (int[] quantP, int[] quantN, double[] precoP, double[] precoN) {
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

     public int[] getQuantP() {
        int[] r = new int[12];
        for (int i = 0; i < 12; i++){
            r[i] = this.quantP[i];
        }
        return r;
     }

     public int getQuantPMes(int i) { return this.quantP[i]; }

     public int[] getQuantN() {
         int[] r = new int[12];
         for (int i = 0; i < 12; i++){
             r[i] = this.quantN[i];
         }
         return r;
     }

    public int getQuantNMes(int i) {
        return this.quantN[i];
    }

    public double[] getPrecoP() {
        double[] r = new double[12];
        for (int i = 0; i < 12; i++){
            r[i] = this.precoP[i];
        }
        return r;
    }

    public double getPrecoPMes(int i) {
        return this.precoP[i];
    }

    public double[] getPrecoN() {
        double[] r = new double[12];
        for (int i = 0; i < 12; i++){
            r[i] = this.precoN[i];
        }
        return r;
    }

    public double getPrecoNMes(int i) {
        return this.precoN[i];
    }

    public void setQuantP(int[] q) {
        for (int i = 0; i < 12; i++){
            this.quantP[i] = q[i];
        }
    }

    public void setQuantN(int[] q) {
        for (int i = 0; i < 12; i++){
            this.quantN[i] = q[i];
        }
    }

    public void setPrecoP(double[] q) {
        for (int i = 0; i < 12; i++){
            this.precoP[i] = q[i];
        }
    }

    public void setPrecoN(double[] q) {
        for (int i = 0; i < 12; i++){
            this.precoN[i] = q[i];
        }
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

    public void addVendaToProd(String vendaString){
        String venda[];
        int mes;

        venda = vendaString.split(" ");
        mes = Integer.parseInt(venda[5])-1;

        if(venda[3].equals("N")){
            this.precoN[mes] += Double.parseDouble(venda[1]) * Integer.parseInt(venda[2]);
            this.quantN[mes] = Integer.parseInt(venda[2]);
        }
        else {
            this.precoP[mes] += Double.parseDouble(venda[1]) * Integer.parseInt(venda[2]);
            this.quantP[mes] =  Integer.parseInt(venda[2]);
        }
    }
}
