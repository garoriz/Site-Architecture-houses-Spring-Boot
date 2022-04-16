package ru.kpfu.stud.rizrgaripov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.stud.rizrgaripov.dto.ArticleDto;
import ru.kpfu.stud.rizrgaripov.dto.CreatePhotoDto;
import ru.kpfu.stud.rizrgaripov.dto.PhotoDto;
import ru.kpfu.stud.rizrgaripov.model.Article;
import ru.kpfu.stud.rizrgaripov.model.Photo;
import ru.kpfu.stud.rizrgaripov.repository.PhotoRepository;
import ru.kpfu.stud.rizrgaripov.service.PhotoService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public List<PhotoDto> getAll() {
        return photoRepository.findAll().stream().map(PhotoDto::fromModel).collect(Collectors.toList());
    }

    @Override
    public void save(CreatePhotoDto createPhotoDto) {
        Photo photo = new Photo(
                createPhotoDto.getUrlPhoto(),
                createPhotoDto.getUser()
        );
        photoRepository.save(photo);
    }
}
