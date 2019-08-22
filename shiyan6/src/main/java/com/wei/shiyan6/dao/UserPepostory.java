package com.wei.shiyan6.dao;

import com.wei.shiyan6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPepostory extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
