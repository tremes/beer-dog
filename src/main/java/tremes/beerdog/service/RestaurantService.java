package tremes.beerdog.service;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import tremes.beerdog.model.Restaurant;

@RequestScoped
@Transactional
public class RestaurantService {

    @PersistenceContext
    EntityManager em;

    public void addNew(Restaurant restaurant) {
        em.persist(restaurant);
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = em.createQuery("select r from Restaurant r", Restaurant.class).getResultList();
        
        return restaurants;
    }

    public Restaurant getRestaurantById(Long id) {
        Restaurant restaurant = em.find(Restaurant.class, id);
        return restaurant;
    }

}
