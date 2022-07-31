package com.sample.jms.ibmmq.mq.controller;

import com.sample.jms.ibmmq.mq.client.JmsClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {
    @Autowired
    private JmsClient jmsClient;

    @RequestMapping(value = "/produce" , method = RequestMethod.POST)
    @ResponseBody
    public String produce(@RequestParam("msg") String msg) {

        jmsClient.send(msg);
        return "Message Sent Successfully from Client WebBrowser";

    }

}
