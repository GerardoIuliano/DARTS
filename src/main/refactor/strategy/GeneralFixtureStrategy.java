package main.refactor.strategy;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.refactoring.*;
import com.intellij.refactoring.extractclass.ExtractClassProcessor;
import main.refactor.IRefactor;
import main.testSmellDetection.bean.PsiClassBean;
import main.testSmellDetection.bean.PsiMethodBean;
import main.testSmellDetection.testSmellInfo.generalFixture.GeneralFixtureInfo;
import main.testSmellDetection.testSmellInfo.generalFixture.MethodWithGeneralFixture;

import java.util.*;

public class GeneralFixtureStrategy implements IRefactor {
    private GeneralFixtureInfo generalFixtureInfo;
    private MethodWithGeneralFixture methodWithGeneralFixture;
    private Project project;
    private ExtractClassProcessor processor;
    private Editor editor;

    public GeneralFixtureStrategy(MethodWithGeneralFixture methodWithGeneralFixture, Project project, GeneralFixtureInfo generalFixtureInfo){
        this.methodWithGeneralFixture = methodWithGeneralFixture;
        this.project = project;
        this.generalFixtureInfo = generalFixtureInfo;
    }

    @Override
    public void doRefactor() throws Exception {
        PsiClassBean originalClassBean = generalFixtureInfo.getClassWithGeneralFixture();
        PsiClass psiOriginalClass = originalClassBean.getPsiClass();
        //PsiClass psiOriginalClass = PsiUtil.getPsi(originalClass, project);
        String packageName = originalClassBean.getPsiPackage().getName();

        List<PsiMethod> methodsToMove = new ArrayList<>();
        List<PsiField> fieldsToMove = new ArrayList<>();
        List<PsiClass> innerClasses = new ArrayList<>();

        PsiMethodBean method = methodWithGeneralFixture.getMethodWithGeneralFixture();
        PsiMethod infectedMethod = method.getPsiMethod();
        //PsiMethod infectedMethod = PsiUtil.getPsi(method, project, psiOriginalClass);
        methodsToMove.add(infectedMethod);

        //Vector<InstanceVariableBean> instances = (Vector<InstanceVariableBean>) method.getUsedInstanceVariables();
        ArrayList<PsiVariable> instances = method.getUsedInstanceVariables();

        PsiElement[] elementsToMove = new PsiElement[instances.size()];

        if(instances.size() > 0) {

            for (int i = 0; i < instances.size(); i++) {
                fieldsToMove.add(psiOriginalClass.findFieldByName(instances.get(i).getName(), true));
            }
        }

        for(PsiClass innerClass: psiOriginalClass.getInnerClasses()){
            innerClasses.add(innerClass);
        }

        //Collection<MethodBean> allMethods = generalFixtureInfo.getClassWithGeneralFixture().getMethods();
        ArrayList<PsiMethodBean> allMethods = generalFixtureInfo.getClassWithGeneralFixture().getPsiMethodBeans();
        PsiMethodBean setup = null;
        for(PsiMethodBean metodo: allMethods) {
            //PsiMethod metodo2 = PsiUtil.getPsi(metodo, project, psiOriginalClass);
            PsiMethod metodo2 = metodo.getPsiMethod();

            for (int i = 0; i < instances.size(); i++) {
                if ((metodo.getUsedInstanceVariables().contains(instances.get(i)) ||
                        metodo.getInitInstanceVariables().contains(instances.get(i))) &&
                        !methodsToMove.contains(metodo2)) {

                    if (!metodo.getName().equals("setUp") && !methodsToMove.contains(metodo2)) {
                        methodsToMove.add(metodo2);
                    } else if (metodo.getName().equals("setUp")) {
                        setup = metodo;
                    }
                }
            }
        }

        if(setup != null) {
            //PsiMethod psiSetup = PsiUtil.getPsi(setup, project, psiOriginalClass);
            PsiMethod psiSetup = setup.getPsiMethod();
            int k = 0;
            for (int j = 0; j < psiSetup.getBody().getStatements().length; j++) {
                PsiElement statement = psiSetup.getBody().getStatements()[j].getFirstChild();

                if (statement instanceof PsiAssignmentExpression) {
                    PsiElement element = ((PsiAssignmentExpression) statement).getLExpression();

                    for (int i = 0; i < instances.size(); i++) {

                        if (element.getText().equals(instances.get(i).getName())) {
                            elementsToMove[k++] = statement;
                        }
                    }
                }
            }
            editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
            ExtractMethodProcessor methodProcessor = new ExtractMethodProcessor(project, editor, elementsToMove, null, "setUpRefactored", "setUp", null);

            if (methodProcessor.prepare()) {
                //methodProcessor.setMethodVisibility(PsiModifier.PUBLIC);
                methodProcessor.testPrepare();
                methodProcessor.testNullability();
                ExtractMethodHandler.extractMethod(project, methodProcessor);
            }

            //Creo l'annotation "Before" da aggiungere al metodo appena estratto
            PsiAnnotation annotation = JavaPsiFacade.getElementFactory(project).createAnnotationFromText("@Before", methodProcessor.getExtractedMethod().getContext());

            //Aggiungo l'annotation creata al metodo
            WriteCommandAction.runWriteCommandAction(project, () -> {
                psiOriginalClass.addBefore(annotation, methodProcessor.getExtractedMethod());
            });

            methodsToMove.add(methodProcessor.getExtractedMethod());
// aoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
            //Utile per cancellare la chiamata al nuovo metodo creato
            WriteCommandAction.ensureFilesWritable(project, () -> {
                PsiStatement[] statements = psiSetup.getBody().getStatements();
                String methodName = methodProcessor.getExtractedMethod().getName() +"();";
                for (PsiStatement statement : statements) {
                    if (statement.getText().equals(methodName)) {
                        statement.delete();
                    }
                }
            });
            WriteCommandAction.runWriteCommandAction(project, () -> {
                PsiStatement[] statements = psiSetup.getBody().getStatements();
                String methodName = methodProcessor.getExtractedMethod().getName() +"();";
                for (PsiStatement statement : statements) {
                    if (statement.getText().equals(methodName)) {
                        statement.delete();
                    }
                }
            });

        }
        String classShortName = psiOriginalClass.getName() + "s";

        processor = new ExtractClassProcessor(
                psiOriginalClass,
                fieldsToMove,
                methodsToMove,
                innerClasses,
                packageName,
                classShortName);
        processor.setPreviewUsages(true);
        processor.run();
    }
}
