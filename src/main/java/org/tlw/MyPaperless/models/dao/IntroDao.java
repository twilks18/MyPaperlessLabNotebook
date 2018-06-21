package org.tlw.MyPaperless.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tlw.MyPaperless.models.Intro;
import org.tlw.MyPaperless.models.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IntroDao extends JpaRepository<Intro, Integer > {

    List<Intro> findAll();



     Intro findById(Integer id);

  List<Intro> findByUser(User user, Pageable pageable);
}
