import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];

        for (int i = 0; i < employees.length; i++) {
            employees[i] = new Employee(String.valueOf(i),
                    String.valueOf(i),
                    String.valueOf(i),
                    String.valueOf(i),
                    i + "@email.com",
                    "8999888000" + i,
                    10000 * i,
                    38+i);
        }

        List<Employee> employees1 = Arrays.stream(employees).filter(employee -> employee.getAge() > 40).collect(Collectors.toList());
        for (Employee employee: employees1) {
            System.out.println(employee.toString()+'\n');
        }
    }
}
