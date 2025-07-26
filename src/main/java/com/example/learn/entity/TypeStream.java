package com.example.learn.entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "type_streams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeStream {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "streamType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<StreamSessions> streamSessions;
}
