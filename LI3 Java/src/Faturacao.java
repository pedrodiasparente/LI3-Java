import java.io.Serializable;

public class Faturacao implements Serializable {

    private int[][] nVendasP = new int[3][12];
    private int[][] nVendasN = new int[3][12];
    private float[][] precoTotalP = new float[3][12];
    private float[][] precoTotalN = new float[3][12];

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

    public Faturacao(int[][] nvendasP, int[][] nVendasN, float[][] precoTotalP, float[][] precoTotalN) {
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
        return nVendasP;
    }

    public int[][] getnVendasN() {
        return nVendasN;
    }

    public float[][] getPrecoTotalP() {
        return precoTotalP;
    }

    public float[][] getPrecoTotalN() {
        return precoTotalN;
    }

    public void setnVendasP(int[][] nVendasP) {
        this.nVendasP = nVendasP;
    }

    public void setnVendasN(int[][] nVendasN) {
        this.nVendasN = nVendasN;
    }

    public void setPrecoTotalP(float[][] precoTotalP) {
        this.precoTotalP = precoTotalP;
    }

    public void setPrecoTotalN(float[][] precoTotalN) {
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
}
