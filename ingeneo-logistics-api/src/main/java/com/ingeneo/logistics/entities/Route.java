package com.ingeneo.logistics.entities;

import com.ingeneo.logistics.enumeration.State;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table
public class Route {
    @Id
    @SequenceGenerator(name = "route_id_seq", sequenceName = "route_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "route_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name="route_id", unique = true, nullable = false)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private State state;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="warehouse_id", nullable = false)
    private Warehouse warehouse;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
