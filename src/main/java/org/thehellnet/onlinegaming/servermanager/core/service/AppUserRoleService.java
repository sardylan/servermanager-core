package org.thehellnet.onlinegaming.servermanager.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.AppUser;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.AppUserRole;
import org.thehellnet.onlinegaming.servermanager.core.repository.AppUserRepository;
import org.thehellnet.onlinegaming.servermanager.core.repository.AppUserRoleRepository;

@Service
public class AppUserRoleService {

    private final AppUserRepository appUserRepository;
    private final AppUserRoleRepository appUserRoleRepository;

    @Autowired
    public AppUserRoleService(AppUserRepository appUserRepository, AppUserRoleRepository appUserRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appUserRoleRepository = appUserRoleRepository;
    }

    @Transactional(readOnly = true)
    public boolean appUserHasRoleTag(AppUser appUser, String... roleTags) {
        appUser = appUserRepository.getOne(appUser.getId());
        for (String roleTag : roleTags) {
            AppUserRole loginAppUserRole = appUserRoleRepository.findByTag(roleTag);
            if (loginAppUserRole == null) {
                return false;
            }
            if (!appUser.getAppUserRoles().contains(loginAppUserRole)) {
                return false;
            }
        }

        return true;
    }
}
