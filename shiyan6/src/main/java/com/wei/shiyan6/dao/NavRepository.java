package com.wei.shiyan6.dao;

import com.wei.shiyan6.model.Nav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NavRepository extends JpaRepository<Nav,Long> {
}
