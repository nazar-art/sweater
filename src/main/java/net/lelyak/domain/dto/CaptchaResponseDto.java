package net.lelyak.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Nazar Lelyak.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptchaResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success;

    @JsonAlias("error-codes")
    private Set<String> errorCodes;
}
