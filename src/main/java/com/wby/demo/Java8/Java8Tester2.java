package com.wby.demo.Java8;

/**
 * 变量作用域
 * lambda 表达式只能引用标记了 final 的外层局部变量，这就是说不能在lambda 内部修改定义在域外的局部变量，否则会编译错误。
 * @author Administrator
 *
 */
public class Java8Tester2 {
	
	final static String salutation  = "Hello！";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sameName = "same";
		GreetingService greetingService1 = message ->{
			System.out.println(salutation+message);
			/**
			 * 在Lambda 表达式当中不允许声明一个与局部变量同名的参数或者局部变量。
			 */
			//String sameName = "name";//会报错
			/**
			 * lambda 表达式的局部变量可以不用声明为 final，但是必须不可被后面的代码修改（即隐性的具有final 的语义）
			 */
			//salutation = "aaaa";//会报错
		};
		greetingService1.sayMessage("Runoob");
		//====================相当于下面==============================
		GreetingService greetingService2 = new GreetingService(){
			@Override
			public void sayMessage(String message) {
				// TODO Auto-generated method stub
				System.out.println(salutation+message);
			}
		};
		greetingService2.sayMessage("Jack");
	}
	interface GreetingService {
	    void sayMessage(String message);
	}
}
