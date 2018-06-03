package tremes.beerdog.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import tremes.beerdog.model.Beer;

@RequestScoped
@Transactional
public class BeerService implements Serializable {

  @PersistenceContext EntityManager em;

  public void addNew(Beer beer) {
    em.persist(beer);
  }

  public Beer getBeerById(Long id){
    return em.find(Beer.class, id);
  }

  public List<Beer> getBeers() {
    List<Beer> beers = em.createQuery("select b from Beer b", Beer.class).getResultList();
    return beers;
  }
}
