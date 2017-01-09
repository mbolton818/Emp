package cn.mldn.dao.impl;

import cn.mldn.dao.IEmpDAO;
import cn.mldn.vo.Emp;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Tony on 2017/1/2.
 */
public class EmpDAOImpl implements IEmpDAO {
    private Connection conn ;
    private PreparedStatement pstmt ;

    /**
     * 实例化数据层子类对象，同时传入一个数据库连接对象
     * @param conn
     */
    public EmpDAOImpl(Connection conn){
        this.conn = conn;
    }

    @Override
    public boolean DoCreate(Emp vo) throws Exception {
        String sql = "INSERT INTO emp(empno,ename,job.hiredate,sal,comm) VALUES (?,?,?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1,vo.getEmpno());
        this.pstmt.setString(2,vo.getEname());
        this.pstmt.setString(3,vo.getJob());
        this.pstmt.setDate(4,new java.sql.Date(vo.getHiredate().getTime()));
        this.pstmt.setDouble(5,vo.getSal());
        this.pstmt.setDouble(6,vo.getComm());

        return this.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean DoUpdate(Emp vo) throws Exception {
        String sql = "UPDATE emp SET ename=?,job=?.hiredate=?,sal=?,comm=? WHERE empno=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,vo.getEname());
        this.pstmt.setString(2,vo.getJob());
        this.pstmt.setDate(3,new java.sql.Date(vo.getHiredate().getTime()));
        this.pstmt.setDouble(4,vo.getSal());
        this.pstmt.setDouble(5,vo.getComm());
        this.pstmt.setInt(6,vo.getEmpno());
        return this.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean DoRemove(Set<Integer> ids) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM emp WHERE empno IN (");
        Iterator<Integer> iter =  ids.iterator();
        while (iter.hasNext()){
            sql.append(iter.next()).append(",");
        }
        sql.delete(sql.length()-1,sql.length());
        sql.append(")");
        this.pstmt = this.conn.prepareStatement(sql.toString());
        return this.pstmt.executeUpdate() == ids.size();
    }

    @Override
    public Emp findById(Integer id) throws Exception {
        Emp vo = null;
        String sql = "SELECT empno,ename,job,hiredate,sal,comm FROM emp WHERE empno=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1,id);
        ResultSet rs =  this.pstmt.executeQuery();
        if (rs.next()){
            vo = new Emp();
            vo.setEmpno(rs.getInt(1));
            vo.setEname(rs.getString(2));
            vo.setJob(rs.getString(3));
            vo.setHiredate(rs.getDate(4));
            vo.setSal(rs.getDouble(5));
            vo.setComm(rs.getDouble(6));
        }
        return vo;
    }

    @Override
    public List<Emp> findAll() throws Exception {
        List<Emp> all = new ArrayList<Emp>();
        String sql = "SELECT empno,ename,job,hiredate,sal,comm FROM emp";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs =  this.pstmt.executeQuery();
        while (rs.next()){
            Emp vo = new Emp();
            vo.setEmpno(rs.getInt(1));
            vo.setEname(rs.getString(2));
            vo.setJob(rs.getString(3));
            vo.setHiredate(rs.getDate(4));
            vo.setSal(rs.getDouble(5));
            vo.setComm(rs.getDouble(6));
            all.add(vo);
        }
        return all;
    }

    @Override
    public List<Emp> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws Exception {
        List<Emp> all = new ArrayList<Emp>();
        String sql = "SELECT * FROM  "
                    + "( SELECT empno,ename,job,hiredate,sal,comm,ROWNUM "
                    + "FROM emp WHERE "+column+ "LIKE ? AND ROWNUM<=?) Tmp "
                    + "WHERE Tmp.rownum>?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,"%"+keyWord+"%");
        this.pstmt.setInt(2,currentPage* lineSize);
        this.pstmt.setInt(3,(currentPage-1)*lineSize);
        ResultSet rs =  this.pstmt.executeQuery();

        while (rs.next()){
            Emp vo = new Emp();
            vo.setEmpno(rs.getInt(1));
            vo.setEname(rs.getString(2));
            vo.setJob(rs.getString(3));
            vo.setHiredate(rs.getDate(4));
            vo.setSal(rs.getDouble(5));
            vo.setComm(rs.getDouble(6));
            all.add(vo);
        }
        return all;
    }

    @Override
    public Integer getAllCount(String column,String keyWord) throws Exception{
        String sql = "SELECT COUNT(*) FROM emp WHERE " + column + "LIKE ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,"%"+keyWord+"%");
        ResultSet rs = this.pstmt.executeQuery();
            if (rs.next()){
                return rs.getInt(1);
            }
        return  null;
    }
}
