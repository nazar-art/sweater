package net.lelyak.repository;

import net.lelyak.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Nazar Lelyak.
 */
public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
}
