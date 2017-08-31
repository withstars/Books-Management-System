package com.book.web;

import com.book.domain.Admin;
import com.book.domain.ReaderCard;
import com.book.domain.ReaderInfo;
import com.book.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//标注为一个Spring mvc的Controller
@Controller
public class LoginController {

    private LoginService loginService;


    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    //负责处理login.html请求
    @RequestMapping(value = {"/","/login.html"})
    public String toLogin(HttpServletRequest request){
        request.getSession().invalidate();
        return "index";
    }


    //负责处理loginCheck.html请求
    //请求参数会根据参数名称默认契约自动绑定到相应方法的入参中
    @RequestMapping("/main.html")
    public ModelAndView loginCheck(HttpServletRequest request, int id,String passwd){

                boolean isReader = loginService.hasMatchReader(id, passwd);
                boolean isAdmin = loginService.hasMatchAdmin(id, passwd);
                if (isAdmin==false&&isReader==false) {
                    return new ModelAndView("index", "error","账号或密码错误");
                } else if(isAdmin){
                    Admin admin=new Admin();
                    admin.setAdminId(id);
                    admin.setPassword(passwd);
                    request.getSession().setAttribute("admin",admin);
                    return new ModelAndView("admin_main","login","进入管理员页面");
                }else {
                    ReaderCard readerCard = loginService.findReaderCardByUserId(id);
                    ReaderInfo readerInfo=loginService.findReaderInfoByReaderId(id);
                    request.getSession().setAttribute("readercard", readerCard);
                    request.getSession().setAttribute("readerinfo", readerInfo);
                    return new ModelAndView("reader_main","login","进入读者页面");
                }

    };
    @RequestMapping("/admin_main.html")
    public ModelAndView toAdminMain(HttpServletResponse response) {

            return new ModelAndView("admin_main");
    }


    @RequestMapping("/reader_main.html")
    public ModelAndView toReaderMain(HttpServletResponse response) {

        return new ModelAndView("reader_main");
    }

    @RequestMapping("/reader_info.html")
    public ModelAndView toReaderInfo(HttpServletResponse response) {

        return new ModelAndView("reader_info");
    }

    @RequestMapping("/admin_repasswd.html")
    public ModelAndView reAdminPasswd() {

        return new ModelAndView("admin_repasswd");
    }
    @RequestMapping("/admin_repasswd_do")
    public ModelAndView reAdminPasswdDo(HttpServletRequest request,String oldPasswd,String newPasswd,String reNewPasswd ) {

        Admin admin=(Admin) request.getSession().getAttribute("admin");
        int id=admin.getAdminId();
        String passwd=loginService.getAdminPasswd(id);

        if(passwd.equals(oldPasswd)){
            boolean succ=loginService.adminRePasswd(id,newPasswd);
            if (succ){
                ModelAndView modelAndView=new ModelAndView("admin_repasswd");
                modelAndView.addObject("succ","密码修改成功");
                return modelAndView;
            }
            else {
                ModelAndView modelAndView=new ModelAndView("admin_repasswd");
                modelAndView.addObject("succ","密码修改失败");
                return modelAndView;
            }
        }else {
            ModelAndView modelAndView=new ModelAndView("admin_repasswd");
            modelAndView.addObject("error","旧密码错误");
            return modelAndView;
        }
    };

    //配置404页面
     @RequestMapping("*")
     public String notFind(){
     return "404";
       }


}