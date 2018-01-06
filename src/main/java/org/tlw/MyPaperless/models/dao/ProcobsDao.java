package org.tlw.MyPaperless.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tlw.MyPaperless.models.Procobs;
import org.tlw.MyPaperless.models.Reagent;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProcobsDao extends CrudRepository<Procobs, Integer > {


    List<Procobs> findAll();

    Procobs findByUid(Integer uid);

    List<Procobs> findByProcedure(String procedure);

    List<Procobs> findByObservations(String observations);

}
