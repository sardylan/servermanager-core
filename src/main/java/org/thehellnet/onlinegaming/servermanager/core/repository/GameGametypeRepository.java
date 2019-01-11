package org.thehellnet.onlinegaming.servermanager.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.Game;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.GameGametype;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.Gametype;

@Repository
public interface GameGametypeRepository extends JpaRepository<GameGametype, Long> {

    GameGametype findByGameAndGametype(Game game, Gametype gametype);
}
