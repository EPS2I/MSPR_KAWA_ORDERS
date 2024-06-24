package com.mspr_kawa.sales.main.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    @Id
    @UuidGenerator
    private UUID id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "sale_products", joinColumns = @JoinColumn(name = "sale_id"))
    @Column(name = "product_id")
    private Set<UUID> products = new HashSet<>();
    private UUID customer;

    @CreationTimestamp
    private Date createdAt;
}
