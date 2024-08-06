package org.example.auctionflowbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "item")
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String productImageUrl;
    private String title;
    private String productStatus;
    private String description;
    private BigDecimal startingBid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime auctionEndTime;
    private String itemBidStatus;

    @OneToMany(mappedBy = "item")
    private Set<Bid> bids;

    @OneToMany(mappedBy = "item")
    private Set<Declaration> declarations;

    @OneToMany(mappedBy = "item")
    private Set<Like> likes;
}
