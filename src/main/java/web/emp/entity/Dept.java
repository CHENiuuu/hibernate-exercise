package web.emp.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Dept {
	@Id
	private Integer deptno;
    private String dname;
    private String loc;
    
//    @OneToMany
//    @JoinColumn(name = "DEPTNO", 
//    	referencedColumnName = "DEPTNO") //講議16-2 p55
//    private List<Emp> emps;
    
    @OneToMany(mappedBy = "dept")
    private List<Emp> emps;
}
