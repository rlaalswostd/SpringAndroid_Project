package com.example.new_order.entity;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "menu")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    private Category category;

    @Column(name="store_id")
    private String storeId;

    private String name;

    @Column(name = "price")
    private BigDecimal price;

    
    @Column(name = "is_available")
    private Boolean isAvailable = false;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.REMOVE)  // CASCADE 설정
    @JsonIgnore
    private List<OrderItem> orderItems;
}