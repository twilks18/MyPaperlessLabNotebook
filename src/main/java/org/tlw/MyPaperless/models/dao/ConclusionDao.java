package org.tlw.MyPaperless.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tlw.MyPaperless.models.Conclusion;


import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public interface ConclusionDao extends CrudRepository<Conclusion, Integer > {


     List<Conclusion> findAll();

    Conclusion findByConid(Integer id);

     List<Conclusion> findByConclusions(String conclusions);
}
