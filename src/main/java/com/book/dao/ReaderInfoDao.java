package com.book.dao;

import com.book.domain.ReaderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class ReaderInfoDao {

    private JdbcTemplate jdbcTemplate;

    private final static String ADD_READER_INFO_SQL="INSERT INTO reader_info VALUES(?,?,?,?,?,?)";
    private final static String DELETE_READER_INFO_SQL="DELETE FROM reader_info where reader_id = ? ";
    private final static String GET_READER_INFO_SQL="SELECT * FROM reader_info where reader_id = ? ";
    private final static String UPDATE_READER_INFO="UPDATE reader_info set name = ? ,sex = ? ,birth = ? ,address = ? ,telcode = ? where reader_id = ? ";
    private final static String ALL_READER_INFO_SQL="SELECT * FROM reader_info";


    public ArrayList<ReaderInfo> getAllReaderInfo() {
        final ArrayList<ReaderInfo> readers=new ArrayList<ReaderInfo>();
        jdbcTemplate.query(ALL_READER_INFO_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    ReaderInfo reader=new ReaderInfo();
                    reader.setAddress(resultSet.getString("address"));
                    reader.setBirth(resultSet.getDate("birth"));
                    reader.setName(resultSet.getString("name"));
                    reader.setReaderId(resultSet.getInt("reader_id"));
                    reader.setSex(resultSet.getString("sex"));
                    reader.setTelcode(resultSet.getString("telcode"));
                    readers.add(reader);
                }
            }
        });
        return readers;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ReaderInfo findReaderInfoByReaderId(int readerId){
        final ReaderInfo reader=new ReaderInfo();
        jdbcTemplate.query(GET_READER_INFO_SQL, new Object[]{readerId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                reader.setAddress(resultSet.getString("address"));
                reader.setBirth(resultSet.getDate("birth"));
                reader.setName(resultSet.getString("name"));
                reader.setReaderId(resultSet.getInt("reader_id"));
                reader.setSex(resultSet.getString("sex"));
                reader.setTelcode(resultSet.getString("telcode"));
            }
        });
        return reader;
    }

    public int deleteReaderInfo(int readerId){
        return jdbcTemplate.update(DELETE_READER_INFO_SQL,readerId);
    }

    public int editReaderInfo(ReaderInfo readerInfo){
        String address=readerInfo.getAddress();
        Date birth=readerInfo.getBirth();
        String name=readerInfo.getName();
        int readerId=readerInfo.getReaderId();
        String sex=readerInfo.getSex();
        String telcode=readerInfo.getTelcode();
        return jdbcTemplate.update(UPDATE_READER_INFO,new Object[]{name,sex,birth,address,telcode,readerId});
    }

    public int addReaderInfo(ReaderInfo readerInfo){
        String address=readerInfo.getAddress();
        Date birth=readerInfo.getBirth();
        String name=readerInfo.getName();
        String sex=readerInfo.getSex();
        String telcode=readerInfo.getTelcode();
        int readerId=readerInfo.getReaderId();

        return jdbcTemplate.update(ADD_READER_INFO_SQL,new Object[]{readerId,name,sex,birth,address,telcode});
    }


}
