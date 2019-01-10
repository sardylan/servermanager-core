package org.thehellnet.onlinegaming.servermanager.core.model.persistence;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_id_seq")
    @SequenceGenerator(name = "game_id_seq", sequenceName = "game_id_seq")
    private Long id;

    @Basic
    @Column(name = "tag", nullable = false, unique = true)
    private String tag;

    @Basic
    @Column(name = "name")
    private String name;

    public Game() {
    }

    public Game(String tag) {
        this.tag = tag;
    }

    public Game(String tag, String name) {
        this.tag = tag;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id.equals(game.id) &&
                tag.equals(game.tag) &&
                Objects.equals(name, game.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tag);
    }

    @Override
    public String toString() {
        return name != null ? name : tag;
    }
}
