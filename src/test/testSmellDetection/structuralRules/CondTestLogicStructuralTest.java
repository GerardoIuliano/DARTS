package testSmellDetection.structuralRules;

import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import testSmellDetection.bean.PsiClassBean;
import testSmellDetection.structuralRules.CondTestLogicStructural;
import testSmellDetection.testSmellInfo.conditionalTestLogic.MethodWithCondTestLogic;
import utility.ConverterUtilities;
import utility.TestSmellUtilities;
import java.util.ArrayList;

public class CondTestLogicStructuralTest extends LightJavaCodeInsightFixtureTestCase {

    private static final int THRESHOLD_1 = 1;
    private static final int THRESHOLD_0 = 0;

    @Override
    protected String getTestDataPath() {
        return "src/test/resources/testdata/";
    }

    public void testCondTestLogicNotPresent() {
        myFixture.configureByFile("test/CondTestLogicNotPresentTest.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        ArrayList<MethodWithCondTestLogic> smellList = CondTestLogicStructural.checkMethodsThatCauseCondTestLogic(testClassBeans.get(0),THRESHOLD_1);
        assertEquals(null, smellList);
    }

    public void testCondTestLogicPresentThreshold0() {
        myFixture.configureByFile("test/CondTestLogicPresentTest.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        ArrayList<MethodWithCondTestLogic> smellList = CondTestLogicStructural.checkMethodsThatCauseCondTestLogic(testClassBeans.get(0),THRESHOLD_0);
        assertEquals(3, smellList.size());
    }

    public void testCondTestLogicPresentThreshold1() {
        myFixture.configureByFile("test/CondTestLogicPresentTest.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        ArrayList<MethodWithCondTestLogic> smellList = CondTestLogicStructural.checkMethodsThatCauseCondTestLogic(testClassBeans.get(0),THRESHOLD_1);
        assertEquals(1, smellList.size());
    }

    public void testCondTestLogicPresentIf() {
        myFixture.configureByFile("test/CondTestLogicPresentIf.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        ArrayList<MethodWithCondTestLogic> smellList = CondTestLogicStructural.checkMethodsThatCauseCondTestLogic(testClassBeans.get(0),THRESHOLD_0);
        assertEquals(1, smellList.size());
    }

    public void testCondTestLogicPresentSwitch() {
        myFixture.configureByFile("test/CondTestLogicPresentSwitch.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        ArrayList<MethodWithCondTestLogic> smellList = CondTestLogicStructural.checkMethodsThatCauseCondTestLogic(testClassBeans.get(0),THRESHOLD_0);
        assertEquals(1, smellList.size());
    }

    public void testCondTestLogicPresentLoopStatement() {
        myFixture.configureByFile("test/CondTestLogicPresentLoopStatement.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        ArrayList<MethodWithCondTestLogic> smellList = CondTestLogicStructural.checkMethodsThatCauseCondTestLogic(testClassBeans.get(0),THRESHOLD_0);
        assertEquals(2, smellList.size());
    }
}
