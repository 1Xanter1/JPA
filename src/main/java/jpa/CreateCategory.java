package jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jpa.entity.Category;
import jpa.entity.Characteristic;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateCategory {
        public static void main(String[] args) {
            // Введите название категории: Мебель
            // Введите характеристики категории: Ширина, Высота, Материал изготовления

            // Создать в таблицах категории и характеристики записи которые бы
            // соотвествовали введённой информации.

            EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("main");

            EntityManager manager = managerFactory.createEntityManager();

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
                    }  else {
                        System.out.println("Такое имя уже существует. Попробуйте снова");
                        break;
                    }
                }
            } catch (Exception e) {
                manager.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                manager.close();

                managerFactory.close();
            }
        }
    }
