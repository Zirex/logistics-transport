package com.ingeneo.logistics.entities;

import com.ingeneo.logistics.enumeration.State;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table
public class DocumentType {
    @Id
    @SequenceGenerator(name = "document_type_id_seq", sequenceName = "document_type_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "document_type_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "document_type_id", unique = true, nullable = false)
    private Integer id;
    @Column(length = 3, nullable = false)
    private String code;
    @Column(length = 70, nullable = false)
    private String description;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private State state;
    @OneToMany(mappedBy = "documentType")
    private Set<Client> clients;
}
