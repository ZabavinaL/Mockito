package ru.netology.mockito;
import org.mockito.InjectMocks;
import ru.netology.repository.CartRepository;
import ru.netology.manager.CartManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.PurchaseItem;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // для того, чтобы JUnit мог распознать аннотации Mockito
class CartManagerTestNonEmpty {
    @Mock // создает спец класс для Mockito (передает мокнутый репозиторий)
    private CartRepository repository;  // заглушка на основе класса CartRepository(тут не заполняла)

    @InjectMocks // аннотация относится именно к менеджеру. Проверяет, какие есть рядом аннотации мок
    private CartManager manager;
    private PurchaseItem first = new PurchaseItem (1, 1, "first",1,1);
    private PurchaseItem second = new PurchaseItem (2, 2, "second",1,1);
    private PurchaseItem third = new PurchaseItem (3, 3, "third",1,1);

    @Test
    public void shouldCalculateSum(){
        //настройка заглушки
        PurchaseItem[] returned = new PurchaseItem[]{first, second, third}; // создаем то, что хотим вернуть из метода findAll (ходил в базы, делал запросы и тд), но мы хотем сделать заглушку
        doReturn(returned).when(repository).findAll(); // переопределение поведения заглушки.

        int expected = 212;
        int actual = manager.sum();
        assertEquals(expected, actual);

        // удостоверяемся, что заглушка была вызвана
        // но это уже проверка "внутренней" реализации
        verify(repository).findAll(); // проверить что вызввался метод репозитория findAll
    }


    @Test
    public void shouldRemoveIfExists() {
        CartManager manager = new CartManager();

        int idToRemove = 1;


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