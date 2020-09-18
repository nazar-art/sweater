package net.lelyak.service;

import lombok.RequiredArgsConstructor;
import net.lelyak.domain.Message;
import net.lelyak.domain.User;
import net.lelyak.domain.dto.MessageDto;
import net.lelyak.repository.MessageRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Nazar Lelyak.
 */
@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepo messageRepo;

    public Page<MessageDto> messageList(Pageable pageable, String filter, User user) {
        if (filter != null && !filter.isEmpty()) {
            return messageRepo.findByTag(filter, pageable, user);
        } else {
            return messageRepo.findAll(pageable, user);
        }
    }

    public Page<MessageDto> messageListForUser(Pageable pageable, User currentUser, User author) {
        return messageRepo.findByUser(pageable, author, currentUser);
    }
}
