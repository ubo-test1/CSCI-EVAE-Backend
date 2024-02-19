package com.back.csaback.DTO;

import com.back.csaback.Models.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RubQRequest {
    private Integer rubriqueId;
    private List<Integer> qList;

    @Override
    public String toString() {
        return "RubQRequest{" +
                "rubriqueId=" + rubriqueId +
                ", qList=" + qList +
                '}';
    }
}
