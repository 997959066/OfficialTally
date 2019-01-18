package cn.xiaoyu.common;
/**
 * 描述:
 * @author  xiaoyu.zhang
 * 日期:2018-12-20 删除没用方法
*/ 
public class CommonUtils {
    
    /***  检查输入参数是否为NULL */
    public static void checkNull(String[] names, Object...args){
        if(null == names || names.length == 0 || null == args || args.length == 0){
            throw new DefaultException(MessageCode.PARAMETER_INPUT_ERROR,"参数不能为空！");
        }
        if(names.length != args.length){
            throw new DefaultException(MessageCode.PARAMETER_INPUT_COUNT_ERROR,"参数名个数和需要验证的参数个数不一致！");
        }
        for(int i=0;i<args.length;i++){
            if(null == args[i]){
                throw new DefaultException(MessageCode.PARAMETER_INPUT_ERROR,names[i]);
            }
        }
    }
 
    
}