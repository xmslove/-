package com.bus.exception.ifs;

import com.bus.exception.UnifiedException;

/**
 * 
 * <Description>异常处理支持接口<br> 
 *  
 * @author xms<br>
 * @version 1.0<br>
 * @taskId <br>
 */
public interface HandlerSupport {
    

    /**
     * 
     * Description:是否支持该异常处理<br> 
     *  
     * @author wanglei<br>
     * @taskId <br>
     * @param ue 统一异常
     * @return <br>
     */
    public boolean supports(UnifiedException ue);
}
