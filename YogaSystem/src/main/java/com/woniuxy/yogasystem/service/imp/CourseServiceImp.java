package com.woniuxy.yogasystem.service.imp;

import com.woniuxy.yogasystem.dao.PrivateCourseDao;
import com.woniuxy.yogasystem.dao.PublicCourseDao;
import com.woniuxy.yogasystem.pojo.*;
import com.woniuxy.yogasystem.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Transactional
@Service("courseService")
public class CourseServiceImp implements CourseService {

    @Resource
    private PrivateCourseDao privateCourseDao;

    @Resource
    private PublicCourseDao publicCourseDao;

    @Override
    public void insertPrivateCourse(Private_Course course) {
        privateCourseDao.insertPrivateCourse(course);
    }

    @Override
    public void completeOrder(Private_Course status) {
        privateCourseDao.completeOrder(status);
    }

    @Override
    public List<Private_Course> selectPrivateCourse(Private_Course course) {
        return privateCourseDao.selectPrivateCourse(course);
    }

    @Override
    public void insertPublicCourse(Public_Course course) {
        publicCourseDao.insertPublicCourse(course);
    }

    @Override
    public List<Public_Course> selectCourse(Public_Course course) {
        return publicCourseDao.selectCourse(course);
    }

    @Override
    public void deletePrivate(Integer id) {
        privateCourseDao.deletePrivate(id);
    }

    @Override
    public void deletePublic(Integer id) {
        publicCourseDao.deletePublic(id);
    }

    @Override
    public Venues selectVname(Integer id) {
        return publicCourseDao.selectVname(id);
    }

    @Override
    public Trainee selectTname(Integer id) {
        return publicCourseDao.selectTname(id);
    }

    @Override
    public Coach selectCname(Integer id) {
        return publicCourseDao.selectCname(id);
    }
}
