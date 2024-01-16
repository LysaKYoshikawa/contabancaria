package com.yoshikawa.contabancaria.services;

import com.yoshikawa.contabancaria.DTOs.NotificationDTO;
import com.yoshikawa.contabancaria.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception{
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://run.mocky.io/v3/9769bf3a-b0b6-477a-9ff5-91f63010c9d3", notificationRequest, String.class);

        //Object messageSentObject = notificationResponse.getBody().get("messageSent");

        if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
            System.out.println("Erro ao enviar o aviso");
            throw new Exception("Serviço de notificação não esta disponivel no momento");

        }
    }
}
