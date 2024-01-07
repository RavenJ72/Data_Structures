package Entitys;

import StackAndArrayDeque.List;

import java.util.Objects;
import java.util.UUID;

public class Route {
    private String id;
    private double distance;
    private int popularity;
    public boolean isFavorite;


    private List<String> points;

    public Route(double distance, int popularity, boolean isFavorite, List<String> points) {
        this.id = String.valueOf(UUID.randomUUID());
        this.distance = distance;
        this.popularity = popularity;
        this.isFavorite = isFavorite;
        this.points = points;
    }

    public Route(double distance, List<String> points) {
        this.id = String.valueOf(UUID.randomUUID());
        this.distance = distance;
        this.points = points;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Route route = (Route) obj;
        return Double.compare(route.distance, distance) == 0 &&
                Objects.equals(points.peekFirst(), route.points.peekFirst())
                && Objects.equals(points.peekLast(), route.points.peekLast());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, distance, popularity, isFavorite, points);
    }

    public String getId() {
        return id;
    }
    public void increasePopularity(){
        this.popularity++;
    }

    public double getDistance() {
        return distance;
    }

    public int getPopularity() {
        return popularity;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id='" + id + '\'' +
                ", distance=" + distance +
                ", popularity=" + popularity +
                ", isFavorite=" + isFavorite +
                ", points=" + points +
                '}';
    }

    public List<String> getPoints() {
        return points;
    }
}
