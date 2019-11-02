package com.apress.controller;


import com.apress.domain.Poll;
import com.apress.exception.ResourceNotFoundException;
import com.apress.repository.PollRepository;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;


@RestController
public class PollController {

    @Inject
    private PollRepository pollRepository;



    @RequestMapping(value = "/polls", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/polls", method = RequestMethod.POST)
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {

        poll = pollRepository.save(poll);

        //Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);

        return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
    }
    //retreiving an individual poll
    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        Optional<Poll> p = pollRepository.findById(pollId);
        if (!p.isPresent()) {
            throw new ResourceNotFoundException("Poll with id " + pollId + "not found");
        }
        return new ResponseEntity<>(p,HttpStatus.OK);
    }
    //update poll
    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
        Poll p = pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //delete a poll
    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {

        pollRepository.deleteById(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
