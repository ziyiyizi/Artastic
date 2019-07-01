package com.javaee.artastic.Artastic.service.impl; 

import com.csvreader.CsvReader;
import com.javaee.artastic.Artastic.App;
import net.minidev.json.JSONArray;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.thymeleaf.context.Context;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/** 
* MailService Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 1, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class MailServiceTest{
    @Autowired
    MailService mailService;

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
}

/** 
* 
* Method: sendSimpleMail(String toMail, String subject, String content) 
* 
*/ 
@Test
public void testSendSimpleMail() throws Exception {

//TODO: Test goes here... 
} 

/** 
* 
* Method: sendHtmlMail(String toMail, String subject, String htmlPage, Context context) 
* 
*/ 
@Test
public void testSendHtmlMail() throws Exception { 
//TODO: Test goes here...
    String testCase = "UT_TC_001_001";
    System.out.println(testCase);

    CsvReader csvReader = new CsvReader("src/testcase/unittest/UT_TC_001_004.csv", ',', Charset.forName("utf8"));
    csvReader.readHeaders();
    System.out.println("Test-function--sendHtmlMail");
    //用来存储是否通过测试的Boolean
    List<Boolean> passedList = new ArrayList<>();

    String subject = "theme:Click to activate your Artastic account!";

    int i = 0;
    while (csvReader.readRecord()) {

        String keyword = csvReader.get(0);
        boolean expect = Boolean.valueOf(csvReader.get(1));

        //java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);//关闭烦人的hibernate日志输出

        Context context = new Context();
        context.setVariable("email", keyword);
        context.setVariable("registerLink", "http://localhost:8080/Register/check");
        boolean output = mailService.sendHtmlMail(keyword, subject, "activate", context);

        boolean isPassed = (output == expect) ? true : false;
        if (isPassed) {
            System.out.print(testCase+"-"+(++i)+" passed\n" );
        } else {
            System.out.print(testCase+"-"+(++i)+" failed\n" );
        }
        System.out.println("output:" + output);
        System.out.println("expect:" + expect);
        //将测试结果放在列表里
        passedList.add(isPassed);
        System.out.println("\n");
    }
    //断言是为了调用junit工具来记录是否通过测试
    for (boolean p : passedList) {
        assert p;
    }
} 


} 
