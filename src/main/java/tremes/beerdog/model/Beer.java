package tremes.beerdog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Tomas Remes
 */
@Entity
public class Beer {

    @Id
    private int id;

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

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public int getId() {
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

}
