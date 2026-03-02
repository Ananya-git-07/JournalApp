package com.SpringBoot.JournalApp.service;

import com.SpringBoot.JournalApp.entity.JournalEntry;
import com.SpringBoot.JournalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
        @Autowired
        private JournalEntryRepository journalEntryRepository;

        public void saveEntry(JournalEntry journalEntry){
            journalEntryRepository.save(journalEntry);
        }

        public List<JournalEntry> getAll(){
            return journalEntryRepository.findAll();
        }

        public Optional<JournalEntry> findById(Long id){
          return journalEntryRepository.findById(id);
        }

        public void deleteById(Long id){
            journalEntryRepository.deleteById(id);
        }
    }

