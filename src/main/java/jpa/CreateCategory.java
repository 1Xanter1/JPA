package jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jpa.entity.Category;
import jpa.entity.Characteristic;
import jpa.entity.Product;
import jpa.entity.Product_Characteristic;

import java.lang.management.ManagementFactory;

public class CreateCategory {
        public static void main(String[] args) {
            // Введите название категории: Мебель
            // Введите характеристики категории: Ширина, Высота, Материал изготовления

            // Создать в таблицах категории и характеристики записи которые бы
            // соотвествовали введённой информации.

            EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("new_category");

            EntityManager manager = managerFactory.createEntityManager();

            try{
                manager.getTransaction().begin();

                String categoryName = "Мебель";

                String[] characteristicNames = {"Ширина", "Высота", "Материал изготовления", "Стоимость"};

                Category category = new Category();

                category.setName(categoryName);

                manager.persist(category);

                for (String characteristicName : characteristicNames) {
                    Characteristic characteristic = new Characteristic();

                    characteristic.setCategory(category);
                    characteristic.setCharacteristicName(characteristicName);
                    category.getCharacteristics().add(characteristic);
                }

                manager.getTransaction().commit();
            } catch (Exception e){
                if (manager.getTransaction().isActive()){
                    manager.getTransaction().rollback();
                }
                e.getStackTrace();
            }
            manager.close();
            managerFactory.close();
        }
    }
