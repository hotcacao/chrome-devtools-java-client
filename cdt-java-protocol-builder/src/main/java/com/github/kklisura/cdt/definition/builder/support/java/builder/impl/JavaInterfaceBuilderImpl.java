package com.github.kklisura.cdt.definition.builder.support.java.builder.impl;

/*-
 * #%L
 * cdt-java-protocol-builder
 * %%
 * Copyright (C) 2018 Kenan Klisura
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import static com.github.kklisura.cdt.definition.builder.support.java.builder.utils.JavadocUtils.INDENTATION_NO_INDENTATION;
import static com.github.kklisura.cdt.definition.builder.support.java.builder.utils.JavadocUtils.INDENTATION_TAB;

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.kklisura.cdt.definition.builder.support.java.builder.JavaInterfaceBuilder;
import com.github.kklisura.cdt.definition.builder.support.java.builder.impl.utils.CompilationUnitUtils;
import com.github.kklisura.cdt.definition.builder.support.java.builder.support.MethodParam;
import com.github.kklisura.cdt.definition.builder.support.java.builder.utils.JavadocUtils;
import java.util.List;
import java.util.Optional;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Java interface builder.
 *
 * @author Kenan Klisura
 */
public class JavaInterfaceBuilderImpl extends BaseBuilder implements JavaInterfaceBuilder {
  private static final String DEPRECATED_ANNOTATION = "Deprecated";

  private String name;
  private ClassOrInterfaceDeclaration declaration;
  private String annotationsPackage;

  /**
   * Instantiates a new class builder implementation.
   *
   * @param packageName Package name.
   * @param name Interface name.
   * @param annotationsPackage Package where support annotations are located (Optional,
   *     Experimental...)
   */
  public JavaInterfaceBuilderImpl(String packageName, String name, String annotationsPackage) {
    super(packageName);
    this.name = name;
    this.declaration = getCompilationUnit().addInterface(name);
    this.annotationsPackage = annotationsPackage;
  }

  @Override
  public void setJavaDoc(String comment) {
    if (StringUtils.isNotEmpty(comment)) {
      declaration.setJavadocComment(
          JavadocUtils.createJavadocComment(comment, INDENTATION_NO_INDENTATION));
    }
  }

  @Override
  public void addAnnotation(String annotationName) {
    Optional<AnnotationExpr> annotation = declaration.getAnnotationByName(annotationName);
    if (!annotation.isPresent()) {
      MarkerAnnotationExpr annotationExpr = new MarkerAnnotationExpr();
      annotationExpr.setName(annotationName);
      declaration.addAnnotation(annotationExpr);

      importAnnotation(annotationName);
    }
  }

  @Override
  public void addMethodAnnotation(String methodName, String annotationName) {
    List<MethodDeclaration> methods = declaration.getMethodsByName(methodName);
    for (MethodDeclaration methodDeclaration : methods) {
      Optional<AnnotationExpr> annotation = methodDeclaration.getAnnotationByName(annotationName);
      if (!annotation.isPresent()) {
        methodDeclaration.addMarkerAnnotation(annotationName);
      }
    }

    importAnnotation(annotationName);
  }

  @Override
  public void addParametrizedMethodAnnotation(
      String methodName, String annotationName, String parameter) {
    List<MethodDeclaration> methods = declaration.getMethodsByName(methodName);
    for (MethodDeclaration methodDeclaration : methods) {
      Optional<AnnotationExpr> annotation = methodDeclaration.getAnnotationByName(annotationName);
      if (!annotation.isPresent()) {
        methodDeclaration.addSingleMemberAnnotation(
            annotationName, new StringLiteralExpr(parameter));
      }
    }

    importAnnotation(annotationName);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void addImport(String packageName, String object) {
    Name name = new Name();
    name.setQualifier(new Name(packageName));
    name.setIdentifier(object);

    if (!getPackageName().equalsIgnoreCase(packageName)
        && !CompilationUnitUtils.isImported(getCompilationUnit(), name)) {
      getCompilationUnit().addImport(new ImportDeclaration(name, false, false));
    }
  }

  @Override
  public void addMethod(
      String name, String description, List<MethodParam> methodParams, String returnType) {
    MethodDeclaration methodDeclaration = declaration.addMethod(name);
    methodDeclaration.setBody(null);

    if (StringUtils.isNotEmpty(description)) {
      methodDeclaration.setJavadocComment(
          JavadocUtils.createJavadocComment(description, INDENTATION_TAB));
    }

    if (StringUtils.isNotEmpty(returnType)) {
      methodDeclaration.setType(returnType);
    }

    if (CollectionUtils.isNotEmpty(methodParams)) {
      for (MethodParam methodParam : methodParams) {
        Parameter parameter =
            methodDeclaration.addAndGetParameter(methodParam.getType(), methodParam.getName());

        if (CollectionUtils.isNotEmpty(methodParam.getAnnotations())) {
          for (MethodParam.Annotation annotation : methodParam.getAnnotations()) {
            Optional<AnnotationExpr> currentAnnotation =
                parameter.getAnnotationByName(annotation.getName());
            if (!currentAnnotation.isPresent()) {
              if (StringUtils.isNotEmpty(annotation.getValue())) {
                parameter.addSingleMemberAnnotation(
                    annotation.getName(), new StringLiteralExpr(annotation.getValue()));
              } else {
                parameter.addMarkerAnnotation(annotation.getName());
              }

              if (!DEPRECATED_ANNOTATION.equals(annotation.getName())) {
                addImport(annotationsPackage, annotation.getName());
              }
            }
          }
        }
      }
    }
  }

  private void importAnnotation(String annotationName) {
    if (!DEPRECATED_ANNOTATION.equals(annotationName)) {
      addImport(annotationsPackage, annotationName);
    }
  }
}
