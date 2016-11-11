package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kybaby.newbussiness.doctorsign.bo.DoctorRegisterDataGatherService;

public class ServiceTest {
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("SpringBeans.xml");
		DoctorRegisterDataGatherService doctorRegisterDataGatherService = 
				(DoctorRegisterDataGatherService)ac.getBean("doctorRegisterDataGatherService");
		doctorRegisterDataGatherService.getMajors(null, "first");
	}
}
