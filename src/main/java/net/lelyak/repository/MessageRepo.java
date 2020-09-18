package net.lelyak.repository;

import net.lelyak.domain.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;

/**
 * @author Nazar Lelyak.
 */
public interface MessageRepo extends CrudRepository<Message, Integer> {

    Page<Message> findAll(Pageable pageable);

    Page<Message> findByTag(String tag, Pageable pageable);

}
