package com.earthquake.api.freemarker.template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;

/**
 * Created by Semih, 11.10.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Component
@AllArgsConstructor
public class FreemarkerTemplateProcessor implements TemplateProcessor {

    private final Configuration configuration;

    @Override
    public String apply(String templatePath, Object o) {

        try {

            final Template template = configuration.getTemplate(templatePath);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, o);
        }
        catch (IOException | TemplateException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
