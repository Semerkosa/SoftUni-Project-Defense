package bg.softuni.GimyApi.service;

import bg.softuni.GimyApi.model.entity.AuthorityEntity;

import java.util.List;

public interface AuthorityService {

    List<AuthorityEntity> getAuthorities();

    void initRoles();
}
