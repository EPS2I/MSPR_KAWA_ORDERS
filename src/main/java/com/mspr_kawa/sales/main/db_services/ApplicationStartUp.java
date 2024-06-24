package com.mspr_kawa.sales.main.db_services;

import com.mspr_kawa.sales.main.model.Sale;
import com.mspr_kawa.sales.main.repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ApplicationStartUp {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartUp.class);

    private final SalesSyncService salesSyncService;

    private final SaleRepository saleRepository;

    public ApplicationStartUp(SalesSyncService salesSyncService, SaleRepository saleRepository) {
        this.salesSyncService = salesSyncService;
        this.saleRepository = saleRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationEvent() throws IOException {
        logger.info("Application started, fetching clients from main db...");
        try {
            List<Sale> orderList = salesSyncService.fetchSalesFromMainDb();
            saleRepository.saveAll(orderList);
            logger.info("Fetched and saved {} customers from main db.", orderList.size());
        } catch (Exception e) {
            logger.error("Error fetching clients from main db", e);
        }
    }
}
