package com.innometrics.apsis.loadtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * Created by KILL_PACK on 27.03.2016.
 */
@Controller
@EnableAutoConfiguration
public class TestController {
    public static final int MAX_DALAY = 10000;
    long sendOk =0;
     long sendFail =0;
    Random random =new Random();
    @RequestMapping(value = "/v1/transactional/projects/**" ,method = RequestMethod.POST )
    @ResponseBody
    ResponseEntity<String> home(@RequestBody String body) throws InterruptedException {
        Thread.sleep(Math.round(random.nextFloat()* MAX_DALAY));

        if (random.nextBoolean()){
            sendOk++;
            return new ResponseEntity<String>("", HttpStatus.OK);
        }else {
            sendFail++;
            return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/info/")
    @ResponseBody
    ResponseEntity<String> info() {
            return new ResponseEntity<String>("failed =" + sendFail + " sended = "+ sendOk, HttpStatus.OK);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestController.class, args);
    }
}