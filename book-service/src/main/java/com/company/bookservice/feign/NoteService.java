package com.company.bookservice.feign;

import com.company.bookservice.models.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "note-service")
public interface NoteService {
    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    List<Note> getNotesByBook();
}
