package com.mspr_kawa.sales.main.db_services;

import com.mspr_kawa.sales.main.model.Sale;
import com.mspr_kawa.sales.main.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SalesSyncScheduler {

    private final SalesSyncService salesSyncService;
    private final SaleService saleService;


    @Autowired
    public SalesSyncScheduler(SalesSyncService salesSyncService, SaleService saleService) {
        this.salesSyncService = salesSyncService;
        this.saleService = saleService;
    }

    @Scheduled(fixedRate = 3000)  // Sync every 3 seconds
    public void syncSales() {
        List<Sale> sales = this.fetchSalesFromSQLite();
        salesSyncService.syncSalesToMainDb(sales);
    }

    private List<Sale> fetchSalesFromSQLite() {
        return this.saleService.getAllSales();
    }
}
