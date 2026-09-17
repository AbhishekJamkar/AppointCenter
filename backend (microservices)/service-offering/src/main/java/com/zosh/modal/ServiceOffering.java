package com.zosh.modal;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class ServiceOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int duration;

    @Column(name = "salon_id")
    private Long businessId;

    @Column(nullable = false)
    private boolean available;

    @Column(nullable = false)
    private Long categoryId;


    private String image;


}
