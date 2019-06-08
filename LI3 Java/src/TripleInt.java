public class TripleInt {

    private int[] int1 = new int[12];
    private int[] int2 = new int[12];
    private double[] int3 = new double[12];

    public TripleInt(){
        for(int i = 0; i < 12; i++){
            this.int1[i] = 0;
        }
        for(int i = 0; i < 12; i++){
            this.int2[i] = 0;
        }
        for(int i = 0; i < 12; i++){
            this.int3[i] = 0;
        }
    }

    public TripleInt(int[] i1, int[] i2, double[] i3){
        this.int1 = i1;
        this.int2 = i2;
        this.int3 = i3;
    }

    public int[] getInt1() {
        int[] res = new int[12];
        System.arraycopy(this.int1, 0, res, 0, 12);
        return res;
    }

    public int[] getInt2() {
        int[] res = new int[12];
        System.arraycopy(this.int2, 0, res, 0, 12);
        return res;
    }

    public double[] getInt3() {
        double[] res = new double[12];
        System.arraycopy(this.int3, 0, res, 0, 12);
        return res;
    }

    public void setInt1(int[] int1) {
        System.arraycopy(int1, 0, this.int1, 0, 12);
    }

    public void addInt1Mes(int mes) {
        this.int1[mes]++;
    }

    public void setInt2(int[] int2) {
        System.arraycopy(int2, 0, this.int2, 0, 12);
    }

    public void addInt2Mes(int mes) {
        this.int2[mes]++;
    }

    public void setInt3(double[] int3) {
        System.arraycopy(int3, 0, this.int3, 0, 12);
    }

    public int[] addArraysInt(int[] array1, int[] array2){
        int[] res = new int[12];
        for (int i = 0; i < 12; i++) {
            res[i] = array1[i] + array2[i];
        }
        return res;
    }

    public double[] addArraysDouble(double[] array1, double[] array2){
        double[] res = new double[12];
        for (int i = 0; i < 12; i++) {
            res[i] = array1[i] + array2[i];
        }
        return res;
    }
}
