package org.tlw.MyPaperless.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.tlw.MyPaperless.models.Reagent;

public interface ReagentDao extends CrudRepository<Reagent, Integer > {
}
