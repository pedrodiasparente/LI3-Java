public class QuantidadeProduto {
    private String produto;
    private int quantidade;

    QuantidadeProduto(){
        produto = "";
        quantidade = 0;
    }

    QuantidadeProduto(String p, int q){
        this.produto = p;
        this.quantidade = q;
    }

    QuantidadeProduto(QuantidadeProduto qp){
        this.produto = qp.getProduto();
        this.quantidade = qp.getQuantidade();
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public QuantidadeProduto clone(){
        return new QuantidadeProduto(this);
    }

    public String toString(){
        return "Produto: " + this.produto + " (Quantidade: " + this.getQuantidade() + ")";
    }

    public void addQuantidade(int quantidade){
        this.quantidade += quantidade;
    }
}
