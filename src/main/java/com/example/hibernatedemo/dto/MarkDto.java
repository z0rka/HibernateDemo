package com.example.hibernatedemo.dto;

import com.example.hibernatedemo.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkDto {

    private Integer id;

    private Student student;

    private String discipline;

    private float value;
}
