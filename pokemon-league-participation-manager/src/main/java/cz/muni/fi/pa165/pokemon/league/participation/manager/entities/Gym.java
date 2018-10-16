package cz.muni.fi.pa165.pokemon.league.participation.manager.entities;

import cz.muni.fi.pa165.pokemon.league.participation.manager.enums.PokemonType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity class for object gym.
 *
 * @author Michal Mokros 456442
 */
@Entity(name = "Gym")
@Table(name = "GYM")
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String location;

    @NotNull
    @Column(nullable = false)
    @Enumerated
    private PokemonType type;

    @NotNull
    @Column(nullable = false)
    @OneToOne
    @JoinColumn(name = "trainer_id")
    private Trainer gymLeader;

    @OneToMany(cascade=CascadeType.PERSIST, mappedBy = "trainer")
    private Set<Badge> badges = new HashSet<>();

    public Gym() {
    }

    public Gym(Long id) {
        this.id = id;
        this.location = null;
        this.type = null;
        this.gymLeader = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public PokemonType getType() {
        return type;
    }

    public void setType(PokemonType type) {
        this.type = type;
    }

    public Trainer getGymLeader() {
        return gymLeader;
    }

    public void setGymLeader(Trainer gymLeader) {
        this.gymLeader = gymLeader;
    }

    @Override
    public String toString() {
        return "Gym{ " +
                "id= " + getId() +
                ", location= " + getLocation() +
                ", type= " + getType() +
                ", gymLeader= " + getGymLeader() +
                " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Gym)) return false;
        final Gym gym = (Gym) o;
        return Objects.equals(getLocation(), gym.getLocation());
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash = 19 * hash + (int) (this.id ^ (this.id >>> 16));
        return hash;
    }
}
