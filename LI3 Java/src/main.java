public class main {
    public static void main(String[] args) {

        Cat_Clientes clientes = new Cat_Clientes();
        Cat_Produtos produtos = new Cat_Produtos();
        Faturacao fat = new Faturacao();
        FatGlobal fatGlobal = new FatGlobal();


        fatGlobal.add("feijoada", fat);
        produtos.add("hey whatup");
        produtos.add("hasuuu dude");
        clientes.add("aaaaaaaaaaaaaaaaa");
        clientes.add("zzzzzzzzzzzzzzzzz");
        clientes.add("qqqqqqqqqqqqqqqqq");

        System.out.println(clientes.toString());
        System.out.println(produtos.toString());
        System.out.println(fatGlobal.getFaturacao("feijoada"));
    }
}