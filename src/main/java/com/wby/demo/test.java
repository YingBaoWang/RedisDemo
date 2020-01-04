package com.wby.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "北京市(朝阳区)(西城区)(海淀区)";
        Pattern p = Pattern.compile(".*?(?=\\()");
        Matcher m = p.matcher(str);
        if(m.find()) {
            System.out.println(m.group());
        }
        
        try {
        	Integer[] integers = {new Integer(1),new Integer(2),new Integer(3),new Integer(5),new Integer(4)};
			Integer integer = getNumber(integers);
			System.out.println("----------"+integer.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * java8demo
	 * @param integers
	 * @return
	 * @throws Exception
	 */
	public static Integer getNumber(Integer ...integers)throws Exception{
		List<Integer> integerList1 = new ArrayList<>(Arrays.asList(integers));
		//List<Integer> list1 = Arrays.asList(integers);
		List<Integer> integerList2 = new ArrayList<Integer>();
		
		integerList2 = integerList1.stream()
				.filter(integer ->(!(integer.compareTo(Integer.parseInt("6"))<0)))
				.collect(Collectors.toList());
		
		Integer []integers2 = integerList2.stream().toArray(Integer[]::new);
		
		integerList2.forEach(System.out::println);
		System.out.println("*************数组循环赋值***********************");
		
		final Integer[] nums = new Integer[1] ;
		
		integerList1.forEach(listVal->{
			System.out.println("===="+listVal);
			
			if (!(listVal.compareTo(Integer.parseInt("6"))<0)) {
				nums[0] = listVal;
				System.out.println("存在值大于等于6！");
				
				return;
			}
		});
		if (null==nums[0]) {
			throw new Exception("=》不存在值大于等于6！");
		}
		return nums[0];
	}
}
