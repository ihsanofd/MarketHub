package com.Market.MarketHub.Model;

import com.Market.MarketHub.Enum.EmployeePosition;
import com.Market.MarketHub.Enum.EmployeeStatus;
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
public class StoreEmployee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeePosition position;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime joinedAt;


    @PrePersist
    public void onCreate() {
        joinedAt = LocalDateTime.now();
    }
}

