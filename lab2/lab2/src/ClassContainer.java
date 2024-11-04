import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassContainer {
    Map<String, ClassTeacher> teacherGroups;

    public ClassContainer() {
        teacherGroups = new HashMap<String, ClassTeacher>();
    }

    public void addClass(String groupName, double Capacity){
        if(teacherGroups.containsKey(groupName)){
            System.out.println("Grupa o takiej nazwie juz istnieje!");
        }else{
            teacherGroups.put(groupName, new ClassTeacher(groupName,10));
            System.out.println("Dodano nowa grupe: " + groupName);
        }
    }

    public void removeClass(String groupName){
        if(teacherGroups.containsKey(groupName)){
            System.out.println("Usunieto grupe: " + groupName);
            teacherGroups.remove(groupName);
        }else{
            System.out.println("Nie ma takiej grupy!");
        }
    }

    public List<String> findEmpty(){
        List<String> empty = new ArrayList<>();
        for(Map.Entry<String, ClassTeacher> entry : teacherGroups.entrySet()){
            if(entry.getValue().teachers.isEmpty()){
                empty.add(entry.getKey());
            }
        }
        return empty;
    }

    public void summary(){
        for(Map.Entry<String, ClassTeacher> entry : teacherGroups.entrySet()){
            ClassTeacher teacher = entry.getValue();
            int size = teacher.teachers.size();
            double percent = ((double) size/ teacher.capacity)*100;
            System.out.println(teacher.groupName + ": procentowe zapełnienie: " + percent + "%");
        }
    }
}
