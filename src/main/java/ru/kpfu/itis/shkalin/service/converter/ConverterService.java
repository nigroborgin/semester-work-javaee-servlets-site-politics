package ru.kpfu.itis.shkalin.service.converter;

import ru.kpfu.itis.shkalin.dto.AbstractDto;
import ru.kpfu.itis.shkalin.entity.AbstractEntity;

public interface ConverterService{

//    AbstractEntity convert(AbstractDto dto);
//
//    AbstractDto convert(AbstractEntity entity);

    AbstractDto getUpdateDto(AbstractEntity entity, AbstractDto dto);

    AbstractEntity getUpdateEntity(AbstractEntity entity, AbstractDto dto);

    void update(Object updater, Object updatable);
}
