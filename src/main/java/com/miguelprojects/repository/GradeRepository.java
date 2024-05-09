package com.miguelprojects.repository;

import com.miguelprojects.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    Grade findByStudentNameAndSectionId(String studentName, String section);
    Grade findByStudentNameContaining(String studentName);
    Grade findByStudentNameLike(String studentName);

    @Query("SELECT sectionId, AVG(score) FROM Grade GROUP BY sectionId ORDER BY AVG(score) ASC")
    List<Object[]> findAverageScoreBySection();

    @Query("SELECT sectionId, AVG(score) FROM Grade GROUP BY sectionId HAVING COUNT(*) >= ?1 ORDER BY AVG(score)")
    List<Object[]> findAverageScoreBySectionWithCapacity(Long minEnrolled);

    @Query("SELECT sectionId, AVG(score) FROM Grade GROUP BY sectionId HAVING COUNT(*) >= :minEnrolled ORDER BY AVG(score)")
    List<Object[]> findAverageScoreBySectionWithCapacity2(@Param("minEnrolled") long minEnrolled);

    @Query(value="SELECT student_name, CAST(AVG(score) AS double) FROM grade GROUP BY student_name HAVING AVG(score) < :score  ORDER BY student_name DESC", nativeQuery=true)
    List<Object[]> findAverageScoreBySectionWithCapacityNative(@Param("score") double score);

    @Query(value="SELECT Grade.studentName as Nombre, Grade.score as Calificacion FROM Grade WHERE score > 50 ORDER BY score")
    List<Object[]> findScoreGreaterThan50();

    @Query(value="SELECT Grade.studentName as Nombre, Grade.score as Calificación FROM Grade WHERE score > 70 ORDER BY studentName")
    List<Object[]> findScoreGreaterThan70Sorted();

    @Query(value="SELECT Grade.studentName as Nombre, Section.id as Seccion FROM Grade WHERE Section.id IS NOT 'CS101-A'")
    List<Object[]> StudentsExcludingSection();
}
