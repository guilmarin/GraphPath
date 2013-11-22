import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private String name;

    private List<Edge> edges = new ArrayList<Edge>();

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void connectTo(Vertex target, int distance) {
        edges.add(new Edge(target, distance));
    }

    public boolean isNext(String to){
        boolean isnext=false;
        int i=0;
        while(!isnext && i<edges.size()){
            if(edges.get(i).getTarget().getName().equals(to)){
                isnext=true;
            }
            i++;
        }
        return isnext;
    }

    public int getDistance(String to){
        int i=0;
        int distance = -1;
        if(to.equals(name)){
            distance=0;
        }
        else{
            while (i<edges.size()){

                if(edges.get(i).getTarget().getName().equals(to)){
                    distance=edges.get(i).getDistance();
                }
                i++;
            }
        }
        return distance;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (name != null ? !name.equals(vertex.name) : vertex.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}