package org.thehellnet.onlinegaming.servermanager.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.AppUserToken;

@Repository
public interface AppUserTokenRepository extends JpaRepository<AppUserToken, Long> {
}
