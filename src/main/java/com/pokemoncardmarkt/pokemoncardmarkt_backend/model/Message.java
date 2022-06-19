package com.pokemoncardmarkt.pokemoncardmarkt_backend.model;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.Status;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Message {

    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    private Status status;
}
