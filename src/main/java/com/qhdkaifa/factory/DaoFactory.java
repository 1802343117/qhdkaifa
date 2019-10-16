package com.qhdkaifa.factory;


import com.qhdkaifa.dao.UserDao;
import com.qhdkaifa.dao.impl.UserDaoImpl;

public class DaoFactory {
    public static UserDao getUserDaoInstance(){
        return new UserDaoImpl();
    }
}
