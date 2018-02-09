package org.tlw.MyPaperless.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tlw.MyPaperless.models.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer >{

     List<User> findAll();

     User findByUid(Integer uid);

     User findByUsername(String uname);

}
