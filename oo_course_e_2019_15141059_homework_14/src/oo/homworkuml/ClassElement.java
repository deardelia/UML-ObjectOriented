package oo.homworkuml;

import com.oocourse.uml2.models.elements.UmlElement;

import java.util.ArrayList;
import java.util.HashSet;

public class ClassElement extends ClassLevelAbstract {
    private ArrayList<UmlElement> attributes = new ArrayList<>();
    private ArrayList<String> achievedInterfaces = new ArrayList<>();
    private String topParent = null;
    private HashSet<String> allInterfaces = new HashSet<>();
    private HashSet<String> duplicateInterfaces = new HashSet<>();

    public ClassElement(UmlElement x) {
        super(x);
    }

    public void addAttributes(UmlElement attribute) {
        attributes.add(attribute);
    }

    public void setAllInterfaces(String x) {
        allInterfaces.add(x);
    }

    public HashSet<String> getAllInterfaces() {
        return allInterfaces;
    }

    public void setDuplicateInterfaces(String x) {
        duplicateInterfaces.add(x);
    }

    public HashSet<String> getDuplicateInterfaces() {
        return duplicateInterfaces;
    }

    public void setAchievedInterfaces(String x) {
        achievedInterfaces.add(x);
    }

    public ArrayList<String> getAchievedInterfaces() {
        return achievedInterfaces;
    }

    public ArrayList<UmlElement> getAttributes() {
        return attributes;
    }

    public ClassElement getParent() {
        return (ClassElement) super.getParent();
    }

    public String getTopParent() {
        return topParent;
    }

    public void setTopParent(String x) {
        topParent = x;
    }
}


