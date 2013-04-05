package com.eric.thinking.java.annotation;

import java.util.ArrayList;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;

public class InterfaceExtractorProcessor implements AnnotationProcessor {

	private final AnnotationProcessorEnvironment env;
	private ArrayList<MethodDeclaration> interfaceMethods = new ArrayList<MethodDeclaration>();

	public InterfaceExtractorProcessor(AnnotationProcessorEnvironment env) {
		this.env = env;
	}

	@Override
	public void process() {
		for(TypeDeclaration typeDecl: env.getSpecifiedTypeDeclarations()){
			
		}
	}

}
