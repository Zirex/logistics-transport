package com.ingeneo.logistics.entities;

import com.ingeneo.logistics.enumeration.State;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table
public class ProductType {
    @Id
    @SequenceGenerator(name = "product_type_id_seq", sequenceName = "product_type_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_type_id_seq")
    @Column(name="product_type_id", unique = true, nullable = false)
    private Long id;
    @Column(nullable = false)
    private String description;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private State state;
    @OneToMany(mappedBy = "productType")
    private Set<OrderItem> orderItems;
    @ManyToMany
    @JoinTable(name="logistics_product",
            joinColumns = @JoinColumn(name="product_type_id"),
            inverseJoinColumns = @JoinColumn(name="logistics_type_id"))
    private Set<LogisticsType> logisticsTypeSet;
}
