package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("from Role", Role.class)
                .getResultList();
    }

    @Override
    public Set<Role> getRoleByName(String[] role) {
        return entityManager.createQuery("select r from Role r where r.role in (:nameRole)", Role.class).
                setParameter("nameRole", Arrays.asList(role)).
                getResultList().
                stream().collect(Collectors.toSet());
    }
}
