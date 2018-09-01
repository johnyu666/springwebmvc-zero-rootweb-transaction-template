package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	private final String p="execution(* dao.CustomerDao.addCustomer(..))";
	@Before(value=p)
	public void logBefore() {
		System.out.println("log before....");
	}
	@Around(value=p)
	public Object logAround(ProceedingJoinPoint point) {
		Object o=null;
		System.out.println("log around before...");
		try {
			o=point.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("log around after....");
		return o;
	}
}
