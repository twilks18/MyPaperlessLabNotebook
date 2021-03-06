package org.tlw.MyPaperless.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tlw.MyPaperless.models.Observations;


@Repository
@Transactional
public interface ObservationsDao extends JpaRepository<Observations,Integer> {

    Observations findByObservid(Integer observid);
    boolean exists(Integer primaryKey);
}
