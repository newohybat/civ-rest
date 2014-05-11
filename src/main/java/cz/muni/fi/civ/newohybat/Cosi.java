package cz.muni.fi.civ.newohybat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class Cosi extends Application{
	 public Set<Class<?>> getClasses() {
	        return new HashSet<Class<?>>(Arrays.asList(App.class,DBRest.class));
	    }
}
