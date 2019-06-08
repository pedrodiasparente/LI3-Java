import java.util.Comparator;

public class QuantidadeComparator implements Comparator<QuantidadeProduto> {
    public int compare(QuantidadeProduto q1, QuantidadeProduto q2) {
        if (q1.getQuantidade() < (q2.getQuantidade())) return -1;
        else if (q1.getQuantidade() > (q2.getQuantidade())) return 1;
        else return(q1.getProduto().compareTo(q2.getProduto()));
    }
}
