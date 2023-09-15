package com.demo.HibDemo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class EmployeeDAO {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("HibJPA");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    public void create(Employee employee) {
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
    }

    public Employee getById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    public List<Employee> getAll() {
        return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    public void update(Employee employee) {
        entityManager.getTransaction().begin();
        entityManager.merge(employee);
        entityManager.getTransaction().commit();
    }


    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        if (employee != null) {
            entityManager.remove(employee);
        }
        entityManager.getTransaction().commit();
    }
    
    public void deleteAll() {
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery("DELETE FROM employees");
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }
}
