package com.SpringBoot.JournalApp.service;

import com.SpringBoot.JournalApp.entity.JournalEntry;
import com.SpringBoot.JournalApp.entity.User;
import com.SpringBoot.JournalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry){
        journalEntry.setDate(LocalDateTime.now());
        journalEntryRepository.save(journalEntry);
    }

    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String username){
        User user = userService.findByUserName(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}

