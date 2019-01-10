package org.thehellnet.onlinegaming.servermanager.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Game findByTag(String tag);
}
