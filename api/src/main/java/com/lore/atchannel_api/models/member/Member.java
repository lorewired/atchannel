package com.lore.atchannel_api.models.member;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(
        name = "ip",
        unique = true,
        updatable = false,
        nullable = false
    )
    private String ip;
    
    @Column(
        name = "city",
        nullable = false
    )
    private String city;

    @Column(name = "coords")
    private String coords;

    @CreationTimestamp
    @Column(
        name = "enter_at",
        updatable = false,
        nullable = false
    )
    private LocalDateTime enter_at;
    
}
