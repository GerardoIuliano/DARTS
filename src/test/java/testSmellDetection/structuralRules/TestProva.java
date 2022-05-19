package testSmellDetection.structuralRules;

import org.junit.jupiter.api.Test;
import testSmellDetection.bean.PsiClassBean;
import testSmellDetection.testSmellInfo.conditionalTestLogic.MethodWithCondTestLogic;
import utility.ConverterUtilities;
import utility.TestSmellUtilities;

import javax.swing.*;
import java.util.ArrayList;

public class TestProva extends TestConfig{


    @Test
    public void test1(){
        JButton jb = new JButton();
        jb.addActionListener(actionEvent -> {
            super.setFileName("CondTestLogicPresentTest.java");
            ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
            ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
            ArrayList<MethodWithCondTestLogic> smellList = CondTestLogicStructural.checkMethodsThatCauseCondTestLogic(testClassBeans.get(0),0);
            assertEquals(3, smellList.size());
        });
    }

}
