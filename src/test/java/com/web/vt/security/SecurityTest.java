package com.web.vt.security;

import com.web.vt.common.ControllerTestSupporter;
import com.web.vt.domain.employee.EmployeeVO;
import com.web.vt.domain.user.AdminVO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
public class SecurityTest extends ControllerTestSupporter {

    @Test @DisplayName("시스템 관리자로 로그인합니다.")
    public void adminLogin() throws Exception {
        AdminVO vo = new AdminVO()
                .id("test")
                .password("1234");

        mvc.perform(post("/admin/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(vo)))
                .andDo(
                        docs.document(
                                requestFields(
                                        fieldWithPath("id").type(JsonFieldType.STRING).description("로그인 id"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                        fieldWithPath("status").ignored(),
                                        fieldWithPath("createBy").ignored(),
                                        fieldWithPath("createdAt").ignored(),
                                        fieldWithPath("updatedBy").ignored(),
                                        fieldWithPath("updatedAt").ignored()
                                )
                        )
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test @DisplayName("동물병원 관리자로 로그인합니다")
    public void employeeAdminLogin() throws Exception {
        EmployeeVO vo = new EmployeeVO()
                .id("test")
                .password("1234");

        mvc.perform(post("/client/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(vo)))
                .andDo(
                        docs.document(
                                requestFields(
                                        fieldWithPath("id").type(JsonFieldType.STRING).description("로그인 id"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                        fieldWithPath("role").ignored(),
                                        fieldWithPath("clinicId").ignored(),
                                        fieldWithPath("status").ignored(),
                                        fieldWithPath("position").ignored(),
                                        fieldWithPath("createBy").ignored(),
                                        fieldWithPath("createdAt").ignored(),
                                        fieldWithPath("updatedBy").ignored(),
                                        fieldWithPath("updatedAt").ignored()
                                )
                        )
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test @DisplayName("동물병원 일반 사용자로 로그인합니다")
    public void employeeUserLogin() throws Exception {
        EmployeeVO vo = new EmployeeVO()
                .id("user")
                .password("1234");

        mvc.perform(post("/client/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(vo)))
                .andDo(
                        docs.document(
                                requestFields(
                                        fieldWithPath("id").type(JsonFieldType.STRING).description("로그인 id"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                        fieldWithPath("role").ignored(),
                                        fieldWithPath("clinicId").ignored(),
                                        fieldWithPath("status").ignored(),
                                        fieldWithPath("position").ignored(),
                                        fieldWithPath("createBy").ignored(),
                                        fieldWithPath("createdAt").ignored(),
                                        fieldWithPath("updatedBy").ignored(),
                                        fieldWithPath("updatedAt").ignored()
                                )
                        )
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test @DisplayName("시스템 관리자 권한이 필요한 api를 요청합니다")
    public void adminApi() throws Exception {
        mvc.perform(get("/v1/admin/test")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJsb2NhbGhvc3Q6ODA4MCIsImlkIjoidGVzdCIsImV4cCI6MTY5MTY0MjMzMn0.rWeZf1h00PAbVfd3Ipa9rfBgM3HqF4G97jTJ_uapszw"))
                .andDo(
                        docs.document(
                                requestHeaders(
                                        headerWithName(HttpHeaders.AUTHORIZATION).description("Access Token")
                                )
                        )
                )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test @DisplayName("동물병원 관리자 권한이 필요한 api를 요청합니다")
    public void employeeAdminApi() throws Exception {
        mvc.perform(get("/v1/client/test")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJsb2NhbGhvc3Q6ODA4MCIsImlkIjoidGVzdCIsImV4cCI6MTY5MTY0MjI5OH0.9_AqFdwFDHnoT5hzrocls2KpagRxVgAaPvwkf7LLOEo"))
                .andDo(
                        docs.document(
                                requestHeaders(
                                        headerWithName(HttpHeaders.AUTHORIZATION).description("Access Token")
                                )
                        )
                )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test @DisplayName("동물병원 관리자 권한이 필요한 api를 요청합니다")
    public void employeeUserApi() throws Exception {
        mvc.perform(get("/v1/client/test")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJsb2NhbGhvc3Q6ODA4MCIsImlkIjoidXNlciIsImV4cCI6MTY5MTY0MjE3Mn0.FBF5IeDE0qNaqoMTA8iIz4eZ7cPdYPr4AIdo-D5CiWk"))
                .andDo(
                        docs.document(
                                requestHeaders(
                                        headerWithName(HttpHeaders.AUTHORIZATION).description("Access Token")
                                )
                        )
                )
                .andDo(print())
                .andExpect(status().isForbidden());
    }

}
