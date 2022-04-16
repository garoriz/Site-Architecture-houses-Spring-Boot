package ru.kpfu.stud.rizrgaripov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.stud.rizrgaripov.model.Article;
import ru.kpfu.stud.rizrgaripov.model.Photo;

import java.util.List;
import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    Optional<Photo> getPhotoById(int id);

    List<Photo> findAll();

    List<Photo> findAllById(int id);
}
