package com.ntrophy.config;

import com.ntrophy.converter.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${file.dir}")
    private String uploadPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + uploadPath);

    }
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 컨버터 등록
        registry.addConverter(new SeasonInfoToRankSquad());
        registry.addConverter(new SeasonInfoToSquad());
        registry.addConverter(new SeasonInfoToDuo());
        registry.addConverter(new SeasonInfoToSolo());
        registry.addConverter(new MatchResponseDtoToMatch());
        registry.addConverter(new SeasonDtoToSeason());
        registry.addConverter(new StringToPostTypeConverter());
    }
}
