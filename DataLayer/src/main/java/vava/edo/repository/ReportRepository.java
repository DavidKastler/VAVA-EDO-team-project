package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vava.edo.model.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    @Query("select r from Report r where r.status = 'PENDING'")
    List<Report> findAllByStatusIsPending();
}
