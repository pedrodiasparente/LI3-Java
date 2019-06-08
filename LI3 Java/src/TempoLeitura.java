import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TempoLeitura {
    public static void main(String[] args) {
        ModelTime model = new ModelTime();
        model.loadData();
        Crono.start();
        model.readFile("Vendas_1M.txt");
        Crono.stop();
        System.out.println(Crono.print());
        Crono.start();
        model.readFileParsing("Vendas_1M.txt");
        Crono.stop();
        System.out.println(Crono.print());
        Crono.start();
        model.readFileValidation("Vendas_1M.txt");
        Crono.stop();
        System.out.println(Crono.print());
        System.exit(0);
    }
}
