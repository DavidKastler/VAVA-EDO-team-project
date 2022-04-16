package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vava.edo.model.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {

}
