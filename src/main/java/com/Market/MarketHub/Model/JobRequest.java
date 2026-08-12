package com.Market.MarketHub.Model;


import com.Market.MarketHub.Enum.JobRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Enumerated(EnumType.STRING)
    private JobRequestStatus status;

    private String position;

    @Column(nullable = false , updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime respondedAt;

    @PrePersist
    public void onCreate(){
        createdAt=LocalDateTime.now();
    }
}
