package com.ingeneo.logistics.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ingeneo.logistics.enumeration.State;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders", uniqueConstraints = {@UniqueConstraint(columnNames = {"orderNumber"})})
public class Order {
    @Id
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "order_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "order_id", unique = true, nullable = false)
    private Long id;
    @Column(length = 10, nullable = false)
    private UUID orderNumber;
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(nullable = false)
    private LocalDate deliveryDate;
    @Column(length = 15, nullable = false)
    private String transportNumber;
    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal total;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private State state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;
    @OneToMany(mappedBy = "order")
    private Set<Route> routes;
    @OneToMany(mappedBy = "order", cascade = {CascadeType.ALL})
    @JsonManagedReference("workDirective-order-orderItem")
    private Set<OrderItem> orderItems;
}
