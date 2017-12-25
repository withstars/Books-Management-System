# 图书管理系统
#### 基于Spring+Spring MVC(Maven方式构建)
[![Build Status](https://travis-ci.org/withstars/Books-Management-System.svg?branch=master)](https://travis-ci.org/withstars/Books-Management-System)
### 项目简介
本图书管理系统基于spring,spring mvc,数据库为mysql。前端使用了Bootstrap。 
### 系统功能
该系统实现读者和管理员登陆，图书的增删改查，读者的增删改查，借还图书，密码修改，卡号挂失，超期提醒等功能。
### 如何使用(以下演示步骤集成开发环境为IDEA)
1. *git clone https://github.com/withstars/Books-Management-System* <br/>
	*cd  Books-Management-System*<br/>
2.  导入sql文件到数据库.<br/>
3.  用IDEA打开该项目.
4.  配置*Books-Management-System/src/main/resources/book-context.xml*文件中的数据库设置.
5.  Maven下载依赖.(点击IDEA右侧栏,Maven管理里的刷新键下载依赖,如下图)<br/>
    <img src="https://github.com/withstars/Books-Management-System/blob/master/preview/htu_1.png">
4.  启动Jetty服务器,默认监听端口号`9000`.(如下图)<br/>
    <img src="https://github.com/withstars/Books-Management-System/blob/master/preview/htu_2.png"><br/>
    启动成功后显示如下<br/>
    <img src="https://github.com/withstars/Books-Management-System/blob/master/preview/htu_3.png">
5.  浏览器进入 http://localhost:9000
### 说明<br/>
1. 如果使用该项目出现问题，请联系我 withstars@126.com
2. 如果该项目对您有帮助,请star鼓励我,感谢与您一起进步
### 下一步:整合MyBatis 个人博客系统<br/>
*https://github.com/withstars/Blog-System*
### 本项目截图<br/>
<img src="https://github.com/ValueStar/Books-Management-System/blob/master/preview/1.PNG">
<img src="https://github.com/ValueStar/Books-Management-System/blob/master/preview/2.PNG">
<img src="https://github.com/ValueStar/Books-Management-System/blob/master/preview/3.PNG">
<img src="https://github.com/ValueStar/Books-Management-System/blob/master/preview/4.PNG">
<img src="https://github.com/ValueStar/Books-Management-System/blob/master/preview/5.PNG">
<img src="https://github.com/ValueStar/Books-Management-System/blob/master/preview/6.PNG">
