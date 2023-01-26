package com.example.hibernatedemo.service;

import com.example.hibernatedemo.dto.MarkDto;
import com.example.hibernatedemo.dto.StudentDto;
import com.example.hibernatedemo.model.Mark;
import com.example.hibernatedemo.model.Student;
import com.example.hibernatedemo.repository.MarkRepository;
import com.example.hibernatedemo.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service to work with DB of students
 */
@Service
@RequiredArgsConstructor
@Slf4j

public class StudentService {
    public static final String DISCIPLINE_IS_NULL = "Discipline is null!";
    private final MarkRepository markRepository;
    private final StudentRepository studentRepository;
    private final ObjectMapper objectMapper;

    /**
     * Method to add student
     *
     * @param name  - name
     * @param email - email
     */
    @Transactional
    public void addStudent(String name, String email) {
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);

        studentRepository.add(student);

        log.info("Student with name " + name + " added");
    }


    /**
     * Method to add mark to student
     *
     * @param studentId  - id
     * @param discipline - type of lesson
     * @param value      - mark
     */
    @Transactional
    public void addMark(int studentId, String discipline, float value) {
        Student student = studentRepository.getById(studentId);

        if (student == null) {
            log.error("User with id " + studentId + " not found");
            return;
        } else if (discipline == null) {
            log.error(DISCIPLINE_IS_NULL);
            return;
        }

        Mark mark = new Mark();
        mark.setDiscipline(discipline);
        mark.setValue(value);
        mark.setStudent(student);

        markRepository.add(mark);

        log.info("Mark with discipline " + discipline + " added");
    }

    /**
     * Method to delete student
     *
     * @param id - id
     */
    @Transactional
    public void delete(int id) {
        studentRepository.delete(id);
        log.info("Student with id " + id + " deleted");
    }

    /**
     * Method to update student info
     *
     * @param id    - id
     * @param name  - name
     * @param email - new email
     */
    @Transactional
    public void updateStudent(int id, String name, String email) {
        Student student1 = studentRepository.getById(id);

        if (name == null || email == null || student1 == null) {
            log.error("Something wrong with data");
            return;
        }

        studentRepository.update(id,name,email);

        log.info("Student with id " + id + " updated");
    }

    /**
     * Method to update mark for student
     *
     * @param studentId  - id
     * @param discipline - mark to delete
     * @param value      - new value
     */
    @Transactional
    public void updateMark(int studentId, String discipline, float value) {
        if (discipline == null) {
            log.error(DISCIPLINE_IS_NULL);
            return;
        }
        Student student = studentRepository.getById(7);

        if (student == null || student.getMark().stream().noneMatch(mark -> mark.getDiscipline().equals(discipline))) {
            log.error("No such discipline " + discipline + " registered for that student");
            return;
        }

        markRepository.update(studentId, discipline, value);

        log.info("Mark with studentId " + studentId + " updated");
    }

    /**
     * Method to delete mark from student
     *
     * @param studentId  - id
     * @param discipline - mark to delete
     */
    @Transactional
    public void deleteMark(int studentId, String discipline) {
        if (discipline == null) {
            log.error(DISCIPLINE_IS_NULL);
            return;
        }

        markRepository.delete(studentId, discipline);

        log.info("Mark with studentId " + studentId + "and discipline " + discipline + " deleted");
    }

    /**
     * Method to get all students from DB
     *
     * @return List<StudentDto>
     */

    public StudentDto getById(int id) {
        Student student = studentRepository.getById(id);

        if (student == null) {
            log.error("No student with such id!");
            return null;
        }

        List<MarkDto> mark = student.getMark().stream()
                .map(mark1 -> objectMapper.convertValue(mark1, MarkDto.class))
                .toList();

        StudentDto studentDto = objectMapper.convertValue(student, StudentDto.class);
        studentDto.setMark(mark);

        log.info("Student with studentId " + id + " found");
        return studentDto;
    }

    /**
     * Method to get all students from DB
     *
     * @return List<StudentDto>
     */
    public List<StudentDto> getAll() {
        List<Student> students = studentRepository.getAll();

        log.info("Students found");

        return students.stream().map(student -> {
            StudentDto studentDto = objectMapper.convertValue(student, StudentDto.class);

            List<MarkDto> collect = student.getMark().stream()
                    .map(mark -> objectMapper.convertValue(mark, MarkDto.class))
                    .toList();

            studentDto.setMark(collect);

            return studentDto;
        }).toList();
    }
}
