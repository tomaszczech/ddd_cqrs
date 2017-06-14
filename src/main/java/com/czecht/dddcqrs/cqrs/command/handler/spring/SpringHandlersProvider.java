package com.czecht.dddcqrs.cqrs.command.handler.spring;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.czecht.dddcqrs.cqrs.command.handler.CommandHandler;
import com.czecht.dddcqrs.cqrs.command.handler.HandlersProvider;

@Component
public class SpringHandlersProvider implements HandlersProvider, ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ConfigurableListableBeanFactory beanFactory;

	private Map<Class<?>, String> handlers = new HashMap<Class<?>, String>();

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		handlers.clear();
		String[] commandHandlersNames = beanFactory.getBeanNamesForType(CommandHandler.class);
		for (String beanName : commandHandlersNames) {
			BeanDefinition commandHandler = beanFactory.getBeanDefinition(beanName);
			try {
				Class<?> handlerClass = Class.forName(commandHandler.getBeanClassName());
				handlers.put(getHandledCommandType(handlerClass), beanName);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public CommandHandler<Object, Object> getHandler(Object command) {
		String beanName = handlers.get(command.getClass());
		if (beanName == null) {
			throw new RuntimeException("command handler not found. Command class is " + command.getClass());
		}
		CommandHandler<Object, Object> handler = beanFactory.getBean(beanName, CommandHandler.class);
		return handler;
	}

	private Class<?> getHandledCommandType(Class<?> clazz) {
		Type[] genericInterfaces = clazz.getGenericInterfaces();
		ParameterizedType type = findByRawType(genericInterfaces, CommandHandler.class);
		return (Class<?>) type.getActualTypeArguments()[0];
	}

	private ParameterizedType findByRawType(Type[] genericInterfaces, Class<?> expectedRawType) {
		for (Type type : genericInterfaces) {
			if (type instanceof ParameterizedType) {
				ParameterizedType parametrized = (ParameterizedType) type;
				if (expectedRawType.equals(parametrized.getRawType())) {
					return parametrized;
				}
			}
		}
		throw new RuntimeException();
	}
}
