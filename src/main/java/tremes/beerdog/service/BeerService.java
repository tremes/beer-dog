package tremes.beerdog.service;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

import tremes.beerdog.model.Beer;

@RequestScoped
@Transactional
public class BeerService implements Serializable {

    @PersistenceContext
    EntityManager em;

    public void addNew(Beer beer) {
        em.persist(beer);
    }

    public List<Beer> getBeerByRestaurant(Long id) {
        List<Beer> beers = em.createQuery("select b from Beer b where b.restaurant.id = :id", Beer.class).setParameter("id", id).getResultList();
        return beers;
    }

    public Beer getBeerById(Long id) {
        return em.find(Beer.class, id);
    }

    public List<Beer> getBeers() {
        List<Beer> beers = em.createQuery("select b from Beer b", Beer.class).getResultList();
        return beers;
    }

    public void removeBeer(Beer beerToRemove) {
        if(!em.contains(beerToRemove)){
           beerToRemove = em.merge(beerToRemove);
        }
        em.remove(beerToRemove);

    }
}
