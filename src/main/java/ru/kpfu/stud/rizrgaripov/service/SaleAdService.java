package ru.kpfu.stud.rizrgaripov.service;

import ru.kpfu.stud.rizrgaripov.dto.CreatePhotoDto;
import ru.kpfu.stud.rizrgaripov.dto.CreateSaleAdDto;
import ru.kpfu.stud.rizrgaripov.dto.SaleAdDto;
import ru.kpfu.stud.rizrgaripov.model.SaleAd;

import java.util.List;

public interface SaleAdService {
    List<SaleAdDto> getAll();

    SaleAdDto get(int id);

    void save(CreateSaleAdDto createSaleAdDto);
}
