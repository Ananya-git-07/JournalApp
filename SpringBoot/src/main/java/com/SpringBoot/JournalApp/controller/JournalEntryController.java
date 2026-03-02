package com.SpringBoot.JournalApp.controller;

import com.SpringBoot.JournalApp.entity.JournalEntry;
import com.SpringBoot.JournalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/get-entries")
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    @PostMapping("/add-entry")
    public boolean postEntry(@RequestBody JournalEntry entry){
        entry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(entry);
        return true;
    }

    @GetMapping("/get-entry/{id}")
    public JournalEntry getById(@PathVariable Long id){
        return journalEntryService.findById(id).orElse(null);
    }

    @DeleteMapping("/delete-entry/{id}")
    public void deleteById(@PathVariable Long id){
        journalEntryService.deleteById(id);
    }

    @PutMapping("/update-entry/{id}")
    public JournalEntry updateById(@PathVariable Long id, @RequestBody JournalEntry entry){
        JournalEntry temp = journalEntryService.findById(id).orElse(null);
        if(temp!=null){
            temp.setTitle(entry.getTitle() != null && !entry.getTitle().equals("") ? entry.getTitle() : temp.getTitle());
            temp.setContent(entry.getContent() != null && !entry.getContent().equals("") ? entry.getContent() : temp.getContent());
        }
        journalEntryService.saveEntry(temp);
        return temp;
    }
}