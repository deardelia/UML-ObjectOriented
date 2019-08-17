package oo.homworkuml;

import com.oocourse.uml2.models.elements.*;

import java.util.*;

public class StateMachineElem {
    private UmlStateMachine stateMachineSelf;
    private HashMap<String, HashMap<String, ArrayList<String>>>
            transitionMetrics = new HashMap<>();
    private HashMap<String, String> stateName = new HashMap<>();
    private HashSet<String> stateDuplicateName =  new HashSet<>();
    private ArrayList<TransationElem> transitions = new ArrayList<>();
    private ArrayList<UmlState> states = new ArrayList<>();
    private UmlPseudostate startState = null;
    private ArrayList<UmlFinalState> finalStates = new ArrayList<>();
    private String region = null;
    private HashMap<String, ArrayList<String>> subsequentMap = new HashMap<>();

    public StateMachineElem(UmlStateMachine x) {
        stateMachineSelf = x;
    }

    public void replicateStates() {
        Iterator<UmlState> statesIt = states.iterator();
        while (statesIt.hasNext()) {
            UmlState curState = statesIt.next();
            String curName = curState.getName();
            if (stateName.containsKey(curName)) {
                stateDuplicateName.add(curName);
            } else {
                stateName.put(curName, curState.getId());
            }
        }
    }

    public void concateTrans() {

    }

    public HashMap<String, HashMap<String, ArrayList<String>>>
    getTransitionMetrics() {
        return transitionMetrics;
    }

    public int getSubsequenceNum(String stateId) {
        HashSet<String> stateIdSets = new HashSet<>();
        if (subsequentMap.containsKey(stateId)) {
            Iterator<String> subsequncesIt = subsequentMap.
                    get(stateId).iterator();
            while (subsequncesIt.hasNext()) {
                String curSubId = subsequncesIt.next();
                stateIdSets.add(curSubId);
                Queue<String> que = new LinkedList<>();
                que.add(curSubId);
                while (!que.isEmpty()) {
                    String tmpState = que.poll();
                    if (subsequentMap.containsKey(tmpState)) {
                        Iterator<String> subsequencesSubs = subsequentMap.
                                get(tmpState).iterator();
                        while (subsequencesSubs.hasNext()) {
                            String parentId = subsequencesSubs.next();
                            if (!stateIdSets.contains(parentId)) {
                                stateIdSets.add(parentId);
                                que.offer(parentId);                        }
                        }
                    }
                }
            }
            return stateIdSets.size();
        }
        return 0;
    }

    public void setTransitionMetrics() {
        Iterator<TransationElem> transationsIt = transitions.iterator();
        while (transationsIt.hasNext()) {
            TransationElem curTrans = transationsIt.next();
            String source = curTrans.getTransition().getSource();
            String target = curTrans.getTransition().getTarget();
            if (subsequentMap.containsKey(source)) {
                ArrayList<String> subsequences = subsequentMap.get(source);
                if (!subsequences.contains(target)) {
                    subsequences.add(target);
                }
                subsequentMap.put(source, subsequences);
            } else {
                ArrayList<String> newSubsequences = new ArrayList<>();
                newSubsequences.add(target);
                subsequentMap.put(source, newSubsequences);
            }
            if (transitionMetrics.containsKey(source)) {
                HashMap<String, ArrayList<String>> curMetrics = transitionMetrics.get(source);
                if (curMetrics.containsKey(target)) {
                    ArrayList<String> curTransations = curMetrics.get(target);
                    curTransations.add(curTrans.getTransition().getId());
                    curMetrics.put(target, curTransations);
                } else {
                    ArrayList<String> newTransations = new ArrayList<>();
                    newTransations.add(curTrans.getTransition().getId());
                    curMetrics.put(target, newTransations);
                }
                transitionMetrics.put(source, curMetrics);
            } else {
                HashMap<String, ArrayList<String>> newMetrics= new HashMap<>();
                transitionMetrics.put(source, newMetrics);
            }
        }
    }

    public HashMap<String, String> getStateName() {
        return stateName;
    }

    public HashSet<String> getStateDuplicateName() {
        return stateDuplicateName;
    }

    public void setTransitions(TransationElem x) {
        transitions.add(x);
    }

    public ArrayList<TransationElem> getTransitions() {
        return transitions;
    }

    public void setStates(UmlState x) {
        states.add(x);
    }

    public ArrayList<UmlState> getStates() {
        return states;
    }

    public void setFinalStates(UmlFinalState x) {
        finalStates.add(x);
    }

    public ArrayList<UmlFinalState> getFinalStates() {
        return finalStates;
    }

    public void setStartState(UmlPseudostate x) {
        startState = x;
    }

    public String getStartState() {
        return startState.getId();
    }

    public void setRegion(String x) {
        region = x;
    }

    public String getRegion() {
        return region;
    }
}
