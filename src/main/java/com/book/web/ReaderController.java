package com.book.web;

import com.book.domain.ReaderInfo;
import com.book.service.ReaderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class ReaderController {

    private ReaderInfoService readerInfoService;
    @Autowired
    public void setReaderInfoService(ReaderInfoService readerInfoService) {
        this.readerInfoService = readerInfoService;
    }

    @RequestMapping("allreaders.html")
    public ModelAndView allBooks(){
        ArrayList<ReaderInfo> readers=readerInfoService.readerInfos();
        ModelAndView modelAndView=new ModelAndView("admin_readers");
        modelAndView.addObject("readers",readers);
        return modelAndView;
    }

    @RequestMapping("reader_delete.html")
    public ModelAndView readerDelete(HttpServletRequest request){
        int readerId= Integer.parseInt(request.getParameter("readerId"));
        boolean success=readerInfoService.deleteReaderInfo(readerId);

        if(success){
            ArrayList<ReaderInfo> readers=readerInfoService.readerInfos();
            ModelAndView modelAndView=new ModelAndView("admin_readers");
            modelAndView.addObject("succ","删除成功！");
            modelAndView.addObject("readers",readers);
            return modelAndView;
        }else {
            ArrayList<ReaderInfo> readers=readerInfoService.readerInfos();
            ModelAndView modelAndView= new ModelAndView("admin_readers");
            modelAndView.addObject("error","删除失败！");
            modelAndView.addObject("readers",readers);
            return modelAndView;
        }

    }
    @RequestMapping("reader_edit.html")
    public ModelAndView readerInfoEdit(HttpServletRequest request){
        int readerId= Integer.parseInt(request.getParameter("readerId"));
        ReaderInfo readerInfo=readerInfoService.getReaderInfo(readerId);
        ModelAndView modelAndView=new ModelAndView("admin_reader_edit");
        modelAndView.addObject("readerInfo",readerInfo);
        return modelAndView;

    }

    @RequestMapping("reader_edit_do.html")
    public ModelAndView readerInfoEditDo(HttpServletRequest request,String name,String sex,String birth,String address,String telcode){
        int readerId= Integer.parseInt(request.getParameter("id"));
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date nbirth=new Date();
        try{
            java.util.Date date=sdf.parse(birth);
            nbirth=date;
        }catch (ParseException e){
            e.printStackTrace();
        }

        ReaderInfo readerInfo=new ReaderInfo();
        readerInfo.setAddress(address);
        readerInfo.setBirth(nbirth);
        readerInfo.setName(name);
        readerInfo.setReaderId(readerId);
        readerInfo.setTelcode(telcode);
        readerInfo.setSex(sex);

        boolean succ=readerInfoService.editReaderInfo(readerInfo);
        ArrayList<ReaderInfo> readers=readerInfoService.readerInfos();
        if(succ){
            ModelAndView modelAndView=new ModelAndView("admin_readers");
            modelAndView.addObject("succ","读者信息修改成功");
            modelAndView.addObject("readers",readers);
            return modelAndView;
        }else {
            ModelAndView modelAndView=new ModelAndView("admin_readers");
            modelAndView.addObject("error","读者信息修改失败");
            modelAndView.addObject("readers",readers);
            return modelAndView;
        }

    }

    @RequestMapping("reader_add.html")
    public ModelAndView readerInfoAdd(){
        ModelAndView modelAndView=new ModelAndView("admin_reader_add");
        return modelAndView;

    }
    @RequestMapping("reader_add_do.html")
    public ModelAndView readerInfoAddDo(String name,String sex,String birth,String address,String telcode){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date nbirth=new Date();
        try{
            java.util.Date date=sdf.parse(birth);
            nbirth=date;
        }catch (ParseException e){
            e.printStackTrace();
        }

        ReaderInfo readerInfo=new ReaderInfo();
        readerInfo.setAddress(address);
        readerInfo.setBirth(nbirth);
        readerInfo.setName(name);
        readerInfo.setReaderId(0);
        readerInfo.setTelcode(telcode);
        readerInfo.setSex(sex);
        boolean succ=readerInfoService.addReaderInfo(readerInfo);
        ArrayList<ReaderInfo> readers=readerInfoService.readerInfos();
        if (succ){
            ModelAndView modelAndView=new ModelAndView("admin_readers");
            modelAndView.addObject("succ","添加读者成功");
            modelAndView.addObject("readers",readers);
            return modelAndView;
        }else {
            ModelAndView modelAndView=new ModelAndView("admin_readers");
            modelAndView.addObject("succ","添加读者失败");
            modelAndView.addObject("readers",readers);
            return modelAndView;
        }


    }

}
