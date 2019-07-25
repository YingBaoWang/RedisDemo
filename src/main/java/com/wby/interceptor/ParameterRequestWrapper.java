package com.wby.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ParameterRequestWrapper extends HttpServletRequestWrapper {

	private Map<String, String[]> params = new HashMap<String, String[]>();
	private Logger logger = LogManager.getLogger(ParameterRequestWrapper.class);
	 
    public ParameterRequestWrapper(HttpServletRequest request) {
        // 将request交给父类，以便于调用对应方法的时候，将其输出，其实父亲类的实现方式和第一种new的方式类似
        super(request);
        
        //将参数表，赋予给当前的Map以便于持有request中的参数
        this.params.putAll(request.getParameterMap());
        this.modifyParameterValues();
    }
 
    //重载一个构造方法
    public ParameterRequestWrapper(HttpServletRequest request, Map<String, Object> extendParams) {
        this(request);
        addAllParameters(extendParams);//这里将扩展参数写入参数表
    }
 
    public void modifyParameterValues() {//将parameter的值去除空格后重写回去
        Set<String> set = params.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                values[i] = values[i].trim();
            }
            params.put(key, values);
        }
 
    }
 
    @Override
    public Enumeration<String> getParameterNames() {
        return new Vector<String>(params.keySet()).elements();
    }
    @Override
    public String getParameter(String name) {//重写getParameter，代表参数从当前类中的map获取
    	try {
    		String[] values = params.get(name);
            if (values == null || values.length == 0) {
                return null;
            }else{
            	/*
            	 * url中传递的参数带有加号+，会被浏览器转换成空格，这样会导致后台获取到无效的参数
            	 * 解决方式
    				1 后端replace（“ ”，“+”）；
    				2 前端用将前端的key参数进行编码encodeURIComponent(key)进行处理，然后传送
    				原因
    				URL传递字符串式，如果没有进行encodeURIComponent(key)的话，原始字符串在后台接收到后需要进行URLdecode.decode处理这时候会把特殊字符进行处理了，
    				+就没了。因为+被当做字符串连接符了。前端处理一下就好了。
            	 */
            	values[0] = values[0].replace(" ", "+");
            	//System.out.println(name+"---"+AESCipher.decryptAES(values[0].trim(), ""));
            	
            	//return AESCipher.decryptAES(values[0].trim(), "");
            }
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("========", e);
		}
    	return null;
    }
 
    public String[] getParameterValues(String name) {//同上
        return params.get(name);
    }
 
 
    public void addAllParameters(Map<String, Object> otherParams) {//增加多个参数
        for (Map.Entry<String, Object> entry : otherParams.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    }
 
 
    public void addParameter(String name, Object value) {//增加参数
        if (value != null) {
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }
        }
    }
}