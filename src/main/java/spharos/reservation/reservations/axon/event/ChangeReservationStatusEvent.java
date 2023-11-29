package spharos.reservation.reservations.axon.event;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spharos.reservation.reservations.domain.enumPackage.ReservationState;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChangeReservationStatusEvent {
    private String reservation_num;
    private ReservationState status;

    private String clientEmail;
    private String paymentType;
    private int totalAmount;
    private LocalDateTime approvedAt;
    private String paymentStatus;

    private String paymentKey;

}
