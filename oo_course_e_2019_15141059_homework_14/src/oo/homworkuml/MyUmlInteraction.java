package oo.homworkuml;

import com.oocourse.uml2.interact.common.AttributeClassInformation;
import com.oocourse.uml2.interact.common.AttributeQueryType;
import com.oocourse.uml2.interact.common.OperationQueryType;
import com.oocourse.uml2.interact.exceptions.user.AttributeDuplicatedException;
import com.oocourse.uml2.interact.exceptions.user.AttributeNotFoundException;
import com.oocourse.uml2.interact.exceptions.user.ClassDuplicatedException;
import com.oocourse.uml2.interact.exceptions.user.ClassNotFoundException;
import com.oocourse.uml2.interact.exceptions.user.InteractionDuplicatedException;
import com.oocourse.uml2.interact.exceptions.user.InteractionNotFoundException;
import com.oocourse.uml2.interact.exceptions.user.LifelineDuplicatedException;
import com.oocourse.uml2.interact.exceptions.user.LifelineNotFoundException;
import com.oocourse.uml2.interact.exceptions.user.StateDuplicatedException;
import com.oocourse.uml2.interact.exceptions.user.StateMachineDuplicatedException;
import com.oocourse.uml2.interact.exceptions.user.StateMachineNotFoundException;
import com.oocourse.uml2.interact.exceptions.user.StateNotFoundException;
import com.oocourse.uml2.interact.exceptions.user.UmlRule002Exception;
import com.oocourse.uml2.interact.exceptions.user.UmlRule008Exception;
import com.oocourse.uml2.interact.exceptions.user.UmlRule009Exception;
import com.oocourse.uml2.interact.format.UmlGeneralInteraction;
import com.oocourse.uml2.models.common.Visibility;
import com.oocourse.uml2.models.elements.UmlAssociation;
import com.oocourse.uml2.models.elements.UmlAssociationEnd;
import com.oocourse.uml2.models.elements.UmlAttribute;
import com.oocourse.uml2.models.elements.UmlClass;
import com.oocourse.uml2.models.elements.UmlClassOrInterface;
import com.oocourse.uml2.models.elements.UmlElement;
import com.oocourse.uml2.models.elements.UmlEndpoint;
import com.oocourse.uml2.models.elements.UmlEvent;
import com.oocourse.uml2.models.elements.UmlFinalState;
import com.oocourse.uml2.models.elements.UmlGeneralization;
import com.oocourse.uml2.models.elements.UmlInteraction;
import com.oocourse.uml2.models.elements.UmlInterfaceRealization;
import com.oocourse.uml2.models.elements.UmlLifeline;
import com.oocourse.uml2.models.elements.UmlMessage;
import com.oocourse.uml2.models.elements.UmlOperation;
import com.oocourse.uml2.models.elements.UmlParameter;
import com.oocourse.uml2.models.elements.UmlPseudostate;
import com.oocourse.uml2.models.elements.UmlRegion;
import com.oocourse.uml2.models.elements.UmlState;
import com.oocourse.uml2.models.elements.UmlStateMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MyUmlInteraction implements UmlGeneralInteraction {
    private UmlElement[] umlElements;
    private HashMap<String, UmlClass> classNameMap = new HashMap<>();
    private HashSet<String> classDuplicateName = new HashSet<>();
    private HashMap<String, OperationElement> operationMap = new HashMap<>();
    private HashMap<String, UmlAttribute> attributeMap = new HashMap<>();
    private HashMap<String, UmlParameter> paramterMap = new HashMap<>();
    private HashMap<String, UmlGeneralization> generalizeMap = new HashMap<>();
    private HashMap<String, UmlAssociation> associationMap = new HashMap<>();
    private HashMap<String, UmlAssociationEnd> associationEndMap
            = new HashMap<>();
    private HashMap<String, UmlInterfaceRealization> interfaceReMap
            = new HashMap<>();
    private HashMap<String, ClassElement> classRestruct = new HashMap<>();
    private HashMap<String, InterfaceElement> interfaceRestruct
            = new HashMap<>();
    private HashMap<String, ClassLevelAbstract> abstractClass = new HashMap<>();
    private HashMap<String, StateMachineElem> stateMachineMap = new HashMap<>();
    private HashMap<String, UmlStateMachine> stateMachineNameMap
            = new HashMap<>();
    private HashSet<String> stateMachineDuplicateName = new HashSet<>();
    private HashMap<String, TransationElem> transitionMap = new HashMap<>();
    private HashMap<String, UmlState> statesMap = new HashMap<>();
    private HashMap<String, UmlFinalState> finalStateMap = new HashMap();
    private HashMap<String, UmlRegion> regionMap = new HashMap<>();
    private HashMap<String, UmlPseudostate> pseudostateMap = new HashMap<>();
    private HashMap<String, UmlEvent> eventMap = new HashMap<>();
    private HashMap<String, InteractionElem> interactionMap = new HashMap<>();
    private HashMap<String, UmlInteraction> interactionNameMap
            = new HashMap<>();
    private HashSet<String> interactionDuplicateName = new HashSet<>();
    private HashMap<String, UmlLifeline> lifelineHashMap = new HashMap<>();
    private HashMap<String, UmlMessage> messageHashMap = new HashMap<>();
    private HashMap<String, UmlEndpoint> endpointHashMap = new HashMap<>();
    private Util util;

    public MyUmlInteraction(UmlElement... elements) {
        umlElements = elements;
        util = new Util(classRestruct, interfaceRestruct, abstractClass,
                umlElements);
        util.initForAll(classNameMap, classDuplicateName, operationMap,
                attributeMap, paramterMap, associationMap, associationEndMap,
                interfaceReMap, generalizeMap, stateMachineMap,
                stateMachineNameMap, stateMachineDuplicateName, transitionMap,
                statesMap, finalStateMap, regionMap, pseudostateMap, eventMap,
                interactionMap, interactionNameMap, interactionDuplicateName,
                lifelineHashMap, messageHashMap, endpointHashMap);
        structInit();
    }

    private void structInit() {
        util.structOperation(paramterMap, operationMap);
        util.structSelfAssociate(associationEndMap);
        util.getAttribute(attributeMap);
        util.getInterface(interfaceReMap);
        util.structInherit(generalizeMap);
        util.structAssociation(associationMap, associationEndMap);

        util.structRegions(regionMap, stateMachineMap);
        util.structStates(statesMap, pseudostateMap, finalStateMap,
                stateMachineMap);
        util.structTransitions(eventMap, transitionMap, stateMachineMap);
        util.adjustStateMachine(stateMachineMap);

        util.structInteraction(interactionMap, lifelineHashMap,
                endpointHashMap, messageHashMap);
    }


    // TODO : IMPLEMENT
    public void checkForUml002() throws UmlRule002Exception {
        Set<AttributeClassInformation> result = new HashSet<>();
        util.roneCheck(result);
        if (result.size() > 0) {
            throw new UmlRule002Exception(result);
        }
    }

    public void checkForUml008() throws UmlRule008Exception {
        Set<UmlClassOrInterface> result = new HashSet<>();
        util.rtwoCheck(result);
        if (result.size() > 0) {
            throw new UmlRule008Exception(result);
        }
    }

    public void checkForUml009() throws UmlRule009Exception {
        Set<UmlClassOrInterface> result = new HashSet<>();
        util.rthreeCheck(result);
        if (result.size() > 0) {
            throw new UmlRule009Exception(result);
        }
    }

    public int getStateCount(String stateMachineName)
            throws StateMachineNotFoundException,
            StateMachineDuplicatedException {
        int count = 0;
        if (!stateMachineNameMap.containsKey(stateMachineName)) {
            throw new StateMachineNotFoundException(stateMachineName);
        } else if (stateMachineDuplicateName.contains(stateMachineName)) {
            throw new StateMachineDuplicatedException(stateMachineName);
        } else {
            String stateMachineId = stateMachineNameMap.
                    get(stateMachineName).getId();
            StateMachineElem curStateMachine = stateMachineMap.
                    get(stateMachineId);
            if (curStateMachine.getStartState() != null) {
                count += 1;
            }
            count += curStateMachine.getFinalStates().size();
            count += curStateMachine.getStates().size();
        }
        return count;
    }

    public int getTransitionCount(String stateMachineName)
            throws StateMachineNotFoundException,
            StateMachineDuplicatedException {
        int count = 0;
        if (!stateMachineNameMap.containsKey(stateMachineName)) {
            throw new StateMachineNotFoundException(stateMachineName);
        } else if (stateMachineDuplicateName.contains(stateMachineName)) {
            throw new StateMachineDuplicatedException(stateMachineName);
        } else {
            String stateMachineId = stateMachineNameMap.
                    get(stateMachineName).getId();
            StateMachineElem curStateMachine = stateMachineMap.
                    get(stateMachineId);
            count = curStateMachine.getTransitions().size();
        }
        return count;
    }

    public int getSubsequentStateCount(String stateMachineName, String stateName)
            throws StateMachineNotFoundException,
            StateMachineDuplicatedException,
            StateNotFoundException, StateDuplicatedException {
        int count = 0;
        if (!stateMachineNameMap.containsKey(stateMachineName)) {
            throw new StateMachineNotFoundException(stateMachineName);
        } else if (stateMachineDuplicateName.contains(stateMachineName)) {
            throw new StateMachineDuplicatedException(stateMachineName);
        } else {
            String stateMachineId = stateMachineNameMap.
                    get(stateMachineName).getId();
            StateMachineElem curStateMachine = stateMachineMap.
                    get(stateMachineId);
            if (!curStateMachine.getStateName().containsKey(stateName)) {
                throw new StateNotFoundException(stateMachineName, stateName);
            } else if (curStateMachine.getStateDuplicateName().
                    contains(stateName)) {
                throw new StateDuplicatedException(stateMachineName,stateName);
            } else {
                String stateId = curStateMachine.getStateName().get(stateName);
                count += curStateMachine.getSubsequenceNum(stateId);
            }
        }
        return count; // pay attention to duplicate state
    }

    public int getParticipantCount(String interactionName)
            throws InteractionNotFoundException,
            InteractionDuplicatedException {
        int count = 0;
        if (!interactionNameMap.containsKey(interactionName)) {
            throw new InteractionNotFoundException(interactionName);
        } else if (interactionDuplicateName.contains(interactionName)) {
            throw new InteractionDuplicatedException(interactionName);
        } else {
            count += interactionMap.get(interactionNameMap.get(interactionName).
                    getId()).getLifelines().size();
        }
        return count;
    }

    public int getMessageCount(String interactionName)
            throws InteractionNotFoundException,
            InteractionDuplicatedException {
        int count = 0;
        if (!interactionNameMap.containsKey(interactionName)) {
            throw new InteractionNotFoundException(interactionName);
        } else if (interactionDuplicateName.contains(interactionName)) {
            throw new InteractionDuplicatedException(interactionName);
        } else {
            count += interactionMap.get(interactionNameMap.get(interactionName).
                    getId()).getMessages().size();
        }
        return count;
    }

    public int getIncomingMessageCount(String interactionName,
                                       String lifelineName)
            throws InteractionNotFoundException,
            InteractionDuplicatedException,
            LifelineNotFoundException, LifelineDuplicatedException {
        int count = 0;
        if (!interactionNameMap.containsKey(interactionName)) {
            throw new InteractionNotFoundException(interactionName);
        } else if (interactionDuplicateName.contains(interactionName)) {
            throw new InteractionDuplicatedException(interactionName);
        } else {
            String interactionId = interactionNameMap.
                    get(interactionName).getId();
            InteractionElem curInteraction = interactionMap.
                    get(interactionId);
            if (!curInteraction.getLifelineName().containsKey(lifelineName)) {
                throw new LifelineNotFoundException(interactionName, lifelineName);
            } else if (curInteraction.getLifeineDuplicateName().
                    contains(lifelineName)) {
                throw new LifelineDuplicatedException
                        (interactionName,lifelineName);
            } else {
                count += curInteraction.getIncommingCount(lifelineName);
            }
        }
        return count;
    }

    @Override
    public int getClassCount() {
        int count = classRestruct.size();
        return count;
    }

    @Override
    public int getClassOperationCount(String className,
                                      OperationQueryType queryType)
            throws ClassNotFoundException, ClassDuplicatedException {
        int count = 0;
        if (!classNameMap.containsKey(className)) {
            throw new ClassNotFoundException(className);
        } else if (classDuplicateName.contains(className)) {
            throw new ClassDuplicatedException(className);
        } else {
            ClassLevelAbstract tmpClass = classRestruct
                    .get(classNameMap.get(className).getId());
            ArrayList<UmlElement> operations = tmpClass.getMethods();
            Iterator<UmlElement> it = operations.iterator();
            while (it.hasNext()) {
                UmlElement curMethod = it.next();
                OperationElement curRealMethod = operationMap
                        .get(curMethod.getId());
                if (queryType.equals(OperationQueryType.ALL)) {
                    count++;
                } else if (queryType.equals((OperationQueryType.PARAM))) {
                    if (curRealMethod.getParamSub().size() > 0) {
                        count++;
                    }
                } else if (queryType.equals((OperationQueryType.NON_PARAM))) {
                    if (curRealMethod.getParamSub().size() <= 0) {
                        count++;
                    }
                } else if (queryType.equals((OperationQueryType.RETURN))) {
                    if (curRealMethod.getReturnSub().size() > 0) {
                        count++;
                    }
                } else if (queryType.equals((OperationQueryType.NON_RETURN))) {
                    if (curRealMethod.getReturnSub().size() <= 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    @Override
    public int getClassAssociationCount(String className)
            throws ClassNotFoundException, ClassDuplicatedException {
        int count = 0;
        if (!classNameMap.containsKey(className)) {
            throw new ClassNotFoundException(className);
        } else if (classDuplicateName.contains(className)) {
            throw new ClassDuplicatedException(className);
        } else {
            ClassLevelAbstract currentClass = classRestruct
                    .get(classNameMap.get(className).getId());
            while (currentClass != null) {
                count += currentClass.getSelfAssiociations().size();
                currentClass = currentClass.getParent();
            }
        }
        return count;
    }

    @Override
    public int getClassAttributeCount(String className,
                                      AttributeQueryType attributeQueryType)
            throws ClassNotFoundException, ClassDuplicatedException {
        int count = 0;
        if (!classNameMap.containsKey(className)) {
            throw new ClassNotFoundException(className);
        } else if (classDuplicateName.contains(className)) {
            throw new ClassDuplicatedException(className);
        } else {
            ClassElement tmpClass = classRestruct
                    .get(classNameMap.get(className).getId());
            if (attributeQueryType.equals(AttributeQueryType.ALL)) {
                while (tmpClass != null) {
                    ArrayList<UmlElement> attributes = tmpClass.getAttributes();
                    count += attributes.size();
                    tmpClass = tmpClass.getParent();
                }
            } else if (attributeQueryType.equals(
                    (AttributeQueryType.SELF_ONLY))) {
                ArrayList<UmlElement> attributes = tmpClass.getAttributes();
                count += attributes.size();
            }
        }
        return count;
    }

    @Override
    public List<String> getClassAssociatedClassList(String className)
            throws ClassNotFoundException, ClassDuplicatedException {
        List<String> result = new ArrayList<>();
        HashSet<String> map = new HashSet<>();
        if (!classNameMap.containsKey(className)) {
            throw new ClassNotFoundException(className);
        } else if (classDuplicateName.contains(className)) {
            throw new ClassDuplicatedException(className);
        } else {
            ClassLevelAbstract curClass = classRestruct
                    .get(classNameMap.get(className).getId());
            while (curClass != null) {
                Iterator<String> associations = curClass.getAssociationMap()
                        .keySet().iterator();
                while (associations.hasNext()) {
                    String tmpAid = associations.next();
                    if (!map.contains(tmpAid)) {
                        map.add(tmpAid);
                    }
                }
                curClass = curClass.getParent();
            }
        }
        Iterator<String> ids = map.iterator();
        while (ids.hasNext()) {
            String id = ids.next();
            ClassLevelAbstract tmpClass = abstractClass.get(id);
            if (tmpClass.getTypeInt() == 2) {
                continue;
            }
            String name = abstractClass.get(id).getCurElement().getName();
            result.add(name);
        }
        return result;
    }

    @Override
    public Map<Visibility, Integer> getClassOperationVisibility(
            String className, String s1)
            throws ClassNotFoundException, ClassDuplicatedException {
        Map<Visibility, Integer> result = new HashMap<>();
        int publics = 0;
        int protectes = 0;
        int privates = 0;
        int pakagePrivates = 0;
        if (!classNameMap.containsKey(className)) {
            throw new ClassNotFoundException(className);
        } else if (classDuplicateName.contains(className)) {
            throw new ClassDuplicatedException(className);
        } else {
            ClassLevelAbstract curClass = classRestruct
                    .get(classNameMap.get(className).getId());
            Iterator<UmlElement> operations = curClass.getMethods().iterator();
            while (operations.hasNext()) {
                UmlElement opre = operations.next();
                String name = opre.getName();
                UmlOperation operReal = (UmlOperation) opre;
                if (name.equals(s1)) {
                    if ((operReal.getVisibility()).equals(Visibility.PUBLIC)
                    || (operReal.getVisibility()).equals(Visibility.DEFAULT)) {
                        publics++;
                    } else if ((operReal.getVisibility())
                            .equals(Visibility.PRIVATE)) {
                        privates++;
                    } else if ((operReal.getVisibility())
                            .equals(Visibility.PACKAGE)) {
                        pakagePrivates++;
                    } else if ((operReal.getVisibility())
                            .equals(Visibility.PROTECTED)) {
                        protectes++;
                    }
                }
            }
        }
        result.put(Visibility.PUBLIC, publics);
        result.put(Visibility.PROTECTED, protectes);
        result.put(Visibility.PRIVATE, privates);
        result.put(Visibility.PACKAGE, pakagePrivates);
        return result;
    }

    private boolean attributeNotExist(String className, String s1) {
        ClassElement curClass = classRestruct
                .get(classNameMap.get(className).getId());
        while (curClass != null) {
            Iterator<UmlElement> attributes
                    = curClass.getAttributes().iterator();
            while (attributes.hasNext()) {
                UmlElement attr = attributes.next();
                if (attr.getName().equals(s1)) {
                    return false;
                }
            }
            curClass = curClass.getParent();
        }
        return true;
    }

    private boolean repeateAttribute(String className, String s1) {
        ClassElement curClass = classRestruct
                .get(classNameMap.get(className).getId());
        int count = 0;
        while (curClass != null) {
            Iterator<UmlElement> attributes
                    = curClass.getAttributes().iterator();
            while (attributes.hasNext()) {
                UmlElement attr = attributes.next();
                if (attr.getName().equals(s1)) {
                    count++;
                }
            }
            curClass = curClass.getParent();
        }
        if (count > 1) {
            return true;
        }
        return false;
    }

    @Override
    public Visibility getClassAttributeVisibility(String className, String s1)
            throws ClassNotFoundException, ClassDuplicatedException,
            AttributeNotFoundException, AttributeDuplicatedException {
        if (!classNameMap.containsKey(className)) {
            throw new ClassNotFoundException(className);
        } else if (classDuplicateName.contains(className)) {
            throw new ClassDuplicatedException(className);
        } else if (attributeNotExist(className, s1)) {
            throw new AttributeNotFoundException(className, s1);
        } else if (repeateAttribute(className, s1)) {
            throw new AttributeDuplicatedException(className, s1);
        } else {
            ClassElement curClass = classRestruct
                    .get(classNameMap.get(className).getId());
            ArrayList<UmlElement> attrList = new ArrayList<>();
            while (curClass != null) {
                Iterator<UmlElement> attributes
                        = curClass.getAttributes().iterator();
                while (attributes.hasNext()) {
                    UmlElement attr = attributes.next();
                    if (attr.getName().equals(s1)) {
                        attrList.add(attr);
                    }
                }
                curClass = curClass.getParent();
            }
            if (attrList.size() == 0) {
                throw new AttributeNotFoundException(className, s1);
            } else if (attrList.size() > 1) {
                throw new AttributeDuplicatedException(className, s1);
            } else {
                UmlAttribute attr = (UmlAttribute) attrList.get(0);
                return attr.getVisibility();
            }
        }
    }

    @Override
    public String getTopParentClass(String className)
            throws ClassNotFoundException, ClassDuplicatedException {
        if (!classNameMap.containsKey(className)) {
            throw new ClassNotFoundException(className);
        } else if (classDuplicateName.contains(className)) {
            throw new ClassDuplicatedException(className);
        } else {
            ClassElement curClass = classRestruct
                    .get(classNameMap.get(className).getId());
            /*while (curClass.getParent() != null) {
                curClass = curClass.getParent();
            }*/
            return curClass.getTopParent();
        }
    }

    @Override
    public List<String> getImplementInterfaceList(String className)
            throws ClassNotFoundException, ClassDuplicatedException {
        List<String> result = new ArrayList<>();
        HashSet<String> map = new HashSet<>();
        if (!classNameMap.containsKey(className)) {
            throw new ClassNotFoundException(className);
        } else if (classDuplicateName.contains(className)) {
            throw new ClassDuplicatedException(className);
        } else {
            ClassElement curClass = classRestruct
                    .get(classNameMap.get(className).getId());
            while (curClass != null) {
                Iterator<String> interfaces = curClass
                        .getAchievedInterfaces().iterator();
                while (interfaces.hasNext()) {
                    String interfaceId = interfaces.next();
                    map.add(interfaceId);
                    Queue<String> que = new LinkedList<>();
                    que.add(interfaceId);
                    while (!que.isEmpty()) {
                        String tmpRefer = que.poll();
                        Iterator<String> parents = interfaceRestruct
                                .get(tmpRefer).getParents().iterator();
                        while (parents.hasNext()) {
                            String parentId = parents.next();
                            if (!map.contains(parentId)) {
                                map.add(parentId);
                                que.offer(parentId);
                            }
                        }
                    }
                }
                curClass = curClass.getParent();
            }
        }
        Iterator<String> it = map.iterator();
        while (it.hasNext()) {
            String curId = it.next();
            String curName = abstractClass.get(curId).getCurElement().getName();
            result.add(curName);
        }
        return result;
    }

    @Override
    public List<AttributeClassInformation> getInformationNotHidden(
            String className)
            throws ClassNotFoundException, ClassDuplicatedException {
        List<AttributeClassInformation> result = new ArrayList<>();
        if (!classNameMap.containsKey(className)) {
            throw new ClassNotFoundException(className);
        } else if (classDuplicateName.contains(className)) {
            throw new ClassDuplicatedException(className);
        } else {
            ClassElement curClass = classRestruct
                    .get(classNameMap.get(className).getId());
            while (curClass != null) {
                Iterator<UmlElement> attributes = curClass.getAttributes()
                        .iterator();
                String curName = curClass.getCurElement().getName();
                while (attributes.hasNext()) {
                    UmlAttribute curAttribute = (UmlAttribute) attributes
                            .next();
                    if (!curAttribute.getVisibility()
                            .equals(Visibility.PRIVATE)) {
                        AttributeClassInformation newAttribute =
                                new AttributeClassInformation(
                                        curAttribute.getName(), curName);
                        result.add(newAttribute);
                    }
                }
                curClass = curClass.getParent();
            }
        }
        return result;
    }
}

