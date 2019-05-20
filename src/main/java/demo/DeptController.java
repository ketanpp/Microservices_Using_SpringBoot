package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/dept")
class DeptController{
	
	@Autowired
	private DeptRepository repo;
	
	private List<Dept> data=new ArrayList<Dept>();
	
	
	@PostConstruct
	public void insert()
	{
		
		for(int i=20;i<30;i++){
			Dept d=new Dept();
			d.setDeptno(i);
			d.setDeptName("Dept"+(i*10));
			d.setLoc(i+10);
			repo.save(d);
		}
		
	}
	@GetMapping
	public List<Dept> list(){
		System.out.println("List Size:"+data.size());
		return repo.findAll();
	}
	
	@GetMapping(value="/{dno}")
	public ResponseEntity<Dept> getonerec(@PathVariable(name="dno")int deptNo){
	   for(Dept dept:data){
		   if(dept.getDeptno()==deptNo)
		   {
			   return ResponseEntity.status(HttpStatus.OK).body(dept);
		   }
	   }
	   return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	@PostMapping(consumes=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public String Create(@RequestBody Dept[] d){
		for(Dept de:d)
		data.add(de);
		return "success";
	}
	
	@DeleteMapping(value="/{dno}")
	public ResponseEntity<String> DeleteOneRec(@PathVariable(name="dno")int deptNo){
		for(Dept dept:data)
		{
			if(dept.getDeptno()==deptNo)
			{
				data.remove(dept);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping
	public ResponseEntity<String> Delete()
	{
		data.clear();
		if(data.size()==0)
		{
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	@PutMapping(consumes= org.springframework.http.MediaType.APPLICATION_JSON_VALUE,produces=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Dept> update(@RequestBody Dept d)
	{
		for(Dept dept:data)
		{
			if(dept.getDeptno()==d.getDeptno()) 
			{
				dept.setDeptName(d.getDeptName());
				dept.setLoc(d.getLoc());
				return ResponseEntity.status(HttpStatus.OK).body(dept);
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}