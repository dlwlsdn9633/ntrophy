package com.ntrophy.converter;

import com.ntrophy.domain.enums.PostType;
import org.springframework.core.convert.converter.Converter;


public class StringToPostTypeConverter implements Converter<String, PostType> {
    @Override
    public PostType convert(String source) {
        return PostType.fromLabel(source.toLowerCase());
    }
}
