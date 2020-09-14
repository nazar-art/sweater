package net.lelyak.controller;

import lombok.RequiredArgsConstructor;
import net.lelyak.domain.Message;
import net.lelyak.domain.User;
import net.lelyak.repository.MessageRepo;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

/**
 * @author Nazar Lelyak.
 */
@Service
@RequiredArgsConstructor
public class UserMessagesController {

    private final MessageRepo messageRepo;
    private final MainController mainController;

    @GetMapping("/user-messages/{user}")
    public String userMessages(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Message message
    ) {
        Set<Message> messages = user.getMessages();

        model.addAttribute("messages", messages);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "userMessages";
    }

    @PostMapping("/user-messages/{user}")
    public String updateMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Message message,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        if (message.getAuthor().equals(currentUser)) {
            if (StringUtils.isNotBlank(text)) {
                message.setText(text);
            }

            if (StringUtils.isNotBlank(tag)) {
                message.setTag(tag);
            }

            mainController.saveFile(message, file);

            messageRepo.save(message);
        }

        return String.format("redirect:/user-messages/%s", user);
    }
}