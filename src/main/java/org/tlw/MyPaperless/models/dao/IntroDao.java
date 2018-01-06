package org.tlw.MyPaperless.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tlw.MyPaperless.models.Intro;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IntroDao extends CrudRepository<Intro, Integer > {

    List<Intro> findAll();

     Intro findByUid(Integer uid);

      List<Intro> findByTitle(String title);
}
