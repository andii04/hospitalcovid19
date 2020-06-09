package hospital;
import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private String name;
    ArrayList<Department> departments;

    public Hospital(String name, ArrayList<Department> departments){
        this.name = name;
        this.departments = departments;

    }



    public static class Builder{

        private String name;
        List<Department> departments = new ArrayList<Department>();

        public Hospital.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Hospital.Builder setDepartmentsList(List<Department> departments){
            this.departments =departments;
            return this;
        }

        public Hospital build() {
            return new Hospital(name, (ArrayList<Department>) departments);
        }
    }
}