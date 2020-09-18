package net.lelyak.domain.util;

import lombok.experimental.UtilityClass;
import net.lelyak.domain.User;

/**
 * @author Nazar Lelyak.
 */
@UtilityClass
public class MessageHelper {

    public String getAuthorName(User author) {
        return author != null ? author.getUsername() : "<none>";
    }

}
