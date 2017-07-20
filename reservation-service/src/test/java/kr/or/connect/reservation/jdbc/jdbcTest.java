package kr.or.connect.reservation.jdbc;
 

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.config.RootApplicationContextConfig;
import kr.or.connect.reservation.service.CategoryService;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional  // Transactional이 있을 때와 없을 때 각각 실행해보고 그 때마다 msyql에서 결과를 select해본다.
public class jdbcTest {
    @Autowired
    CategoryService categoryService;
  
	/*
    @Test
    public void insertFileTest(){
    	//Image img = new Image(1,1,"img","uuid",12, 1,0,new Date(), new Date());
    	//ProductImage product = new ProductImage(1,1,1,1);
    	
    	fileDao.insertFileInfo(img);
		productImageDao.insertFileInfo(product);
		
		//List<Map<String, Object>> result = productImageDao.selectProductImg(1);
		//S/ystem.out.println("!@#");
		//assertThat(result.get(0).get("id"), is(1));
		
    }*/
    
    @Test
    public void shouldSelect(){
    	//Member member = new Member("sejun", "riding1516@gmail.com", "sejun");
    	
    	
    }
    
    @Test
    public void shouldDelete(){
    	categoryService.delete(1);
    }
    
}
    