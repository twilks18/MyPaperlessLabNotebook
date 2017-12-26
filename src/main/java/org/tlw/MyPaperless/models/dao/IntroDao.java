package org.tlw.MyPaperless.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.tlw.MyPaperless.models.Intro;

import java.util.List;


public interface IntroDao extends CrudRepository<Intro, Integer > {

    public List<Intro> findAll();

    public Intro findByIntroid(Integer introid);

    public List<Intro> findByTitle(String title);
}
