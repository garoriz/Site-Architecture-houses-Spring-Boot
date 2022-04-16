package ru.kpfu.stud.rizrgaripov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.stud.rizrgaripov.model.Article;
import ru.kpfu.stud.rizrgaripov.model.Photo;
import ru.kpfu.stud.rizrgaripov.model.SaleAd;

import java.util.List;
import java.util.Optional;

public interface SaleAdRepository extends JpaRepository<SaleAd, Integer> {
    Optional<SaleAd> getSaleAdById(int id);

    List<SaleAd> findAll();

    List<SaleAd> findAllById(int id);

    List<SaleAd> findAllByContentAndPriceIsLessThanEqual(String content, int maxPrice);
}
