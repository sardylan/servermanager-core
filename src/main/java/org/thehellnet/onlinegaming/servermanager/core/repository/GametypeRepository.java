package org.thehellnet.onlinegaming.servermanager.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.Gametype;

@Repository
public interface GametypeRepository extends JpaRepository<Gametype, Long> {

    Gametype findByName(String name);
}
