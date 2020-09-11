package springBootBauerProject.Bootstrap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springBootBauerProject.Bootstrap.models.Role;
import springBootBauerProject.Bootstrap.repository.RoleRepository;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Role getRole(String name) {
        return roleRepository.findRoleByRoleName(name);
    }

    @Transactional
    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Transactional
    @Override
    public Set<Role> getRoles(String[] ids) {
        return roleRepository.getRolesById(ids);
    }

}
