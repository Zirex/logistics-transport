package com.ingeneo.logistics.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ingeneo.logistics.enumeration.State;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table
@JsonIgnoreProperties({"warehouses", "discounts", "productsType", "hibernateLazyInitializer", "handler"})
public class LogisticsType implements Serializable {
    @Id
    @SequenceGenerator(name= "logistics_type_id_seq", sequenceName = "logistics_type_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "logistics_type_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name="logistics_type_id", unique = true, nullable = false)
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private State state;
    @OneToMany(mappedBy = "logisticsType")
    private Set<Warehouse> warehouses;
    @OneToMany(mappedBy = "logisticsType")
    private Set<Discount> discounts;
    @ManyToMany(mappedBy = "logisticsTypeSet")
    private Set<ProductType> productsType;
}
