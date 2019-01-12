package org.thehellnet.onlinegaming.servermanager.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.AppUser;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.AppUserToken;
import org.thehellnet.onlinegaming.servermanager.core.repository.AppUserRepository;
import org.thehellnet.onlinegaming.servermanager.core.repository.AppUserTokenRepository;
import org.thehellnet.utility.EmailUtility;
import org.thehellnet.utility.PasswordUtility;
import org.thehellnet.utility.TokenUtility;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserTokenRepository appUserTokenRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, AppUserTokenRepository appUserTokenRepository) {
        this.appUserRepository = appUserRepository;
        this.appUserTokenRepository = appUserTokenRepository;
    }

    @Transactional(readOnly = true)
    public AppUser findByEmailAndPassword(String email, String password) {
        if (!EmailUtility.validateForLogin(email)) {
            return null;
        }

        AppUser appUser = appUserRepository.findByEmail(email);
        if (appUser == null) {
            return null;
        }

        if (!PasswordUtility.verify(appUser.getPassword(), password)) {
            return null;
        }

        return appUser;
    }

    @Transactional
    public AppUserToken newToken(AppUser appUser) {
        if (appUser == null) {
            return null;
        }

        String token = TokenUtility.generate();
        return appUserTokenRepository.save(new AppUserToken(token, appUser));
    }
}
