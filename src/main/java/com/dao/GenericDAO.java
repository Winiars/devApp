package com.dao;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Gruby on 10.04.2017.
 */
public abstract class GenericDAO<T> implements Serializable {

    private static final EntityManagerFactory emf= Persistence.createEntityManagerFactory("DevApp_Persistence");
    private EntityManager em;
    private Class<T> entityClass;
    private static final long serialVersionUID = 1L;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void save(T entity){
        em.persist(entity);
    }

    public void beginTransaction(){
        em=emf.createEntityManager();
        em.getTransaction().begin();
    }

    public void commitTransaction(){
        em.getTransaction().commit();
    }

    public void closeTransaction(){
        em.close();
    }

    // Using the unchecked because JPA does not have a
    // query.getSingleResult()<T> method
    @SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
        T result = null;

        try {
            Query query = em.createNamedQuery(namedQuery);

            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }

            result = (T) query.getSingleResult();

        } catch (NoResultException e) {
            System.out.println("No result found for named query: " + namedQuery);
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    protected void delete(Object id){
        T entityToRemove = em.getReference(entityClass,id);
        em.remove(entityToRemove);
    }

    public T findReference(int entityId){
        System.out.println("entity Id= "+entityId);
        return em.getReference(entityClass,entityId);
    }

    public T find(int entityId){

        return em.find(entityClass,entityId);
    }

    public T update(T entity){
        return em.merge(entity);
    }

    public void joinTransaction(){
        em=emf.createEntityManager();
        em.joinTransaction();
    }



}
