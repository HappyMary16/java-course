package com.mborodin.javacourse.javapoet;

import com.squareup.javapoet.*;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementFilter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Як це працює в архітектурі компілятора:
 * 1. Компілюється наш BuilderProcessor
 * (це прописано в pom.xml в maven-compiler-plugin).
 * 2. Компілятор (javac) зчитує всі файли програми. Він бачить
 * анотацію @Builder і викликає BuilderProcessor.
 * 3. BuilderProcessor створює новий файл, в прикладі це CatBuilder.java.
 * В цьому файлі знаходиться звичайний java код.
 * 4. Компілятор знову пробігається по циклу, щоб відкомпілювати цей новий файл.
 * <p>
 * Щоб Java знала, що BuilderProcessor — не просто код, а частина компілятора,
 * його треба зареєструвати. Для цього в нас створений файл
 * src/main/resources/META-INF/services/javax.annotation.processing.Processor
 */
@SupportedAnnotationTypes("com.mborodin.javacourse.javapoet.BuilderPoet")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class BuilderProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(BuilderPoet.class)) {
            if (element.getKind() == ElementKind.CLASS) {
                generateCode((TypeElement) element);
            }
        }

        System.out.println("BuilderProcessor created using JavaPoet was executed.");
        return true; // Анотації "оброблені", іншим процесорам їх чіпати не треба
    }

    private void generateCode(TypeElement typeElement) {
        String className = typeElement.getSimpleName().toString();
        String builderClassName = className + "Builder";
        String packageName = processingEnv.getElementUtils().getPackageOf(typeElement).toString();

        // Створюємо класи-орієнтири для JavaPoet
        ClassName originalClass = ClassName.get(packageName, className);
        ClassName builderClass = ClassName.get(packageName, builderClassName);

        // 1. Починаємо будувати клас Builder
        TypeSpec.Builder builderTypeSpec = TypeSpec.classBuilder(builderClassName)
                                                   .addModifiers(Modifier.PUBLIC);

        // 2. Метод newBuilder()
        MethodSpec newBuilderMethod = MethodSpec.methodBuilder("newBuilder")
                                                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                                                .returns(builderClass)
                                                .addStatement("return new $T()", builderClass)
                                                .build();
        builderTypeSpec.addMethod(newBuilderMethod);

        // 3. Створюємо поля та методи-сеттери для кожного поля
        for (VariableElement field : getFieldsOfClass(typeElement)) {
            builderTypeSpec.addMethod(generateBuildMethod(field, builderTypeSpec, builderClass));
        }

        // 4. Метод build(), який збирає всі аргументи докупи
        MethodSpec.Builder buildMethod = MethodSpec.methodBuilder("build")
                                                   .addModifiers(Modifier.PUBLIC)
                                                   .returns(originalClass);

        buildMethod.addStatement("return new $T($L)", originalClass, getFildNames(typeElement));
        builderTypeSpec.addMethod(buildMethod.build());

        // 5. Записуємо файл через Filer
        JavaFile javaFile = JavaFile.builder(packageName, builderTypeSpec.build())
                                    .build();

        try {
            javaFile.writeTo(processingEnv.getFiler());
            System.out.println("Successfully generated class " + typeElement.getSimpleName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MethodSpec generateBuildMethod(VariableElement field, TypeSpec.Builder builderTypeSpec,
                                           ClassName builderClass) {
        String fieldName = field.getSimpleName().toString();
        TypeName fieldType = TypeName.get(field.asType());

        // Додаємо приватне поле в Builder
        builderTypeSpec.addField(fieldType, fieldName, Modifier.PRIVATE);

        // Додаємо fluent-сеттер (наприклад, public UserBuilder name(String val))
        return MethodSpec.methodBuilder(fieldName)
                         .addModifiers(Modifier.PUBLIC)
                         .returns(builderClass)
                         .addParameter(fieldType, "val")
                         .addStatement("this.$N = val", fieldName)
                         .addStatement("return this")
                         .build();
    }

    private String getFildNames(TypeElement classElement) {
        StringJoiner fieldNames = new StringJoiner(", ");
        for (VariableElement element : getFieldsOfClass(classElement)) {
            fieldNames.add(element.getSimpleName().toString());
        }
        return fieldNames.toString();
    }

    private List<VariableElement> getFieldsOfClass(TypeElement classElement) {
        // Get all enclosed elements (methods, constructors, fields, etc.)
        List<? extends Element> allEnclosedElements = classElement.getEnclosedElements();

        // Use ElementFilter to safely extract ONLY the fields (VariableElements)
        return ElementFilter.fieldsIn(allEnclosedElements);
    }
}
