package ru.kpfu.stud.rizrgaripov.service;

import ru.kpfu.stud.rizrgaripov.dto.CreatePhotoDto;
import ru.kpfu.stud.rizrgaripov.dto.PhotoDto;
import ru.kpfu.stud.rizrgaripov.model.Photo;

import java.util.List;

public interface PhotoService {
    List<PhotoDto> getAll();

    void save(CreatePhotoDto createPhotoDto);
}
