package StackAndArrayDeque;

import Entitys.Minion;

import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomStack<Minion> minionStack = new CustomStack<>(5);

        minionStack.push(new Minion("a", "ababa", 10));
        minionStack.push(new Minion("a", "ababa", 5));
        minionStack.push(new Minion("a", "ababa", 25));

        
        minionStack.push(new Minion("c", "Yellow", 25));
        minionStack.push(new Minion("b", "Yellow", 25));
        minionStack.push(new Minion("a", "bbbb", 25));

        List<Minion> listForSort = new ArrayList<>();

        // Итерация по стеку минионов и вывод информации о них
        System.out.println("Итерация по стеку минионов:");
        for (Minion minion : minionStack) {
            listForSort.add(minion);
            System.out.println("Имя: " + minion.getName() + ", Цвет: " + minion.getBodyColor() + ", Возраст: " + minion.getAge());
        }

        Collections.sort(listForSort);

        listForSort.stream().forEach(System.out::println);

        Minion minion1 = new Minion("Dave", "Yellow", 20);
        Minion minion2 = new Minion("Kevin", "Yellow", 25);

        int result = minion1.compareTo(minion2);

        if (result < 0) {
            System.out.println(minion1.getName() + " меньше чем " + minion2.getName());
        } else if (result > 0) {
            System.out.println(minion1.getName() + " больше чем " + minion2.getName());
        } else {
            System.out.println(minion1.getName() + " равен " + minion2.getName());
        }

    }
}
