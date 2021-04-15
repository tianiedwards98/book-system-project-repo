package com.company.notequeueconsumer.util.feign;

import com.company.notequeueconsumer.util.messages.Note;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "note-service")
public interface NoteServiceClient {

    //Need To ask Dan how we use feign client for posts and puts
    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    public Note postNote(Note note);

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
    public void updateNote(Note note);
}
