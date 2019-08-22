package com.wei.shiyan6.controller;

import com.wei.shiyan6.bean.LuntanForm;
import com.wei.shiyan6.model.Luntan;
import com.wei.shiyan6.service.LuntanService;
import com.wei.shiyan6.service.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class LuntanController {
    @Autowired
    LuntanService luntanService;
    @Autowired
    NavService navService;

    @RequestMapping(value = "/luntan", method = RequestMethod.GET)
    public String searchAllMessage(HttpSession session) {
        session.setAttribute("allMsg", luntanService.getAllLuntanMsg());
        session.setAttribute("navMsg", navService.findAllNavMsgs());
        session.setAttribute("reviewMsg",luntanService.findAllReviewMsg());
//        System.out.println(luntanService.findAllReviewMsg().get(0).get("id"));
        return "/luntan";
    }

    @RequestMapping(value = "/editmessage", method = RequestMethod.GET)
    public String testEditMessage() {
        return "editmessage";
    }

    @RequestMapping(value = "/editmessage/{id}", method = RequestMethod.GET)
    public String getUpadateMsg(@PathVariable("id") Long id, Model model) {
        model.addAttribute("updateMsg", luntanService.getUpdateMsgById(id));
        return "editmessage";
    }

    @RequestMapping(value = "/postMessage", method = RequestMethod.POST)
    public String postMessage(LuntanForm luntanForm, HttpSession session) {
        if (luntanForm.getTitle().equals("") || luntanForm.getMsg().equals("")) {
            session.setAttribute("postError", "标题或详细信息不能为空，暂无法发布");
            return "editmessage";
        } else if (luntanForm.getUsername() != null && !luntanForm.getUsername().equals("")) {
            session.setAttribute("postError", "");
            int imgNum = (int) (Math.random() * 8 + 1);
            SimpleDateFormat postTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

            luntanService.addLuntan(new Luntan(imgNum + ".png", luntanForm.getTitle() + " " + luntanForm.getMsg(), luntanForm.getUsername(), "0",postTime.format(new Date())));
//            System.out.println(df.format(new Date()));
            return searchAllMessage(session);
        } else {
//            System.out.println(luntanForm.getUpdateId());
            SimpleDateFormat updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            luntanService.updateMsgById(luntanForm.getTitle() + " " + luntanForm.getMsg(),updateTime.format(new Date()),(Long) luntanForm.getUpdateId());
            return searchAllMessage(session);
        }
    }
    @RequestMapping(value = "/postMessage", method = RequestMethod.GET)
    public String postMessageDoGet(LuntanForm luntanForm, HttpSession session) {
        return searchAllMessage(session);
    }

    @RequestMapping(value = "/deletemessage/{id}", method = RequestMethod.GET)
    public String deleteMsg(HttpSession session, @PathVariable("id") Long id) {
        luntanService.deleteMsgById(id);
        luntanService.deleteReviewById(id);
        return searchAllMessage(session);
    }
    @ResponseBody
    @RequestMapping(value = "/doReview",method = RequestMethod.GET)
    public int addReviewMsg(@RequestParam (value="id") Long id,@RequestParam(value = "owerUserName") String owerUserName,
                               @RequestParam(value="reviewUserName") String reviewUserName,@RequestParam(value = "reviewMsg")String reviewMsg){
//        System.out.println(id+owerUserName+reviewUserName+reviewMsg);
//        System.out.println(luntanService.findReviewNumById(id).get(0).get("reviewnum"));
        String previousReviewNum = (String) luntanService.findReviewNumById(id).get(0).get("reviewnum");
        int reviewNum = Integer.valueOf(previousReviewNum) + 1;
        luntanService.updateReviewNumById(String.valueOf(reviewNum),id);
        luntanService.insertReviewMsg(id,owerUserName,reviewUserName,reviewMsg);
        return reviewNum;
    }
}
