package ru.kpfu.stud.rizrgaripov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.stud.rizrgaripov.model.Article;
import ru.kpfu.stud.rizrgaripov.model.Photo;
import ru.kpfu.stud.rizrgaripov.model.SaleAd;

import java.util.List;
import java.util.Optional;

public interface SaleAdRepository extends JpaRepository<SaleAd, Integer> {
    Optional<SaleAd> getSaleAdById(int id);

    List<SaleAd> findAll();

    List<SaleAd> findAllByUserId(int id);

    @Query(value = "select * from sale_ad sa where sa.heading like ?1 and sa.price <= ?2", nativeQuery = true)
    List<SaleAd> findAllByHeadingAndMaxPrice(String content, int maxPrice);

    @Transactional
    @Modifying
    void deleteById(int id);

    @Transactional
    @Modifying
    @Query(value = "update sale_ad sa set" +
            " heading = ?2," +
            " content = ?3," +
            " price = ?4, " +
            "phone_number = ?5," +
            " url_photo = ?6" +
            " where sa.id = ?1", nativeQuery = true)
    void updateById(int id, String heading, String content, int price, String phoneNumber, String urlPhoto);
}
