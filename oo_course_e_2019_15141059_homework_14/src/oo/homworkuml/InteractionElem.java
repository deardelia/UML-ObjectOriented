package oo.homworkuml;

import com.oocourse.uml2.models.elements.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class InteractionElem {
    private ArrayList<UmlLifeline> lifelines = new ArrayList<>();
    private ArrayList<UmlMessage> messages = new ArrayList<>();
    private ArrayList<UmlEndpoint> ends = new ArrayList<>();
    private HashMap<String, String> lifelineName = new HashMap<>();
    private HashSet<String> lifeineDuplicateName =  new HashSet<>();
    private HashMap<String, Integer> incommingMap = new HashMap<>();
    private UmlInteraction interaction;

    public InteractionElem(UmlInteraction x) {
        interaction = x;
    }

    public void adjustInteraction() {
        // get duplicate lifeline
        Iterator<UmlLifeline> lifelineIt = lifelines.iterator();
        while (lifelineIt.hasNext()) {
            UmlLifeline curLifeline = lifelineIt.next();
            String curName = curLifeline.getName();
            if (lifelineName.containsKey(curName)) {
                lifeineDuplicateName.add(curName);
            } else {
                lifelineName.put(curName, curLifeline.getId());
            }
        }

        // get incoming message for lifeline
        Iterator<UmlMessage> messageIt = messages.iterator();
        while (messageIt.hasNext()) {
            UmlMessage curMessage = messageIt.next();
            String source = curMessage.getSource();
            String target = curMessage.getTarget();
            if (incommingMap.containsKey(target)) {
                int curCount = incommingMap.get(target);
                curCount = curCount + 1;
                incommingMap.put(target, curCount);
            } else {
                incommingMap.put(target, 1);
            }
        }
    }

    public int getIncommingCount(String lifelinename) {
        String lifelineId = lifelineName.get(lifelinename);
        return incommingMap.get(lifelineId);
    }

    public HashMap<String, String> getLifelineName() {
        return lifelineName;
    }

    public HashSet<String> getLifeineDuplicateName() {
        return lifeineDuplicateName;
    }

    public void setEnds(UmlEndpoint x) {
        ends.add(x);
    }

    public void setLifelines(UmlLifeline x) {
        lifelines.add(x);
    }

    public ArrayList<UmlLifeline> getLifelines() {
        return lifelines;
    }

    public void setMessages(UmlMessage x) {
        messages.add(x);
    }

    public ArrayList<UmlMessage> getMessages() {
        return messages;
    }

    public UmlInteraction getInteraction() {
        return interaction;
    }


}
