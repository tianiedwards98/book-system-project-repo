package com.company.notequeueconsumer.util.feign;

import com.company.notequeueconsumer.util.messages.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "note-service")
public interface NoteServiceClient {

    //Need To ask Dan how we use feign client for posts and puts
    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    public Note postNote(@RequestBody Note note);

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
    public void updateNote(@PathVariable int id,@RequestBody Note note);
}
