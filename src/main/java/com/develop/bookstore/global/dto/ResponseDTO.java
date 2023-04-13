package com.develop.bookstore.global.dto;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseDTO {

    private int code = HttpServletResponse.SC_OK;
    private String msg = "성공";

    public ResponseDTO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
