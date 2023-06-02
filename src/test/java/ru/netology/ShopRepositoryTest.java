package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    @Test
    public void deletingAnExisting() {
        ShopRepository shop = new ShopRepository();
        Product products1 = new Product(111, "Шапка", 2343);
        Product products2 = new Product(222, "Перчатки", 789);
        shop.add(products1);
        shop.add(products2);
        shop.remove(111);

        Product[] expected = {products2};
        Product[] actual = shop.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void deletingAnNoExisting() {
        ShopRepository shop = new ShopRepository();
        Product products1 = new Product(111, "Шапка", 2343);
        Product products2 = new Product(222, "Перчатки", 789);
        shop.add(products1);
        shop.add(products2);

        Assertions.assertThrows(NotFoundException.class, () -> {
            shop.removeById(333);
        });

    }

    @Test
    public void successfulAdditionElement() {
        ShopRepository shop = new ShopRepository();
        Product products1 = new Product(111, "Шапка", 2343);
        shop.add(products1);

        Product[] expected = {products1};
        Product[] actual = shop.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void elementWithDuplicateId() {
        ShopRepository shop = new ShopRepository();
        Product products1 = new Product(111, "Шапка", 2343);
        Product products2 = new Product(222, "Перчатки", 789);
        Product products3 = new Product(111, "Шарф", 1564);
        shop.add(products1);
        shop.add(products2);


        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            shop.addById(products3);
        });

    }
}
