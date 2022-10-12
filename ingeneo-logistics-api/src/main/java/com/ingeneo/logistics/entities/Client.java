package com.ingeneo.logistics.entities;

import com.ingeneo.logistics.enumeration.State;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table
public class Client {
    @Id
    @SequenceGenerator(name = "client_id_seq", sequenceName = "client_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "client_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "client_id", unique = true, nullable = false)
    private Long id;
    @Column(length = 11, nullable = false)
    private String document;
    @Column(length = 60, nullable = false)
    private String firstname;
    @Column(length = 70, nullable = false)
    private String lastname;
    @Column(length = 15, nullable = false)
    private String phone;
    @Column(length = 70, nullable = false)
    private String email;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private State state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;
    @OneToMany(mappedBy = "client")
    private Set<Order> orders;
}
