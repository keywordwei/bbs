package com.wei.shiyan6.dao;

import com.wei.shiyan6.model.Luntan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
public interface LuntanRepository extends JpaRepository<Luntan,Long> {
    @Modifying
    @Query( value = "update t_luntan luntan set luntan.title=?1,luntan.posttime = ?2 where luntan.id=?3",nativeQuery = true)
    int update(String title,String updateTime,Long id);

    @Modifying
    @Query( value = "insert into t_review(id,owerUserName,reviewUserName,reviewMsg) values (?1,?2,?3,?4)",nativeQuery = true)
    int insertReviewMsg(Long id,String owerUserName,String reviewUserName,String reviewMsg);
    @Modifying
    @Query( value = "select * from t_review",nativeQuery = true)
    List<Map<String,Object>> findAllReviewMsg();
    @Modifying
    @Query( value = "update t_luntan luntan set reviewnum = ?1 where luntan.id = ?2",nativeQuery = true)
    int updateReviewNumById(String reviewnum,Long id);
    @Modifying
    @Query( value = "select reviewnum from t_luntan luntan where luntan.id = ?1",nativeQuery = true)
    List<Map<String,Object>> findReviewNumById(Long id);
    @Modifying
    @Query( value = "delete from t_review  where id = ?1",nativeQuery = true)
    int deleteReviewById(Long id);
}
