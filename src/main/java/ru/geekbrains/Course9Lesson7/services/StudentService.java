package ru.geekbrains.Course9Lesson7.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.Course9Lesson7.entities.Student;
import ru.geekbrains.Course9Lesson7.exceptions.ResourceNotFoundException;
import ru.geekbrains.Course9Lesson7.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public void changeAge(Long studentId, Integer delta) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Unable to change student's age. Student not found, id: " + studentId));
        student.setAge(student.getAge() + delta);
    }

    public List<Student> findByAgeBetween(Integer min, Integer max) {
        return studentRepository.findAllByAgeBetween(min, max);
    }
}
