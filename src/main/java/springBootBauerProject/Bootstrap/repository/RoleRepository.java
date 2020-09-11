package springBootBauerProject.Bootstrap.repository;

import org.springframework.data.repository.CrudRepository;
import springBootBauerProject.Bootstrap.models.Role;

import java.util.Set;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findRoleByRoleName(String name);

    @Override
    Iterable<Role> findAll();

    Set<Role> getRolesById(String[] ids);
}
