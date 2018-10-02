package com.simple.bean;

/**
 * 容器内的Bean定义
 */
public class BeanDefinition {

    public final static String  SCOPE_SINGLETON = "singleton";
    public final static String  SCOPE_PROTOTYPE = "prototype";
    public final static String  SCOPE_DEFAULT = SCOPE_SINGLETON;

    private String name;
    private String className;
    private String scope;

    public BeanDefinition(String name, String className,String scope) {
        this.name = name;
        this.className = className;
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public String getScope() {
        return scope;
    }

    public Boolean isSingleton(){
        return scope.equals(SCOPE_SINGLETON);
    }

    public Boolean isPrototype(){
        return scope.equals(SCOPE_PROTOTYPE);
    }

}
