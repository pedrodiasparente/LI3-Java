import java.util.Comparator;

public class QuantidadeComparator implements Comparator<QuantidadeString> {
    public int compare(QuantidadeString q1, QuantidadeString q2) {
        if (q1.getQuantidade() < (q2.getQuantidade())) return -1;
        else if (q1.getQuantidade() > (q2.getQuantidade())) return 1;
        else return(q1.getString().compareTo(q2.getString()));
    }
}
