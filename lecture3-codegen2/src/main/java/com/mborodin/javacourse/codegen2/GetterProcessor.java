package com.mborodin.javacourse.codegen2;

import com.sun.source.util.Trees;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.code.Type;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import java.util.Set;

/**
 * Як це працює в архітектурі компілятора:
 * 1. Компілюється наш GetterProcessor
 * (це прописано в pom.xml в maven-compiler-plugin).
 * 2. Компілятор (javac) зчитує всі файли програми. Він бачить
 * анотацію @Getter і викликає GetterProcessor.
 * 3. GetterProcessor додає гетери для всіх полів класу Cat безпосередньо в цей клас.
 * <p>
 * Щоб Java знала, що GetterProcessor — не просто код, а частина компілятора,
 * його треба зареєструвати. Для цього в нас створений файл
 * src/main/resources/META-INF/services/javax.annotation.processing.Processor
 */
@SupportedAnnotationTypes("com.mborodin.javacourse.codegen2.Getter")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class GetterProcessor extends AbstractProcessor {

    private TreeMaker treeMaker;
    private Names names;

    public GetterProcessor() {}

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        JavacProcessingEnvironment javacEnv = (JavacProcessingEnvironment) processingEnv;
        this.treeMaker = TreeMaker.instance(javacEnv.getContext());
        this.names = Names.instance(javacEnv.getContext());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Getter.class)) {
            if (element.getKind() == ElementKind.CLASS) {
                generateCode((TypeElement) element);
            }
        }
        return true;
    }

    private void generateCode(TypeElement typeElement) {
        // Отримуємо AST-дерево класу, в яке вноситимемо зміни
        JCTree tree = (JCTree) Trees.instance(processingEnv).getTree(typeElement);

        tree.accept(new TreeScanner() {
            @Override
            public void visitClassDef(JCTree.JCClassDecl jcClassDecl) {
                super.visitClassDef(jcClassDecl);

                getFieldsOfClass(typeElement)
                        .stream()
                        // генеруємо методи
                        .map(var -> createGetterMethod(var))
                        // додаємо методи в AST-дерево класу
                        .forEach(method -> jcClassDecl.defs = jcClassDecl.defs.append(method));
            }
        });
    }

    private JCTree.JCMethodDecl createGetterMethod(VariableElement variableElement) {
        String fieldName = variableElement.getSimpleName().toString();

        // 1. Create the Getter Name: e.g., "getName"
        String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

        // 2. Create the Method Body: { return this.fieldName; }
        JCTree.JCBlock body = treeMaker.Block(0, List.of(
                treeMaker.Return(
                        treeMaker.Select(
                                treeMaker.Ident(names.fromString("this")),
                                names.fromString(fieldName)
                        )
                )
        ));

        // 3. Get the Return Type from the variable
        JCTree.JCExpression returnType = treeMaker.Type((Type) variableElement.asType());

        // 4. Assemble the Method
        return treeMaker.MethodDef(
                treeMaker.Modifiers(Flags.PUBLIC),          // public
                names.fromString(getterName),               // name
                returnType,                                 // return type
                List.nil(),                                 // generic type params
                List.nil(),                                 // method params
                List.nil(),                                 // thrown exceptions
                body,                                       // branch/body
                null                                        // default value (for annotations)
        );
    }


    private java.util.List<VariableElement> getFieldsOfClass(TypeElement classElement) {
        // Get all enclosed elements (methods, constructors, fields, etc.)
        java.util.List<? extends Element> allEnclosedElements = classElement.getEnclosedElements();

        // Use ElementFilter to safely extract ONLY the fields (VariableElements)
        return ElementFilter.fieldsIn(allEnclosedElements);
    }
}
