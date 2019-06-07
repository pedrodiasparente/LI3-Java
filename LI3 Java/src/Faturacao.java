import java.io.Serializable;

public class Faturacao implements Serializable {

    private int[][] nVendasP = new int[3][12];
    private int[][] nVendasN = new int[3][12];
    private double[][] precoTotalP = new double[3][12];
    private double[][] precoTotalN = new double[3][12];

    public Faturacao () {
        for (int i = 0; i < nVendasP.length; i++) {
            for (int j = 0; j < nVendasP[i].length; j++) {
                nVendasP[i][j] = 0;
            }
        }
        for (int i = 0; i < nVendasN.length; i++) {
            for (int j = 0; j < nVendasN[i].length; j++) {
                nVendasN[i][j] = 0;
            }
        }
        for (int i = 0; i < precoTotalP.length; i++) {
            for (int j = 0; j < precoTotalP[i].length; j++) {
                precoTotalP[i][j] = 0;
            }
        }
        for (int i = 0; i < precoTotalN.length; i++) {
            for (int j = 0; j < precoTotalN[i].length; j++) {
                precoTotalN[i][j] = 0;
            }
        }
    }

    public Faturacao(int[][] nvendasP, int[][] nVendasN, double[][] precoTotalP, double[][] precoTotalN) {
        this.nVendasP = nvendasP;
        this.nVendasN = nVendasN;
        this.precoTotalP = precoTotalP;
        this.precoTotalN = precoTotalN;
    }

    public Faturacao (Faturacao obj) {
        this.nVendasP = obj.getnVendasP();
        this.nVendasN = obj.getnVendasN();
        this.precoTotalP = obj.getPrecoTotalP();
        this.precoTotalN= obj.getPrecoTotalN();
    }

    public int[][] getnVendasP() {
        return this.nVendasP;
    }

    public int[][] getnVendasN() {
        return this.nVendasN;
    }

    public double[][] getPrecoTotalP() {
        return this.precoTotalP;
    }

    public double[][] getPrecoTotalN() {
        return this.precoTotalN;
    }

    public void setnVendasP(int[][] nVendasP) {
        this.nVendasP = nVendasP;
    }

    public void setnVendasN(int[][] nVendasN) {
        this.nVendasN = nVendasN;
    }

    public void setPrecoTotalP(double[][] precoTotalP) {
        this.precoTotalP = precoTotalP;
    }

    public void setPrecoTotalN(double[][] precoTotalN) {
        this.precoTotalN = precoTotalN;
    }

    public Faturacao clone() {
        return new Faturacao(this);
    }

    public String toString() {
        String aux = "";

        aux += "nVendasP\n";
        for (int i = 0; i < nVendasP.length; i++) {
            for (int j = 0; j < nVendasP[i].length; j++) {
                aux += "[" + i + "][" + j + "] = " + nVendasP[i][j] + "\n";
            }
        }

        aux += "nVendasN\n";
        for (int i = 0; i < nVendasN.length; i++) {
            for (int j = 0; j < nVendasN[i].length; j++) {
                aux += "[" + i + "][" + j + "] = " + nVendasN[i][j] + "\n";
            }
        }

        aux += "precoTotalP\n";
        for (int i = 0; i < precoTotalP.length; i++) {
            for (int j = 0; j < precoTotalP[i].length; j++) {
                aux += "[" + i + "][" + j + "] = " + precoTotalP[i][j] + "\n";
            }
        }

        aux += "precoTotalN\n";
        for (int i = 0; i < precoTotalN.length; i++) {
            for (int j = 0; j < precoTotalN[i].length; j++) {
                aux += "[" + i + "][" + j + "] = " + precoTotalN[i][j] + "\n";
            }
        }
        return aux;
    }

    public void addToFat(String vendaString){
        String venda[];
        int filial, mes;

        venda = vendaString.split(" ");
        filial = Integer.parseInt(venda[6])-1;
        mes = Integer.parseInt(venda[6])-1;

        if(venda[3].equals("N")){
            this.precoTotalN[filial][mes] += Double.parseDouble(venda[1]) * Integer.parseInt(venda[2]);
            this.nVendasN[filial][mes]++;
        }
        if(venda[3].equals("P")){
            this.precoTotalP[filial][mes] += Double.parseDouble(venda[1]) * Integer.parseInt(venda[2]);
            this.nVendasP[filial][mes]++;
        }
    }
}
