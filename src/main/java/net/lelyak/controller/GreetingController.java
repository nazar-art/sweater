package net.lelyak.controller;

import lombok.AllArgsConstructor;
import net.lelyak.domain.Message;
import net.lelyak.repository.MessageRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author Nazar Lelyak.
 */
@Controller
@AllArgsConstructor
public class GreetingController {

    private final MessageRepository messageRepository;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(required = false, defaultValue = "World") String name,
            Map<String, Object> model
    ) {

        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        model.put("messages", messageRepository.findAll());
        return "main";
    }

    @PostMapping
    public String addMessage(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {

        Message message = Message.builder().text(text).tag(tag).build();
        messageRepository.save(message);

        model.put("messages", messageRepository.findAll());
        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;

        if (StringUtils.isNotBlank(filter)) {
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }

        model.put("messages", messages);
        return "main";
    }


}
