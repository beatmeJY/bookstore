package com.develop.bookstore.global.dto;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseDataListDTO<T> {

    private int code = HttpServletResponse.SC_OK;
    private String msg = "성공";

    private List<T> data = new ArrayList<>();

    public ResponseDataListDTO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void addData(T o) {
        this.data.add(o);
    }


    public void setData(List<T> o) {
        this.data = o;
    }
}
