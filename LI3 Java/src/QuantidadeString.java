public class QuantidadeString {
    private String string;
    private int quantidade;

    QuantidadeString(){
        string = "";
        quantidade = 0;
    }

    QuantidadeString(String p, int q){
        this.string = p;
        this.quantidade = q;
    }

    QuantidadeString(QuantidadeString qp){
        this.string = qp.getString();
        this.quantidade = qp.getQuantidade();
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public QuantidadeString clone(){
        return new QuantidadeString(this);
    }

    public void addQuantidade(int quantidade){
        this.quantidade += quantidade;
    }
}
