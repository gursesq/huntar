package com.example.prototype.Event;


import com.example.prototype.Event.MyEvent;

public class OpenEvent extends MyEvent {

    //properties


    //consturctrors

    public OpenEvent( MyEvent event, boolean bool) {
        super(event.getName(),event.getDate(),event.getDistance());
        super.isFollowed = bool;
    }

    //methods
}
