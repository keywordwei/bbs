package com.wei.shiyan6.service;

import com.wei.shiyan6.dao.NavRepository;
import com.wei.shiyan6.model.Nav;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavService {
    @Autowired
    NavRepository navRepository;

    public void addNav(Nav navMsg){
        try{
            Nav nav = navRepository.save(navMsg);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Nav> findAllNavMsgs(){
        return navRepository.findAll();
    }
}
