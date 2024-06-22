package com.example.cyber.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "reviews")
@Table(name = "reviews")
public class Review {

    @Id
    @SequenceGenerator(sequenceName = "review_seq", name = "review_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "productId")
    private Long productId;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "rating")
    private int rating;

    @Column(name = "text")
    private String text;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public void setText(String text) {
        this.text = text;
    }
}
