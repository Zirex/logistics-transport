package com.ingeneo.logistics.entities;

import com.ingeneo.logistics.enumeration.State;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table
public class Warehouse {
    @Id
    @SequenceGenerator(name="warehouse_id_seq", sequenceName = "warehouse_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "warehouse_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "warehouse_id", unique = true, nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private boolean isInternational;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private State state;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="logistics_type_id", nullable=false)
    private LogisticsType logisticsType;
    @OneToMany(mappedBy = "warehouse")
    private Set<Route> routes;
    @OneToMany(mappedBy = "warehouse")
    private Set<Order> orders;
}
