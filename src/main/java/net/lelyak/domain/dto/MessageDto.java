package net.lelyak.domain.dto;

import lombok.*;
import net.lelyak.domain.Message;
import net.lelyak.domain.User;
import net.lelyak.domain.util.MessageHelper;

/**
 * @author Nazar Lelyak.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "author", "likes", "meLiked"})
public class MessageDto {
    private Integer id;
    private String text;
    private String tag;
    private User author;
    private String filename;

    private Long likes;
    private Boolean meLiked;

    public MessageDto(Message message, Long likes, Boolean meLiked) {
        this.id = message.getId();
        this.text = message.getText();
        this.tag = message.getTag();
        this.author = message.getAuthor();
        this.filename = message.getFilename();

        this.likes = likes;
        this.meLiked = meLiked;
    }

    public String getAuthorName() {
        return MessageHelper.getAuthorName(author);
    }
}
