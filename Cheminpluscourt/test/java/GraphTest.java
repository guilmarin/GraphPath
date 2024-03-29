
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class GraphTest {
    private Vertex lille = new Vertex("Lille");
    private Vertex paris = new Vertex("Paris");
    private Vertex reims = new Vertex("Reims");
    private Vertex nancy = new Vertex("Nancy");
    private Vertex lyon = new Vertex("Lyon");
    private Vertex marseille = new Vertex("Marseille");
    private Vertex lemans = new Vertex("Le Mans");
    private Vertex nantes = new Vertex("Nantes");
    private Vertex bordeaux = new Vertex("Bordeaux");
    private Vertex toulouse = new Vertex("Toulouse");
    private Vertex clermont = new Vertex("Clermont Ferrant");
    private Vertex montpellier = new Vertex("Montpellier");

    @Before
    public void setup() {
        lille.connectTo(reims, 206);
        lille.connectTo(paris, 222);
        lille.connectTo(nancy, 418);

        reims.connectTo(paris, 144);
        reims.connectTo(nancy, 245);
        reims.connectTo(lyon, 489);

        paris.connectTo(lyon, 465);
        paris.connectTo(lemans, 208);
        paris.connectTo(clermont, 423);

        lyon.connectTo(clermont, 166);
        lyon.connectTo(marseille, 313);
        lyon.connectTo(montpellier, 304);

        lemans.connectTo(nantes, 189);
        lemans.connectTo(bordeaux, 443);

        nantes.connectTo(bordeaux, 347);

        bordeaux.connectTo(toulouse, 243);

        toulouse.connectTo(montpellier, 245);

        montpellier.connectTo(marseille, 169);
        montpellier.connectTo(toulouse, 245);

        marseille.connectTo(montpellier, 169);

        clermont.connectTo(lyon, 166);
        clermont.connectTo(montpellier, 333);
        clermont.connectTo(marseille, 474);
    }

    @Test
    public void getDistanceForTwoAdjacentVertices() {
        Graph graph = new Graph(paris, lyon);
        assertEquals(graph.getDistance("Paris", "Lyon"), 465);
    }

    @Test
    public void getDistanceForTwoVerticesWithOneIntermediate() {
        Graph graph = new Graph(paris, clermont, montpellier);

        assertEquals(graph.getDistance("Paris", "Montpellier"), 423+333);
    }

    @Test
          public void getDistanceForTwoVerticesWithTwoIntermediate() {
        Graph graph = new Graph(paris, clermont, montpellier,lille);

        assertEquals(graph.getDistance("Lille", "Montpellier"), 222+423+333);
    }

    @Test
    public void getDistanceForTwoVerticesWithDifferentWay() {
        Graph graph = new Graph(paris, reims, nancy,lille);

        assertEquals(graph.getDistance("Lille", "Nancy"), 418);
    }

    @Test
    public void getDistanceForTwoVerticesWithCycle() {
        Graph graph = new Graph(toulouse,lyon, clermont,paris, marseille,montpellier);

        assertEquals(graph.getDistance("Paris", "Toulouse"), 423+333+245);
    }

    @Test
    public void getDistanceEmptyGraph() {
        Graph graph = new Graph();

        assertEquals(graph.getDistance("Paris", "Toulouse"), -1);
    }


    @Test
    public void getDistanceWithFromEqualsTo() {
        Graph graph = new Graph(paris);

        assertEquals(graph.getDistance("Paris", "Paris"), 0);
    }

    @Test
    public void getDistanceWithUnknownFrom() {
        Graph graph = new Graph(toulouse,lyon,clermont,montpellier);

        assertEquals(graph.getDistance("Paris", "Toulouse"), -1);
    }

    @Test
    public void getDistanceWithUnknownTo() {
        Graph graph = new Graph(paris,lyon,clermont,montpellier);

        assertEquals(graph.getDistance("Paris", "Toulouse"), -1);
    }

    @Test
    public void getDistanceWithUnknownToAndUnknownFrom() {
        Graph graph = new Graph(paris,lyon,clermont,montpellier);

        assertEquals(graph.getDistance("Lille", "Toulouse"), -1);
    }

    @Test
    public void getDistanceForTwoVerticesWithAnImpossibleWay() {
        Graph graph = new Graph(lille,nancy);

        assertEquals(graph.getDistance("Nancy", "Lille"), -1);
    }
}