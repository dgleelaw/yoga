package com.woniuxy.yogasystem.controller;

import com.woniuxy.yogasystem.pojo.*;
import com.woniuxy.yogasystem.service.CourseService;
import com.woniuxy.yogasystem.service.UserService;
import com.woniuxy.yogasystem.service.VenuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    /**
     * 删除私教课程
     * @return
     */
    @RequestMapping("/deleteprivate")
    public String deletePrivate(Integer id) {
        courseService.deletePrivate(id);
        return "redirect:/course/privateCourse";
    }

    /**
     * 删除公开课程
     * @param id
     * @return
     */
    @RequestMapping("/deletepublic")
    public String deletePublic(Integer id) {
        courseService.deletePublic(id);
        return "redirect:/course/privateCourse";
    }

    /**
     * 查询私教课
     * 1、查询所有课程信息
     * 2、查询出教练，场馆名字
     *
     *
     */
    @RequestMapping("/privateCourse")
    public ModelAndView findPrivateCourse(Private_Course course, HttpServletRequest request){
        //从session获取uid
        //获取权限再设定是cid(status=1)还是vid(status=2)、还是学员tid
        //学员从订单查询私有课程
        int status=1;
        Integer uid = (Integer) request.getSession().getAttribute("uid");
        int role= (int) request.getSession().getAttribute("role");
        
        if(role==1){
            Coach coach=userService.selectCoach(uid);
            course.setCid(coach.getId());
             status=1;
        }else if(role==2){
            Venues venues=userService.selectVenue(uid);
            course.setVid(venues.getId());
            status=2;
        }else if(role==0){
            Trainee trainee=userService.selectTrainee(uid);
            course.setTid(trainee.getId());
            status=0;
        }

        //查询出私教表
        List<Private_Course> list=courseService.selectPrivateCourse(course);

        //查询出公开课
        Public_Course public_course=new Public_Course();
        List<Public_Course> list2=courseService.selectCourse(public_course);

        //将私教表中id转为name
        for(Private_Course private_course:list){
           private_course.setCname(courseService.selectCname(private_course.getCid()).getName());
           private_course.setVname(courseService.selectVname(private_course.getVid()).getName());
           private_course.setEndtime("私教课");
           private_course.setStartTime(private_course.getStartTime());
           private_course.setFlag(status);
        }
        //将公开课中的id转为name
        for(Public_Course public_course1:list2){
            public_course1.setCname(courseService.selectCname(public_course1.getCid()).getName());
            public_course1.setVname(courseService.selectVname(public_course1.getVid()).getName());
            public_course1.setEndTime("公开课");
            public_course1.setStartTime(public_course1.getStartTime());
            public_course1.setFlag(status);
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("privatecourses", list);
        modelAndView.addObject("publiccourses", list2);
        modelAndView.addObject("status",status);

        modelAndView.setViewName("html/course.html");
        return modelAndView;
    }

    /**
     * 查询出场馆选项
     * @return
     */
    @RequestMapping("toaddcourse")
    @ResponseBody
    public ModelAndView toaddCourse(){
        ModelAndView model=new ModelAndView();
        List<Venues> venues=userService.selectAllVenues();
        List<Coach> coachs=userService.selectAllCoach();
        model.addObject("venues",venues);
        model.addObject("coachs",coachs);
        model.setViewName("html/add_course.html");
        return model;
    }
    @RequestMapping("toaddprivatecourse")
    @ResponseBody
    public ModelAndView toaddprivatecourse(HttpServletRequest request){

        Integer uid= (Integer) request.getSession().getAttribute("uid");
        ModelAndView model=new ModelAndView();
        List<Venues> venues=userService.selectAllVenues();
        List<Coach> coachs=userService.selectAllCoach();
        model.addObject("venues",venues);
        //获取当前教练id
        for(Coach coach:coachs){
            if(coach.getUid()==uid){
                System.out.println(coach);
            }
        }
        model.setViewName("html/add_private_course.html");
        return model;
    }

    /**
     * 发布公开课程
     * @return
     */
    @RequestMapping("/addPublic")
    public ModelAndView insertPublic(Public_Course course,HttpServletRequest request){
        Integer uid = (Integer) request.getSession().getAttribute("uid");
        Venues venues=userService.selectVenue(uid);
        course.setVid(venues.getId());
        ModelAndView modelAndView = new ModelAndView();
            //查询出当天有没有公开课,根据日期查
            List<Public_Course> list = courseService.selectCourse(course);
            if(list.size()==0){
                courseService.insertPublicCourse(course);
            }
        modelAndView.setViewName("redirect:/course/privateCourse");
        return modelAndView;
    }
    /**
     * 发布私教课程
     */
    @RequestMapping("/addPrivate")
    public ModelAndView insertPrivate(HttpServletRequest request,Private_Course course){
        Integer uid = (Integer) request.getSession().getAttribute("uid");
        Coach coach=userService.selectCoach(uid);
        course.setCid(coach.getId());
        ModelAndView modelAndView = new ModelAndView();
            //查询出当天有没有公开课,根据日期查
        List<Private_Course> list2=courseService.selectPrivateCourse(course);
            if(list2.size()==0){
                courseService.insertPrivateCourse(course);
            }
        modelAndView.setViewName("redirect:/course/privateCourse");
        return modelAndView;
    }

    /**
     * 检验开始结束时间是否正确
     * @param starttime
     * @param endtime
     * @return
     */
    public boolean checkTime(String starttime,String endtime){
        if(Integer.valueOf(endtime.split("-")[0])<Integer.valueOf(starttime.split("-")[0])){
            return false;
        }
        if(endtime.split("-")[0].equals(starttime.split("-")[0])&&
                (Integer.valueOf(endtime.split("-")[1])<Integer.valueOf(starttime.split("-")[1]))){
            return false;
        }
        if(endtime.split("-")[0].equals(starttime.split("-")[0])&&
                endtime.split("-")[1].equals(starttime.split("-")[1])&&
                Integer.valueOf(endtime.split("-")[2].substring(0,2))<Integer.valueOf(starttime.split("-")[2].substring(0,2))){
            return false;
        }
        if(endtime.split("-")[0].equals(starttime.split("-")[0])&&
                endtime.split("-")[1].equals(starttime.split("-")[1])&&
                endtime.split("-")[2].substring(0,2).equals(starttime.split("-")[2].substring(0,2))&&
                Integer.valueOf(endtime.split(" ")[1].split(":")[0])<Integer.valueOf(starttime.split(" ")[1].split(":")[0])){
            return false;
        }
        if(endtime.split("-")[0].equals(starttime.split("-")[0])&&
                endtime.split("-")[1].equals(starttime.split("-")[1])&&
                endtime.split("-")[2].substring(0,2).equals(starttime.split("-")[2].substring(0,2))&&
                endtime.split(" ")[1].split(":")[0].equals(starttime.split(" ")[1].split(":")[0])&&
                Integer.valueOf(endtime.split(" ")[1].split(":")[1])<Integer.valueOf(starttime.split(" ")[1].split(":")[1])){
            return false;
        }
        return true;
    }
}
