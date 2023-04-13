package com.develop.bookstore.global.dto;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseDataDTO<T> {

    private int code = HttpServletResponse.SC_OK;
    private String msg = "성공";

    private T data;

    public ResponseDataDTO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setData(T o) {
        this.data = o;
    }
}
