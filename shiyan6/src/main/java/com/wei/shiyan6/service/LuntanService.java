package com.wei.shiyan6.service;

import com.wei.shiyan6.dao.LuntanRepository;
import com.wei.shiyan6.model.Luntan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LuntanService {
    @Autowired
    LuntanRepository luntanRepository;

    public List<Luntan> getAllLuntanMsg(){
        Sort sort =new Sort(Sort.Direction.DESC,"posttime");
        return  luntanRepository.findAll(sort);
    }
    public void addLuntan(Luntan msg){
        try{
            Luntan luntan = luntanRepository.save(msg);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Optional<Luntan> getUpdateMsgById(Long id){
        Optional<Luntan> luntan = luntanRepository.findById(id);
        if(luntan != null){
            return luntan;
        }else{
            return null;
        }
    }
    @Transactional
    public int updateMsgById(String title,String updateTime,Long id){
        return luntanRepository.update(title,updateTime,id);
    }
    public void deleteMsgById(Long id){
        luntanRepository.deleteById(id);
    }
    @Transactional
    public int insertReviewMsg(Long id,String owerUserName,String reviewUserName,String reviewMsg){
        return luntanRepository.insertReviewMsg(id,owerUserName,reviewUserName,reviewMsg);
    }
    @Transactional
    public  List<Map<String,Object>> findAllReviewMsg(){
        return luntanRepository.findAllReviewMsg();
    }
    @Transactional
    public  List<Map<String,Object>> findReviewNumById(Long id){
        return luntanRepository.findReviewNumById(id);
    }
    @Transactional
    public  int updateReviewNumById(String reviewNum,Long id){
        return luntanRepository.updateReviewNumById(reviewNum,id);
    }
    @Transactional
    public  int deleteReviewById(Long id){
        return luntanRepository.deleteReviewById(id);
    }
}
