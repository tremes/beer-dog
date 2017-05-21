package tremes.beerdog.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * @author Tomas Remes
 */
@Entity
public class Restaurant implements Serializable{

    @Id
    private int id;

    @Embedded
    private Address address;

    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "BEER_ID")
    private Set<Beer> beers = new HashSet<Beer>();

    public int getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Beer> getBeers() {
        return beers;
    }

    public void addBeer(Beer beer) {
        this.beers.add(beer);
    }

}
