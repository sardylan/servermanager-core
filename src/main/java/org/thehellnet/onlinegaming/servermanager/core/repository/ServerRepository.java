package org.thehellnet.onlinegaming.servermanager.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.Server;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {

    Server findByTag(String tag);

    Server findByAddressAndPort(String address, Integer port);
}
