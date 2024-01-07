package Navigator;

import Entitys.Route;

import java.util.Comparator;

public class RouteUtils {

    public static final Comparator<Route> SEARCH_ROUTES_COMPARATOR = (route1, route2) -> {
        if (route1.isFavorite && !route2.isFavorite) return -1;
        if (!route1.isFavorite && route2.isFavorite) return 1;
        int distanceComparison = Double.compare(route1.getDistance(), route2.getDistance());
        if (distanceComparison != 0) return distanceComparison;
        return -Integer.compare(route1.getPopularity(), route2.getPopularity());
    };

    public static final Comparator<Route> FAVORITE_ROUTES_COMPARATOR = (route1, route2) -> {
        int distanceComparison = Double.compare(route1.getDistance(), route2.getDistance());
        if (distanceComparison != 0) return distanceComparison;
        return -Integer.compare(route1.getPopularity(), route2.getPopularity());
    };

    public static final Comparator<Route> TOP3_ROUTES_COMPARATOR = (route1, route2) -> {
        int popularityComparison = -Integer.compare(route1.getPopularity(), route2.getPopularity());
        if (popularityComparison != 0) return popularityComparison;
        int distanceComparison = Double.compare(route1.getDistance(), route2.getDistance());
        if (distanceComparison != 0) return distanceComparison;
        return Integer.compare(route1.getPoints().length(), route2.getPoints().length());
    };
}