package ru.kpfu.stud.rizrgaripov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public List<SaleAdDto> getAllByHeadingAndPrice(String searchName, int maxPrice) {
        searchName = "%" + searchName + "%";
        return saleAdRepository
                .findAllByHeadingAndMaxPrice(searchName, maxPrice)
                .stream()
                .map(SaleAdDto::fromModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<SaleAdDto> getAll() {
        return saleAdRepository.findAll()
                .stream()
                .map(SaleAdDto::fromModel)
                .collect(Collectors.toList());
    }

    @Override
    public SaleAdDto get(int id) {
        return saleAdRepository.findById(id).map(SaleAdDto::fromModel).orElse(null);
    }

    @Override
    public void save(CreateSaleAdDto createSaleAdDto) {
        SaleAd saleAd = new SaleAd(
                createSaleAdDto.getUserId(),
                createSaleAdDto.getHeading(),
                createSaleAdDto.getContent(),
                createSaleAdDto.getPrice(),
                createSaleAdDto.getPhoneNumber(),
                createSaleAdDto.getUrlPhoto()
        );
        saleAdRepository.save(saleAd);
    }

    @Override
    public List<SaleAdDto> getAllByUserId(int id) {
        return saleAdRepository.findAllByUserId(id)
                .stream()
                .map(SaleAdDto::fromModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) {
        saleAdRepository.deleteById(id);
    }

    @Override
    public void update(int id, String heading, String content, int price, String phoneNumber, String urlPhoto) {
        saleAdRepository.updateById(id, heading, content, price, phoneNumber, urlPhoto);
    }
}
