package com.wei.shiyan6.service;



import com.wei.shiyan6.ErrorMsg;
import com.wei.shiyan6.dao.UserPepostory;
import com.wei.shiyan6.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserPepostory userRePostory;

    public User addUser(User user, ErrorMsg errorMsg){
        try{
            User registerUser = userRePostory.save(user);
            return registerUser;
        }catch (Exception e){
            errorMsg.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public User checkLogin(User user, ErrorMsg error){
        if(user.getUsername().equals("") && user.getPassword().equals("")){
            error.setErrorMsg("用户名和密码不能为空");
            return null;
        }else if(user.getUsername().equals("")){
            error.setErrorMsg("用户名不能为空");
            return null;
        }else if(user.getPassword().equals("")){
            error.setErrorMsg("密码不能为空");
            return null;
        }
        User findUser = userRePostory.findByUsername(user.getUsername());
        if(findUser == null){
            error.setErrorMsg("用户名不存在");
            return null;
        }else{
            if(findUser.getPassword().equals(user.getPassword())){
                return findUser;
            }else{
                error.setErrorMsg("用户名和密码不匹配");
                return null;
            }
        }
    }

}
