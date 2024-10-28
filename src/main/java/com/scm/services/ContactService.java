package com.scm.services;

import com.scm.entities.Contact;
import com.scm.entities.User;

import java.util.List;

import org.springframework.data.domain.Page;


public interface ContactService {

    // save contacts
    Contact save(Contact contact); 

    // update contacts
    Contact update(Contact contact);

    // get contacts
    List<Contact> getAll();

    // get contact by id
    Contact getById(String id);

    // delete contact
    Contact delete(String id);

    // search contact
    Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy, String order, User user);

    // get contacts by userid
    List<Contact> getByUserId(String userPage);


    Page<Contact> getByUser(User user,int page, int size, String sortField, String sortDirection);
    






}
