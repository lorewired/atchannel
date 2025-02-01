package com.lore.atchannel_api.models.message;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lore.atchannel_api.models.thread.MessageThread;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "visual_id", updatable = false, unique = true)
    private String visual_id;

    @Column(name = "nickname", updatable = false, nullable = false)
    private String nickname;

    @Column(name = "content", length = 4000, nullable = false)
    private String content;

    @Column(name = "image_url")
    private String image_url;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime created_at;

    @Column(name = "message_number", nullable = false, updatable = false)
    private int message_number;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "thread_id", nullable = false)
    private MessageThread thread;
    
}