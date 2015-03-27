package org.jboss.reddeer.swt.condition;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import org.jboss.reddeer.common.logging.Logger;
import org.jboss.reddeer.core.exception.SWTLayerException;
import org.jboss.reddeer.common.condition.WaitCondition;
/**
 * Wait condition to wait for constructor to finish successfully
 * 
 * @author Vlado Pakan
 *
 */
public class ConstructorFinishedSuccessfully implements WaitCondition {
	private static final Logger log = Logger.getLogger(ConstructorFinishedSuccessfully.class);
	private static MethodHandles.Lookup lookup = MethodHandles.lookup();
	private MethodHandle mhConstructor;
	private Object[] constructorParametersValues;
	private Class<?> classToConstruct;
	/**
	 * Creates condition for constructor of classToConstruct class with parameters
	 * of types constructorParametersClasses and values constructorParametersValues
	 * to finish successfully
	 * @param classToConstruct
	 * @param constructorParametersClasses
	 * @param constructorParametersValues
	 */
	public ConstructorFinishedSuccessfully(Class<?> classToConstruct,
			Class<?>[] constructorParametersClasses,
			Object[] constructorParametersValues) {
		super();

		MethodType mtConstructor;
		if (constructorParametersClasses != null && constructorParametersClasses.length > 0) {
			mtConstructor = MethodType.methodType(void.class,constructorParametersClasses);
		}
		else {
			mtConstructor = MethodType.methodType(void.class);
		}
		
		this.constructorParametersValues = constructorParametersValues;
		this.classToConstruct = classToConstruct;
		
		try {
			this.mhConstructor = lookup.findConstructor(classToConstruct,mtConstructor);
		} catch (NoSuchMethodException nsme) {
			log.error("Unable to find constructor for " + classToConstruct + ". " + nsme);
			throw new SWTLayerException("Unable to find constructor for " + classToConstruct + ". ", nsme);
		} catch (IllegalAccessException iae) {
			log.error("Unable to find constructor for " + classToConstruct + ". " + iae);
			throw new SWTLayerException("Unable to find constructor for " + classToConstruct + ". ", iae);
		}
	}

	@Override
	public boolean test() {
		boolean isOK = false;
		try {
			this.mhConstructor.invokeWithArguments(this.constructorParametersValues);
			isOK = true;
		} catch (Throwable e) {
			log.debug("Constructor for class " + classToConstruct + " finished with error " + e);
			isOK = false;
		}
		return isOK;
	}

	@Override
	public String description() {
		return "constructor for " +  classToConstruct + " invoked with no exception.";
	}
}
