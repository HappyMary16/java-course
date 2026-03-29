package com.mborodin.javacourse.codegen;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.JavaFileObject;
import java.io.PrintWriter;
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
@SupportedAnnotationTypes("com.mborodin.javacourse.codegen.Builder")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class BuilderProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Builder.class)) {
            if (element.getKind() == ElementKind.CLASS) {
                generateCode((TypeElement) element);
            }
        }

        System.out.println("BuilderProcessor created using plain Java was executed.");
        return true; // Анотації "оброблені", іншим процесорам їх чіпати не треба
    }

    private void generateCode(TypeElement typeElement) {
        String className = typeElement.getSimpleName().toString();
        String builderClassName = className + "Builder";
        String packageName = processingEnv.getElementUtils().getPackageOf(typeElement).toString();

        try {
            // Створюємо новий файл через Filer
            JavaFileObject builderFile = processingEnv.getFiler()
                                                      .createSourceFile(packageName + "." + builderClassName);

            try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {
                out.println("""
                            package %s;
                            
                            public class %s {
                                public static %s newBuilder() {
                                    return new %s();
                                }
                            """.formatted(packageName, builderClassName, builderClassName, builderClassName));

                getFieldsOfClass(typeElement).forEach(field -> generateBuildMethod(builderClassName, field, out));

                out.println("""
                                public %s build() {
                                    return new %s(%s);
                                }
                            }""".formatted(className, className, getFildNames(typeElement)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateBuildMethod(String builderName, VariableElement field, PrintWriter out) {
        String fieldName = field.getSimpleName().toString();
        String fieldType = field.asType().toString();
        out.println("""     
                        private %s %s = null;
                        public %s %s(%s val) {
                            this.%s = val;
                            return this;
                        }
                    """.formatted(fieldType, fieldName, builderName, fieldName, fieldType, fieldName));
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
