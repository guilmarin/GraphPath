import java.util.*;

public class Graph {
    private List<Vertex> vertices = new ArrayList<Vertex>();

    public Graph(Vertex... vertices) {
        this.vertices.addAll(Arrays.asList(vertices));
    }

    public int getDistance(String from, String to) {

        int distance=0;
        if(getVertexByName(from)!=null && getVertexByName(to)!=null){
            List<Distance> distances=createListDistances(from);
            List<Vertex> visitedvertices= new ArrayList<Vertex>();
            visitedvertices.add(getVertexByName(from));

            while(visitedvertices.size()!=vertices.size() && !visitedvertices.contains(getVertexByName(to))){
                distances=Update(distances,visitedvertices);
                Vertex minvertex=getMinVertex(distances,visitedvertices);
                if(getDistanceByName(distances,minvertex.getName()).getDistance()!=-1){
                    visitedvertices.add(minvertex) ;
                }
                else{
                    break;
                }
            }

            distance=getDistanceByName(distances,to).getDistance();
        }
        else{
            distance=-1;
        }
        return distance;
    }

    private List<Distance> Update(List<Distance> distances, List<Vertex> visitedvertices) {
        for(Vertex visitedvertex:visitedvertices){
            for(Vertex vertex:vertices){
                if(!visitedvertices.contains(vertex)){
                    if(visitedvertex.isNext(vertex.getName())){
                        UpdateOneVertex(distances,vertex,visitedvertex);
                    }
                }
            }
        }
       return distances;
    }

    private void UpdateOneVertex(List<Distance> distances,Vertex vertex, Vertex visitedvertex){
        for(Distance distance: distances){
            if(distance.getTo().equals(vertex.getName())){
                if(distance.getDistance()==-1){
                    distance.setDistance(getDistanceByName(distances,visitedvertex.getName()).getDistance()+visitedvertex.getDistance(vertex.getName()));
                }
                else{
                    distance.setDistance(Math.min(distance.getDistance(),getDistanceByName(distances,vertex.getName()).getDistance()+visitedvertex.getDistance(vertex.getName())));
                }
            }
        }
    }

    private Vertex getMinVertex(List<Distance> distances, List<Vertex> visitedvertices){
        Vertex minvertex=null;
        for(Vertex vertex:vertices){
            if(!visitedvertices.contains(vertex)){
                if(minvertex!=null && getDistanceByName(distances,minvertex.getName()).getDistance()!=-1){
                    if((getDistanceByName(distances,vertex.getName()).getDistance()>=0 && getDistanceByName(distances,vertex.getName()).getDistance()<getDistanceByName(distances,minvertex.getName()).getDistance()) ){
                        minvertex=vertex;
                    }
                }
                else{
                    minvertex=vertex;
                }
            }
        }
        return minvertex;
    }

    private Vertex getVertexByName(String from) {
        Vertex vertex=null;
        for (Vertex vertice : vertices) {
            if (vertice.getName().equals(from)) {
                vertex = vertice;
            }
        }
        return vertex;
    }

    private List<Distance> createListDistances(String from) {
        List<Distance> distances=new ArrayList<Distance>();

        for (Vertex vertex : vertices) {
            if(vertex.getName().equals(from)){
                distances.add(new Distance(vertex.getName(),0));
            }
            else{
                distances.add(new Distance(vertex.getName(),-1));
            }
        }

        return distances;
    }

    private Distance getDistanceByName(List<Distance> distances, String name){
        Distance distanceselected=null;
        for(Distance distance:distances){
            if(distance.getTo().equals(name)){
                distanceselected=distance;
            }
        }
        return distanceselected;
    }
}