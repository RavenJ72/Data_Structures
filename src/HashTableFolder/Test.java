package HashTableFolder;

import Entitys.Route;
import Navigator.NavigatorImpl;
import StackAndArrayDeque.List;

public class Test {
    public static void main(String[] args) {
        List<String> listForFirstRoute = new List<>();
        listForFirstRoute.addLast("Барнаул");
        listForFirstRoute.addLast("Москва");
        listForFirstRoute.addLast("Екб");

        List<String> listForSecondRoute = new List<>();
        listForSecondRoute.addLast("Санкт-Петербург");
        listForSecondRoute.addLast("Новосибирск");
        listForSecondRoute.addLast("Казань");

        List<String> listForThirdRoute = new List<>();
        listForThirdRoute.addLast("Сочи");
        listForThirdRoute.addLast("Краснодар");
        listForThirdRoute.addLast("Воронеж");

        List<String> listForFourthRoute = new List<>();
        listForFourthRoute.addLast("Иркутск");
        listForFourthRoute.addLast("Омск");
        listForFourthRoute.addLast("Тюмень");

        List<String> listForFifthRoute = new List<>();
        listForFifthRoute.addLast("Барнаул");
        listForFifthRoute.addLast("Екб");

        // Создаем маршруты с разными характеристиками
        Route route1 = new Route(3000, 2, true, listForFirstRoute);
        Route route2 = new Route(1000, 12, false, listForFirstRoute);
        Route route3 = new Route(5, 12, false, listForFirstRoute);
        Route route4 = new Route(200, 10, false, listForFirstRoute);

        Route route6 = new Route(1500, 50, true, listForSecondRoute);
        Route route7 = new Route(2500, 5, true, listForThirdRoute);
        Route route8 = new Route(800, 20, false, listForFourthRoute);
        Route route9 = new Route(100, 100, true, listForFifthRoute);
        Route route10 = new Route(600, 30, false, listForFifthRoute);


        NavigatorImpl navigator = new NavigatorImpl();

        navigator.addRoute(route1);
        navigator.addRoute(route2);
        navigator.addRoute(route3);
        navigator.addRoute(route4);

        navigator.addRoute(route6);
        navigator.addRoute(route7);
        navigator.addRoute(route8);
        navigator.addRoute(route9);
        navigator.addRoute(route10);


        // Тестирование метода contains
        System.out.println("Проверка наличия маршрута route1: " + navigator.contains(route1));
        System.out.println("Проверка наличия маршрута route11 (не существует): " + navigator.contains(new Route(123, new List<>())));
        System.out.println("--------------------------------------");

        // Тестирование метода size
        System.out.println("Количество маршрутов в навигаторе: " + navigator.size());
        System.out.println("--------------------------------------");

        // Тестирование метода getRoute
        System.out.println("Получение маршрута по ID (route1): " + navigator.getRoute(route1.getId()));
        System.out.println("--------------------------------------");

        // Тестирование метода chooseRoute
        System.out.println("Увеличение популярности маршрута route1");
        navigator.chooseRoute(route1.getId());
        System.out.println("Проверка увеличения популярности (route1): " + navigator.getRoute(route1.getId()).getPopularity());
        System.out.println("--------------------------------------");

        // Тестирование метода removeRoute
        System.out.println("Удаление маршрута route1");
        navigator.removeRoute(route1.getId());
        System.out.println("Проверка удаления маршрута route1: " + navigator.contains(route1));
        System.out.println("Обновленное количество маршрутов в навигаторе: " + navigator.size());
        System.out.println("--------------------------------------");

        // тестируем searchRoutes, getFavoriteRoutes и getTop3Routes
        navigator.searchRoutes("Барнаул", "Екб").forEach(System.out::println);
        System.out.println("--------------------------------------");

        navigator.getFavoriteRoutes("Екб").forEach(System.out::println);
        System.out.println("--------------------------------------");

        navigator.getTop3Routes().forEach(System.out::println);


        //Добавим маршрут с такой же начальной и конечной точкой как например в последнем маршруте.
        System.out.println("Попытка добавления 11 маршрута.");
        Route route11 = new Route(600, 30, true, listForFifthRoute);
        navigator.addRoute(route11);

        //Добавим маршрут с пустым списком.
        System.out.println("Попытка добавления 12 маршрута.");
        Route route12 = new Route(600, 30, true, new List<>());
        navigator.addRoute(route12);


    }
}
