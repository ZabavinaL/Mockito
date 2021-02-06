package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import ru.netology.domain.PurchaseItem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


// Аннотации. Здесь @BeforeEach
class CartManagerTestNonEmptyWithSetup {
    private CartManager manager = new CartManager();
    private PurchaseItem first = new PurchaseItem (1, 1, "first",1,1); // кладем
    private PurchaseItem second = new PurchaseItem (2, 2, "second",1,1); // ..в корзину
    private PurchaseItem third = new PurchaseItem (3, 3, "third",1,1); // .. несколько id (first, second, third)

    @BeforeEach
    public void setUp() { // создаем метод setUp и помечаем его аннотацией BeforeEach
        manager.add(first);
        manager.add(second);
        manager.add(third);
    }

    @Test
    public void shouldRemoveIfExists() {
        int idToRemove = 1;

        manager.removeById(idToRemove);

        // сравниваем actual & expected
        PurchaseItem[] actual = manager.getAll();
        PurchaseItem[] expected = new PurchaseItem[] {third, second};

        // assertEquals(expected, actual) - для массивов не сработает (примитивный тип)
        assertArrayEquals(expected, actual); // AssertArrayEquals - для сравнения массивов (ссылочный тип)
    }

    @Test
    // Пробуем удалить элемент, которого не существует в нашем массиве Items (int idToRemove = 4;). Тест упадет
    public void shouldNotRemoveIfNotExists() {
        int idToRemove = 4; // определяем, какой id мы хотим удалять. кейс с несколькими элементами в корзине

        manager.removeById(idToRemove); // вызываем метод, который будем тестировать. начало теста. выше была подготовка данных

        // сравниваем actual & expected
        PurchaseItem[] actual = manager.getAll();
        PurchaseItem[] expected = new PurchaseItem[] {third, second}; // сравниваем третий и второй элементы, тк предполагаем, что первый мы удалили

        // assertEquals(expected, actual) - для массивов не сработает (примитивный тип)
        assertArrayEquals(expected, actual); // AssertArrayEquals - для сравнения массивов (ссылочный тип)
    }

}