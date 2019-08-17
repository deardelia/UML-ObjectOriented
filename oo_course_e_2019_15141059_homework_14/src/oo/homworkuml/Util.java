package oo.homworkuml;

import com.oocourse.uml2.interact.common.AttributeClassInformation;
import com.oocourse.uml2.models.common.Direction;
import com.oocourse.uml2.models.common.ElementType;
import com.oocourse.uml2.models.elements.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Util {
    private HashMap<String, ClassElement> classRestruct;
    private HashMap<String, InterfaceElement> interfaceRestruct;
    private HashMap<String, ClassLevelAbstract> abstractClass;
    private UmlElement[] umlElements;
    private HashMap<String, String> stateMahineRegionMap = new HashMap<>();

    public Util(HashMap<String, ClassElement> x,
                HashMap<String, InterfaceElement> y,
                HashMap<String, ClassLevelAbstract> z,
                UmlElement[] elements) {
        classRestruct = x;
        interfaceRestruct = y;
        abstractClass = z;
        umlElements = elements;
    }

    public void initForAll(HashMap<String, UmlClass> classNameMap,
                             HashSet<String> classDuplicateName,
                             HashMap<String, OperationElement> operationMap,
                             HashMap<String, UmlAttribute> attributeMap,
                             HashMap<String, UmlParameter> paramterMap,
                             HashMap<String, UmlAssociation> associationMap,
                             HashMap<String, UmlAssociationEnd>
                                     associationEndMap,
                             HashMap<String, UmlInterfaceRealization>
                                     interfaceReMap,
                             HashMap<String, UmlGeneralization> generalizeMap,
                             HashMap<String, StateMachineElem> stateMachineMap,
                             HashMap<String, UmlStateMachine>
                                   stateMachineNameMap,
                             HashSet<String> stateMachineDuplicateName,
                             HashMap<String, TransationElem> transitionMap,
                             HashMap<String, UmlState> statesMap,
                             HashMap<String, UmlFinalState> finalStateMap,
                             HashMap<String, UmlRegion> regionMap,
                             HashMap<String, UmlPseudostate> peseudoStateMap,
                             HashMap<String, UmlEvent> eventMap,
                             HashMap<String, InteractionElem> interactionMap,
                             HashMap<String, UmlInteraction> interactionNameMap,
                             HashSet<String> interactionDuplicateName,
                             HashMap<String, UmlLifeline> lifelineHashMap,
                             HashMap<String, UmlMessage> messageHashMap,
                             HashMap<String, UmlEndpoint> endpointHashMap) {
        for (int i = 0; i < umlElements.length; i++) {
            String tmpS = umlElements[i].getId();
            if (umlElements[i].getElementType()
                    .equals(ElementType.UML_STATE_MACHINE)) {
                UmlStateMachine stateMachineElem = (UmlStateMachine)
                        umlElements[i];
                StateMachineElem curStateMachine = new
                        StateMachineElem(stateMachineElem);
                stateMachineMap.put(tmpS, curStateMachine);
                String nameS = umlElements[i].getName();
                if (stateMachineNameMap.containsKey(nameS)) {
                    stateMachineDuplicateName.add(nameS);
                } else {
                    stateMachineNameMap.put(nameS, stateMachineElem);
                }
            } else if (umlElements[i].getElementType()
                    .equals(ElementType.UML_TRANSITION)) {
                TransationElem curTrans = new TransationElem((UmlTransition)
                        umlElements[i]);
                transitionMap.put(tmpS, curTrans);
            } else if (umlElements[i].getElementType()
                    .equals(ElementType.UML_STATE)) {
                statesMap.put(tmpS, (UmlState) umlElements[i]);
            } else if (umlElements[i].getElementType()
                    .equals(ElementType.UML_FINAL_STATE)) {
                finalStateMap.put(tmpS, (UmlFinalState) umlElements[i]);
            } else if (umlElements[i].getElementType()
                    .equals(ElementType.UML_PSEUDOSTATE)) {
                peseudoStateMap.put(tmpS, (UmlPseudostate) umlElements[i]);
            } else if (umlElements[i].getElementType()
                    .equals(ElementType.UML_REGION)) {
                regionMap.put(tmpS, (UmlRegion) umlElements[i]);
            } else if (umlElements[i].getElementType()
                    .equals(ElementType.UML_EVENT)) {
                eventMap.put(tmpS, (UmlEvent) umlElements[i]);
            } else if (umlElements[i].getElementType()
                    .equals(ElementType.UML_INTERACTION)) {
                UmlInteraction interactionElem = (UmlInteraction)
                        umlElements[i];
                InteractionElem newInteraction = new
                        InteractionElem(interactionElem);
                interactionMap.put(tmpS, newInteraction);
                String nameS = umlElements[i].getName();
                if (interactionNameMap.containsKey(nameS)) {
                    interactionDuplicateName.add(nameS);
                } else {
                    interactionNameMap.put(nameS, interactionElem);
                }
            } else if (umlElements[i].getElementType()
                    .equals(ElementType.UML_LIFELINE)) {
                lifelineHashMap.put(tmpS, (UmlLifeline) umlElements[i]);
            } else if (umlElements[i].getElementType()
                    .equals(ElementType.UML_MESSAGE)) {
                messageHashMap.put(tmpS, (UmlMessage) umlElements[i]);
            } else if (umlElements[i].getElementType()
                    .equals(ElementType.UML_ENDPOINT)) {
                endpointHashMap.put(tmpS, (UmlEndpoint) umlElements[i]);
            } else if (umlElements[i].getElementType().equals
                    (ElementType.UML_CLASS)
                    || umlElements[i].getElementType()
                    .equals(ElementType.UML_INTERFACE)) {
                if (umlElements[i].getElementType().equals(ElementType
                        .UML_CLASS)) {
                    UmlClass classElem = (UmlClass) umlElements[i];
                    ClassElement curClass = new ClassElement(umlElements[i]);
                    classRestruct.put(tmpS, curClass);
                    abstractClass.put(tmpS, curClass);
                    String nameS = umlElements[i].getName();
                    if (classNameMap.containsKey(nameS)) {
                        classDuplicateName.add(nameS);
                    } else {
                        classNameMap.put(nameS, classElem);
                    }
                } else if (umlElements[i].getElementType().equals(ElementType
                        .UML_INTERFACE)) {
                    InterfaceElement curInterface
                            = new InterfaceElement(umlElements[i]);
                    curInterface.setTypeInt(2);
                    interfaceRestruct.put(tmpS, curInterface);
                    abstractClass.put(tmpS, curInterface);
                }
            } else if (umlElements[i].getElementType()
                    .equals(ElementType.UML_OPERATION)) {
                UmlOperation opElem = (UmlOperation) umlElements[i];
                OperationElement curOperation = new OperationElement(opElem);
                operationMap.put(tmpS, curOperation);
            } else if (umlElements[i].getElementType()
                    .equals(ElementType.UML_ATTRIBUTE)) {
                UmlAttribute attrElem = (UmlAttribute) umlElements[i];
                attributeMap.put(tmpS, attrElem);
            } else if (umlElements[i].getElementType()
                    .equals(ElementType.UML_PARAMETER)) {
                UmlParameter paramElem = (UmlParameter) umlElements[i];
                paramterMap.put(tmpS, paramElem);
            } else if (umlElements[i].getElementType()
                    .equals(ElementType.UML_ASSOCIATION)) {
                UmlAssociation assoElem = (UmlAssociation) umlElements[i];
                associationMap.put(tmpS, assoElem);
            }  else if (umlElements[i].getElementType()
                    .equals(ElementType.UML_ASSOCIATION_END)) {
                UmlAssociationEnd assoendElem =
                        (UmlAssociationEnd) umlElements[i];
                associationEndMap.put(tmpS, assoendElem);
            }  else if (umlElements[i].getElementType()
                    .equals(ElementType.UML_GENERALIZATION)) {
                UmlGeneralization generaElem =
                        (UmlGeneralization) umlElements[i];
                generalizeMap.put(tmpS, generaElem);
            }  else if (umlElements[i].getElementType()
                    .equals(ElementType.UML_INTERFACE_REALIZATION)) {
                UmlInterfaceRealization interalizeElem =
                        (UmlInterfaceRealization) umlElements[i];
                interfaceReMap.put(tmpS, interalizeElem);
            }
        }
    }

    public void structInteraction(HashMap<String, InteractionElem>
                                          interactionMap,
                                  HashMap<String, UmlLifeline> lifelineHashMap,
                                  HashMap<String, UmlEndpoint> endMap,
                                  HashMap<String, UmlMessage> messageMap) {
        Iterator<String> lifelineIt = lifelineHashMap.keySet().iterator();
        while (lifelineIt.hasNext()) {
            String curlifeLine = lifelineIt.next();
            InteractionElem curInteraction = interactionMap.get
                    (lifelineHashMap.get(curlifeLine).getParentId());
            curInteraction.setLifelines(lifelineHashMap.get(curlifeLine));
        }
        Iterator<String> messageIt = messageMap.keySet().iterator();
        while (messageIt.hasNext()) {
            String curMessage = messageIt.next();
            InteractionElem curInteraction = interactionMap.get
                    (messageMap.get(curMessage).getParentId());
            curInteraction.setMessages(messageMap.get(curMessage));
        }
        Iterator<String> endIt = endMap.keySet().iterator();
        while (endIt.hasNext()) {
            String curEnd = endIt.next();
            InteractionElem curInteraction = interactionMap.get
                    (endMap.get(curEnd).getParentId());
            curInteraction.setEnds(endMap.get(curEnd));
        }
        Iterator<String> interactionIt = interactionMap.keySet().iterator();
        while (interactionIt.hasNext()) {
            String curInteractionId = interactionIt.next();
            interactionMap.get(curInteractionId).adjustInteraction();
        }
    }

    public void structRegions(HashMap<String, UmlRegion> regionMap,
                              HashMap<String, StateMachineElem>
                                      stateMachineMap) {
        Iterator<String> regionIt = regionMap.keySet().iterator();
        while (regionIt.hasNext()) {
            String curRegionId = regionIt.next();
            String curStateMachineId = regionMap.get(curRegionId).getParentId();
            if (stateMachineMap.containsKey(curStateMachineId)) {
                StateMachineElem curStateMachine
                        = stateMachineMap.get(curStateMachineId);
                curStateMachine.setRegion(curRegionId);
                stateMahineRegionMap.put(curRegionId, curStateMachineId);
            }
        }
    }

    public void structStates(HashMap<String, UmlState> statesMap,
                             HashMap<String, UmlPseudostate> pseudostateMap,
                             HashMap<String, UmlFinalState> finalStateMap,
                             HashMap<String, StateMachineElem>
                                     stateMachineMap) {
        Iterator<String> startStatesIt = pseudostateMap.keySet().iterator();
        while(startStatesIt.hasNext()) {
            String curStateId = startStatesIt.next();
            String regionId = pseudostateMap.get(curStateId).getParentId();
            String stateMachineId = stateMahineRegionMap.get(regionId);
            StateMachineElem stateMachine = stateMachineMap.get(stateMachineId);
            stateMachine.setStartState(pseudostateMap.get(curStateId));
        }
        // states
        Iterator<String> normalStatesIt = statesMap.keySet().iterator();
        while(normalStatesIt.hasNext()) {
            String curStateId = normalStatesIt.next();
            String regionId = statesMap.get(curStateId).getParentId();
            String stateMachineId = stateMahineRegionMap.get(regionId);
            StateMachineElem stateMachine = stateMachineMap.get(stateMachineId);
            stateMachine.setStates(statesMap.get(curStateId));
        }
        // finalStates
        Iterator<String> finalStatesIt = finalStateMap.keySet().iterator();
        while(finalStatesIt.hasNext()) {
            String curStateId = finalStatesIt.next();
            String regionId = finalStateMap.get(curStateId).getParentId();
            String stateMachineId = stateMahineRegionMap.get(regionId);
            StateMachineElem stateMachine = stateMachineMap.get(stateMachineId);
            stateMachine.setFinalStates(finalStateMap.get(curStateId));
        }
    }

    public void structTransitions(HashMap<String, UmlEvent> eventHashMap,
                                  HashMap<String, TransationElem>
                                          transationElemHashMap,
                                  HashMap<String, StateMachineElem>
                                          stateMachineMap) {
        Iterator<String> eventIt = eventHashMap.keySet().iterator();
        while (eventIt.hasNext()) {
            String curEventId = eventIt.next();
            UmlEvent curEvent = eventHashMap.get(curEventId);
            String curTransId = curEvent.getParentId();
            if (transationElemHashMap.containsKey(curTransId)) {
                transationElemHashMap.get(curTransId).setEvents(curEvent);
            }
        }
        Iterator<String> transIt = transationElemHashMap.keySet().iterator();
        while (transIt.hasNext()) {
            String curTransId = transIt.next();
            String regionId = transationElemHashMap.get(curTransId).
                    getTransition().getParentId();
            String stateMachineId = stateMahineRegionMap.get(regionId);
            StateMachineElem stateMachine = stateMachineMap.get(stateMachineId);
            stateMachine.setTransitions(transationElemHashMap.get(curTransId));
        }
    }

    public void adjustStateMachine(HashMap<String, StateMachineElem>
                                           stateMachineMap) {
        Iterator<String> stateMachineIt = stateMachineMap.keySet().iterator();
        while (stateMachineIt.hasNext()) {
            String stateMachineId = stateMachineIt.next();
            StateMachineElem stateMachineElem = stateMachineMap.
                    get(stateMachineId);
            stateMachineElem.replicateStates();
            stateMachineElem.setTransitionMetrics();
        }
    }

    public void structAssociation(HashMap<String, UmlAssociation>
                                          associationMap,
                                  HashMap<String, UmlAssociationEnd>
                                          associationEndMap) {
        Iterator<String> it4 = associationMap.keySet().iterator();
        while (it4.hasNext()) {
            String tmpS = it4.next();
            UmlAssociation associtate = associationMap.get(tmpS);
            String end1 = associtate.getEnd1();
            String end2 = associtate.getEnd2();
            if (abstractClass.containsKey(associationEndMap
                    .get(end1).getReference())
                    && abstractClass.containsKey(associationEndMap
                    .get(end2).getReference())) {
                ClassLevelAbstract end1Reference = abstractClass
                        .get(associationEndMap.get(end1).getReference());
                ClassLevelAbstract end2Reference = abstractClass
                        .get(associationEndMap.get(end2).getReference());
                end1Reference.setAssociationMap(end2Reference.getCurElement()
                        .getId(), end2Reference);
                end2Reference.setAssociationMap(end1Reference.getCurElement()
                        .getId(), end1Reference);
            }
        }
    }

    public void structInherit(HashMap<String, UmlGeneralization>
                                      generalizeMap) {
        Iterator<String> it3 = generalizeMap.keySet().iterator();
        while (it3.hasNext()) {
            String tmpS = it3.next();
            UmlGeneralization generalize = generalizeMap.get(tmpS);
            String parent = generalize.getTarget();
            String child = generalize.getSource();
            if (abstractClass.containsKey(parent)
                    && abstractClass.containsKey(child)) {
                ClassLevelAbstract children = abstractClass.get(child);
                ClassLevelAbstract father = abstractClass.get(parent);
                children.setParent(father);
                if (children.getTypeInt() == 2) {
                    children.setParents(father.getCurElement().getId());
                }
            }
        }
    }

    public void structSelfAssociate(HashMap<String, UmlAssociationEnd>
                                            associationEndMap) {
        Iterator<String> it5 = associationEndMap.keySet().iterator();
        while (it5.hasNext()) {
            String tmpS = it5.next();
            UmlAssociationEnd associtate = associationEndMap.get(tmpS);
            String reference = associtate.getReference();
            if (abstractClass.containsKey(reference)) {
                ClassLevelAbstract tmpclass = abstractClass.get(reference);
                tmpclass.setSelfAssiociations(tmpS, associtate);
            }
        }
    }

    public void structOperation(HashMap<String, UmlParameter> paramterMap,
                                HashMap<String, OperationElement>
                                        operationMap) {
        Iterator<String> it = paramterMap.keySet().iterator();
        while (it.hasNext()) {
            String tmpS = it.next();
            UmlParameter parameter = paramterMap.get(tmpS);
            String parentId = parameter.getParentId();
            OperationElement curOperation = operationMap.get(parentId);
            if (parameter.getDirection().equals(Direction.RETURN)) {
                curOperation.setReturnSub(parameter);
            } else if (parameter.getDirection().equals(Direction.IN)
                    || parameter.getDirection().equals(Direction.INOUT)
            || parameter.getDirection().equals(Direction.OUT)
            || parameter.getDirection().equals(Direction.DEFAULT)) {
                curOperation.setParamSub(parameter);
            }
            curOperation.setAllSub(parameter);
        }
        Iterator<String> it1 = operationMap.keySet().iterator();
        while (it1.hasNext()) {
            String tmpS = it1.next();
            UmlElement operation = operationMap.get(tmpS).getCurOperation();
            String parentId = operation.getParentId();
            if (abstractClass.containsKey(parentId)) {
                ClassLevelAbstract curElem = abstractClass.get(parentId);
                curElem.addMethods(operation);
            }
        }
    }

    public void getAttribute(HashMap<String, UmlAttribute> attributeMap) {
        Iterator<String> it2 = attributeMap.keySet().iterator();
        while (it2.hasNext()) {
            String tmpS = it2.next();
            UmlElement attribute = attributeMap.get(tmpS);
            String parentId = attribute.getParentId();
            if (classRestruct.containsKey(parentId)) {
                ClassElement curClass = classRestruct.get(parentId);
                curClass.addAttributes(attribute);
            }
        }
    }

    public void getInterface(HashMap<String, UmlInterfaceRealization>
                                     interfaceReMap) {
        Iterator<String> it6 = interfaceReMap.keySet().iterator();
        while (it6.hasNext()) {
            String tmpS = it6.next();
            UmlInterfaceRealization curInterfaceRe = interfaceReMap.get(tmpS);
            String source = curInterfaceRe.getSource();
            String target = curInterfaceRe.getTarget();
            if (classRestruct.containsKey(source)) {
                ClassElement sourcClass = classRestruct.get(source);
                sourcClass.setAchievedInterfaces(target);
            }
            if (interfaceRestruct.containsKey(target)) {
                InterfaceElement curInterface = interfaceRestruct.get(target);
                curInterface.setDependencyId(source);
            }
        }
    }

    public void roneCheck(Set<AttributeClassInformation> result) {
        HashMap<String, String> map = new HashMap<>();
        Iterator<String> elems = classRestruct.keySet().iterator();
        while (elems.hasNext()) {
            String tmpClassId = elems.next();
            ClassElement tmpClass = classRestruct.get(tmpClassId);
            HashSet<String> nameSet = new HashSet<>();
            Iterator<UmlElement> attributes = tmpClass.
                    getAttributes().iterator();
            while (attributes.hasNext()) {
                UmlAttribute tmpAttr = (UmlAttribute) attributes.next();
                String attrName = tmpAttr.getName();
                if (nameSet.contains(attrName)) {
                    map.put(attrName, tmpClass.getCurElement().getName());
                    AttributeClassInformation tmpPairs = new
                            AttributeClassInformation(attrName,
                            tmpClass.getCurElement().getName());
                    result.add(tmpPairs);
                } else {
                    nameSet.add(attrName);
                }
            }
            HashMap<String, UmlElement> selfAssociations =
                    tmpClass.getSelfAssiociations();
            Iterator<String> it = selfAssociations.keySet().iterator();
            while (it.hasNext()) {
                String associationId = it.next();
                String assoName = selfAssociations.get(associationId).getName();
                if (nameSet.contains(assoName)) {
                    map.put(assoName, tmpClass.getCurElement().getName());
                    AttributeClassInformation tmpPairs = new
                            AttributeClassInformation(assoName,
                            tmpClass.getCurElement().getName());
                    result.add(tmpPairs);
                }
            }
        }
    }

    public void rtwoCheck(Set<UmlClassOrInterface> result) {
        Iterator<String> elems = classRestruct.keySet().iterator();
        while (elems.hasNext()) {
            String tmpClassId = elems.next();
            ClassElement tmpClass = classRestruct.get(tmpClassId);
            HashSet<String> fatherClasses = new HashSet();
            String topParent = null;
            while (tmpClass != null) {
                if (fatherClasses.contains(tmpClass.getCurElement().getId())) {
                    for (String s : fatherClasses) {
                        result.add((UmlClassOrInterface) classRestruct.
                                get(s).getCurElement());
                    }
                    return;
                } else {
                    fatherClasses.add(tmpClass.getCurElement().getId());
                }
                topParent = tmpClass.getCurElement().getName();
                tmpClass = tmpClass.getParent();
            }
            tmpClass = classRestruct.get(tmpClassId);
            tmpClass.setTopParent(topParent);
        }

        Iterator<String> interfacesElems = interfaceRestruct.keySet().
                iterator();
        while (interfacesElems.hasNext()) {
            String tmpInterfaceId = interfacesElems.next();
            Queue<String> que = new LinkedList<>();
            que.add(tmpInterfaceId);
            InterfaceElement tmpInterface = interfaceRestruct.
                    get(tmpInterfaceId);
            Iterator<String> parents = interfaceRestruct
                    .get(tmpInterfaceId).getParents().iterator();
            while (parents.hasNext()) {
                String parentId = parents.next();
                String parentName = interfaceRestruct.get(parentId).
                        getCurElement().getName();
                if (!tmpInterface.getAllParents().contains(parentId)) {
                    if (parentId.equals(tmpInterfaceId)) {
                        tmpInterface.setRecursiveInherit();
                    }
                    tmpInterface.setAllParents(parentId);
                    que.offer(parentId);
                }
            }
            if (tmpInterface.getRecursiveInherit()) {
                result.add((UmlClassOrInterface) tmpInterface.getCurElement());
            }
        }
    }

    public void rthreeCheck(Set<UmlClassOrInterface> result) {
        Iterator<String> classIt = classRestruct.keySet().iterator();
        while (classIt.hasNext()) {
            String classId = classIt.next();
            ClassElement curClass = classRestruct.get(classId);
            while (curClass != null) {
                Iterator<String> interfaces = curClass
                        .getAchievedInterfaces().iterator();
                while (interfaces.hasNext()) {
                    String interfaceId = interfaces.next();
                    String interfaceName = interfaceRestruct.get(interfaceId).
                            getCurElement().getName();
                    InterfaceElement tmpInterface = interfaceRestruct.
                            get(interfaceId);
                    if (curClass.getAllInterfaces().contains(interfaceName)) {
                        curClass.setDuplicateInterfaces(interfaceName);
                    } else {
                        curClass.setAllInterfaces(interfaceName);
                    }
                    Queue<String> que = new LinkedList<>();
                    que.add(interfaceId);
                    while (!que.isEmpty()) {
                        String tmpRefer = que.poll();
                        Iterator<String> parents = interfaceRestruct
                                .get(tmpRefer).getParents().iterator();
                        while (parents.hasNext()) {
                            String parentId = parents.next();
                            String parentName = interfaceRestruct.get(parentId).
                                    getCurElement().getName();
                            if (curClass.getAllInterfaces().
                                    contains(parentName)) {
                                curClass.setDuplicateInterfaces(parentName);
                            } else {
                                curClass.setAllInterfaces(parentName);
                                que.offer(parentId);
                            }
                        }
                    }
                }
                if (curClass.getDuplicateInterfaces().size() > 0) {
                    result.add((UmlClassOrInterface) curClass.getCurElement());
                }
                curClass = curClass.getParent();
            }
        }

        Iterator<String> interfacesElems = interfaceRestruct.keySet().
                iterator();
        while (interfacesElems.hasNext()) {
            String interfaceId = interfacesElems.next();
            InterfaceElement tmpInterface = interfaceRestruct.get(interfaceId);
            Queue<String> que = new LinkedList<>();
            que.add(interfaceId);
            while (!que.isEmpty()) {
                String tmpRefer = que.poll();
                Iterator<String> parents = interfaceRestruct
                        .get(tmpRefer).getParents().iterator();
                while (parents.hasNext()) {
                    String parentId = parents.next();
                    String parentName = interfaceRestruct.get(parentId).
                            getCurElement().getName();
                    if (tmpInterface.getAllInterfaces().
                            contains(parentName)) {
                        tmpInterface.setDuplicateInterfaces(parentName);
                    } else {
                        tmpInterface.setAllInterfaces(parentName);
                        que.offer(parentId);
                    }
                }
            }
            if (tmpInterface.getDuplicateInterfaces().size() > 0) {
                result.add((UmlClassOrInterface) tmpInterface.getCurElement());
            }
        }
    }
}
