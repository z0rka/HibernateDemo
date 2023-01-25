package com.example.hibernatedemo.dto;

import com.example.hibernatedemo.model.Mark;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Integer id;

    private String name;

    private String email;

    private List<Mark> mark;
}
