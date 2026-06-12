package com.mercureit.DebtCollectorBFF.controller;

import com.mercureit.DebtCollectorBFF.entities.Event;
import com.mercureit.DebtCollectorBFF.services.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/event", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events-list")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @PostMapping("/create-event")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createEvent = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(createEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        Event UpdateEvent = eventService.updateEvent(id, event);
        if (UpdateEvent != null) {
            return ResponseEntity.ok(UpdateEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
