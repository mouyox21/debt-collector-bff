package com.mercureit.DebtCollectorBFF.services.event;

import com.mercureit.DebtCollectorBFF.entities.Event;

import java.util.List;

public interface IEventService {
    public List<Event> getAllEvents();
    public Event createEvent(Event event);
    public Event updateEvent(Long id, Event event);
    public void deleteEvent(Long id);
}
