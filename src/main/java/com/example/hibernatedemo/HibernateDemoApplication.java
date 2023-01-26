package com.example.hibernatedemo;

import com.example.hibernatedemo.dto.StudentDto;
import com.example.hibernatedemo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
@Slf4j
public class HibernateDemoApplication {

    @Autowired
    private StudentService service;

    public static void main(String[] args) {
        SpringApplication.run(HibernateDemoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onInit() {
        addStudents();
        addMarks();
        getStudents();

        service.deleteMark(1, "PE");

        getStudent();

        service.updateStudent(1, "John", "john@hotmail.com");
        service.updateMark(2, "History", 50);

        getStudents();
        
        service.delete(3);
    }

    private void getStudent() {
        StudentDto student = service.getById(1);
        log.info(student.getName());
        student.getMark().forEach(markDto -> log.info(markDto.toString()));
    }

    private void getStudents() {
        List<StudentDto> students = service.getAll();
        students.forEach(studentDto -> {
            log.info(studentDto.toString());
            studentDto.getMark().forEach(markDto -> log.info("{} /t {}", markDto.getDiscipline(), markDto.getValue()));
        });
    }

    private void addMarks() {
        service.addMark(1, "PE", 100);
        service.addMark(1, "Math", 72.2f);

        service.addMark(2, "History", 0);
        service.addMark(2, "Math", 72.2f);

        service.addMark(3, "PE", 70);
        service.addMark(3, "Math", 71.3f);
        service.addMark(3, "History", 32.5f);
    }

    private void addStudents() {
        service.addStudent("John", "john@gmail.com");
        service.addStudent("Tolik", "tolik@gmail.com");
        service.addStudent("Maksim", "maksim@gmail.com");
    }
}
