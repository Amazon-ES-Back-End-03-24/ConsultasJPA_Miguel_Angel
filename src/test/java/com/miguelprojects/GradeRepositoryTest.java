package com.miguelprojects;


import com.miguelprojects.model.Course;
import com.miguelprojects.model.Grade;
import com.miguelprojects.model.Section;
import com.miguelprojects.repository.CourseRepository;
import com.miguelprojects.repository.GradeRepository;
import com.miguelprojects.repository.SectionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GradeRepositoryTest {

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    SectionRepository sectionRepository;

    private List<Course> courses;
    private List<Section> sections;
    private List<Grade> grades;

    @BeforeEach
    void setUp() {

        courses = courseRepository.saveAll(List.of(
                new Course("CS101", "Intro to java"),
                new Course("CS103", "Databases")
        ));

        sections = sectionRepository.saveAll(List.of(
                new Section("CS101-A", "CS101",  (short) 1802, "Balderez"),
                new Section("CS101-B", "CS101",  (short) 1650, "Su"),
                new Section("CS103-A", "CS103",  (short) 1200, "Rojas"),
                new Section("CS103-B", "CS103",  (short) 1208, "Tonno")
        ));

        grades = gradeRepository.saveAll(List.of(
                new Grade("Maya Charlotte", "CS101-A", 98),
                new Grade("James Fields", "CS101-A", 82),
                new Grade("Michael Alcocer", "CS101-B", 65),
                new Grade("Maya Charlotte", "CS103-A", 89),
                new Grade("Tomas Lacroix", "CS101-A", 99),
                new Grade("Sara Bisat", "CS101-A", 87),
                new Grade("James Fields", "CS101-B", 46),
                new Grade("Helena Sepulvida", "CS103-A", 72)
        ));

    }

    @AfterEach
    void tearDown() {
        gradeRepository.deleteAll();
        sectionRepository.deleteAll();
        courseRepository.deleteAll();
    }

    @Test
    void findAverageScoreBySection_ValidData_SectionAndAVG() {
        List<Object[]> result = gradeRepository.findAverageScoreBySection();

        assertEquals(3, result.size());
        assertEquals(55.5, result.get(0)[1]);

    }

    @Test
    public void testJPA_and(){
        Grade grade = gradeRepository.findByStudentNameAndSectionId("Michael Alcocer", "CS103-A");
        assertEquals(grade.getScore(), 65);
    }

    @Test
    public void testJPA_containing(){
        Grade grade = gradeRepository.findByStudentNameContaining("ma");
        assertEquals(grade.getScore(), 98);
    }

    @Test
    public void testJPA_like(){
        Grade grade = gradeRepository.findByStudentNameLike("_a%");
        assertEquals(grade.getScore(), 98);
    }

    @Test
    void findAverageScoreBySectionWithCapacity_ValidData_SectionAndAVG() {
        List<Object[]> result = gradeRepository.findAverageScoreBySectionWithCapacity(2L);
        assertEquals("CS101-B", result.get(0)[0]);
        assertEquals(55.5, result.get(0)[1]);
    }

    @Test
    void findAverageScoreBySectionWithCapacityNative_ValidData_SectionAndAVG() {
        List<Object[]> result = gradeRepository.findAverageScoreBySectionWithCapacityNative(80);
        assertEquals("Michael Alcocer", result.get(0)[0]);
        assertEquals(65.0, result.get(0)[1]);
    }

    @Test
    void update_ValidData(){
        List<Grade> grades = gradeRepository.findAll();
        Grade firstGrade = grades.get(0);
        firstGrade.setScore(60);
        gradeRepository.save(firstGrade);

        Grade firstRepoGrade = gradeRepository.findById(firstGrade.getScore()).get();
        assertEquals(firstRepoGrade.getScore(), 60);
        assertEquals(firstRepoGrade, firstGrade);


    }

    @Test
    void findAll_ValidData(){
        List<Grade> grades = gradeRepository.findAll();
        Assertions.assertEquals(8, grades.size());

    }

    @Test
    void delete_ValidData(){
        List<Grade> grades = gradeRepository.findAll();
        gradeRepository.delete(grades.get(0));
        Assertions.assertEquals(7, grades.size());
    }

}
