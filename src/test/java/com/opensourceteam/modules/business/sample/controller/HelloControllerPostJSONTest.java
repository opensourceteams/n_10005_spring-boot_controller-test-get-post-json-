package com.opensourceteam.modules.business.sample.controller;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 开发人:刘文
 * 日期:  2017/11/25.
 * 功能描述:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {HelloController.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackages = {"com.opensourceteam.business","com.opensourceteam"})
@EnableWebMvc
@PropertySource({"application.properties"})
public class HelloControllerPostJSONTest {

    Logger logger = LoggerFactory.getLogger(HelloControllerPostJSONTest.class);



    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void hello() throws Exception {



        String url = "http://localhost:" + port + "/helloJSON";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message","数据");
        jsonObject.put("code","代码");
        String json = jsonObject.toString() ;
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        HttpEntity<String> response = this.restTemplate.postForEntity(url ,request,String.class) ;

        logger.info("result:{}" , response.getBody());
    }

}