package testSmellDetection.detector;

import com.intellij.openapi.project.Project;
import contextualAnalysis.hashUtilies.ProductionClassesSingleton;
import testSmellDetection.bean.PsiClassBean;
import testSmellDetection.bean.PsiMethodBean;
import testSmellDetection.structuralRules.*;
import testSmellDetection.testSmellInfo.DuplicateAssert.DuplicateAssertInfo;
import testSmellDetection.testSmellInfo.DuplicateAssert.MethodWithDuplicateAssert;
import testSmellDetection.testSmellInfo.ExceptionHandlingInfo.MethodWithExceptionHandling;
import testSmellDetection.testSmellInfo.IgnoredTest.IgnoredTestInfo;
import testSmellDetection.testSmellInfo.IgnoredTest.MethodWithIgnoredTest;
import testSmellDetection.testSmellInfo.conditionalTestLogic.CondTestLogicInfo;
import testSmellDetection.testSmellInfo.ExceptionHandlingInfo.ExceptionHandlingInfo;
import testSmellDetection.testSmellInfo.conditionalTestLogic.MethodWithCondTestLogic;
import testSmellDetection.testSmellInfo.constructorInitialization.ConstructorInitializationInfo;
import testSmellDetection.testSmellInfo.constructorInitialization.MethodWithConstructorInitialization;
import testSmellDetection.testSmellInfo.magicNamberTest.MagicNumberTestInfo;
import testSmellDetection.testSmellInfo.magicNamberTest.MethodWithMagicNumber;
import utility.ConverterUtilities;
import utility.TestSmellUtilities;

import testSmellDetection.testSmellInfo.eagerTest.EagerTestInfo;
import testSmellDetection.testSmellInfo.eagerTest.MethodWithEagerTest;
import testSmellDetection.testSmellInfo.generalFixture.GeneralFixtureInfo;
import testSmellDetection.testSmellInfo.generalFixture.MethodWithGeneralFixture;
import testSmellDetection.testSmellInfo.lackOfCohesion.LackOfCohesionInfo;
import testSmellDetection.textualRules.EagerTestTextual;
import testSmellDetection.textualRules.GeneralFixtureTextual;
import testSmellDetection.textualRules.LackOfCohesionOfTestSmellTextual;
import java.util.ArrayList;

public class TestSmellStructuralDetector implements IDetector{
    private ArrayList<PsiClassBean> classBeans;
    private ArrayList<PsiClassBean> testClasses;
    private ArrayList<PsiClassBean> productionClasses;
    private ProductionClassesSingleton productionClassesSingleton;

    //variabili per l'analisi di GeneralFixture
    private int numberOfProductionTypes = 3;
    private int numberOfObjectUsesInSetup = 3;

    public TestSmellStructuralDetector(Project project){
        classBeans = ConverterUtilities.getClassesFromPackages(project);
        testClasses = TestSmellUtilities.getAllTestClasses(classBeans);
        productionClasses = TestSmellUtilities.getAllProductionClasses(classBeans, testClasses);
        productionClassesSingleton = ProductionClassesSingleton.getIstance();
        productionClassesSingleton.setProductionClasses(productionClasses);
    }

    @Override
    public ArrayList<MagicNumberTestInfo> executeDetectionForMagicNumber() {
        ArrayList<MagicNumberTestInfo> classesWithMagicNumber = new ArrayList<>();
        for(PsiClassBean testClass : testClasses){
            ArrayList<MethodWithMagicNumber> methodWithMagicNumbers = MagicNumberStructural.checkMethodsThatCauseMagicNumber(testClass);
            if(methodWithMagicNumbers != null){
                classesWithMagicNumber.add(new MagicNumberTestInfo(testClass, methodWithMagicNumbers));
            }
        }
        return classesWithMagicNumber;
    }

    @Override
    public ArrayList<ExceptionHandlingInfo> executeDetectionForExceptionHandling(int threshold) {
        ArrayList<ExceptionHandlingInfo> classesWithExceptionHandling = new ArrayList<>();
        for(PsiClassBean testClass : testClasses){
            ArrayList<MethodWithExceptionHandling> methodWithExceptionHandling = ExceptionHandlingStructural.checkMethodsThatContainExceptions(testClass,threshold);
            if(methodWithExceptionHandling != null){
                classesWithExceptionHandling.add(new ExceptionHandlingInfo(testClass, methodWithExceptionHandling));
            }
        }
        return classesWithExceptionHandling;
    }

    @Override
    public ArrayList<ConstructorInitializationInfo> executeDetectionForConstructorInitialization() {
        ArrayList<ConstructorInitializationInfo> classesWithConstructorInitialization = new ArrayList<>();
        for(PsiClassBean testClass : testClasses){
            ArrayList<MethodWithConstructorInitialization> methodWithMagicNumbers = ConstructorInitStructural.checkMethodsThatCauseConstructorInitialization(testClass);
            if(methodWithMagicNumbers != null){
                classesWithConstructorInitialization.add(new ConstructorInitializationInfo(testClass, methodWithMagicNumbers));
            }
        }
        return classesWithConstructorInitialization;
    }

    @Override
    public ArrayList<CondTestLogicInfo> executeDetectionForCondTestLogic(int threshold) {
        ArrayList<CondTestLogicInfo> classesWithCondTestLogic = new ArrayList<>();
        for(PsiClassBean testClass : testClasses){
            ArrayList<MethodWithCondTestLogic> methodWithCondTestLogics = CondTestLogicStructural.checkMethodsThatCauseCondTestLogic(testClass, threshold);
            if(methodWithCondTestLogics != null){
                classesWithCondTestLogic.add(new CondTestLogicInfo(testClass, methodWithCondTestLogics));
            }
        }
        return classesWithCondTestLogic;
    }

    @Override
    public ArrayList<DuplicateAssertInfo> executeDetectionForDuplicateAssertInfo() {
        ArrayList<DuplicateAssertInfo> classesWithDuplicateAssert = new ArrayList<>();
        for(PsiClassBean testClass : testClasses){
            ArrayList<MethodWithDuplicateAssert> methodWithDuplicateAsserts = DuplicateAssertStructural.checkMethodsThatCauseDuplicateAssert(testClass);
            if(methodWithDuplicateAsserts != null){
                classesWithDuplicateAssert.add(new DuplicateAssertInfo(testClass, methodWithDuplicateAsserts));
            }
        }
        return classesWithDuplicateAssert;
    }

    @Override
    public ArrayList<IgnoredTestInfo> executeDetectionForIgnoredTestInfo() {
        ArrayList<IgnoredTestInfo> classesWithIgnoredTest = new ArrayList<>();
        for(PsiClassBean testClass : testClasses){
            ArrayList<MethodWithIgnoredTest> methodsWithIgnoredTest = IgnoredTestStructural.checkMethodsThatCauseIgnoredTest(testClass);
            if (methodsWithIgnoredTest != null) {
                classesWithIgnoredTest.add(new IgnoredTestInfo(testClass, methodsWithIgnoredTest));
            }
        }
        return classesWithIgnoredTest;
    }

    public ArrayList<GeneralFixtureInfo> executeDetectionForGeneralFixture() {
        ArrayList<GeneralFixtureInfo> classesWithGeneralFixture = new ArrayList<>();
        for(PsiClassBean testClass : testClasses){
            if(GeneralFixtureStructural.isGeneralFixture(testClass, productionClasses, testClasses, numberOfProductionTypes, numberOfObjectUsesInSetup)){
                ArrayList<MethodWithGeneralFixture> methodWithGeneralFixtures = GeneralFixtureTextual.checkMethodsThatCauseGeneralFixture(testClass);
                classesWithGeneralFixture.add(new GeneralFixtureInfo(testClass, methodWithGeneralFixtures));
            }
        }
        return classesWithGeneralFixture;
    }

    public ArrayList<EagerTestInfo> executeDetectionForEagerTest() {
        ArrayList<EagerTestInfo> classesWithEagerTest = new ArrayList<>();
        for(PsiClassBean testClass : testClasses){
            if(testClass.getProductionClass() != null) {
                if (EagerTestStructural.isEagerTest(testClass, testClasses, productionClasses)) {
                    ArrayList<MethodWithEagerTest> methodWithEagerTests = EagerTestTextual.checkMethodsThatCauseEagerTest(testClass, testClass.getProductionClass());
                    classesWithEagerTest.add(new EagerTestInfo(testClass, testClass.getProductionClass(), methodWithEagerTests));
                }
            }
        }
        return classesWithEagerTest;
    }

    public ArrayList<LackOfCohesionInfo> executeDetectionForLackOfCohesion() {
        ArrayList<LackOfCohesionInfo> classesWithLackOfCohesion = new ArrayList<>();
        for(PsiClassBean testClass : testClasses){
            if(testClass.getProductionClass() != null) {
                if (LackOfCohesionOfTestSmellStructural.isLackOfCohesion(testClass)) {
                    ArrayList<PsiMethodBean> methodsWithLackOfCohesion = LackOfCohesionOfTestSmellTextual.checkMethodsThatCauseLackOfCohesion(testClass);
                    classesWithLackOfCohesion.add(new LackOfCohesionInfo(testClass, testClass.getProductionClass(), methodsWithLackOfCohesion));
                }
            }
        }
        return classesWithLackOfCohesion;
    }
}
