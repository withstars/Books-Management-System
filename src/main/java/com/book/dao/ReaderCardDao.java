package com.book.dao;

import com.book.domain.ReaderCard;
import com.book.domain.ReaderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Repository
public class ReaderCardDao {

    private JdbcTemplate jdbcTemplate;
    //根据用户查询的SQL语句
    private final static String MATCH_COUNT_SQL="select count(*) from reader_card where reader_id = ? and passwd = ? ";
    private final static String FIND_READER_BY_USERID="select reader_id, name, passwd, card_state from reader_card where reader_id = ? ";
    private final static String RE_PASSWORD_SQL="UPDATE reader_card set passwd = ? where reader_id = ? ";
    private final static String ADD_READERCARD_SQL="INSERT INTO reader_card (reader_id,name) values ( ? , ?)";
    private final static String UPDATE_READER_NAME_SQL="UPDATE reader_card set name = ? where reader_id = ?";


    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(int readerId,String passwd){
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL,new Object[]{readerId,passwd},Integer.class);
    }

    public ReaderCard findReaderByReaderId(int userId){
        final ReaderCard readerCard=new ReaderCard();
        jdbcTemplate.query(FIND_READER_BY_USERID, new Object[]{userId},
                //匿名类实现的回调函数
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        readerCard.setReaderId(resultSet.getInt("reader_id"));
                        readerCard.setPasswd(resultSet.getString("passwd"));
                        readerCard.setName(resultSet.getString("name"));
                        readerCard.setCardState(resultSet.getInt("card_state"));
                    }
                });
        return readerCard;
    }

    public int rePassword(int readerId,String newPasswd){
        return jdbcTemplate.update(RE_PASSWORD_SQL,new Object[]{newPasswd,readerId});
    }

    public int addReaderCard(ReaderInfo readerInfo){

        String name=readerInfo.getName();
        int readerId=readerInfo.getReaderId();

        return jdbcTemplate.update(ADD_READERCARD_SQL,new Object[]{readerId,name});
    }

    public int updateName(int readerId,String name){
        return jdbcTemplate.update(UPDATE_READER_NAME_SQL,new Object[]{name,readerId,});
    }
}
