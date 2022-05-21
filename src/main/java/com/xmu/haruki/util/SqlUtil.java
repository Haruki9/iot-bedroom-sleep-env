package com.xmu.haruki.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class SqlUtil {
    private static SqlSessionFactory factory;
    public static SqlSessionFactory getSessionFactory() {
        if (factory!=null)return factory;

        String resource = "config/MybatisConfiguration.xml";
        try {
            factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(
                    resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return factory;
    }
}
