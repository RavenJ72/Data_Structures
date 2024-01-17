package Navigator;

import Entitys.Route;
import HashTableFolder.MultiMap;
import StackAndArrayDeque.List;


import java.util.Comparator;


public class NavigatorImpl implements Navigator {

    private MultiMap<String, Route> routes;

    public NavigatorImpl() {
        this.routes = new MultiMap<>();
    }

    @Override
    public void addRoute(Route route) {
        boolean flag = true;

        for (Route routeInMemory : routes.getValues()) {
            if (routeInMemory.equals(route)) {
                flag = false;
                System.err.println("Данный маршрут уже существует!");
                break;

            }
        }
        if (flag){
            if(route.getPoints().length() > 1){
                routes.add(route.getId(), route);
            }else{
                System.err.println("Некоректный маршрут!");
            }
        }



    }

    @Override
    public void removeRoute(String routeId) {
        routes.remove(routeId);
    }

    @Override
    public boolean contains(Route route) {
        return routes.containsKey(route.getId());
    }

    @Override
    public int size() {
        return routes.size();
    }

    @Override
    public Route getRoute(String routeId) {
        return routes.getValues(routeId).peekFirst();
    }

    @Override
    public void chooseRoute(String routeId) {
        routes.getValues(routeId).peekFirst().increasePopularity();

    }

    @Override
    public Iterable<Route> searchRoutes(String startPoint, String endPoint) {
        List<Route> allRoutes = routes.getValues();
        List<Route> sortedRoutes = new List<>();

        for (Route route : allRoutes) {
            if (route.getPoints().peekFirst().equals(startPoint) && route.getPoints().peekLast().equals(endPoint)) {
                sortedRoutes.addLast(route);
            }
        }

        sortedRoutes.sort(RouteUtils.SEARCH_ROUTES_COMPARATOR);

        return sortedRoutes;
    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {

        List<Route> allRoutes = routes.getValues();
        List<Route> sortedRoutes = new List<>();

        for (Route route : allRoutes) {
            if (route.isFavorite && route.getPoints().peekLast().equals(destinationPoint)) {
                sortedRoutes.addLast(route);

            }
        }
        sortedRoutes.sort(RouteUtils.FAVORITE_ROUTES_COMPARATOR);
        return sortedRoutes;
    }

    @Override
    public Iterable<Route> getTop3Routes() {

        List<Route> allRoutes = routes.getValues();
        List<Route> sortedRoutes = new List<>();
        int counter = 0;

        for (Route route : allRoutes) {
            if (counter < 3) {
                sortedRoutes.addLast(route);
                counter++;
            }
        }
        sortedRoutes.sort(RouteUtils.FAVORITE_ROUTES_COMPARATOR);
        return sortedRoutes;
    }
}
