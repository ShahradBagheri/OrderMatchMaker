package com.example.maktabproject.model.view;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Immutable
@Entity
@Table(name = "expert_sub_service_view", schema = "public")
public class ExpertSubServiceView {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private Long expertId;
    private String expertFirstname;
    private String expertLastname;
    private String expertEmail;
    private Long subServiceId;
    private String subServiceName;
    private Long subServiceBasePrice;
}
