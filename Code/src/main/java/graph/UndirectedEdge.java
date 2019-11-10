package graph;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Class for both weighted and unweighted graph edges.
 *
 * Probably should be split into two classes.
 *
 * @param <T> Node value type
 */
public class UndirectedEdge<T> {
    private final Node<T> a;
    private final Node<T> b;
    
    private final OptionalInt weight;

    public UndirectedEdge(Node<T> a, Node<T> b) {
        this(a, b, OptionalInt.empty());
    }

    public UndirectedEdge(Node<T> a, Node<T> b, int weight) {
        this(a, b, OptionalInt.of(weight));
    }

    private UndirectedEdge(Node<T> a, Node<T> b, OptionalInt weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

}