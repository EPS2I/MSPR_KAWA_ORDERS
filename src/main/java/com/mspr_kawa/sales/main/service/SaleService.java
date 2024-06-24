package com.mspr_kawa.sales.main.service;

import com.mspr_kawa.sales.main.model.Sale;
import com.mspr_kawa.sales.main.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(UUID id) {
        Optional<Sale> optionalOrder = saleRepository.findById(id);
        return optionalOrder.orElse(null);
    }

    public Sale createSale(Sale sale) {
        sale.setId(null);
        return saleRepository.save(sale);
    }

    public Sale updateSale(UUID id, Sale sale) {
        Optional<Sale> optionalOrder = saleRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Sale existingSale = optionalOrder.get();
            if (existingSale.getId().equals(sale.getId())) {
                return saleRepository.save(sale);
            }
        }
        return null;
    }

    public boolean deleteSale(UUID id) {
        Optional<Sale> optionalOrder = saleRepository.findById(id);
        if (optionalOrder.isPresent()) {
            saleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

