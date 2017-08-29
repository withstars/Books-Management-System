package com.book.service;

import com.book.dao.AdminDao;
import com.book.dao.ReaderCardDao;
import com.book.dao.ReaderInfoDao;
import com.book.domain.ReaderCard;
import com.book.domain.ReaderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private ReaderCardDao readerCardDao;
    private ReaderInfoDao readerInfoDao;
    private AdminDao adminDao;
    @Autowired
    public void setReaderCardDao(ReaderCardDao readerCardDao) {
        this.readerCardDao = readerCardDao;
    }

    @Autowired
    public void setReaderInfoDao(ReaderInfoDao readerInfoDao) {
        this.readerInfoDao = readerInfoDao;
    }

    @Autowired
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public boolean hasMatchReader(int readerId,String passwd){
        return  readerCardDao.getMatchCount(readerId, passwd)>0;
    }

    public ReaderCard findReaderCardByUserId(int readerId){
        return readerCardDao.findReaderByReaderId(readerId);
    }
    public ReaderInfo findReaderInfoByReaderId(int readerId){
        return readerInfoDao.findReaderInfoByReaderId(readerId);
    }

    public boolean hasMatchAdmin(int adminId,String password){
        return adminDao.getMatchCount(adminId,password)==1;
    }

    public boolean adminRePasswd(int adminId,String newPasswd){
        return adminDao.rePassword(adminId,newPasswd)>0;
    }
    public String getAdminPasswd(int id){
        return adminDao.getPasswd(id);
    }



}
