package com.example.maktabproject.model.view;

import com.example.maktabproject.model.enums.OrderState;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Immutable
@Entity
@Table(name = "order_view", schema = "public")
public class OrderView {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String orderDetails;
    private String orderAddress;
    private OrderState orderState;
    private String subServiceName;
    private String mainServiceName;
    private Double subServiceBasePrice;
    private LocalDateTime orderStartingDate;
    private LocalDateTime offerCompletionDate;
    private LocalDateTime offerStartingDate;
    private Double offerSuggestedPrice;
    private String customerUsername;
    private String expertUsernames;
    private Long customerId;
    private Long expertId;
    private Long subServiceId;
    private Long mainServiceId;
}
