package net.lelyak.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Nazar Lelyak.
 */
@Getter
@Setter
@Entity
@Builder
@ToString(of = {"id", "text", "tag"})
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Please fill the message")
    @Length(max = 2048, message = "Message too long (more than 2KB)")
    private String text;

    @Length(max = 255, message = "Message too long (more than 255)")
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

}
