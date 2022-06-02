package ru.geekbrains.Course9Lesson7.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.Course9Lesson7.entities.Student;
import ru.geekbrains.Course9Lesson7.exceptions.ResourceNotFoundException;
import ru.geekbrains.Course9Lesson7.services.StudentService;

import java.util.List;

@RestController
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found, id: " + id));
    }

    @GetMapping("/students/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }

    @GetMapping("/students/change_age")
    public void changeAge(@RequestParam Long studentId, @RequestParam Integer delta) {
        studentService.changeAge(studentId, delta);
    }

    @GetMapping("/students/age_between")
    public List<Student> findStudentsByAgeBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "100") Integer max) {
        return studentService.findByAgeBetween(min, max);
    }
}
