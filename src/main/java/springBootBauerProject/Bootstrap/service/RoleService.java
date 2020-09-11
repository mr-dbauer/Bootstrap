package springBootBauerProject.Bootstrap.service;

import springBootBauerProject.Bootstrap.models.Role;
import springBootBauerProject.Bootstrap.models.User;

import java.util.List;
import java.util.Set;

public interface RoleService {

    Role getRole(String name);

    Iterable<Role> findAll();

    Set<Role> getRoles(String[] ids);
}