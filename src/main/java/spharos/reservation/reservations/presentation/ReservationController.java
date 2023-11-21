package spharos.reservation.reservations.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spharos.reservation.global.common.response.BaseResponse;
import spharos.reservation.reservations.application.ReservationService;
import spharos.reservation.reservations.dto.CreateReservationDto;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "서비스 신청",
            description = "유저의 서비스 신청",
            tags = { "NewReservation" })
    @PostMapping("/create")
    public BaseResponse<?> reservationNewService(@RequestBody CreateReservationDto request) {

        reservationService.createReservation(request);

        return new BaseResponse<>();
    }

}