package spharos.reservation.reservations.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class NewReservationDto {

    private Long reservationGoodsId;
    private Long serviceId;
    private Long workerId;
    private String userEmail;
    private String reservationDate;
    private String serviceStart;
    private String serviceEnd;
    private Integer paymentAmount;
    private String request;
    private String address;

}
