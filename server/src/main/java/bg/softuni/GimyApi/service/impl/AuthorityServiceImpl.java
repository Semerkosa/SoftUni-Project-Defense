package bg.softuni.GimyApi.service.impl;

import bg.softuni.GimyApi.model.entity.AuthorityEntity;
import bg.softuni.GimyApi.repository.AuthorityRepository;
import bg.softuni.GimyApi.service.AuthorityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<AuthorityEntity> getAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public void initRoles() {
        AuthorityEntity adminRole = new AuthorityEntity("ADMIN");
        AuthorityEntity userRole = new AuthorityEntity("USER");

        authorityRepository.saveAllAndFlush(List.of(adminRole, userRole));
    }
}
