package com.ntrophy.converter;

import com.ntrophy.domain.enums.PostType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StringToPostTypeConverter implements Converter<String, PostType> {
    @Override
    public PostType convert(String source) {
        log.info("convert!");
        return PostType.fromLabel(source);
    }
}
