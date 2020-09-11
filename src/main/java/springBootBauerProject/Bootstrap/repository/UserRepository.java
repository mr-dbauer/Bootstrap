package springBootBauerProject.Bootstrap.repository;

import org.springframework.data.repository.CrudRepository;
import springBootBauerProject.Bootstrap.models.User;


public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailAddress(String login);

}