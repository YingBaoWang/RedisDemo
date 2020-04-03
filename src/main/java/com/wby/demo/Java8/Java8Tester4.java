package com.wby.demo.Java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * ava 8 Stream
 * Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据。
 * Stream使用一种类似用SQL语句从数据库查询数据的直观方式来提供一种对Java集合运算和表达的高阶抽象。
 * Stream API可以极大提高Java程序员的生产力，让程序员写出高效率、干净、简洁的代码。
 * 这种风格将要处理的元素集合看作一种流，流在管道中传输，并且可以在管道的节点上进行处理，比如筛选，排序，聚合等。
 * 元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。
 * @author Administrator
 *
 */
public class Java8Tester4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
		
		System.out.println("列表："+strings);
		
		/**
		 * 在Java 8中,集合接口有两个方法来生成流：stream() −为集合创建串行流。parallelStream() − 为集合创建并行流。
		 * Count 计数是一个最终操作，返回Stream中元素的个数，返回值类型是long。
		 */
		int count = (int)strings.stream().filter(string -> string.isEmpty()).count();
		System.out.println("空字符串的数量:"+count);
		
		count = (int)strings.parallelStream().filter(string -> string.isEmpty()).count();
		System.out.println("空字符串的数量为: " + count);
		count = (int)strings.stream().filter(string -> 3==string.length()).count();
		System.out.println("字符串长度为3的数量:"+count);
		
		/**
		 * Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors可用于返回列表或字符串：
		 */
		List<String> filtered = strings.stream().filter(
				string -> !string.isEmpty()).collect(Collectors.toList());
		System.out.println("筛选后的列表:"+filtered);
		String mergedString = strings.stream().filter(
				string -> !string.isEmpty()).collect(Collectors.joining(","));
		System.out.println("合并后的字符串:"+mergedString);
		
		List<Integer> numbers = Arrays.asList(3,2,2,3,7,3,5);
		System.out.println("列表:"+numbers);
		/**
		 * map 方法用于映射每个元素到对应的结果。distinct 方法数值不重复。
		 */
		List<Integer> squaresList = numbers.stream().
				map(i->i*i).distinct().collect(Collectors.toList());
		System.out.println("平方列表:"+squaresList);
		
		/**
		 * 另外，一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果。
		 */
		IntSummaryStatistics statistics = numbers.stream().mapToInt(x->x).summaryStatistics();
		System.out.println("列表中最大的数 : " + statistics.getMax());
        System.out.println("列表中最小的数 : " + statistics.getMin());
        System.out.println("所有数之和 : " + statistics.getSum());
        System.out.println("平均数 : " + statistics.getAverage());
        Collections.sort(numbers,(a,b)->b.compareTo(a));
        List<Integer> sortList = numbers.stream().sorted().collect(Collectors.toList());
        System.out.println("排序列表（从小到大）:"+sortList);
        sortList = numbers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("排序列表（从大到小）:"+sortList);
        System.out.println("随机数: ");
        /**
         *  limit 方法用于获取指定数量的流。sorted 方法用于对流进行排序。
         */
        Random random = new Random();
		random.ints().limit(10).sorted().forEach(System.out::println);
		
	}
}
