package com.learn.controller;

import com.learn.domain.Team;
import com.learn.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.learn.interceptor.WebSecurityConfig.SESSION_KEY;

@Controller
public class FileController {

    @Autowired
    TeamRepository teamRepository;

    @RequestMapping("/uploadWeek")
    public @ResponseBody
    Map<String ,Object> uploadWeek(@SessionAttribute(SESSION_KEY)String uid,@RequestParam("uploadFile") MultipartFile uploadFile){
        List<Team> teamList = teamRepository.findByName(uid);
        Team team = teamList.get(0);
        Map<String,Object> map = new HashMap<>();
        if (uploadFile.isEmpty()) {
            map.put("success",false);
            map.put("message","上传文件为空");
            return map;
        }
        // 获取文件名
        String semesterPath = team.getSemester().getName();
        String coursePath = team.getCourse().getName();
        String clasPath = team.getClas().getName();
        String teamPath = uid;
        String fileName = uploadFile.getOriginalFilename();
  //      String fileName = "周报";
        System.out.println("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传的后缀名为：" + suffixName);
        fileName = "周报"+suffixName;
        // 文件上传后的路径
        String filePath = "E://test//"+semesterPath+"//"+coursePath+"//"+clasPath+"//"+teamPath+"//";
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            uploadFile.transferTo(dest);
            map.put("success",true);
            map.put("message","上传成功");
            team.setUploadweek(1);
            teamRepository.save(team);
            return map;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        map.put("success",false);
        map.put("message","上传失败");
        return map;
    }

    @RequestMapping("/uploadOpen")
    public @ResponseBody
    Map<String ,Object> uploadOpen(@SessionAttribute(SESSION_KEY)String uid,@RequestParam("uploadFile") MultipartFile uploadFile) {
        List<Team> teamList = teamRepository.findByName(uid);
        Team team = teamList.get(0);
        Map<String,Object> map = new HashMap<>();
        if (uploadFile.isEmpty()) {
            map.put("success",false);
            map.put("message","上传文件为空");
            return map;
        }
        // 获取文件名
        String semesterPath = team.getSemester().getName();
        String coursePath = team.getCourse().getName();
        String clasPath = team.getClas().getName();
        String teamPath = uid;
        String fileName = uploadFile.getOriginalFilename();
        //      String fileName = "周报";
        System.out.println("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传的后缀名为：" + suffixName);
        fileName = "开题报告"+suffixName;
        // 文件上传后的路径
        String filePath = "E://test//"+semesterPath+"//"+coursePath+"//"+clasPath+"//"+teamPath+"//";
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            uploadFile.transferTo(dest);
            map.put("success",true);
            map.put("message","上传开题报告成功");
            team.setUploadopen(1);
            teamRepository.save(team);
            return map;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        map.put("success",false);
        map.put("message","上传失败");

        return map;
    }

    @RequestMapping("/uploadMid")
    public @ResponseBody
    Map<String ,Object> uploadMid(@SessionAttribute(SESSION_KEY)String uid,@RequestParam("uploadFile") MultipartFile uploadFile) {
        List<Team> teamList = teamRepository.findByName(uid);
        Team team = teamList.get(0);
        Map<String,Object> map = new HashMap<>();
        if (uploadFile.isEmpty()) {
            map.put("success",false);
            map.put("message","上传文件为空");
            return map;
        }
        // 获取文件名
        String semesterPath = team.getSemester().getName();
        String coursePath = team.getCourse().getName();
        String clasPath = team.getClas().getName();
        String teamPath = uid;
        String fileName = uploadFile.getOriginalFilename();
        //      String fileName = "周报";
        System.out.println("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传的后缀名为：" + suffixName);
        fileName = "中检报告"+suffixName;
        // 文件上传后的路径
        String filePath = "E://test//"+semesterPath+"//"+coursePath+"//"+clasPath+"//"+teamPath+"//";
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            uploadFile.transferTo(dest);
            map.put("success",true);
            map.put("message","上传中检报告成功");
            team.setUploadmid(1);
            teamRepository.save(team);
            return map;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        map.put("success",false);
        map.put("message","上传失败");

        return map;
    }

    @RequestMapping("/uploadEnd")
    public @ResponseBody
    Map<String ,Object> uploadEnd(@SessionAttribute(SESSION_KEY)String uid,@RequestParam("uploadFile") MultipartFile uploadFile) {
        List<Team> teamList = teamRepository.findByName(uid);
        Team team = teamList.get(0);
        Map<String,Object> map = new HashMap<>();
        if (uploadFile.isEmpty()) {
            map.put("success",false);
            map.put("message","上传文件为空");
            return map;
        }
        // 获取文件名
        String semesterPath = team.getSemester().getName();
        String coursePath = team.getCourse().getName();
        String clasPath = team.getClas().getName();
        String teamPath = uid;
        String fileName = uploadFile.getOriginalFilename();
        //      String fileName = "周报";
        System.out.println("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传的后缀名为：" + suffixName);
        fileName = "末检报告"+suffixName;
        // 文件上传后的路径
        String filePath = "E://test//"+semesterPath+"//"+coursePath+"//"+clasPath+"//"+teamPath+"//";
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            uploadFile.transferTo(dest);
            map.put("success",true);
            map.put("message","上传末检报告成功");
            team.setUploadend(1);
            teamRepository.save(team);
            return map;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        map.put("success",false);
        map.put("message","上传失败");

        return map;
    }
}
