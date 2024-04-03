package com.lk.shortlink.admin.dto.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lk.shortlink.admin.common.serialize.PhoneDesensitizationSerializer;
import com.lk.shortlink.admin.dao.entity.UserDO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 用户返回参数响应
 */

@Data
public class UserRespDTO {
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    @JsonSerialize(using = PhoneDesensitizationSerializer.class)
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    public static UserRespDTO convertModel(UserDO userDO) {
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }
}
