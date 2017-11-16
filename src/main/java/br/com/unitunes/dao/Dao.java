/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unitunes.dao;

/**
 *
 * @author LuisFernandoTorriani
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.spi.PersistenceUnitTransactionType;

public abstract class Dao<T, I extends Serializable> {

    public final static String user = "oracle_dba";
    private EntityManagerFactory factory;
    private Class<T> persistedClass;

    public Dao(Class<T> persistedClass) {
        this.persistedClass = persistedClass;
    }

    protected void conectar() {
        this.factory = Persistence.createEntityManagerFactory("uniTunesPU");
    }

    protected EntityManager getEntityManager() {
        return this.factory.createEntityManager();
    }

    protected void close() {
        this.factory.close();
    }

    ;
    public Boolean adicionar(T item) {
        try {
            this.conectar();
            EntityManager em = this.getEntityManager();
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
            em.close();
            this.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean atualizar(T item) {
        try {
            this.conectar();
            EntityManager em = this.getEntityManager();
            em.getTransaction().begin();
            em.merge(item);
            em.getTransaction().commit();
            em.close();
            this.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean excluir(T item) {
        try {
            this.conectar();
            EntityManager em = this.getEntityManager();
            em.getTransaction().begin();
            em.remove(em.contains(item) ? item : em.merge(item));
            em.getTransaction().commit();
            em.close();
            this.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<T> getList() {
        this.conectar();
        EntityManager em = this.getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(this.persistedClass);
        query.from(this.persistedClass);
        List<T> retorno = em.createQuery(query).getResultList();
        this.close();
        return retorno;
    }

    public T encontrar(I id) {
        this.conectar();
        T retorno = this.getEntityManager().find(persistedClass, id);
        this.close();
        return retorno;
    }

    public List<T> getQueryList(String query) {
        this.conectar();
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        List<T> retorno = em.createQuery(query).getResultList();
        this.close();
        return retorno;
    }

    public List<T> getQueryList(String query, TreeMap<String, String> parameters) {
        this.conectar();
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery(query);
        for (Map.Entry<String, String> param_a : parameters.entrySet()) {
            q.setParameter(param_a.getKey(), param_a.getValue());
        }
        List<T> retorno = q.getResultList();
        em.close();
        this.close();
        return retorno;
    }

}
