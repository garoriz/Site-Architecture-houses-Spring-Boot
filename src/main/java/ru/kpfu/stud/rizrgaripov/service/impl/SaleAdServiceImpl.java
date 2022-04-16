package ru.kpfu.stud.rizrgaripov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.stud.rizrgaripov.dto.ArticleDto;
import ru.kpfu.stud.rizrgaripov.dto.CreatePhotoDto;
import ru.kpfu.stud.rizrgaripov.dto.CreateSaleAdDto;
import ru.kpfu.stud.rizrgaripov.dto.SaleAdDto;
import ru.kpfu.stud.rizrgaripov.model.SaleAd;
import ru.kpfu.stud.rizrgaripov.repository.SaleAdRepository;
import ru.kpfu.stud.rizrgaripov.service.SaleAdService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleAdServiceImpl implements SaleAdService {
    private final SaleAdRepository saleAdRepository;

    @Autowired
    public SaleAdServiceImpl(SaleAdRepository saleAdRepository) {
        this.saleAdRepository = saleAdRepository;
    }

    @Override
    public List<SaleAdDto> getAll() {
        return saleAdRepository.findAll().stream().map(SaleAdDto::fromModel).collect(Collectors.toList());
    }

    @Override
    public SaleAdDto get(int id) {
        return saleAdRepository.findById(id).map(SaleAdDto::fromModel).orElse(null);
    }

    @Override
    public void save(CreateSaleAdDto createSaleAdDto) {
        SaleAd saleAd = new SaleAd(
                createSaleAdDto.getHeading(),
                createSaleAdDto.getContent(),
                createSaleAdDto.getPrice(),
                createSaleAdDto.getPhoneNumber(),
                createSaleAdDto.getUrlPhoto(),
                createSaleAdDto.getUser()
        );
        saleAdRepository.save(saleAd);
    }
}
