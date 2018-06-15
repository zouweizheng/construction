package com.company.project.test;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/10/29.
 */
@RestController
@RequestMapping("/helloworld")
public class HelloWordController {


    @RequestMapping("/hello")
    public String HelloWorld(){
        return "Hello";
    }

   /* @RequestMapping("/getTestBill")
    public String getTestBill(){
        BillPojo billPojo = new BillPojo();
        return billService.getNameTest("name" ,billPojo);
    }*/
    /*@RequestMapping("/getTestCon")
    public String getTestCon(){
        ConstructionPojo billPojo = new ConstructionPojo();
        return constructionService.getNameTest("name" ,billPojo);
    }
    @RequestMapping("/getTestBillMapper")
    public String getTestBillMapper(){
        BillPojo billPojo = new BillPojo();
        BillMapper billMapper = new BillMapper(billPojo);
        return billService.getNameTest("name" ,billMapper);
    }
    @RequestMapping("/getTestBillMapperMethod")
    public String getTestBillMapperMethod() throws InstantiationException, IllegalAccessException {
        BillPojo billPojo = new BillPojo();
        BillMapper billMapper = new BillMapper(billPojo);
        return billService.getMethodTest("getName" ,billMapper);
    }*/


}
