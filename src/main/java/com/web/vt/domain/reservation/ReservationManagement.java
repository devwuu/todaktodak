package com.web.vt.domain.reservation;

import com.web.vt.domain.clinic.VeterinaryClinic;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;

@Entity
@Table(name = "reservation_management")
@Getter @Setter
@Accessors(fluent = true, chain = true)
@NoArgsConstructor
public class ReservationManagement {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "clinic_id")
    private VeterinaryClinic clinic;

    @Column(name = "start_date_time")
    private Instant startDateTime;

    @Column(name = "end_date_time")
    private Instant endDateTime;

    public ReservationManagement(ReservationManagementVO vo) {
        id = vo.id();
        startDateTime = vo.startDateTime();
        endDateTime = vo.endDateTime();
    }

    public ReservationManagement addClinic(VeterinaryClinic clinic){
        this.clinic = clinic;
        return this;
    }
}
