package org.tlw.MyPaperless.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tlw.MyPaperless.models.Conclusions;

@Repository
@Transactional
public interface ConclusionsDao extends JpaRepository<Conclusions, Integer> {

    Conclusions findByConid(Integer conid);

    boolean exists(Integer primaryKey);


}
