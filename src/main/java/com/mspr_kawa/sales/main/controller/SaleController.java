package com.mspr_kawa.sales.main.controller;

import com.mspr_kawa.sales.main.model.Sale;
import com.mspr_kawa.sales.main.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/orders")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    // Endpoint pour récupérer une liste des orders
    @GetMapping
    public ResponseEntity<List<Sale>> getAllOrders() {
        List<Sale> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }

    // Endpoint pour récupérer un client par son identifiant
    @GetMapping("/{id}")
    public ResponseEntity<Sale> getOrderById(@PathVariable UUID id) {
        Sale Sale = saleService.getSaleById(id);
        if (Sale != null) {
            return ResponseEntity.ok(Sale);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint pour créer un nouveau order
    @PostMapping
    public ResponseEntity<Sale> createOrder(@RequestBody Sale Sale) {
        Sale createdSale = saleService.createSale(Sale);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSale);
    }

    // Endpoint pour mettre à jour un client existant
    @PutMapping("/{id}")
    public ResponseEntity<Sale> updateOrder(@PathVariable UUID id, @RequestBody Sale Sale) {
        Sale updatedSale = saleService.updateSale(id, Sale);
        if (updatedSale != null) {
            return ResponseEntity.ok(updatedSale);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint pour supprimer un order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
        boolean deleted = saleService.deleteSale(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
