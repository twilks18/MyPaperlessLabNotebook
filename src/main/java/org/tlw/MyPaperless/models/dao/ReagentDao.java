package org.tlw.MyPaperless.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tlw.MyPaperless.models.Reagent;


import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ReagentDao extends CrudRepository<Reagent, Integer > {

     List<Reagent> findAll();

     Reagent findByChemid(Integer chemid);

     List<Reagent> findByChemName(String chemname);
}
