import graph.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void createNodeWithStringValue() {
        // Given
        String myText = "Thank you from the mountain"; // Z góry dziękuję

        // When
        Node node = new Node<String>(myText);

        // Then
        assertEquals(myText, node.getValue());
    }
}