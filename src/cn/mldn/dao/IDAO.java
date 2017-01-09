package cn.mldn.dao;

import cn.mldn.vo.Emp;

import java.util.List;
import java.util.Set;

/**
 * Created by Tony on 2017/1/2.
 */
public interface IDAO<k,v> {
    public boolean DoCreate(v vo) throws Exception;

    public boolean DoUpdate(v vo) throws Exception;

    public boolean DoRemove(Set<k> ids) throws Exception;

    public v findById(k id) throws Exception;

    public List<v> findAll() throws Exception;

    public List<v> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws Exception;

    public k getAllCount(String column, String keyWord) throws Exception;
}
