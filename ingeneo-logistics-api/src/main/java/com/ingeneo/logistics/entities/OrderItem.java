package com.ingeneo.logistics.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ingeneo.logistics.enumeration.State;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table
public class OrderItem {
    @Id
    @SequenceGenerator(name = "order_item_id_seq", sequenceName = "order_item_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "order_item_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "order_item_id", unique = true, nullable = false)
    private Long id;
    @Column(nullable = false)
    private Integer quantity;
    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal unitPrice;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private State state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference("workDirective-order-orderItem")
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_type_id", nullable = false)
    private ProductType productType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_id", nullable = false)
    private Discount discount;
}
