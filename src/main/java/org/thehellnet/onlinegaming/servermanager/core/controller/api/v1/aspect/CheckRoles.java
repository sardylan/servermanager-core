package org.thehellnet.onlinegaming.servermanager.core.controller.api.v1.aspect;

import org.thehellnet.onlinegaming.servermanager.core.model.constant.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckRoles {

    Role[] value() default {};
}
