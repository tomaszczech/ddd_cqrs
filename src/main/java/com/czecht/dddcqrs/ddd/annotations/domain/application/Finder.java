package com.czecht.dddcqrs.ddd.annotations.domain.application;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by czecht on 2017-03-15.
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Finder {

}
