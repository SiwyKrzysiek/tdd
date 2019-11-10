package graph;

import java.util.OptionalInt;

public class DirectedEdge<T> {
	private final Node<T> a;
	private final Node<T> b;
	private final OptionalInt weight;

	public Node<T> getA() {
		return a;
	}

	public Node<T> getB() {
		return b;
	}

	public OptionalInt getWeight() {
		return weight;
	}

	public DirectedEdge(Node<T> a, Node<T> b) {
		this(a,b,OptionalInt.empty());
	}

	public DirectedEdge(Node<T> a, Node<T> b, int weight) {
		this(a,b,OptionalInt.of(weight));
	}

	private DirectedEdge(Node<T> a, Node<T> b, OptionalInt weight){
		this.a = a;
		this.b = b;
		this.weight = weight;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DirectedEdge<?> that = (DirectedEdge<?>) o;
		return (this.a == that.a && this.b == that.b)
				|| (this.a == that.b && this.b == that.a) ;
	}

	@Override
	public int hashCode() {
		return a.hashCode() + b.hashCode();
	}
}
