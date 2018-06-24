package tremes.beerdog.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Restaurant implements Serializable {

    @Id
    @Column(name = "RESTAURANT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private Address address;

    @Column(name = "name")
    private String name;

    // bi-directional relationship
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Beer> beers = new HashSet<Beer>();

    public Restaurant() {
    }

    public Restaurant(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Long getId() {
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
