package ru.geekbrains.Course9Lesson7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.Course9Lesson7.entities.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // @Query("select s from Student s where s.age between ?1 and ?2")
    List<Student> findAllByAgeBetween(Integer min, Integer max);

    // @Query("select s from Student s where s.name = :name")
    Optional<Student> findByName(String name);

    @Query("select s from Student s where s.age < 20")
    List<Student> findLowRatingStudents();

    @Query("select s.age from Student s where s.name = ?1")
    Integer hqlGetAgeByName(String name);

    @Query(value = "select age from students where name = :name", nativeQuery = true)
    Integer nativeSqlGetAgeByName(String name);

// Если бы у студентов был List<Book>, то не ленивая загрузка книг:
// @Query("select s from Student s join fetch s.books where s.id = :id")
// Optional<Student> findByIdWithBooks(String name);
}