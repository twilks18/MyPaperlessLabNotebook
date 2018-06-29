package org.tlw.MyPaperless.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tlw.MyPaperless.models.Proced;

@Repository
@Transactional
public interface ProcedureDao extends JpaRepository<Proced,Integer> {

    Proced findByPid(Integer pid);

    boolean exists(Integer primaryKey);
}
