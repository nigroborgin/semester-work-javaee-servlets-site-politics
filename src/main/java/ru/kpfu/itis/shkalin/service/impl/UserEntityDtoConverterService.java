package ru.kpfu.itis.shkalin.service.impl;

import ru.kpfu.itis.shkalin.dto.AbstractDto;
import ru.kpfu.itis.shkalin.dto.UserReadDto;
import ru.kpfu.itis.shkalin.entity.AbstractEntity;
import ru.kpfu.itis.shkalin.entity.User;
import ru.kpfu.itis.shkalin.service.EntityDtoConverterService;

public class UserEntityDtoConverterService implements EntityDtoConverterService {

    @Override
    public AbstractEntity convert(AbstractDto dto) {
        User entity = new User();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    public AbstractDto convert(AbstractEntity entity) {
        UserReadDto dto = new UserReadDto();
        updateDto(entity, dto);
        return dto;
    }
}
