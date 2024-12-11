package com.example.new_order.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "tables")  // 테이블 이름을 명시적으로 지정
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private Integer tableId; // 테이블번호

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", nullable = false)
    private Store store; // 매장번호 (외래 키)

    @Column(name = "table_number", nullable = false, length = 10)
    private String tableNumber; // 테이블번호 (표시용)

    @Column(name = "is_occupied", nullable = false)
    private Boolean isOccupied = false; // 사용중 여부 (기본값: false)
}