package net.lelyak.repository;

import net.lelyak.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Nazar Lelyak.
 */
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String name);

    User findByActivationCode(String code);
}
