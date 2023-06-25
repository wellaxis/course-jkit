package com.witalis.jkit.flyway.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class EnumTypeMapper<T extends Enum<T>> {

    public void nullSafeSet(PreparedStatement st, T value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, Types.OTHER);
        } else {
            st.setObject(index, value.toString(), Types.OTHER);
        }
    }
}
