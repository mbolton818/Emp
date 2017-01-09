package cn.mldn.dao.factory;

import cn.mldn.dao.IEmpDAO;
import cn.mldn.dao.impl.EmpDAOImpl;

import java.sql.Connection;

/**
 * Created by Tony on 2017/1/8.
 */
public class DAOfactory {
    public static IEmpDAO getIEmpDAOInstacne(Connection conn){
        return new EmpDAOImpl(conn);
    }
}
