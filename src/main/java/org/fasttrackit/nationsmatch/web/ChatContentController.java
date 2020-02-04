package org.fasttrackit.nationsmatch.web;

import org.fasttrackit.nationsmatch.domain.ChatContent;
import org.fasttrackit.nationsmatch.service.ChatContentService;
import org.fasttrackit.nationsmatch.transfer.ChatContentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@Controller
@RequestMapping("/chat-content")
public class ChatContentController {

    private final ChatContentService chatContentService;

    public ChatContentController(ChatContentService chatContentService) {
        this.chatContentService = chatContentService;
    }

    @PostMapping
    public ResponseEntity<ChatContent> createTest(@RequestBody @Valid ChatContentRequest request) {
        ChatContent chatContent = chatContentService.createChat(request);
        return new ResponseEntity<>(chatContent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ChatContent>> getTests(Pageable pageable) {
        Page<ChatContent> tests = chatContentService.getChats(pageable);
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }
}
