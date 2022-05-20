package testSmellDetection.structuralRules;

import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ConditionalTestLogic_Test extends LightJavaCodeInsightFixtureTestCase {

    private static final int THRESHOLD_1 = 1;
    private static final int THRESHOLD_0 = 0;
    private final String filePath = "test/CondTestLogicNotPresentTest.java";

    ArrayList<testSmellDetection.testSmellInfo.conditionalTestLogic.MethodWithCondTestLogic> smellList = null;


    @Override
    protected String getTestDataPath() {
        return "src/test/resources/testdata/";
    }

    @Test
    public void testExistingFile(){
        File f = new File(this.getTestDataPath().concat(filePath));
        Assertions.assertNotNull(f);
    }

    @Test
    public void testCondTestLogicNotPresent() {
        JButton jb = new JButton();
        jb.addActionListener(actionEvent -> {
            myFixture.configureByFile("test/CondTestLogicNotPresentTest.java");
            ArrayList<testSmellDetection.bean.PsiClassBean> psiClassBeans = utility.ConverterUtilities.getClassesFromPackages(getProject());
            ArrayList<testSmellDetection.bean.PsiClassBean> testClassBeans = utility.TestSmellUtilities.getAllTestClasses(psiClassBeans);
            ArrayList<testSmellDetection.testSmellInfo.conditionalTestLogic.MethodWithCondTestLogic> smellList = testSmellDetection.structuralRules.CondTestLogicStructural.checkMethodsThatCauseCondTestLogic(testClassBeans.get(0), THRESHOLD_1);
            assertEquals(null, smellList);
        });
    }

    @Test
    public void testCondTestLogicPresentThreshold0() {
        JButton jb = new JButton();
        jb.addActionListener(actionEvent -> {
            myFixture.configureByFile("test/CondTestLogicPresentTest.java");
            ArrayList<testSmellDetection.bean.PsiClassBean> psiClassBeans = utility.ConverterUtilities.getClassesFromPackages(getProject());
            ArrayList<testSmellDetection.bean.PsiClassBean> testClassBeans = utility.TestSmellUtilities.getAllTestClasses(psiClassBeans);
            ArrayList<testSmellDetection.testSmellInfo.conditionalTestLogic.MethodWithCondTestLogic> smellList = testSmellDetection.structuralRules.CondTestLogicStructural.checkMethodsThatCauseCondTestLogic(testClassBeans.get(0), THRESHOLD_0);
            assertEquals(3, smellList.size());
        });
    }

    @Test
    public void testCondTestLogicPresentThreshold1() {
        JButton jb = new JButton();
        jb.addActionListener(actionEvent -> {
            myFixture.configureByFile("test/CondTestLogicPresentTest.java");
            ArrayList<testSmellDetection.bean.PsiClassBean> psiClassBeans = utility.ConverterUtilities.getClassesFromPackages(getProject());
            ArrayList<testSmellDetection.bean.PsiClassBean> testClassBeans = utility.TestSmellUtilities.getAllTestClasses(psiClassBeans);
            ArrayList<testSmellDetection.testSmellInfo.conditionalTestLogic.MethodWithCondTestLogic> smellList = testSmellDetection.structuralRules.CondTestLogicStructural.checkMethodsThatCauseCondTestLogic(testClassBeans.get(0), THRESHOLD_1);
            assertEquals(1, smellList.size());
        });
    }

    @Test
    public void testCondTestLogicPresentIf() {
        JButton jb = new JButton();
        jb.addActionListener(actionEvent -> {
            myFixture.configureByFile("test/CondTestLogicPresentIf.java");
            ArrayList<testSmellDetection.bean.PsiClassBean> psiClassBeans = utility.ConverterUtilities.getClassesFromPackages(getProject());
            ArrayList<testSmellDetection.bean.PsiClassBean> testClassBeans = utility.TestSmellUtilities.getAllTestClasses(psiClassBeans);
            ArrayList<testSmellDetection.testSmellInfo.conditionalTestLogic.MethodWithCondTestLogic> smellList = testSmellDetection.structuralRules.CondTestLogicStructural.checkMethodsThatCauseCondTestLogic(testClassBeans.get(0), THRESHOLD_0);
            assertEquals(1, smellList.size());
        });
    }

    @Test
    public void testCondTestLogicPresentSwitch() {
        JButton jb = new JButton();
        jb.addActionListener(actionEvent -> {
            myFixture.configureByFile("test/CondTestLogicPresentSwitch.java");
            ArrayList<testSmellDetection.bean.PsiClassBean> psiClassBeans = utility.ConverterUtilities.getClassesFromPackages(getProject());
            ArrayList<testSmellDetection.bean.PsiClassBean> testClassBeans = utility.TestSmellUtilities.getAllTestClasses(psiClassBeans);
            ArrayList<testSmellDetection.testSmellInfo.conditionalTestLogic.MethodWithCondTestLogic> smellList = testSmellDetection.structuralRules.CondTestLogicStructural.checkMethodsThatCauseCondTestLogic(testClassBeans.get(0), THRESHOLD_0);
            assertEquals(1, smellList.size());
        });
    }

    @Test
    public void testCondTestLogicPresentLoopStatement() {
        JButton jb = new JButton();
        jb.addActionListener(actionEvent -> {
            myFixture.configureByFile("test/CondTestLogicPresentLoopStatement.java");
            ArrayList<testSmellDetection.bean.PsiClassBean> psiClassBeans = utility.ConverterUtilities.getClassesFromPackages(getProject());
            ArrayList<testSmellDetection.bean.PsiClassBean> testClassBeans = utility.TestSmellUtilities.getAllTestClasses(psiClassBeans);
            ArrayList<testSmellDetection.testSmellInfo.conditionalTestLogic.MethodWithCondTestLogic> smellList = testSmellDetection.structuralRules.CondTestLogicStructural.checkMethodsThatCauseCondTestLogic(testClassBeans.get(0), THRESHOLD_0);
            assertEquals(2, smellList.size());
        });
    }
}
