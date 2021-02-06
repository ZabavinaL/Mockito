package ru.netology.manager;

import ru.netology.domain.PurchaseItem;

//добавление покупки в корзину
public class CartManager {
    private PurchaseItem[] items = new PurchaseItem[0];

    public void add(PurchaseItem item) {  // создаем метод addБчтобы добавить какую-то карточку

        //создаем новый массив размером на единицу больше
        int length = items.length + 1;
        PurchaseItem[] tmp = new PurchaseItem[length]; // создаем новый массив, тк может измениться количество товара в корзине.

        //itar + tab
        // копируем поэлементно
//        for (int i = 0; i < items.length; i++) { // должны остаться старые элементы массива. к ним добавляем новые
//            tmp[i] = items[i]; // перекладываем вс элементы items в массив tmp. Обращаемся к каждому элементу по индексу и кладем его в массив tmp
//        }

        System.arraycopy(items, 0, tmp, 0, items.length); // замена цикла for по предложению IDEA,
        // методом,который написан не на java.
        // об этом говорит слово native, если раскрыть метод

        // кладем последний наш элемент
        int lastIndex = tmp.length - 1; // индекс последнего элемента
        tmp[lastIndex] = item; // по этому индексу кладем наш item
        items = tmp; // присваиваем массив tmp, то есть, уже измененный массив, в котором +1 товар(item)
    }

    // список покупок
    public PurchaseItem[] getAll() { // метод getAll для того, чтоы получить все items
        PurchaseItem[] items = repository.findAll();
        PurchaseItem[] result = new PurchaseItem[items.length]; // снова слздаем новый массив, НО такой же длины.
        // Мы просто ходим изменить их порядок

        // перебираем массив в прямом порядке
        // но кладем все результаты item в обратном порядке
        for (int i = 0; i < result.length; i++) { // result - новый массив идем ТУДА, а по item обратно
            int index = items.length - i - 1; // последний индекс -1, -2 - это предпоследний
            result[i] = items[index];
        }
        return result;
    }


    // Удаление по id покупки
    public void removeById(int id) {
        int length = items.length - 1;

        PurchaseItem[] tmp = new PurchaseItem[length];
        int index = 0;

        for (PurchaseItem item : items) {
            if (item.getId() != id) { // сравниваем item.getId() c != id
                tmp[index] = item;
                index++;
            }
        }
        // меняем наши элементы

        items = tmp;
    }

    public int sum() {
        int result = 0;
        for(PurchaseItem item : getAll()) {
            result = result + item.getProductPrice() * item.getCount();
        }
        return result;
    }
}