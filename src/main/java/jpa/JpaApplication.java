package jpa;

import jakarta.persistence.*;
import jpa.entity.Category;
import jpa.entity.Characteristic;
import jpa.entity.Product;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JpaApplication {
    public static void main(String[] args) throws Exception {
        // Maven - отдельная программа выполняющая функции системы автоматизации сборки, автоматизируется
        // процесс компиляции и упаковки ее результата в целостный артифакт(конечная точка сборки).
        // В качестве артифакта может выступать как JAR, так и WAR файлы, получить собранный артифакт
        // можно получить и используя стандартные методы сборки предоставленные средой IDEA
        // либо любой другой, но такой вариант будет привязывать сборку к среде разработки. Это
        // неудобно, когда над одним проектом работают несколько разработчиков и каждый в своей
        // среде, то есть отсутсвует единый инструмент для сборки проекта решает эту внешние
        // системы автоматизации сьокри такие как Maven и Gradle, которые не привязаны к среде
        // разработки (при его использовании можно произвести сборку вообще без среды разработки).
        // При разработке проекта могут потребовтся возможности, в которых нет стандартной библиотеке
        // JDK, в таких случаях требуются подключение внешних библиотек реализующие
        // недостающение возможности, также библиотека в контексте разработке проека будет называться
        // зависимостью. Maven позволяет автоматизировать работу пл уравелнию взаимодействими, он умеет
        // их скачивать из репозиториев храенения, включить скаченные зависимости в сборку и так далее.
        // // У Maven есть центральный репазиторий зависимостей по адресу 'mavenrepository.com'
        // (Maven Central Repository) в даном репозитории можно найти почти любую популярную
        // Java библиотеку.


        // В стандартной библиотеке JDK есть стандартный модуль JDBC, позволяющий работать с реляционными
        // базами данных, при условии, что для базы данных реализован жрайвер для взаимодействия. Драйверами
        // называются программная прослойка связывающая собой ЯП и сервер базы данных.

        // `Java Application` -> `PostgreSQL Java Driver` -> `PostgreSQL Server`
        // `C++ Application` -> `PostgreSQL C++ Driver` -> `PostgreSQL Server`

        // Драйвер для работы с той или иной базой данных не входит в стандартную библиотеку JDK, то есть
        // если мы хотим выстроить подключения к PostgreSQL необходимо кодключать к проекту соответствующий
        // драйвер.

        // Для подключения к серверу базы данных PostgreSQL через JDBC необходимо сформировать строку
        // подключения по формату:
        // jdbc.postgresql://<хост>,//<порт>,/<База данных>
        // хост - сетевой адрес устройства на котором работает сервер базы данных
        // порт - номер виртуального сетевого интерфейса к которому привязан сервер базы данных
        // база данных - название базы данных на сервере к которой формируется подключения

        String jdbcUrl = "jdbc:postgresql://localhost:5432/store_db";
        String username = "postgres";
        String password = "postgres";
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

//        String addQuery = "select c.* from categories c";
//        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        while (resultSet.next()){
//            System.out.println(resultSet.getLong("id"));
//            System.out.println(resultSet.getString("name"));


        // Java EE/Jakarta EE - набор стандартов (спецефикаций) описывающие подходы и инструменты
        // упрощающие разработку крупных корпаротивных приложений на Java (в основном веб-приложений).
        // Одном из модулей описанных в Java EE/ Jakarta EE является JPA, данный модуль описывает взитмодействие
        // между реляционной базой данных по прринципу ORM(Object Relational Making). ORM - это система
        // объектно-реляционного сопоставлениея, которая является прослойкой между данными и таблицой виде в виде
        // объектов, вся суть ORM сводиться к преобразовению данных из объекта в табличный вид и из табличного
        // вида в объект. JPA является всего лишь стандартом описывающим понятия, методы или объекты, используемые
        // по принципу ORM,то не конкретная реализация, которую можно использовать, а лишь руководство в разложении
        // (стандарт) к реализации(стандарт, интерфейс).
        // Класс используемый для репрезентации записи таблицы в констексте стандарта JPA называется сущность.


        // table humans         -> ORM -> class humans
        // id serial8           -> ORM -> long id
        // first_name varchar   -> ORM -> String firstName
        // birthdate date       -> ORM -> localDate birthdate

        // Библиотек реализующих стндарты JPA довольно много:
        // 1) Hibernate;
        // 2) EclipseLink;
        // 3) ...;

        // Hibernate является внещшней библиотекой не входящей в стандартную библиотеку JDK, использование
        // возможностей Hibernate требует его подключения, в качестве зависимости, найти можно в центральном
        // репозитории Maven под назвнием `Hibernate-core`.

        // В JPA предусмотрен файл конфигурации persistence.xml предназначенный для определения конфигурационных
        // блоков с параметрами подключений и другими в том числе. Файл конфигурации `persistence.xml` должен
        // находиться внутри папки с названием META-INF, которая в свою очередьдолжен быть включена в
        // итоговую сборку.

        // `EntityManagerFactory` - это главный объект в стандартном JPA через который выстраивается подключение.
        // Объект `EntityManagerFactory` используется для получения более легкостных объектов EntityManager.

        // `EntityManager` - это объект предоставляющий методы для работы с сущностями и выполнения запросов
        // с базы данных. Каждый объект EntityManager имеет локальный кэш, в котором привязанные к нему сущности,
        // объекты одного EntityManager не могут использоваться в комбинации с объектами другого EntityManager.

        // ``entityManager.find(class, Object id) :T` - возвращает сущность заданного типа T по заданному
        // первичного ключа по параметру if при условии что такая сущность существет, иначе результатом будет
        // null.

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");

        EntityManager manager = factory.createEntityManager();

        // select c.* from categories c where c.id = 2;
/*
        Category category = manager.find(Category.class, 1);
        if(category != null) {
            System.out.println(category.getName());
        } else {
            System.out.println("Категория не найдена");
        }
*/
        try {
            manager.getTransaction().begin();
//
            Scanner scanner = new Scanner(System.in);
//
            System.out.println("Введите название категории:");
            String categoryName = scanner.nextLine();

            TypedQuery<Category> categoryTypedQuery = manager.createQuery(
                    "select c from Category c where c.name = :name", Category.class
            );
            categoryTypedQuery.setParameter("name", categoryName);
            while (true) {
                if (categoryTypedQuery.getResultList().isEmpty()) {
                    Category category = new Category();
                    category.setName(categoryName);
                    manager.persist(category);

                    System.out.println("Введите характеристики категории (введите 'end', чтобы завершить ввод): ");
                    List<String> characteristicNames = new ArrayList<>();
                    String characteristicName;
                    while (true) {
                        System.out.print("Характеристика: ");
                        characteristicName = scanner.nextLine();
                        if (characteristicName.equals("end")) {
                            break;
                        }
                        characteristicNames.add(characteristicName);
                    }

                    for (String name : characteristicNames) {
                        Characteristic characteristic = new Characteristic();

                        characteristic.setCategory(category);
                        characteristic.setCharacteristicName(name);

                        category.getCharacteristics().add(characteristic);
                        manager.persist(characteristic);

                        break;

                    }
                } else {
                    System.out.println("Такое имя уже существует. Попробуйте снова");
                    break;
                }
            }
//            String[] characteristicNames = {"Ширина", "Высота", "Материал изготовления", "Стоимость"};


//            for (String characteristicName : characteristicNames) {
//                Characteristic characteristic = new Characteristic();
//
//                characteristic.setCategory(category);
//                characteristic.setCharacteristicName(characteristicName);
//                category.getCharacteristics().add(characteristic);
//
//                manager.persist(characteristic);
//            }

//            manager.getTransaction().commit();
//
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            manager.close();

            factory.close();
        }

        // В стандарте JPA есть возможность выполнения запросов написанная на языка JPQL, запросы могуть быть
        // как на выборку так и на изменение информации. JPQL представляет собой язык похожий на классический SQL, но
        // оринтированный на работу с сущностями. Для выполнения JPQL запросов в стандартах JPA предусмотрены объекты
        // Query(используется для изменения данных в таблице) и TypedQuery<T>(используется для запросов
        // на выборку данных), где <T>, тип результата.

        // JPQL -> ORM -> PostgreSQL Dialect -> PostgreSQL Server.
        // JPQL -> ORM -> MySQL Dialect -> MySQL Server
        //...

        // SQL -> select p.* from products p
        // JPQL -> select p from Product p -> Product.class

        // MySQL -> select p.name from products p
        // JPQL -> select p.name from Product p -> String.class

        // SQL -> select p.* from products p where p.price between ? and ?
        // JPQL -> select p from Product p where p.price between ? and ? -> Product.class

        // SQL -> select count(p.id) from product p where p.category_id - ?
        // JPQL -> select count(p.id) from product p where p.category.id - ? -> long.class

        // SQL -> update products set price = price + 1.1 where category_id = ?
        // JPQL -> update product p set p.price = p.price + 1.1 where category.id = ?


//        TypedQuery<Category> categoryTypedQuery = manager.createQuery(
//                "select c from Category c", Category.class
//        );
//
//        List<Category> categories = categoryTypedQuery.getResultList();
//        for (Category category: categories
//             ) {
//            System.out.println(category.getName());
//        }
//
//        int minPrice = 100_000;
//        int maxPrice = 250_000;
//
//
//        TypedQuery<Product> productTypedQuery = manager.createQuery(
//                "select p from Product p where p.price between ?1and ?2", Product.class);
//        productTypedQuery.setParameter(1,minPrice);
//        productTypedQuery.setParameter(2, maxPrice);
//
//        List<Product> products = productTypedQuery.getResultList();
//        for (Product product : products){
//            System.out.println(product.getName());
//        }

//        try{
//            manager.getTransaction().begin();
//
//            Query query = manager.createQuery(
//                    "update product p set p.price = p.price * 1.1 where p.category.id = ?1", Product.class
//            );
//
//            query.setParameter(1,2);
//            query.executeUpdate();
//            manager.getTransaction().commit();
//        } catch (Exception e){
//            manager.getTransaction().rollback();
//            throw new RuntimeException();
//        }

//        try {
//            Query query = manager.createQuery(
//                    "delete from Product p where p.category.categoryId = ?1", Product.class
//            );
//            query.setParameter(1, 2);
//            query.executeUpdate();
//
//            manager.getTransaction().commit();
//        } catch (Exception e) {
//            manager.getTransaction().rollback();
//            throw new RuntimeException();
//        } finally {
//            manager.close();
//
//            factory.close();
//        }
    }
}