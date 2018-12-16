package com.javaee.artastic.Artastic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javaee.artastic.Artastic.entity.ClientMessage;
import com.javaee.artastic.Artastic.entity.ServerMessage;

@Controller
public class WebSocketController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/sendTest")
    @SendTo("/topic/subscribeTest")
    public ServerMessage sendDemo(ClientMessage message) {
        System.out.println("send:"+message.getName());
        return new ServerMessage(message.getName());
    }

    @SubscribeMapping("/subscribeTest")
    public ServerMessage sub() {

        return new ServerMessage("感谢你的订阅");
    }
    
    public void templateTest() {
        messagingTemplate.convertAndSend("/topic/subscribeTest", new ServerMessage("服务器主动推的数据"));
    }
    
    @RequestMapping(value="webSocketServer")
    @ResponseBody
    public ModelAndView webSocket() {
    	return new ModelAndView("webSocketServer");
    }

}
