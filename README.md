# n_10005_spring-boot_controller-test(get、post、json、请求)
- spring-boot 单元测试 controller
- 请求的方式有(get、port、json)
- 视频地址: https://www.youtube.com/watch?v=R60EBNblFsg

## 控制层
### get 、post 请求方式，接收字符串参数 get 、post 请求方式，接收字符串参数
 

    @Controller
    public class HelloController {
    
        Logger logger = LoggerFactory.getLogger(HelloController.class);
    
        @RequestMapping("/hello")
        @ResponseBody
        String hello(String message) {
            logger.info("hello  message:{} " ,  message);
            return "Hello World! message: " + message;
        }
    
    }
    
## 控制层单元测试
### get 请求方式单元测试
```java
package com.opensourceteam.modules.business.sample.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.Map;

/**
 * 开发人:刘文
 * 日期:  2017/11/25.
 * 功能描述:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {HelloController.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackages = {"com.opensourceteam.business","com.opensourceteam"})
@EnableWebMvc
public class HelloControllerGetTest {

    Logger logger = LoggerFactory.getLogger(HelloControllerGetTest.class);



    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void hello() throws Exception {


        String result = this.restTemplate.getForObject("http://localhost:" + port + "/hello?message=中国",
                String.class);
        logger.info("result:{}" , result);
    }

}
```
### post 请求方式单元测试
```java
package com.opensourceteam.modules.business.sample.controller;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
public class HelloControllerPostTest {

    Logger logger = LoggerFactory.getLogger(HelloControllerPostTest.class);



    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void hello() throws Exception {



        String url = "http://localhost:" + port + "/hello";

        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("message","小明");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        HttpEntity<String> response = this.restTemplate.postForEntity(url ,request,String.class) ;

        logger.info("result:{}" , response.getBody());
    }

}
```

### json 请求方式单元测试
```java
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
```

end