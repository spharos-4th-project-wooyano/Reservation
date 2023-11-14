package shparos.reservation.reservations.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shparos.reservation.global.common.domain.BaseEntity;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "reservation")
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private ReservationGoods reservationGoods;
    @Column(nullable = false, length = 50, name = "user_email")
    private String userEmail;
    @Column(nullable = false, name = "service_id")
    private Long serviceId;
    @Column(nullable = false, name = "worker_id")
    private Long workerId;
    @Column(nullable = false, name = "reservation_date")
    private LocalDate reservationDate;
    @Column(nullable = false, name = "service_start")
    private LocalTime serviceStart;
    @Column(nullable = false, name = "service_end")
    private LocalTime serviceEnd;
    @Column(nullable = false)
    @Convert(converter = ReservationStateConverter.class)
    private ReservationState reservationState;
    @Column(nullable = false, name = "payment_amount")
    private Integer paymentAmount;
    @Column(length = 50, name = "cancel_desc")
    private String cancelDesc;
    @Column(length = 100, name = "request")
    private String request;
    @Column(name = "pet_check")
    private Boolean petCheck;
    @Column(nullable = false, length = 10, name = "reservation_num")
    private String reservationNum;
    @Column(nullable = false, length = 30, name = "address")
    private String address;

}