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
    private int[] nVendasP;
    private int[] nVendasN;

    public InfoProd () {
        this.quantP = new int[12];
        this.quantN = new int[12];
        this.precoP = new double[12];
        this.precoN = new double[12];
        this.nVendasP = new int[12];
        this.nVendasN = new int[12];
    }

    public InfoProd (int[] quantP, int[] quantN, double[] precoP, double[] precoN, int[] nVendasP, int[] nVendasN) {
        this.quantP = quantP;
        this.quantN = quantN;
        this.precoP = precoP;
        this.precoN = precoN;
        this.nVendasP = nVendasP;
        this.nVendasN = nVendasN;
    }
     public InfoProd (InfoProd o) {
        this.quantP = o.getQuantP();
        this.quantN = o.getQuantN();
        this.precoP = o.getPrecoP();
        this.precoN = o.getPrecoN();
        this.nVendasP = o.getNVendasP();
        this.nVendasN = o.getNVendasN();
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

    public int[] getNVendasP() {
        int[] r = new int[12];
        for (int i = 0; i < 12; i++){
            r[i] = this.nVendasP[i];
        }
        return r;
    }

    public int getNVendasPMes(int i) {
        return this.nVendasP[i];
    }

    public int[] getNVendasN() {
        int[] r = new int[12];
        for (int i = 0; i < 12; i++){
            r[i] = this.nVendasN[i];
        }
        return r;
    }

    public int getNVendasNMes(int i) {
        return this.nVendasN[i];
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

    public void setnVendasP(int[] q) {
        for (int i = 0; i < 12; i++){
            this.nVendasP[i] = q[i];
        }
    }

    public void setnVendasN(int[] q) {
        for (int i = 0; i < 12; i++){
            this.nVendasP[i] = q[i];
        }
    }

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
            this.nVendasN[mes]++;
        }
        else {
            this.precoP[mes] += Double.parseDouble(venda[1]) * Integer.parseInt(venda[2]);
            this.quantP[mes] =  Integer.parseInt(venda[2]);
            this.nVendasP[mes]++;
        }
    }
}
