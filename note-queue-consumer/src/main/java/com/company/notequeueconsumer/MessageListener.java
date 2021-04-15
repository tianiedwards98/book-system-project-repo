package com.company.notequeueconsumer;

import com.company.notequeueconsumer.util.feign.NoteServiceClient;
import com.company.notequeueconsumer.util.messages.Note;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {
    private NoteServiceClient client;

    @RabbitListener(queues = NoteQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage(Note note) {
        //Feign Client is used here, if checks have to be refined
        if(note.getNoteId() != 0) {
            client.postNote(note);
        }
        if(note.getNoteId() == ) {
            client.updateNote(note);
        }
    }
}
