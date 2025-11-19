package org.HomeApplianceStore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface Extent extends Serializable{

        static <T> ArrayList<T> loadClassList(String location) throws NullPointerException{
                ArrayList<T> arrayList;
                if (location == null || location.isEmpty()) {
                        throw new NullPointerException("Empty file location string");
                }
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(location))) {
                        arrayList = (ArrayList<T>)ois.readObject();
                } catch (IOException | ClassNotFoundException e) {
                        arrayList = new ArrayList<>();
                }
                return arrayList;
        }

        static <T> void saveClassList(String location, ArrayList<T> list) throws NullPointerException{
                if (location == null || location.isEmpty()) {
                        throw new NullPointerException("Empty file location string");
                }
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(location))) {
                        oos.writeObject(list);
                } catch (IOException e) {
                        return;
                }
        }
        
        static <T> List<T> getImmutableClassList(ArrayList<T> list){
                return Collections.unmodifiableList(list);
        }
}
