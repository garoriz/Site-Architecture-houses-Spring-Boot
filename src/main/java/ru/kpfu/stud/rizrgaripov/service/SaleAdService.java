package ru.kpfu.stud.rizrgaripov.service;

import ru.kpfu.stud.rizrgaripov.dto.CreatePhotoDto;
import ru.kpfu.stud.rizrgaripov.dto.CreateSaleAdDto;
import ru.kpfu.stud.rizrgaripov.dto.PhotoDto;
import ru.kpfu.stud.rizrgaripov.dto.SaleAdDto;
import ru.kpfu.stud.rizrgaripov.model.SaleAd;

import java.util.List;

public interface SaleAdService {
    List<SaleAdDto> getAllByHeadingAndPrice(String searchName, int maxPrice);

    List<SaleAdDto> getAll();

    SaleAdDto get(int id);

    void save(CreateSaleAdDto createSaleAdDto);

    List<SaleAdDto> getAllByUserId(int id);

    void deleteById(int id);

    void update(int id, String heading, String content, int price, String phoneNumber, String urlPhoto);
}
