package com.ingeneo.logistics.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ingeneo.logistics.enumeration.State;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table
public class Discount implements Serializable {
    @Id
    @SequenceGenerator(name = "discount_id_seq", sequenceName = "discount_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discount_id_seq")
    @Column(name = "discount_id", unique = true, nullable = false)
    private Long id;
    @Column(nullable = false)
    private String rule;
    @Column(scale = 2, precision = 3)
    private BigDecimal value;
    @Column(nullable = false)
    private State state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logistics_type_id", nullable = false)
    private LogisticsType logisticsType;
    @OneToMany(mappedBy = "discount")
    private Set<OrderItem> orderItems;
}
