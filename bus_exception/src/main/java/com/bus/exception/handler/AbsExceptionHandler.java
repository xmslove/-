package com.bus.exception.handler;

import com.bus.exception.UnifiedException;
import com.bus.exception.ifs.ExceptionHandler;
import com.bus.exception.vo.GlobalInfo;

/**
 * <Description>异常处理抽象类<br>
 * 
 * @author xms<br>
 * @version 1.0<br>
 * @taskId <br>
 */
public abstract class AbsExceptionHandler implements ExceptionHandler {
    /**
     * boolean flag of Asyn
     */
    private boolean asynFlag = false;
    
    @Override
    public void handle(UnifiedException ue, GlobalInfo gi,ExceptionHandlerChain ehc) {
        if (supports(ue)) {
            if(asynFlag) {
                gi.getExceptionHandleExecutor().execute(new ExceptionHandlerRunnable(ue, gi));
            } else {
                doHandle(ue,gi);
            }
        }
        if(!gi.isLast()) {
            ehc.handleException(ue, gi);
        }
    }

    /**
     * 
     * Description:执行异常处理<br> 
     *  
     * @author wanglei<br>
     * @taskId <br>
     * @param ue 统一异常
     * @param gi 异常处理全局信息
     * @return <br>
     */
    protected abstract void doHandle(UnifiedException ue,GlobalInfo gi);
    
    /**
     * 
     * <Description>runnable used for asyn exception handle<br> 
     *  
     * @author wanglei<br>
     * @version 1.0<br>
     * @taskId <br>
     * @CreateDate 2015年4月27日 <br>
     */
    private class ExceptionHandlerRunnable implements Runnable{

        /**
         * @see UnifiedException
         */
        private UnifiedException ue;
        /**
         * @see GlobalInfo
         */
        private GlobalInfo       gi;

        /**
         * constructor
         * @param ue
         * @param gi
         */
        public ExceptionHandlerRunnable(UnifiedException ue, GlobalInfo gi) {
            this.ue = ue;
            this.gi = gi;
        }

        @Override public void run() {
            doHandle(ue, gi);
        }

    }

    /**
     * get asynFlag
     * @return Returns the asynFlag.<br> 
     */
    public boolean getAsynFlag() {
        return asynFlag;
    }

    /**
     * set asynFlag
     * @param asynFlag The asynFlag to set. <br>
     */
    public void setAsynFlag(boolean asynFlag) {
        this.asynFlag = asynFlag;
    }

}
