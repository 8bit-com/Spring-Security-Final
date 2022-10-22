package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select u from Role u", Role.class).getResultList();
    }

    @Override
    public Role getRole(String role) {
        return entityManager.createQuery("select r from Role r where r.role =: role", Role.class)
                .setParameter("role", role).getSingleResult();
    }

    @Override
    public List<Role> getRoleById(List<Long> roles) {
        TypedQuery<Role> q = entityManager.createQuery("select r from Role r where r.id in :role", Role.class);
        q.setParameter("role",roles);
        return new ArrayList<>(q.getResultList());
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void removeRole(List<Long> roles) {
        entityManager.remove(getRoleById(roles));
    }

    @Override
    public void updateRole(Role role) {
        entityManager.merge(role);
    }
}
