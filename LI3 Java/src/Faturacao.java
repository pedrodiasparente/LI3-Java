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
        int[][] res = new int[3][12];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 12; j++) {
                res[i][j] = this.nVendasP[i][j];
            }
        }
        return res;
    }

    public int[][] getnVendasN() {
        int[][] res = new int[3][12];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 12; j++) {
                res[i][j] = this.nVendasN[i][j];
            }
        }
        return res;
    }

    public double[][] getPrecoTotalP(){
        double[][] res = new double[3][12];
        for (int i = 0; i < precoTotalP.length; i++) {
            for (int j = 0; j < precoTotalP[i].length; j++) {
                res[i][j] = this.precoTotalP[i][j];
            }
        }
        return res;
    }

    public double[][] getPrecoTotalN() {
        double[][] res = new double[3][12];
        for (int i = 0; i < precoTotalN.length; i++) {
            for (int j = 0; j < precoTotalN[i].length; j++) {
                res[i][j] = this.precoTotalN[i][j];
            }
        }
        return res;
    }

    public void setnVendasP(int[][] nVendasP) {
        for (int i = 0; i < nVendasP.length; i++) {
            for (int j = 0; j < nVendasP[i].length; j++) {
                this.nVendasP[i][j] = nVendasP[i][j];
            }
        }
    }

    public void setnVendasN(int[][] nVendasN) {
        for (int i = 0; i < nVendasN.length; i++) {
            for (int j = 0; j < nVendasN[i].length; j++) {
                this.nVendasN[i][j] = nVendasN[i][j];
            }
        }
    }

    public void setPrecoTotalP(double[][] precoTotalP) {
        for (int i = 0; i < precoTotalP.length; i++) {
            for (int j = 0; j < precoTotalP[i].length; j++) {
                this.precoTotalP[i][j] = precoTotalP[i][j];
            }
        }
    }

    public void setPrecoTotalN(double[][] precoTotalN) {
        for (int i = 0; i < precoTotalN.length; i++) {
            for (int j = 0; j < precoTotalN[i].length; j++) {
                this.precoTotalN[i][j] = precoTotalN[i][j];
            }
        }
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
        mes = Integer.parseInt(venda[5])-1;

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
