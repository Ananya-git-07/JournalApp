package com.SpringBoot.JournalApp.repository;

import com.SpringBoot.JournalApp.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalEntryRepository extends MongoRepository<JournalEntry,Long>{

}
