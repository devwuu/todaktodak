package com.web.vt.domain.reservationmanagement;

import com.web.vt.domain.clinic.VeterinaryClinicService;
import com.web.vt.domain.clinic.VeterinaryClinicVO;
import com.web.vt.domain.common.enums.UsageStatus;
import com.web.vt.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationManagementService {

    private final ReservationManagementRepository reservationManagementRepository;
    private final VeterinaryClinicService clinicService;

    public ReservationManagementVO save(ReservationManagementVO vo){

        VeterinaryClinicVO clinic = new VeterinaryClinicVO().id(vo.clinicId()).status(UsageStatus.USE);
        VeterinaryClinicVO findClinic = clinicService.findByIdAndStatus(clinic);

        ReservationManagement management = new ReservationManagement(vo)
                .addClinic(findClinic);

        ReservationManagement saved = reservationManagementRepository.save(management);

        return new ReservationManagementVO(saved).clinicId(saved.clinic().id());
    }

    // need upsert method ?
    public ReservationManagementVO update(ReservationManagementVO vo){

        Optional<ReservationManagement> find = reservationManagementRepository.findById(vo.id());
        if(find.isEmpty()){
            throw new NotFoundException("NOT EXIST MANAGEMENT");
        }
        ReservationManagement saved = find.get()
                .status(vo.status())
                .endDateTime(vo.endDateTime())
                .startDateTime(vo.startDateTime());

        return new ReservationManagementVO(saved).clinicId(vo.clinicId());
    }


}