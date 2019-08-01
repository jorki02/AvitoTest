package com.avito.test.avito_test.configs;

import com.avito.test.avito_test.service.dto.ChatDto;
import com.avito.test.avito_test.service.dto.MessageDto;
import com.avito.test.avito_test.storage.entities.Chat;
import com.avito.test.avito_test.storage.entities.Message;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigs {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<Chat, ChatDto> skipSetUsers = new PropertyMap<Chat, ChatDto>() {
            protected void configure() {
                skip().setUsers(null);
            }
        };

        PropertyMap<Message, MessageDto> skipMessages = new PropertyMap<Message, MessageDto>() {
            protected void configure() {
                skip().setAuthor(null);
                skip().setChat(null);
            }
        };

        modelMapper.addMappings(skipSetUsers);
        modelMapper.addMappings(skipMessages);
        return modelMapper;
    }
}
