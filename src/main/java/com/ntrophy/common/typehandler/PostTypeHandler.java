package com.ntrophy.common.typehandler;

import com.ntrophy.domain.enums.PostType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(PostType.class)
@MappedJdbcTypes(JdbcType.INTEGER)
public class PostTypeHandler extends BaseTypeHandler<PostType> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, PostType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public PostType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return PostType.fromCode(code);
    }

    @Override
    public PostType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return PostType.fromCode(code);
    }

    @Override
    public PostType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return PostType.fromCode(code);
    }
}
