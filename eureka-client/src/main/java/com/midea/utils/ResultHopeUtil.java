package com.midea.utils;

import com.github.pagehelper.PageInfo;
import com.midea.constant.CommonConst;
import com.midea.enums.ResponseStatusEnum;
import com.midea.model.base.PageResultVo;
import com.midea.model.base.ResponseVo;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 接口返回工具
 *

 **/
public class ResultHopeUtil {
    /**
     * ModelAndView
     **/
    public static ModelAndView view(String view) {
        return new ModelAndView(view);
    }

    public static ModelAndView view(String view, Map<String, Object> model) {
        return new ModelAndView(view, model);
    }

    public static ModelAndView redirect(String view) {
        return new ModelAndView("redirect:" + view);
    }


    public static ResponseVo vo(int code, String message, Object data) {
        return new ResponseVo<>(code, message, data);
    }



    public static ResponseVo error(int code, String message) {
        return vo(code, message, null);
    }

    public static ResponseVo error(ResponseStatusEnum statusEnum) {
        return vo(statusEnum.getCode(), statusEnum.getMessage(), null);
    }

    public static ResponseVo error(String message) {
        return vo(CommonConst.DEFAULT_ERROR_CODE, message, null);
    }


    public static ResponseVo success(String message, Object data) {
        return vo(CommonConst.DEFAULT_SUCCESS_CODE, message, data);
    }

    public static ResponseVo success(String message) {
        return success(message, null);
    }

    public static ResponseVo success(ResponseStatusEnum statusEnum) {
        return vo(statusEnum.getCode(), statusEnum.getMessage(), null);
    }


    public static PageResultVo tablePage(Long total, List<?> list) {
        return new PageResultVo(total, list);
    }

    public static PageResultVo tablePage(PageInfo pageInfo) {
        if (pageInfo == null) {
            return new PageResultVo(0L, new ArrayList());
        }
        return tablePage(pageInfo.getTotal(), pageInfo.getList());
    }
}
