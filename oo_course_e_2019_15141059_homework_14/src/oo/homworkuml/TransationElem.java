package oo.homworkuml;

import com.oocourse.uml2.models.elements.UmlEvent;
import com.oocourse.uml2.models.elements.UmlTransition;

import java.util.ArrayList;

public class TransationElem {
    private UmlTransition transition;
    private ArrayList<UmlEvent> events = new ArrayList<>();

    public TransationElem(UmlTransition x) {
        transition = x;
    }

    public void setEvents(UmlEvent x) {
        events.add(x);
    }

    public UmlTransition getTransition() {
        return transition;
    }
}
