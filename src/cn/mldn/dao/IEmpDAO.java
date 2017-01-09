package cn.mldn.dao;

import cn.mldn.vo.Emp;

import java.util.List;
import java.util.Set;

/**
 * Created by Tony on 2017/1/2.
 */
public interface IEmpDAO {
    public boolean DoCreate(Emp vo) throws Exception;

    public boolean DoUpdate(Emp vo) throws Exception;

    public boolean DoRemove(Set<Integer> ids) throws Exception;

    public Emp findById(Integer id) throws Exception;

    public List<Emp> findAll() throws Exception;

    public List<Emp> findAllSplit(String column,String keyWord,Integer currentPage,Integer lineSize) throws Exception;

    public Integer getAllCount(String column,String keyWord) throws Exception;
}
