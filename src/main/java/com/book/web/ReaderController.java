package com.book.web;

import com.book.domain.ReaderInfo;
import com.book.service.ReaderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

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


}
