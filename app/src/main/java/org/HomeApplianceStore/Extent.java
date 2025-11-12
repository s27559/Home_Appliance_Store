package org.HomeApplianceStore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface Extent extends Serializable{

        static <T> ArrayList<T> loadClassList(String location){
                return new ArrayList<>();
        }

        static <T> void saveClassList(String location, ArrayList<T> list){
                return;
        }
        
        static <T> List<T> getImmutableClassList(ArrayList<T> list){
                return Collections.unmodifiableList(list);
        }
}
