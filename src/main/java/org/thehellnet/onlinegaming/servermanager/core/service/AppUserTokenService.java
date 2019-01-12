package org.thehellnet.onlinegaming.servermanager.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.AppUser;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.AppUserToken;
import org.thehellnet.onlinegaming.servermanager.core.repository.AppUserTokenRepository;
import org.thehellnet.utility.TokenUtility;

@Service
public class AppUserTokenService {

    private final AppUserTokenRepository appUserTokenRepository;

    @Autowired
    public AppUserTokenService(AppUserTokenRepository appUserTokenRepository) {
        this.appUserTokenRepository = appUserTokenRepository;
    }

    @Transactional(readOnly = true)
    public AppUser getAppUserByToken(String token) {
        if (token == null) {
            return null;
        }

        AppUserToken appUserToken = appUserTokenRepository.findByToken(token);
        if (appUserToken == null) {
            return null;
        }
        if (!TokenUtility.validateExpiration(appUserToken.getExpirationDateTime())) {
            return null;
        }

        return appUserToken.getAppUser();
    }
}
