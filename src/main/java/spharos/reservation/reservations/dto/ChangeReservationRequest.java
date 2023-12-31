package spharos.reservation.reservations.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spharos.reservation.reservations.axon.command.PaymentStatus;
import spharos.reservation.reservations.axon.command.PaymentType;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ChangeReservationRequest {

    private String reservation_num;
    private String clientEmail; //사업자 이메일
    private String paymentType; //"0"
    private int totalAmount; //결제 금액
    private LocalDateTime approvedAt; //결제 완료,취소가 일어난 날짜와 시간 정보
    private String paymentStatus; //"0"
    private String paymentKey; //결제 고유 번호

}
