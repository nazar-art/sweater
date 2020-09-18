package net.lelyak.controller;

import lombok.experimental.UtilityClass;
import net.lelyak.domain.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Nazar Lelyak.
 */
@UtilityClass
public class ControllerUtils {

    Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }

    public String buildRedirect(RedirectAttributes redirectAttributes, String referer) {
        String redirectPath = ControllerUtils.buildRedirectPath(redirectAttributes, referer);
        return String.format("redirect:%s", redirectPath);
    }

    private String buildRedirectPath(RedirectAttributes redirectAttributes, String referer) {
        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();
        components.getQueryParams()
                .forEach(redirectAttributes::addAttribute);
        return components.getPath();
    }

}
