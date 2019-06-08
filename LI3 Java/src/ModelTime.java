import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ModelTime implements InterfGereVendasModel {
    private Cat_Produtos produtos;
    private Cat_Clientes clientes;

    public ModelTime(){
        produtos = new Cat_Produtos();
        clientes = new Cat_Clientes();
    }


    private void loadClientes(String fich){
        String cliente;
        try{
            BufferedReader br = new BufferedReader(new FileReader(fich));
            while((cliente = br.readLine()) != null){
                this.clientes.add(cliente);
            }
            br.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadProdutos(String fich){
        String produto;
        Faturacao fat = new Faturacao();
        try{
            BufferedReader br = new BufferedReader(new FileReader(fich));
            while((produto = br.readLine()) != null){
                this.produtos.add(produto);
            }
            br.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ReadingData loadData(){
        loadProdutos("Produtos.txt");
        loadClientes("Clientes.txt");
        return new ReadingData();
    }

    public static void readFile(String fich){
        String line;
        try{
            BufferedReader br = new BufferedReader(new FileReader(fich));
            while((line = br.readLine()) != null);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readFileParsing(String fich){
        String line;
        String[] parse;
        try{
            BufferedReader br = new BufferedReader(new FileReader(fich));
            while((line = br.readLine()) != null)
                parse = line.split(" ");
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean valVenda(String vendaString){
        boolean val;
        String[] venda;

        venda = vendaString.split(" ");

        val = true;

        if(!this.produtos.lookup(venda[0])) val = false;

        if (Double.parseDouble(venda[1]) < 0 || Double.parseDouble(venda[1]) >= 1000.0) val = false;

        if (Integer.parseInt(venda[2]) < 1 || Integer.parseInt(venda[2]) > 200) val = false;

        if (!venda[3].equals("N") && !venda[3].equals("P")) val = false;

        if(!this.clientes.lookup(venda[4])) val = false;

        if (Integer.parseInt(venda[5]) < 1 || Integer.parseInt(venda[5]) > 12) val = false;

        if (Integer.parseInt(venda[6]) < 1 || Integer.parseInt(venda[6]) > 3) val = false;

        return val;
    }


    public void readFileValidation(String fich){
        String line;
        String[] parse;
        try{
            BufferedReader br = new BufferedReader(new FileReader(fich));
            while((line = br.readLine()) != null)
                valVenda(line);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
