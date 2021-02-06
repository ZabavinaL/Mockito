package ru.netology.manager;

import ru.netology.domain.PurchaseItem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartManagerTestNonEmpty {

    @Test
    public void shouldRemoveIfExists() {
        CartManager manager = new CartManager(); // создаем экземпляр менеджера, который будем тестировать.

        int idToRemove = 1; // определяем, какой id мы хотим удалять. кейс с несколькими элементами в корзине
        PurchaseItem first = new PurchaseItem (1, 1, "first",1,1); // кладем
        PurchaseItem second = new PurchaseItem (2, 2, "second",1,1); // ..в корзину
        PurchaseItem third = new PurchaseItem (3, 3, "third",1,1); // .. несколько id (first, second, third)

        manager.add(first); // складываем все id элементы в метод add
        manager.add(second);
        manager.add(third);
        // таким образом, сейчас долно лежать в менеджере 3 элемента

        manager.removeById(idToRemove); // вызываем метод, который будем тестировать. начало теста. выше была подготовка данных

        // сравниваем actual & expected
        PurchaseItem[] actual = manager.getAll();
        PurchaseItem[] expected = new PurchaseItem[] {third, second}; // сравниваем третий и второй элементы, тк предполагаем, что первый мы удалили

        // assertEquals(expected, actual) - для массивов не сработает (примитивный тип)
        assertArrayEquals(expected, actual); // AssertArrayEquals - для сравнения массивов (ссылочный тип)
    }

    @Test
    // Пробуем удалить элемент, которого не существует в нашем массиве Items (int idToRemove = 4;). Тест упадет
    public void shouldNotRemoveIfNotExists() {
        CartManager manager = new CartManager(); // создаем экземпляр менеджера, который будем тестировать.

        int idToRemove = 4; // определяем, какой id мы хотим удалять. кейс с несколькими элементами в корзине
        PurchaseItem first = new PurchaseItem (1, 1, "first",1,1); // кладем
        PurchaseItem second = new PurchaseItem (2, 2, "second",1,1); // ..в корзину
        PurchaseItem third = new PurchaseItem (3, 3, "third",1,1); // .. несколько id (first, second, third)

        manager.add(first); // складываем все id элементы в метод add
        manager.add(second);
        manager.add(third);
        // таким образом, сейчас долно лежать в менеджере 3 элемента

        manager.removeById(idToRemove); // вызываем метод, который будем тестировать. начало теста. выше была подготовка данных

        // сравниваем actual & expected
        PurchaseItem[] actual = manager.getAll();
        PurchaseItem[] expected = new PurchaseItem[] {third, second}; // сравниваем третий и второй элементы, тк предполагаем, что первый мы удалили

        // assertEquals(expected, actual) - для массивов не сработает (примитивный тип)
        assertArrayEquals(expected, actual); // AssertArrayEquals - для сравнения массивов (ссылочный тип)
    }

}