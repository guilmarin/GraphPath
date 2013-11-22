
public class Distance {
    private String to;
    private int distance;

    public Distance(String to, int distance) {
        this.to = to;
        this.distance=distance;
    }

    public void setDistance(int distance){
        this.distance=distance;
    }

    public String getTo() {
        return to;
    }

    public int getDistance() {
        return distance;
    }
}
