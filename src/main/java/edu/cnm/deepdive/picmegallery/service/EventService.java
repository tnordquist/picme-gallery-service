package edu.cnm.deepdive.picmegallery.service;

import edu.cnm.deepdive.picmegallery.model.dao.EventRepository;
import edu.cnm.deepdive.picmegallery.model.entity.Event;
import edu.cnm.deepdive.picmegallery.model.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * This is a @Service class and holds the additional logic for the queries involving an Event in the PicMe database.
 */
@Service
public class EventService {

  /**
   *  This private final field is an Event Repository object.
   */
  private final EventRepository eventRepository;

  /**
   * This initializes and creates an EventService object with the eventRepository.
   * @param eventRepository this is the repository field passed in that allows us to run JPA queries.
   */
  @Autowired
  public EventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  /**
   * This is a method to save an Event object
   * @param event is the new event being created
   * @return an Event that is saved.
   */
  public Event save(Event event) {
    return eventRepository.save(event);
  }

  /**
   * Gets a specified event by the event id and passkey
   * @param id this is the event primary key.
   * @param passkey the user created passkey for an event
   * @return this returns an Event if the id and passkey match up.
   */
  public Optional<Event> get(long id, String passkey) {
    return eventRepository.findByIdAndPasskey(id, passkey);

  }

  /**
   * This method returns an event by passing in the User who created it and the associated event id.
   * @param id this is the event primary key.
   * @param user this is the current signed in User of the application
   * @return an Event object, if there are any associated with the User.
   */
  public Optional<Event> get(long id, User user) {
    return eventRepository.findByIdAndUser(id, user);
  }

  public Optional<Event> getByName(String name, String passkey) {
    return eventRepository.findByNameAndPasskey(name, passkey);
  }
  /**
   *This gets all the Events created by a user.
   * @param user is a User object, specifically the one who created the event.
   * @return a list of Event objects, if there are any associated with the User.
   */
  public List<Event> getAllUserEvents(User user) {
    return eventRepository.findEventsByUserOrUsersContainingOrderByTimeDesc(user, user);
  }

  /**
   * A method that deletes a specified event
   * @param event the Event object to be deleted
   */
  public void delete(Event event) {
      eventRepository.delete(event);
  }

  public static class EventNotFoundException extends ResponseStatusException {

    private static final String NOT_FOUND_REASON = "Event not found";

    public EventNotFoundException() {
      super(HttpStatus.NOT_FOUND, NOT_FOUND_REASON);
    }

  }

}