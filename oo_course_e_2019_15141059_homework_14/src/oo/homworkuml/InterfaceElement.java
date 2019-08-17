package oo.homworkuml;

import com.oocourse.uml2.models.elements.UmlElement;

import java.util.HashSet;

public class InterfaceElement extends ClassLevelAbstract {
    private String dependencyId  = null;
    private HashSet<String> allInterfaces = new HashSet<>();
    private HashSet<String> duplicateInterfaces = new HashSet<>();

    public InterfaceElement(UmlElement x) {
        super(x);
        super.setTypeInt(2);
    }

    public void setAllInterfaces(String x) {
        allInterfaces.add(x);
    }
    public void setDuplicateInterfaces(String x) {
        duplicateInterfaces.add(x);
    }

    public HashSet<String> getAllInterfaces() {
        return allInterfaces;
    }

    public HashSet<String> getDuplicateInterfaces() {
        return duplicateInterfaces;
    }

    public void setDependencyId(String x) {
        dependencyId = x;
    }
}
