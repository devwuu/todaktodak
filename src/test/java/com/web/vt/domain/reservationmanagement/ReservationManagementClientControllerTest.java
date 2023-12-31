package com.web.vt.domain.reservationmanagement;

import com.web.vt.common.ControllerTestSupporter;
import com.web.vt.domain.common.enums.UsageStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithUserDetails;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static com.web.vt.common.RestDocsConfiguration.field;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithUserDetails(userDetailsServiceBeanName = "employeeDetailService", value = "test")
class ReservationManagementClientControllerTest extends ControllerTestSupporter {

    private final ZonedDateTime START = LocalDateTime.now().atZone(ZoneOffset.UTC);
    private final ZonedDateTime END = START.plusDays(1);

    @Test
    @DisplayName("동물병원 id로 예약관리 정보를 조회합니다")
    public void findByClinicId() throws Exception {
        mvc.perform(RestDocumentationRequestBuilders.get("/v1/client/reservation-management/info"))
                .andDo(docs.document())
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test @DisplayName("예약관리 정보를 수정합니다")
    public void update() throws Exception {

        ReservationManagementVO vo = new ReservationManagementVO()
                .id(2L)
                .status(UsageStatus.NOT_USE)
                .startDateTime(START.toInstant())
                .endDateTime(END.toInstant());

        mvc.perform(post("/v1/client/reservation-management/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(vo)))
                .andDo(docs.document(
                                requestFields(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("예약 관리 정보 id"),
                                        fieldWithPath("clinicId").ignored(),
                                        fieldWithPath("status").type(JsonFieldType.STRING).attributes(field("constraints", "[ Use | NotUse | Deleted ]")).description("상태"),
                                        fieldWithPath("startDateTime").type(JsonFieldType.STRING).attributes(field("constraints", "YYYY-MM-DDTMM:mm:ss.sssZ")).description("예약시작일시 (UTC)").optional(),
                                        fieldWithPath("endDateTime").type(JsonFieldType.STRING).attributes(field("constraints", "YYYY-MM-DDTMM:mm:ss.sssZ")).description("예약종료일시 (UTC)").optional(),
                                        fieldWithPath("createdAt").ignored(),
                                        fieldWithPath("updatedAt").ignored(),
                                        fieldWithPath("createBy").ignored(),
                                        fieldWithPath("updatedBy").ignored()
                                )
                        )
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

}