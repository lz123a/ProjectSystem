package com.learn.repository;

import com.learn.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
  //  public User findByUidAndPswordAAndIdentity(String uid,String psword,int idenity);

    public User findByUidAndPsword(String uid,String psword);

//    public int findteacher_idByuid(String uid);

}
