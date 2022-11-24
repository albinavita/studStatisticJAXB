package comparator;

import model.University;
import org.apache.commons.lang3.StringUtils;

public class UniversityIdComparator implements IUniversityComparator {

    @Override
    public int compare(University o1, University o2) {
        return StringUtils.compare(o1.getId(), o2.getId());
    }
}
