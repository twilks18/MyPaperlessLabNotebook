package org.tlw.MyPaperless.models.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tlw.MyPaperless.models.Conclusions;

@Repository
@Transactional
public interface ConclusionsDao extends CrudRepository<Conclusions, Integer> {

    Conclusions findByConid(Integer conid);


}
