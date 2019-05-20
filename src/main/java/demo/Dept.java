package demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Dept {
@Id
private int deptno;
private String deptName;
private int loc;

public String getDeptName() {
	return deptName;
}
public void setDeptName(String deptName) {
	this.deptName = deptName;
}
public int getDeptno() {
	return deptno;
}
public void setDeptno(int deptno) {
	this.deptno = deptno;
}
public int getLoc() {
	return loc;
}
public void setLoc(int loc) {
	this.loc = loc;
}

@Override             
public String toString()  
{
	return "deptno:"+deptno+"\n"+"deptname:"+deptName+"\n"+"loc:"+loc;
}

}
