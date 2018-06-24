package tremes.beerdog.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Beer implements Serializable {

    @Id
    @Column(name = "BEER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "RATING")
    private long rating;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "VOL")
    private long volume;

    @Column(name = "DEGREES")
    private int degrees;

    @Column(name = "BREW")
    private String brewery;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;

    public Beer(){
    }

    public Beer(String name, String brewery, Restaurant restaurant){
        this.name = name;
        this.brewery = brewery;
        this.restaurant = restaurant;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public Long getId() {
        return id;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((brewery == null) ? 0 : brewery.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Beer other = (Beer) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (brewery == null) {
            if (other.brewery != null) {
                return false;
            }
        } else if (!brewery.equals(other.brewery)) {
            return false;
        }
        return true;
    }

}
