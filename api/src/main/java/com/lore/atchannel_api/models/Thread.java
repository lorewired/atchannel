package com.lore.atchannel_api.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name="THREAD")
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    

}
