package $packageName$;

import com.mycorp.examples.timeservice.ITimeService;

public class TimeServiceImpl implements ITimeService{

	
	public Long getCurrentTime() {
		
		return System.currentTimeMillis();
	}

}
