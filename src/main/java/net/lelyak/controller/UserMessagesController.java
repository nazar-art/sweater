package net.lelyak.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lelyak.domain.Message;
import net.lelyak.domain.User;
import net.lelyak.domain.dto.MessageDto;
import net.lelyak.repository.MessageRepo;
import net.lelyak.service.MessageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Nazar Lelyak.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@RequestMapping("/user-messages/{author}")
public class UserMessagesController {

    private final MessageRepo messageRepo;
    private final MessageService messageService;
    private final MessageController messageController;

    @GetMapping
    public String userMessages(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User author,
            Model model,
            @RequestParam(required = false) Message message,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {

        Page<MessageDto> page = messageService.messageListForUser(pageable, currentUser, author);

        model.addAttribute("userChannel", author);
        model.addAttribute("subscribersCount", author.getSubscribers().size());
        model.addAttribute("subscriptionsCount", author.getSubscriptions().size());
        model.addAttribute("isSubscriber", author.getSubscribers().contains(currentUser));

        model.addAttribute("page", page);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(author));

        String urlValue = String.format("/user-messages/%s", author.getId());
        model.addAttribute("url", urlValue);

        return "userMessages";
    }

    @PostMapping
    public String updateMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long author,
            @RequestParam("id") Message message,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag,
            @RequestParam("file") MultipartFile file,
            Model model
    ) throws IOException {

        if (message != null && currentUser.equals(message.getAuthor())) {
            if (StringUtils.isNotBlank(text)) {
                message.setText(text);
            }

            if (StringUtils.isNotBlank(tag)) {
                message.setTag(tag);
            }

            messageController.saveFile(message, file);

            messageRepo.save(message);
        }

        String urlValue = String.format("/user-messages/%s", author);
        model.addAttribute("url", urlValue);

        return String.format("redirect:/user-messages/%s", author);
    }
}
