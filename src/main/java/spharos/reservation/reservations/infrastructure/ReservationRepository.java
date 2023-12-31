package spharos.reservation.reservations.infrastructure;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spharos.reservation.reservations.domain.Reservation;
import spharos.reservation.reservations.domain.ReservationState;

public interface ReservationRepository extends JpaRepository<Reservation, Long>  {

    @Query(value = "SELECT r FROM Reservation r WHERE r.reservationGoods.id = :reservationGoods and r.workerId = :workerId")
    Optional<Reservation> findByReservationGoodsId(@Param("reservationGoods") Long reservationGoods, @Param("workerId") Long workerId);




    List<Reservation> findByUserEmailOrderByIdDesc(String email);
    List<Reservation> findByReservationNum(String reservationNum);

    @Query(value = "SELECT "
            + "DISTINCT reservationNum "
            + "FROM Reservation "
            + "WHERE userEmail = :email "
            + "ORDER BY reservationNum DESC ")
    Page<String> findUserReservationNumber(@Param("email") String email, Pageable pageable);

    @Query(value = "SELECT "
            + "DISTINCT reservationNum "
            + "FROM Reservation "
            + "WHERE userEmail = :email "
            + "AND reservationState = :state "
            + "ORDER BY reservationNum DESC ")
    Page<String> findUserReservationNumberAndState(@Param("email") String email,
                                                   @Param("state") ReservationState state,
                                                   Pageable pageable);


    @Query("SELECT r FROM Reservation r WHERE r.reservationNum = :reservationNum")
   Reservation findByReservationNumOne(@Param("reservationNum") String reservationNum);

    @Query("SELECT r FROM Reservation r WHERE r.reservationNum = :reservationNum")
    List<Reservation> findByReservationNumList(@Param("reservationNum") String reservationNum);

    @Query("SELECT r FROM Reservation r join fetch r.reservationGoods g WHERE r.serviceId = :serviceId and r.reservationState = :reservationState")
    List<Reservation> findByReservationStatusWait(@Param("serviceId") Long serviceId, @Param("reservationState") ReservationState reservationState);

}
