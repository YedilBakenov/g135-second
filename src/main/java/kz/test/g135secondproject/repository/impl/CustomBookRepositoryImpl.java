package kz.test.g135secondproject.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import kz.test.g135secondproject.model.Book;
import kz.test.g135secondproject.repository.CustomBookRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomBookRepositoryImpl implements CustomBookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findBooksMoreCost(int cost) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query  = cb.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);

        Predicate costPredicate = cb.greaterThanOrEqualTo(root.get("cost"), cost);
        query.select(root).where(costPredicate);

        return entityManager.createQuery(query).getResultList();
    }


    @Override
    public List<Book> findByNameOrCost(String name, Integer cost) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);

        List<Predicate> predicates = new ArrayList<>();

        if(name!=null){
            predicates.add(cb.like(root.get("name"), "%" + name + "%"));
            query.select(root).where(predicates.toArray(new Predicate[0]));
        }

        if(cost!=null){
            predicates.add(cb.greaterThanOrEqualTo(root.get("cost"), cost));
            query.select(root).where(predicates.toArray(new Predicate[0]));
        }

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Book> sortBooksByName() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);

        query.select(root).orderBy(cb.desc(root.get("name")));

        return entityManager.createQuery(query).getResultList();
    }
}
