package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vava.edo.model.Report;
import vava.edo.model.Role;

public interface ReportRepository extends JpaRepository<Report, Integer> {

}
