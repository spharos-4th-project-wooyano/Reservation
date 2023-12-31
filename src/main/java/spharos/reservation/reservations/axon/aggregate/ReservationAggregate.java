package spharos.reservation.reservations.axon.aggregate;



import static org.axonframework.modelling.command.AggregateLifecycle.apply;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import spharos.reservation.reservations.axon.command.CancelReservationCommand;
import spharos.reservation.reservations.axon.command.ChangeReservationStatusCommand;
import spharos.reservation.reservations.axon.command.CreateReservationCommand;
import spharos.reservation.reservations.axon.event.CancelReservationStatusEvent;
import spharos.reservation.reservations.axon.event.ChangeReservationStatusEvent;
import spharos.reservation.reservations.axon.event.ReservationCreateEvent;
import spharos.reservation.reservations.domain.ReservationState;

@Aggregate
@Slf4j
@NoArgsConstructor
public class ReservationAggregate {

    @AggregateIdentifier
    private String reservation_num;
    private ReservationState reservationState;

    @CommandHandler
    public ReservationAggregate(CreateReservationCommand command) {
        log.info("CreateReservationCommand");

        ReservationCreateEvent reservationCreateEvent = new ReservationCreateEvent(
                command.getReservationGoodsId(), command.getServiceId(), command.getWorkerId(), command.getUserEmail(),
                command.getReservationDate(), command.getServiceStart(), command.getServiceEnd(),
                command.getPaymentAmount(),
                command.getRequest(), command.getReservationNum(), command.getAddress(), command.getStatus());
        apply(reservationCreateEvent);
    }
    @CommandHandler
    public void on(ChangeReservationStatusCommand command){
        ChangeReservationStatusEvent changeReservationStatusEvent = new ChangeReservationStatusEvent(
                command.getReservation_num(), command.getStatus(), command.getClientEmail(), command.getPaymentType(),
                command.getTotalAmount(), command.getApprovedAt(), command.getPaymentStatus(),command.getPaymentKey());
        apply(changeReservationStatusEvent);
    }

    @CommandHandler
    public void change(CancelReservationCommand command){
        CancelReservationStatusEvent cancelReservationStatusEvent = new CancelReservationStatusEvent(
                command.getReservationNum(),ReservationState.PAYMENT_CANCEL,command.getPaymentKey());
        apply(cancelReservationStatusEvent);
    }

    @EventSourcingHandler
    public void change(ChangeReservationStatusEvent event) {
        this.reservationState = event.getStatus();
    }


    @EventSourcingHandler
    public void createOrder(ReservationCreateEvent event) {
        log.info("ReservationCreateEvent");
        this.reservation_num = event.getReservationNum();
        this.reservationState = event.getStatus();
    }

    @EventSourcingHandler
    public void cancel(CancelReservationStatusEvent event){
        this.reservationState = event.getStatus();
    }



}
