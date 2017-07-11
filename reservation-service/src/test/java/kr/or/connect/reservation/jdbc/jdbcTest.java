package kr.or.connect.reservation.jdbc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.config.RootApplicationContextConfig;
import kr.or.connect.reservation.service.CategoryService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional  // Transactional이 있을 때와 없을 때 각각 실행해보고 그 때마다 msyql에서 결과를 select해본다.
public class jdbcTest {
    @Autowired
    CategoryService categoryService;
    
    /*
    @Test
    public void shouldSelect(){
    	//Member member = new Member("sejun", "riding1516@gmail.com", "sejun");
    	
    	List<Map<String, Object>> result = null;
    	try{
    		result = categoryService.getAllCategory();
    	}catch(Exception e){
    		result = null;
    	}finally{
    		if(result != null){
    			for(Map<String, Object> category: result) {
    			    System.out.println(category.get("name"));
    			}
    	    	assertThat(result.get(0).get("name"), is("test")); // result의 name은 강경미 이다(is). 읽혀지는 코드로 테스트 코드가 작성된다.
    	        assertThat(result.get(0).get("id"), is(1));
    	        
        	}
        	else{
        		System.out.println("result is NULL");
        		//fail("objcet null");
        	}
    	}
    }
    */
    @Test
    public void shouldDelete(){
    	categoryService.delete(1);
    }
}
    