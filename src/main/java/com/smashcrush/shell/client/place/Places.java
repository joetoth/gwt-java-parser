package com.smashcrush.shell.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Base {@link com.google.gwt.place.shared.Place} for all Dashboard places.
 * 
 * @author joetoth@gmail.com (Joe Toth)
 */
public class Places extends Place {

  public static class Tokenizer implements PlaceTokenizer<Places> {

    @Override
    public Places getPlace(String token) {
      return NAVIGATION_PLACE;
    }

    @Override
    public String getToken(Places place) {
      return "???";
    }
  }
  
  public static Places NAVIGATION_PLACE = new Places();
  public static Places EDITOR_PLACE = new Places();
  public static Places STATUS_PLACE = new Places();
  
}