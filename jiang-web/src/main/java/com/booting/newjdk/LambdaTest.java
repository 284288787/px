/**create by liuhua at 2018年1月10日 下午4:43:28**/
package com.booting.newjdk;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang3.StringUtils;

public class LambdaTest {

	public static void main(String[] args) throws ScriptException {
		new Thread( () -> System.out.println("运行一次") ).start();
		new Thread( () -> {
			System.out.println("运行一次");
		}).start();
		
		List<User> nums = Arrays.asList(
			new User("zhang", 81),
			new User("liu", 76),
			new User("li", 62),
			new User("zhao", 99),
			new User("qian", 44)
		);
		nums.sort((a, b) -> Integer.compare(a.getScore(), b.getScore()));
		nums.forEach( i -> System.out.print(i + " "));
//		nums.forEach(System.out :: print);
		System.out.println();
		List<User> nums2 = nums.stream().filter(i -> i.getScore() > 60).collect(Collectors.toList());
		nums2.forEach(u -> System.out.println(u + " <<<<"));
//		Long lo1 = Stream.builder().add(nums).build().count();
//		Long lo2 = Stream.of(nums).filter(i -> ).count();
//		System.out.println(lo2);
		System.out.println("111111111111111111111111111111111111111111111");
		List<Integer> list = Arrays.asList(42, 62, 33, 74, 22, 66, 66, 88, 45, 54);
		long nu = list.stream().filter(i -> i > 33).count();
		System.out.println(nu);
//		list.stream().filter(i -> i >= 60).
		
		User user = new User("zhang", 88);
//		User user = null;
		Optional<User> optional = Optional.ofNullable(user);
		System.out.println(optional.isPresent());
		System.out.println(optional.orElseGet(() -> User.create(User::new)));
		System.out.println(optional.map(u -> "@" + u).orElse(".."));
		optional.ifPresent(u -> {
			u.name = "a";
			u.score = 45;
		});
		System.out.println(optional.map(u -> u));
		StringUtils.isNotBlank("dfasdf");
		User temp = optional.get();
		System.out.println(temp);
		
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName( "JavaScript" );

		System.out.println( engine.getClass().getName() );
		System.out.println( "Result:" + engine.eval( "var a = {a:30,b:2}; a.a+a.b;" ) );
		
		String str = "今天星期四";
		String encoder = Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
		System.out.println(encoder);
		String decoder = new String(Base64.getDecoder().decode(encoder), StandardCharsets.UTF_8);
		System.out.println(decoder);
		
		long[] manyNums = new long[20000000];
		long n = System.currentTimeMillis();
		for (int j = 0; j < manyNums.length; j++) {
			manyNums[j] = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
		}
		long n2 = System.currentTimeMillis();
		System.out.println("->-->:" + (n2 - n)) ;
		Arrays.parallelSetAll(manyNums, i -> ThreadLocalRandom.current().nextLong(Long.MAX_VALUE));
		long n3 = System.currentTimeMillis();
		System.out.println("->-->:" + (n3 - n2));
		Arrays.stream(manyNums).limit(10).forEach(i -> System.out.print(i + " "));
		System.out.println();
		Arrays.parallelSort(manyNums);
		Arrays.stream(manyNums).limit(10).forEach(i -> System.out.print(i + " "));
	}
	
	static final class User{
		private String name;
		private int score;
		
		public static User create( final Supplier< User > supplier ) {
	        return supplier.get();
	    }        
		
		public User(){
			 System.out.println("null User");
		}
		
		public User(String name, int score){
			this.name = name;
			this.score = score;
		}
		
		public String toString(){
			return String.format("userInfo -> name(%s) socre(%s)", name, score);
		}
		public String getName() {
			return name;
		}
		public int getScore() {
			return score;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setScore(int score) {
			this.score = score;
		}
	}
}
